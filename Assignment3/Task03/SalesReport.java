import java.io.*;
import java.nio.file.*;
import java.util.*;

public class SalesReport {
    static void manual(List<Path> inputs, Path output) {
        BufferedWriter writer = null;
        try {
            writer = Files.newBufferedWriter(output);
            for (Path p : inputs) {
                BufferedReader reader = null;
                try {
                    reader = Files.newBufferedReader(p);
                    String line;
                    while ((line=reader.readLine())!=null) writer.write(line + System.lineSeparator());
                } finally {
                    if (reader != null) reader.close();
                }
            }
        } catch (IOException e) {
            System.out.println("Manual report failed at " + e.getMessage());
        } finally {
            if (writer != null) try { writer.close(); } catch (IOException e) {
                System.out.println("Manual close failed: " + e.getMessage());
            }
        }
    }
    static void safe(List<Path> inputs, Path output) {
        try (BufferedWriter writer=Files.newBufferedWriter(output)) {
            for (Path p:inputs) {
                System.out.println("Reading " + p);
                try (BufferedReader reader=Files.newBufferedReader(p)) {
                    String line;
                    while((line=reader.readLine())!=null) writer.write(line+System.lineSeparator());
                }
            }
        } catch(IOException e) {
            System.out.println("Safe report failed at " + e.getMessage());
        }
    }
    public static void main(String[] args) throws IOException {
        Path dir=Paths.get("Assignment3","Task03");
        Path a=dir.resolve("store1.txt"), b=dir.resolve("store2.txt"), missing=dir.resolve("missing-store.txt");
        Files.write(a,Arrays.asList("Store 1: 1200","Store 1: 900"));
        Files.write(b,Arrays.asList("Store 2: 1500"));
        List<Path> inputs=Arrays.asList(a,missing,b);
        manual(inputs,dir.resolve("manual-report.txt"));
        safe(inputs,dir.resolve("safe-report.txt"));
        System.out.println("Report test completed");
    }
}
