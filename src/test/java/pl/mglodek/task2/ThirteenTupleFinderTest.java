package pl.mglodek.task2;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ThirteenTupleFinderTest {

    private static Stream<Arguments> dataInput() {
        return Stream.of( //
                Arguments.of(List.of(1, 2, 10, 7, 5, 3, 6, 6, 13, 0), List.of( //
                        createTuple(0, 13), //
                        createTuple(3, 10), //
                        createTuple(6, 7), //
                        createTuple(6, 7))), //
                Arguments.of(List.of(12, 1, 2, 3, 8, 4, 5, 9, 10, 6, 11, 7), List.of( //
                        createTuple(1, 12), //
                        createTuple(2, 11), //
                        createTuple(3, 10), //
                        createTuple(4, 9), //
                        createTuple(5, 8), //
                        createTuple(6, 7))));
    }

    @ParameterizedTest
    @MethodSource("dataInput")
    void testSimpleFactory(List<Integer> inputData, List<ThirteenTuple> expectedResult) {
        //given
        ThirteenTupleFinder instance = ThirteenTupleFinder.getInstance();

        //when
        List<ThirteenTuple> result = instance.find(inputData);

        //then
        assertThat(result, contains(expectedResult.toArray()));
    }

    private static ThirteenTuple createTuple(int fist, int second) {
        return new ThirteenTuple(fist, second);
    }
}