import java.util.Locale;
public class StringBuildBenchmark {
    interface Builder { String build(int tickets,int fields); }
    static String plus(int tickets,int fields) {
        String last="";
        for(int t=0;t<tickets;t++){String s="";for(int f=0;f<fields;f++)s+="FIELD"+f+"|";last=s;}
        return last;
    }
    static String builder(int tickets,int fields) {
        String last="";
        for(int t=0;t<tickets;t++){StringBuilder s=new StringBuilder();for(int f=0;f<fields;f++)s.append("FIELD").append(f).append('|');last=s.toString();}
        return last;
    }
    static String buffer(int tickets,int fields) {
        String last="";
        for(int t=0;t<tickets;t++){StringBuffer s=new StringBuffer();for(int f=0;f<fields;f++)s.append("FIELD").append(f).append('|');last=s.toString();}
        return last;
    }
    static double measure(Builder b,int tickets,int fields,int repeats) {
        long total=0;int checksum=0;
        for(int r=0;r<repeats;r++){long start=System.nanoTime();String out=b.build(tickets,fields);total+=System.nanoTime()-start;checksum+=out.length();}
        if(checksum==0)throw new AssertionError("Empty result");
        return total/(double)repeats/1_000_000.0;
    }
    public static void main(String[] args) {
        int[][] workloads={{1,6},{100,20},{1000,50}};
        Builder[] methods={StringBuildBenchmark::plus,StringBuildBenchmark::builder,StringBuildBenchmark::buffer};
        String[] names={"StringPlus","StringBuilder","StringBuffer"};
        System.out.println("method,tickets,fields,average_ms,repeats");
        for(int[] w:workloads)for(int m=0;m<methods.length;m++){
            for(int warm=0;warm<2;warm++)methods[m].build(Math.min(w[0],10),w[1]);
            System.out.printf(Locale.US,"%s,%d,%d,%.4f,5%n",names[m],w[0],w[1],measure(methods[m],w[0],w[1],5));
        }
    }
}
