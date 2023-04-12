package pl.mglodek.task3;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GraphCountFinderTest {

    private static Stream<Arguments> dataInput() {
        return Stream.of( //
                Arguments.of(List.of( //
                        createEdge(1, 2), //
                        createEdge(2, 3), //
                        createEdge(4, 5)), 2), //
                Arguments.of(List.of( //
                        createEdge(1, 2), //
                        createEdge(2, 3), //
                        createEdge(3, 4), //
                        createEdge(1, 4)), 1), //
                Arguments.of(List.of( //
                        createEdge(2, 3), //
                        createEdge(1, 2), //
                        createEdge(4, 5), //
                        createEdge(7, 8), //
                        createEdge(7, 9), //
                        createEdge(11, 9)), 3));
    }

    @ParameterizedTest
    @MethodSource("dataInput")
    void testGraphCountFinder(List<GraphEdge> inputData, int expectedCount) {
        //given
        GraphCountFinder instance = GraphCountFinder.getInstance();

        //when
        int result = instance.find(inputData);

        //then
        assertThat(result, is(expectedCount));
    }

    private static GraphEdge createEdge(int start, int end) {
        return new GraphEdge(start, end);
    }
}