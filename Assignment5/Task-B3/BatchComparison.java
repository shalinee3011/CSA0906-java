import java.sql.*;
import java.util.*;

public class BatchComparison {
    static List<String[]> rows(int count) {
        List<String[]> data = new ArrayList<>();
        for (int i = 1; i <= count; i++)
            data.add(new String[]{"P" + i, "REG" + i, "Student" + i, "7", "4500.00"});
        return data;
    }
    static void clear(Connection c) throws SQLException {
        try (Statement s = c.createStatement()) { s.executeUpdate("DELETE FROM bus_pass"); }
    }
    static long statement(Connection c, List<String[]> data) throws SQLException {
        long start = System.nanoTime();
        try (Statement s = c.createStatement()) {
            for (String[] r : data) {
                String q = "INSERT INTO bus_pass VALUES ('" + r[0] + "','" + r[1] + "','" +
                        r[2] + "'," + r[3] + "," + r[4] + ")";
                s.executeUpdate(q);
            }
        }
        return System.nanoTime() - start;
    }
    static long prepared(Connection c, List<String[]> data, boolean batch) throws SQLException {
        long start = System.nanoTime();
        try (PreparedStatement p = c.prepareStatement("INSERT INTO bus_pass VALUES (?,?,?,?,?)")) {
            int count = 0;
            for (String[] r : data) {
                p.setString(1,r[0]); p.setString(2,r[1]); p.setString(3,r[2]);
                p.setInt(4,Integer.parseInt(r[3])); p.setDouble(5,Double.parseDouble(r[4]));
                if (batch) {
                    p.addBatch();
                    if (++count % 1000 == 0) p.executeBatch();
                } else p.executeUpdate();
            }
            if (batch) p.executeBatch();
        }
        return System.nanoTime() - start;
    }
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: java BatchComparison <jdbc-url> <row-count>");
            return;
        }
        try (Connection c = DriverManager.getConnection(args[0],
                System.getenv("DB_USER"), System.getenv("DB_PASSWORD"))) {
            List<String[]> data = rows(Integer.parseInt(args[1]));
            clear(c); System.out.println("Statement ns: " + statement(c, data));
            clear(c); System.out.println("PreparedStatement ns: " + prepared(c, data, false));
            clear(c); c.setAutoCommit(false);
            try { System.out.println("Batch ns: " + prepared(c, data, true)); c.commit(); }
            catch (SQLException e) { c.rollback(); throw e; }
            finally { c.setAutoCommit(true); }
        }
    }
}
