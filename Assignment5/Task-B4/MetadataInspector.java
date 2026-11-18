import java.sql.*;
public class MetadataInspector {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java MetadataInspector <jdbc-url>");
            return;
        }
        try (Connection c = DriverManager.getConnection(args[0],
                System.getenv("DB_USER"), System.getenv("DB_PASSWORD"))) {
            DatabaseMetaData md = c.getMetaData();
            System.out.println("Database: " + md.getDatabaseProductName());
            System.out.println("Database version: " + md.getDatabaseProductVersion());
            System.out.println("Driver: " + md.getDriverName());
            System.out.println("Driver version: " + md.getDriverVersion());
            try (ResultSet tables = md.getTables(c.getCatalog(), null, "%",
                    new String[]{"TABLE"})) {
                while (tables.next())
                    System.out.println("Table: " + tables.getString("TABLE_NAME"));
            }
        }
    }
}
