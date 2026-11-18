import java.util.Locale;
public class RedesignBenchmark {
    static volatile int sink;
    static String original(int tickets,int fields){
        String result="";
        for(int t=0;t<tickets;t++){String ticket="";for(int f=0;f<fields;f++)ticket+="FIELD"+f+"|";result=ticket;}
        return result;
    }
    static String redesigned(int tickets,int fields){
        String result="";
        for(int t=0;t<tickets;t++){StringBuilder ticket=new StringBuilder(fields*8);for(int f=0;f<fields;f++)ticket.append("FIELD").append(f).append('|');result=ticket.toString();}
        return result;
    }
    static double average(boolean revised,int tickets,int fields,int repeats){
        long total=0;
        for(int r=0;r<repeats;r++){long start=System.nanoTime();String out=revised?redesigned(tickets,fields):original(tickets,fields);total+=System.nanoTime()-start;sink+=out.length();}
        return total/(double)repeats/1_000_000.0;
    }
    public static void main(String[] args){
        int[][] tests={{1,6},{100,20},{1000,50}};
        System.out.println("solution,tickets,fields,average_ms,repeats");
        for(int[] t:tests){
            for(int w=0;w<2;w++){original(Math.min(10,t[0]),t[1]);redesigned(Math.min(10,t[0]),t[1]);}
            System.out.printf(Locale.US,"OriginalPlus,%d,%d,%.4f,5%n",t[0],t[1],average(false,t[0],t[1],5));
            System.out.printf(Locale.US,"BuilderRedesign,%d,%d,%.4f,5%n",t[0],t[1],average(true,t[0],t[1],5));
        }
    }
}
