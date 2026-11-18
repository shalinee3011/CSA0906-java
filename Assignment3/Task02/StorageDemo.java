import java.io.IOException;

class ExamException extends Exception {
    ExamException(String message) { super(message); }
    ExamException(String message, Throwable cause) { super(message, cause); }
}
class SubmissionStorageException extends ExamException {
    SubmissionStorageException(String message, Throwable cause) { super(message, cause); }
}
class ExamWindowClosedException extends ExamException {
    ExamWindowClosedException(String m) { super(m); }
}
class NotRegisteredException extends ExamException {
    NotRegisteredException(String m) { super(m); }
}
class Storage {
    void save(String fileName, long sizeKb) throws SubmissionStorageException {
        try {
            if (sizeKb > 500) throw new IOException("Disk quota exceeded");
            System.out.println("Saved " + fileName);
        } catch (IOException e) {
            throw new SubmissionStorageException("Could not store " + fileName, e);
        }
    }
}
class ExamService {
    private final Storage storage = new Storage();
    void submit(String file, long size, boolean registered, boolean windowOpen) throws ExamException {
        if (!registered) throw new NotRegisteredException("Student is not registered");
        if (!windowOpen) throw new ExamWindowClosedException("Exam window has closed");
        storage.save(file, size);
    }
}
class ExamController {
    void handle(String file, long size, boolean registered, boolean open) {
        try {
            new ExamService().submit(file,size,registered,open);
        } catch (ExamException e) {
            System.out.println("Error: " + e.getMessage());
            if (e.getCause() != null) System.out.println("Cause: " + e.getCause().getMessage());
            e.printStackTrace(System.out);
        }
    }
}
public class StorageDemo {
    public static void main(String[] args) {
        ExamController c = new ExamController();
        c.handle("answer01.pdf",120,true,true);
        c.handle("answer02.pdf",800,true,true);
    }
}
