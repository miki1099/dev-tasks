package pl.mglodek;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import lombok.experimental.UtilityClass;
import pl.mglodek.task1.IntListSummary;
import pl.mglodek.task1.IntListSummaryProvider;
import pl.mglodek.task2.ThirteenTuple;
import pl.mglodek.task2.ThirteenTupleFinder;

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

    private static void runThirdTask(Scanner scanner) {

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

}
