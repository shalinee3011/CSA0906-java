import java.sql.*;
import java.util.*;

public class RegistrationDAO {
    private final String url, user, password;
    public RegistrationDAO(String url, String user, String password) {
        this.url = url; this.user = user; this.password = password;
    }
    private Connection connect() throws SQLException { return DriverManager.getConnection(url, user, password); }

    public void register(String studentId, String studentName, int eventId) throws SQLException {
        String sql = "INSERT INTO registrations(student_id, student_name, event_id) VALUES(?,?,?)";
        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, studentId); ps.setString(2, studentName); ps.setInt(3, eventId);
            ps.executeUpdate();
        }
    }

    public List<String[]> findPage(Integer eventId, int limit, int offset) throws SQLException {
        String sql = "SELECT student_id, student_name, event_id, registered_at FROM registrations "
                   + (eventId == null ? "" : "WHERE event_id=? ")
                   + "ORDER BY registered_at DESC LIMIT ? OFFSET ?";
        List<String[]> rows = new ArrayList<>();
        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            int i = 1;
            if (eventId != null) ps.setInt(i++, eventId);
            ps.setInt(i++, limit); ps.setInt(i, offset);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) rows.add(new String[]{rs.getString("student_id"),
                    rs.getString("student_name"), rs.getString("event_id"),
                    rs.getString("registered_at")});
            }
        }
        return rows;
    }
}
