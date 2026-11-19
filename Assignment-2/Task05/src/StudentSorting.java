
import java.util.*;

class Team {
    String name;
    int points;
    int goalDifference;

    Team(String name, int points, int goalDifference) {
        this.name = name;
        this.points = points;
        this.goalDifference = goalDifference;
    }

    @Override
    public String toString() {
        return name + " | Points: " + points
                + " | Goal Difference: " + goalDifference;
    }
}

class Leaderboard<T> {

    public List<T> sortedCopy(
            List<T> teams, Comparator<? super T> comparator) {

        List<T> result = new ArrayList<>(teams);
        result.sort(comparator);
        return result;
    }
}

public class StudentSorting {

    public static void main(String[] args) {

        List<Team> teams = new ArrayList<>();

        teams.add(new Team("Falcons", 18, 12));
        teams.add(new Team("Tigers", 18, 15));
        teams.add(new Team("Warriors", 15, 10));
        teams.add(new Team("Eagles", 18, 15));
        teams.add(new Team("Lions", 12, 5));

        Comparator<Team> rankingComparator =
                Comparator.comparingInt((Team t) -> t.points)
                        .reversed()
                        .thenComparing(
                                Comparator.comparingInt(
                                        (Team t) -> t.goalDifference)
                                        .reversed())
                        .thenComparing(t -> t.name);

        Leaderboard<Team> leaderboard = new Leaderboard<>();

        System.out.println("Q5: GENERIC TEAM LEADERBOARD");

        System.out.println("\nORIGINAL TEAM ORDER:");
        teams.forEach(System.out::println);

        List<Team> rankedTeams =
                leaderboard.sortedCopy(teams, rankingComparator);

        System.out.println("\nRANKED LEADERBOARD:");
        for (int i = 0; i < rankedTeams.size(); i++) {
            System.out.println((i + 1) + ". " + rankedTeams.get(i));
        }

        System.out.println("\nORIGINAL LIST PRESERVED: "
                + teams.get(0).name.equals("Falcons"));

        System.out.println("\nTIE-BREAKING TEST:");
        System.out.println("Tigers and Eagles have equal points "
                + "and goal difference.");
        System.out.println("Alphabetical order: "
                + rankedTeams.get(0).name + " before "
                + rankedTeams.get(1).name);

        System.out.println("\nQ5 leaderboard completed.");
    }
}
