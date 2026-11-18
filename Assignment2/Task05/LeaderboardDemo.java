import java.util.*;
class Team {
    final String name;final int points,goalDiff;
    Team(String n,int p,int g){name=n;points=p;goalDiff=g;}
    @Override public String toString(){return name+"("+points+","+goalDiff+")";}
}
class Leaderboard<T> {
    private final List<T> items=new ArrayList<>();private final Comparator<? super T> order;
    Leaderboard(Comparator<? super T> c){order=c;}
    void add(T item){items.add(item);}
    List<T> ranking(){List<T> copy=new ArrayList<>(items);copy.sort(order);return copy;}
}
public class LeaderboardDemo {
    public static void main(String[] args){
        Comparator<Team> byRank=Comparator.comparingInt((Team t)->t.points).reversed()
            .thenComparing(Comparator.comparingInt((Team t)->t.goalDiff).reversed())
            .thenComparing(t->t.name,String.CASE_INSENSITIVE_ORDER);
        Leaderboard<Team> board=new Leaderboard<>(byRank);
        board.add(new Team("Panthers",9,4));board.add(new Team("Eagles",9,6));
        board.add(new Team("Cobras",6,1));board.add(new Team("Bisons",9,4));
        System.out.println(board.ranking());
        System.out.println("Second tie-break test:");
        Arrays.asList(new Team("Zebras",7,2),new Team("Aces",7,2),new Team("Wolves",7,5),new Team("Falcons",8,0))
            .stream().sorted(byRank).forEach(System.out::println);
    }
}
