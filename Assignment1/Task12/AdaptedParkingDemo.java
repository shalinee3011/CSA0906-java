import java.time.LocalTime;
interface PricingRule { double fee(int minutes,LocalTime entry,LocalTime exit); }
interface ParkingPass { boolean isValid(); }
abstract class AdaptedVehicle {
    private final String registration; private final PricingRule rule;
    protected AdaptedVehicle(String r,PricingRule p) {
        if (r==null || r.isBlank()) throw new IllegalArgumentException("Registration required");
        registration=r; rule=p;
    }
    public double fee(int minutes,LocalTime in,LocalTime out,ParkingPass pass) {
        if (minutes<0) throw new IllegalArgumentException("Negative duration");
        if (pass!=null && pass.isValid()) return 0;
        return rule.fee(minutes,in,out);
    }
}
class EBikeRule implements PricingRule {
    public double fee(int m,LocalTime i,LocalTime o) { return 15.0; }
}
class EBike extends AdaptedVehicle { EBike(String r) { super(r,new EBikeRule()); } }
class DeliveryVanRule implements PricingRule {
    public double fee(int m,LocalTime in,LocalTime out) {
        int hours=(m+59)/60;
        boolean day=!in.isBefore(LocalTime.of(9,0)) && in.isBefore(LocalTime.of(17,0));
        return hours*(day?60.0:30.0);
    }
}
class DeliveryVan extends AdaptedVehicle { DeliveryVan(String r) { super(r,new DeliveryVanRule()); } }
class VisitorOneDayPass implements ParkingPass {
    private final boolean valid; VisitorOneDayPass(boolean v) { valid=v; }
    public boolean isValid() { return valid; }
}
public class AdaptedParkingDemo {
    public static void main(String[] args) {
        System.out.println("E-bike -> Rs "+new EBike("TN01EB1234").fee(120,LocalTime.of(10,0),LocalTime.of(12,0),null));
        System.out.println("Delivery van daytime -> Rs "+new DeliveryVan("TN02DV5678").fee(120,LocalTime.of(10,0),LocalTime.of(12,0),null));
        System.out.println("Visitor one-day pass -> Rs "+new DeliveryVan("TN03DV9999").fee(120,LocalTime.of(10,0),LocalTime.of(12,0),new VisitorOneDayPass(true)));
    }
}
