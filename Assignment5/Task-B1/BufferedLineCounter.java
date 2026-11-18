import java.io.*;
import java.nio.file.*;

public class BufferedLineCounter {
    static long countUnbuffered(Path p) throws IOException {
        long lines = 0;
        try (InputStream in = Files.newInputStream(p)) {
            int b;
            while ((b = in.read()) != -1) if (b == '\n') lines++;
        }
        return lines;
    }
    static long countBuffered(Path p) throws IOException {
        long lines = 0;
        try (BufferedReader r = Files.newBufferedReader(p)) {
            while (r.readLine() != null) lines++;
        }
        return lines;
    }
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java BufferedLineCounter <file>");
            return;
        }
        Path p = Paths.get(args[0]);
        long start = System.nanoTime();
        long a = countUnbuffered(p);
        long t1 = System.nanoTime() - start;
        start = System.nanoTime();
        long b = countBuffered(p);
        long t2 = System.nanoTime() - start;
        System.out.println("Unbuffered lines: " + a + ", time ns: " + t1);
        System.out.println("Buffered lines: " + b + ", time ns: " + t2);
    }
}
