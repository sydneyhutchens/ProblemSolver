package domains.farmer;

import framework.graph.Graph;
import framework.graph.GraphSearcher;
import framework.graph.Vertex;
import framework.problem.Problem;
import framework.solution.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * A test file for graphs of the FWGC problem.
 * @author tcolburn
 */
public class FarmerGraphTest {
    
    public FarmerGraphTest() {
        problem = new FarmerProblem();
        farmerGraph = new FarmerGraph();
        graphSearcher = new GraphSearcher(farmerGraph);
        start = farmerGraph.find(new Vertex(problem.getInitialState()));
        end = farmerGraph.find(new Vertex(problem.getFinalState()));
    }

    @Test
    public void testBFS1() {
        graphSearcher.breadthFirstSearch(start);
        solution = new Solution(start, end);
        assertTrue(solution.getLength() == 7);
        System.out.println("BFS from start to end:\n" + solutionString(solution));
    }

    @Test
    public void testBFS2() {
        graphSearcher.breadthFirstSearch(end);
        solution = new Solution(end, start);
        assertTrue(solution.getLength() == 7);
        System.out.println("BFS from end to start:\n" + solutionString(solution));
    }

    @Test
    public void testDFS1() {
        graphSearcher.depthFirstSearch(start);
        solution = new Solution(start, end);
        assertTrue(solution.getLength() == 7);
        System.out.println("DFS from start to end:\n" + solutionString(solution));
    }

    @Test
    public void testDFS2() {
        graphSearcher.depthFirstSearch(end);
        solution = new Solution(end, start);
        assertTrue(solution.getLength() == 7);
        System.out.println("DFS from end to start:\n" + solutionString(solution));
    }
    
    private String solutionString(Solution solution) {
        StringBuilder builder = new StringBuilder();
        while (solution.hasNext()) {
            builder.append(solution.next().toString());
            builder.append("\n\n");
        }
        return builder.toString();
    }
    
    private final Problem problem;
    private final Graph farmerGraph;
    private final GraphSearcher graphSearcher;
    private final Vertex start;
    private final Vertex end;
    private Solution solution;
}