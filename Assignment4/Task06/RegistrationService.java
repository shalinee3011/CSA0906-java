import java.sql.SQLException;

public class RegistrationService {
    private final RegistrationDAO dao;
    public RegistrationService(RegistrationDAO dao) { this.dao = dao; }

    public void register(String studentId, String name, String eventText) throws Exception {
        if (studentId == null || studentId.trim().isEmpty()) throw new IllegalArgumentException("Student ID is required.");
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Student name is required.");
        final int eventId;
        try { eventId = Integer.parseInt(eventText); }
        catch (Exception ex) { throw new IllegalArgumentException("Choose a valid event."); }
        try { dao.register(studentId.trim(), name.trim(), eventId); }
        catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) throw new IllegalArgumentException("Already registered for this event.");
            throw ex;
        }
    }
}
