package pl.mglodek.task2;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ThirteenTupleFinder {

    private static ThirteenTupleFinder instance;

    public static ThirteenTupleFinder getInstance() {
        if ( instance == null ) {
            instance = new ThirteenTupleFinder();
        }
        return instance;
    }

    public List<ThirteenTuple> find(List<Integer> data) {
        List<Integer> sortedData = data.stream()
                .sorted()
                .toList();

        List<ThirteenTuple> result = new ArrayList<>();

        for (int i = 0; i < sortedData.size(); i++) {
            for (int j = sortedData.size() - 1; j > i; j--) {
                Integer firstValue = sortedData.get(i);
                Integer secondValue = sortedData.get(j);
                if( firstValue + secondValue == 13) {
                    result.add(new ThirteenTuple(firstValue, secondValue));
                }
            }
        }
        return result;
    }
}
