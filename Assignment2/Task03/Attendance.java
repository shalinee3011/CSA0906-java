import java.util.*;
class Student {
    private final String regNo; private final String name;
    Student(String r,String n){regNo=r;name=n;}
    @Override public boolean equals(Object o){if(this==o)return true;if(!(o instanceof Student))return false;return regNo.equals(((Student)o).regNo);}
    @Override public int hashCode(){return Objects.hash(regNo);}
    @Override public String toString(){return regNo+" "+name;}
}
class BrokenStudent {
    final String regNo; BrokenStudent(String r){regNo=r;}
    @Override public boolean equals(Object o){return o instanceof BrokenStudent && regNo.equals(((BrokenStudent)o).regNo);}
    // Deliberately no hashCode override.
}
class MutableStudent {
    String regNo; MutableStudent(String r){regNo=r;}
    @Override public boolean equals(Object o){return o instanceof MutableStudent && regNo.equals(((MutableStudent)o).regNo);}
    @Override public int hashCode(){return Objects.hash(regNo);}
}
public class Attendance {
    public static void main(String[] args){
        Set<BrokenStudent> broken=new HashSet<>();broken.add(new BrokenStudent("192311001"));broken.add(new BrokenStudent("192311001"));
        System.out.println("Broken set size (identity hash codes): "+broken.size());
        Set<Student> present=new HashSet<>();present.add(new Student("192311001","Arun"));present.add(new Student("192311002","Divya"));present.add(new Student("192311001","Arun"));
        System.out.println("Unique students: "+present.size());
        Set<MutableStudent> mutable=new HashSet<>();MutableStudent m=new MutableStudent("REG1");mutable.add(m);m.regNo="REG2";
        System.out.println("Contains after identity mutation: "+mutable.contains(m));
        Set<Student> ordered=new TreeSet<>(Comparator.comparing(s->s.toString().split(" ")[0]));ordered.addAll(present);
        System.out.println("TreeSet size: "+ordered.size());
    }
}
