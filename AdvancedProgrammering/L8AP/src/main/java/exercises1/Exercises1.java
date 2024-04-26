package exercises1;

import java.util.*;
import java.util.stream.Collectors;

public class Exercises1 {

    public static void main(String[] args) {
        // Liste med tal mellem 1 - 50
        List<Integer> list = List.of(1,2,3,2,1,6,3,4,5,2,3,8,8,9,34,32);
        list.forEach(System.out::println);

        //  TODO
        //	Udskriver det største element i listen
        System.out.println("biggest: " + list.stream().max(Integer::compareTo).get());

        //  TODO
        //	Afgør og udskriver om alle tallene i listen er mindre end 50
        System.out.println("Er alle større end 50: " + list.stream().allMatch(i -> i < 50));

        //  TODO
        // 	Udskriver antallet af lige tal i listen
        System.out.println("Antal Lige tal: " + list.stream().filter(i -> i % 2 == 0).count());

        //  TODO
        //	Udskriver antallet af ulige tal i listen
        System.out.println("Antal ulige tal: " + list.stream().filter(i -> i % 2 != 0).count());

        //  TODO
        //  Udskriver
        //      Gennemsnittet af tallene i listen
        //      Antallet af tallene i listen
        //      Antallet af tallene i listen der er større end gennemsnittet
        //      Antallet af tallene i listen der er mindre end gennemsnittet
        System.out.println("Gennemsnit, antal, antal over og antal under gennemsnit");
        IntSummaryStatistics stats = list.stream().mapToInt(Integer::intValue).summaryStatistics();
        System.out.println("Gennemsnit: " + stats.getAverage());
        System.out.println("Antal: " + stats.getCount());
        System.out.println("Antal over gennemsnit: " + list.stream().filter(i -> i > stats.getAverage()).count());
        System.out.println("Antal under gennemsnit: " + list.stream().filter(i -> i < stats.getAverage()).count());

        //  TODO
        //	Udskriver antallet af gange de forskellige tal forekommer i listen
        Map<Integer, Long> count = list.stream().collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        System.out.println("Antal af hvert tal: " + count);

        //  TODO
        //	Udskriver antallet af gange de forskellige tal forekommer i listen i sorteret orden
        count.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
    }
}
