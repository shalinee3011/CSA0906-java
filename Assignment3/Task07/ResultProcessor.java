import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

public class ResultProcessor {
    static final int FILE_COUNT=10000;
    static class Outcome {
        final String file;final boolean success;final long score;final String error;
        Outcome(String f,long s){file=f;success=true;score=s;error="";}
        Outcome(String f,String e){file=f;success=false;score=0;error=e;}
    }
    static Outcome process(Path path){
        try{
            String value=new String(Files.readAllBytes(path)).trim();
            long score=Long.parseLong(value);
            if(score<0||score>100)throw new IllegalArgumentException("Score outside 0..100");
            return new Outcome(path.getFileName().toString(),score);
        }catch(Exception e){return new Outcome(path.getFileName().toString(),e.getClass().getSimpleName()+": "+e.getMessage());}
    }
    static void run(Path dir,int poolSize)throws Exception{
        ExecutorService pool=Executors.newFixedThreadPool(poolSize);
        ConcurrentLinkedQueue<Outcome> results=new ConcurrentLinkedQueue<>();
        List<Future<Outcome>> futures=new ArrayList<>();
        long start=System.nanoTime();
        try{
            for(int i=0;i<FILE_COUNT;i++){
                Path file=dir.resolve("score-"+i+".txt");
                futures.add(pool.submit(()->process(file)));
            }
            for(Future<Outcome> f:futures){
                try{results.add(f.get());}
                catch(ExecutionException e){results.add(new Outcome("unknown",e.getCause().toString()));}
            }
        }finally{pool.shutdown();pool.awaitTermination(1,TimeUnit.MINUTES);}
        long elapsed=System.nanoTime()-start;
        long ok=results.stream().filter(x->x.success).count();
        long failed=results.size()-ok;
        System.out.printf(Locale.US,"Pool=%d Success=%d Failed=%d Total=%d TimeMs=%.2f%n",
                poolSize,ok,failed,results.size(),elapsed/1e6);
        if(results.size()!=FILE_COUNT)throw new IllegalStateException("Incomplete summary");
    }
    public static void main(String[] args)throws Exception{
        Path dir=Paths.get("Assignment3","Task07","scores");Files.createDirectories(dir);
        for(int i=0;i<FILE_COUNT;i++){
            Path p=dir.resolve("score-"+i+".txt");
            if(i%100==0)Files.write(p,Arrays.asList("corrupt-score"));
            else if(i%101==0)Files.deleteIfExists(p);
            else Files.write(p,Arrays.asList(String.valueOf(i%101)));
        }
        int[] pools=args.length==0?new int[]{1,2,4,8}:Arrays.stream(args).mapToInt(Integer::parseInt).toArray();
        for(int p:pools)run(dir,p);
    }
}
