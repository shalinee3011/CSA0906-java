import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
class Wallet {
    final int id; int balance; final ReentrantLock lock=new ReentrantLock();
    Wallet(int id,int balance){this.id=id;this.balance=balance;}
}
public class WalletTransfer {
    static void transferOrdered(Wallet from,Wallet to,int amount)throws InterruptedException{
        Wallet first=from.id<to.id?from:to, second=from.id<to.id?to:from;
        first.lock.lockInterruptibly();
        try{second.lock.lockInterruptibly();try{
            if(from.balance<amount)throw new IllegalStateException("Insufficient funds");
            from.balance-=amount;to.balance+=amount;
        }finally{second.lock.unlock();}}finally{first.lock.unlock();}
    }
    static void transferUnsafe(Wallet from,Wallet to,int amount)throws InterruptedException{
        from.lock.lockInterruptibly();
        try{Thread.sleep(10);to.lock.lockInterruptibly();try{from.balance-=amount;to.balance+=amount;}
        finally{to.lock.unlock();}}finally{from.lock.unlock();}
    }
    public static void main(String[] args)throws InterruptedException{
        Wallet a=new Wallet(1,1000),b=new Wallet(2,1000);
        Thread t1=new Thread(()->{try{for(int i=0;i<100;i++)transferOrdered(a,b,1);}catch(Exception e){e.printStackTrace();}});
        Thread t2=new Thread(()->{try{for(int i=0;i<100;i++)transferOrdered(b,a,1);}catch(Exception e){e.printStackTrace();}});
        t1.start();t2.start();t1.join();t2.join();
        System.out.println("Combined wallet balance: "+(a.balance+b.balance));
    }
}
