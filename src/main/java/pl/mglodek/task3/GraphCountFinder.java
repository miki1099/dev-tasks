package pl.mglodek.task3;

import java.util.List;
import java.util.function.Function;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GraphCountFinder {

    private static final Function<VisitDecorator, Integer> START_EDGE = visitDecorator -> visitDecorator.getEdge()
            .start();

    private static final Function<VisitDecorator, Integer> END_EDGE = visitDecorator -> visitDecorator.getEdge()
            .end();

    private static GraphCountFinder instance;

    public static GraphCountFinder getInstance() {
        if ( instance == null ) {
            instance = new GraphCountFinder();
        }
        return instance;
    }

    public int find(List<GraphEdge> edges) {
        List<VisitDecorator> edgeCopy = edges.stream()
                .map(VisitDecorator::new)
                .toList();

        int countedGraphs = 0;

        for (VisitDecorator visitDecorator : edgeCopy) {
            if ( !visitDecorator.isVisited() ) {
                int start = visitDecorator.getEdge().start();
                visitDown(edgeCopy, start);
                visitUp(edgeCopy, start);
                countedGraphs++;
            }

        }
        return countedGraphs;
    }

    private void visitUp(List<VisitDecorator> edges, int vertex) {
        visit(edges, vertex, true);
    }

    private void visitDown(List<VisitDecorator> edges, int vertex) {
        visit(edges, vertex, false);
    }

    private void visit(List<VisitDecorator> edges, int vertex, boolean visitUp) {
        Function<VisitDecorator, Integer> filterVertex;
        Function<VisitDecorator, Integer> visitVertex;

        if ( visitUp ) {
            filterVertex = START_EDGE;
            visitVertex = END_EDGE;
        } else {
            filterVertex = END_EDGE;
            visitVertex = START_EDGE;
        }

        List<VisitDecorator> edgesToVisit = edges.stream()
                .filter(edge -> !edge.isVisited())
                .filter(edge -> filterVertex.apply(edge) == vertex)
                .toList();
        for (VisitDecorator visitDecorator : edgesToVisit) {
            visitDecorator.setVisited(true);
            Integer nextVertex = visitVertex.apply(visitDecorator);
            visitUp(edges, nextVertex);
            visitDown(edges, nextVertex);
        }
    }

    @Getter
    @RequiredArgsConstructor
    private static class VisitDecorator {

        private final GraphEdge edge;

        @Setter
        private boolean visited = false;

    }

}
