import java.io.*;
import java.nio.file.*;
import java.sql.*;

public class FileToDatabaseMigration {
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.out.println("Usage: java FileToDatabaseMigration <jdbc-url> <input.csv> <rejected.txt>");
            return;
        }
        String sql = "INSERT INTO bus_pass(pass_id,reg_no,name,route_no,amount) VALUES (?,?,?,?,?)";
        int loaded = 0, rejected = 0, lineNo = 0;
        try (Connection c = DriverManager.getConnection(args[0],
                    System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
             BufferedReader reader = Files.newBufferedReader(Paths.get(args[1]));
             BufferedWriter errors = Files.newBufferedWriter(Paths.get(args[2]))) {
            c.setAutoCommit(false);
            try (PreparedStatement ps = c.prepareStatement(sql)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lineNo++;
                    String[] f = line.split(",", -1);
                    try {
                        if (f.length != 5) throw new IllegalArgumentException("Expected 5 fields");
                        int route = Integer.parseInt(f[3]);
                        double amount = Double.parseDouble(f[4]);
                        if (amount < 0) throw new IllegalArgumentException("Negative amount");
                        ps.setString(1, f[0]); ps.setString(2, f[1]); ps.setString(3, f[2]);
                        ps.setInt(4, route); ps.setDouble(5, amount);
                        ps.addBatch();
                        if (++loaded % 1000 == 0) ps.executeBatch();
                    } catch (RuntimeException ex) {
                        rejected++;
                        errors.write("Line " + lineNo + ": " + ex.getMessage() + " | " + line);
                        errors.newLine();
                    }
                }
                ps.executeBatch();
                c.commit();
            } catch (SQLException ex) {
                c.rollback();
                throw ex;
            }
        }
        System.out.println("Loaded: " + loaded);
        System.out.println("Rejected: " + rejected);
        System.out.println("Failure report: " + args[2]);
    }
}
