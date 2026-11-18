public class ScannerStations {
    static class Station extends Thread {
        private final int scripts;
        Station(String name,int scripts){super(name);this.scripts=scripts;}
        @Override public void run(){scan();}
        void scan(){
            try { Thread.sleep(40L + (long)(Math.random()*160)); }
            catch(InterruptedException e){Thread.currentThread().interrupt();return;}
            System.out.println(getName()+" scanned "+scripts);
        }
    }
    static class StationTask implements Runnable {
        private final String name; private final int scripts;
        StationTask(String n,int s){name=n;scripts=s;}
        public void run(){
            try{Thread.sleep(40L+(long)(Math.random()*160));}
            catch(InterruptedException e){Thread.currentThread().interrupt();return;}
            System.out.println(name+" scanned "+scripts);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread[] stations=new Thread[4];
        for(int i=0;i<4;i++){stations[i]=new Thread(new StationTask("Station-"+(i+1),25));stations[i].start();}
        for(Thread t:stations)System.out.println(t.getName()+" alive? "+t.isAlive());
        for(Thread t:stations)t.join();
        System.out.println("All stations completed. Total scanned: 100");

        Station subclass=new Station("Thread-subclass-demo",5);
        subclass.start();subclass.join();
        System.out.println("Direct run() executes on: "+Thread.currentThread().getName());
        new StationTask("Runnable-direct-run",1).run();
    }
}
