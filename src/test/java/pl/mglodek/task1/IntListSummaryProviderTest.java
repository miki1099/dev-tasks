package pl.mglodek.task1;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class IntListSummaryProviderTest {

    private static Stream<Arguments> dataInput() {
        return Stream.of( //
                Arguments.of(List.of(1, 10, 20, 20, 2, 5), List.of(1, 2, 5, 10, 20), 6, 5, 1, 20), //
                Arguments.of(List.of(1, 10, 20, 20, 20, 20, 2, 5), List.of(1, 2, 5, 10, 20), 8, 5, 1, 20), //
                Arguments.of(List.of(1), List.of(1), 1, 1, 1, 1), //
                Arguments.of(List.of(1, 1, 1, 1, 1, 1, 1, 1), List.of(1), 8, 1, 1, 1));
    }

    @ParameterizedTest
    @MethodSource("dataInput")
    void testSimpleFactory(List<Integer> inputData, List<Integer> expectedResult, int expectedCount, long expectedDistinctCount, int expectedMin,
            int expectedMax) {
        //given
        IntListSummaryProvider instance = IntListSummaryProvider.getInstance();

        //when
        IntListSummary summary = instance.process(inputData);

        //then
        assertThat(summary.result(), contains(expectedResult.toArray()));
        assertThat(summary.count(), is(expectedCount));
        assertThat(summary.distinctCount(), is(expectedDistinctCount));
        assertThat(summary.min(), is(expectedMin));
        assertThat(summary.max(), is(expectedMax));
    }

}