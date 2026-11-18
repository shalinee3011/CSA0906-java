import java.util.concurrent.CountDownLatch;
public class SeatBooking {
    private final boolean[] booked;
    private int bookingsMade=0;
    SeatBooking(int seats){booked=new boolean[seats];}
    public synchronized void book(int seat){
        if(seat<0||seat>=booked.length)throw new IllegalArgumentException("Invalid seat");
        if(!booked[seat]){booked[seat]=true;bookingsMade++;}
    }
    public static void main(String[] args)throws InterruptedException{
        SeatBooking screen=new SeatBooking(10);
        Thread[] users=new Thread[100];
        CountDownLatch start=new CountDownLatch(1);
        for(int i=0;i<users.length;i++){
            final int seat=i%10;
            users[i]=new Thread(()->{try{start.await();screen.book(seat);}catch(InterruptedException e){Thread.currentThread().interrupt();}});
            users[i].start();
        }
        start.countDown();
        for(Thread t:users)t.join();
        System.out.println("Seats sold: "+screen.bookingsMade);
    }
}
