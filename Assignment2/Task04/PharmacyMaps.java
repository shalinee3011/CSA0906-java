import java.util.*;
public class PharmacyMaps {
    static volatile int sink;
    static long nanos(Runnable r){long s=System.nanoTime();r.run();return System.nanoTime()-s;}
    public static void main(String[] args){
        int[] sizes={1000,10000,50000};
        System.out.println("medicines,map,100000_lookup_ms,code_report_ms,insertion_report_ms");
        for(int n:sizes){
            Map<String,Integer> hash=new HashMap<>(),tree=new TreeMap<>(),linked=new LinkedHashMap<>();
            for(int i=0;i<n;i++){String k=String.format("MED%06d",i);hash.put(k,i);tree.put(k,i);linked.put(k,i);}
            Map<String,Integer>[] maps=new Map[]{hash,tree,linked};String[] names={"HashMap","TreeMap","LinkedHashMap"};
            for(int m=0;m<maps.length;m++){
                Map<String,Integer> map=maps[m];
                long lookup=nanos(()->{int sum=0;for(int i=0;i<100000;i++)sum+=map.get(String.format("MED%06d",i%n));sink=sum;});
                long code=nanos(()->{List<String> keys=new ArrayList<>(map.keySet());Collections.sort(keys);sink=keys.size();});
                long insertion=nanos(()->{int sum=0;for(String k:map.keySet())sum+=map.get(k);sink=sum;});
                System.out.printf(Locale.US,"%d,%s,%.3f,%.3f,%.3f%n",n,names[m],lookup/1e6,code/1e6,insertion/1e6);
            }
        }
    }
}
