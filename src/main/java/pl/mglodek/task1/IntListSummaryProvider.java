package pl.mglodek.task1;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IntListSummaryProvider {

    private static IntListSummaryProvider instance;

    public static IntListSummaryProvider getInstance() {
        if ( instance == null ) {
            instance = new IntListSummaryProvider();
        }
        return instance;
    }

    public IntListSummary process(List<Integer> data) {
        checkInputData(data);

        int count = data.size();
        List<Integer> sortedData = sortAndRemoveCopies(data);
        IntSummaryStatistics stats = getListStats(sortedData);

        return new IntListSummary(sortedData, count, stats.getCount(), stats.getMin(), stats.getMax());
    }

    private void checkInputData(List<Integer> data) {
        if ( data == null || data.isEmpty() ) {
            throw new IllegalArgumentException("input data should not be empty.");
        }
    }

    private List<Integer> sortAndRemoveCopies(List<Integer> data) {
        return data.stream()
                .distinct()
                .sorted()
                .toList();
    }

    private IntSummaryStatistics getListStats(List<Integer> data) {
        return data.stream()
                .collect(Collectors.summarizingInt(Integer::intValue));
    }

}
