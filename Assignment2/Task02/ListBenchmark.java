import java.util.*;
public class ListBenchmark {
    static volatile long sink;
    static long time(Runnable action,int repeats){long start=System.nanoTime();for(int i=0;i<repeats;i++)action.run();return (System.nanoTime()-start)/repeats;}
    static List<Integer> filled(List<Integer> list,int n){for(int i=0;i<n;i++)list.add(i);return list;}
    public static void main(String[] args){
        int[] sizes={1000,10000,50000};
        System.out.println("size,structure,append_ns,front_insert_remove_ns,index_access_ns,iteration_ns");
        for(int n:sizes){
            for(String type:new String[]{"ArrayList","LinkedList"}){
                List<Integer> base=filled(type.equals("ArrayList")?new ArrayList<>():new LinkedList<>(),n);
                long append=time(()->{List<Integer>x=type.equals("ArrayList")?new ArrayList<>():new LinkedList<>();x.add(1);},1000);
                long front=time(()->{List<Integer>x=new ArrayList<>(base);x.add(0,7);x.remove(0);},20);
                long index=time(()->sink+=base.get(n/2),10000);
                long iterate=time(()->{long sum=0;for(int v:base)sum+=v;sink=sum;},20);
                System.out.printf(Locale.US,"%d,%s,%d,%d,%d,%d%n",n,type,append,front,index,iterate);
            }
            ArrayDeque<Integer> dq=new ArrayDeque<>();for(int i=0;i<n;i++)dq.addLast(i);
            long ends=time(()->{dq.addFirst(-1);dq.removeFirst();dq.addLast(-1);dq.removeLast();},10000);
            long iter=time(()->{long sum=0;for(int v:dq)sum+=v;sink=sum;},20);
            System.out.printf(Locale.US,"%d,ArrayDeque,%d,%d,NA,%d%n",n,ends,ends,iter);
        }
    }
}
