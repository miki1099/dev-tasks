package pl.mglodek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import lombok.experimental.UtilityClass;
import pl.mglodek.task1.IntListSummary;
import pl.mglodek.task1.IntListSummaryProvider;
import pl.mglodek.task2.ThirteenTuple;
import pl.mglodek.task2.ThirteenTupleFinder;
import pl.mglodek.task3.GraphCountFinder;
import pl.mglodek.task3.GraphEdge;

@UtilityClass
public class Menu {

    private static final String INCORRECT_COMMAND = "Incorrect command!";

    public static void runMenu() {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("enter task number that you want to run (1,2,3 or 'end' to exit)");
            String taskNumber = scanner.nextLine();
            runTask(taskNumber, scanner);
        }
    }

    private static void runTask(String chosenNumber, Scanner scanner) {
        switch (chosenNumber) {
            case "1" -> runFirstTask(scanner);
            case "2" -> runSecondTask(scanner);
            case "3" -> runThirdTask(scanner);
            case "end" -> System.exit(0);
            default -> System.out.println(INCORRECT_COMMAND);
        }
    }

    private static void runFirstTask(Scanner scanner) {
        List<Integer> intList = getIntList(scanner);
        if(intList.isEmpty()) {
            return;
        }

        IntListSummary summary = IntListSummaryProvider.getInstance()
                .process(intList);
        System.out.println(summary.toString());
    }

    private static void runSecondTask(Scanner scanner) {
        List<Integer> intList = getIntList(scanner);
        if(intList.isEmpty()) {
            return;
        }

        List<ThirteenTuple> thirteenTuples = ThirteenTupleFinder.getInstance()
                .find(intList);
        thirteenTuples.forEach(System.out::println);
    }

    private static List<Integer> getIntList(Scanner scanner) {
        System.out.println("Enter numbers separated by spaces");
        String numbersInList = scanner.nextLine().trim();

        String[] numbers = numbersInList.split(" ");
        try {
            return Arrays.stream(numbers)
                    .map(Integer::valueOf)
                    .toList();
        } catch (NumberFormatException e) {
            System.out.println("Please enter correct integers");
        }
        return List.of();
    }

    private static void runThirdTask(Scanner scanner) {
        List<GraphEdge> edgeList = getEdgeList(scanner);

        GraphCountFinder instance = GraphCountFinder.getInstance();
        int differentGraphs = instance.find(edgeList);
        System.out.println(differentGraphs);
    }

    private static List<GraphEdge> getEdgeList(Scanner scanner) {
        System.out.println("Enter how many edges you will input:");
        int edgesLines = scanner.nextInt();

        System.out.println("Enter 2 numbers separated by space to enter edge and hit enter");

        List<GraphEdge> edges = new ArrayList<>();

        while (edgesLines > 0) {
            String numbersInList = scanner.nextLine().trim();
            String[] numbers = numbersInList.split(" ");
            try {
                int startVertex = Integer.parseInt(numbers[0]);
                int endVertex = Integer.parseInt(numbers[1]);
                edges.add(new GraphEdge(startVertex, endVertex));
                edgesLines--;
            } catch (NumberFormatException e) {
                System.out.println("Please enter correct integers");
            }
        }

        return edges;
    }

}
