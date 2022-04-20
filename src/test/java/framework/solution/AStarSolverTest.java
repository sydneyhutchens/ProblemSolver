package framework.solution;

import domains.puzzle.PuzzleProblem;
import framework.problem.Benchmark;
import framework.problem.Problem;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author tcolburn
 */
public class AStarSolverTest {
    
    @Test
    public void testPuzzleAStarSolver() {
        Problem problem = new PuzzleProblem();
        AStarSolver aStarSolver = new AStarSolver(problem);
        for (Benchmark b : problem.getBenchmarks()) {
            problem.setCurrentState(b.getStart());
            aStarSolver.solve();
            assertTrue(aStarSolver.getSolution().getLength() == b.getSolutionLength());
            System.out.println(b.getName() + " A* solver test: " + " passed");
        }
    }
    
}