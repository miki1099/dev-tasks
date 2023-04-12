package pl.mglodek.task1;

import java.util.List;
import java.util.stream.Collectors;

public record IntListSummary(List<Integer> result, int count, long distinctCount, int min, int max) {

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(getResultListString());
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("count: ")
                .append(count);
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("distinct: ")
                .append(distinctCount);
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("min: ")
                .append(min);
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("max: ")
                .append(max);
        return stringBuilder.toString();
    }

    private String getResultListString() {
        return result.stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}
