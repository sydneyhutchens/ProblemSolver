package framework.solution;

import framework.graph.Vertex;
import framework.problem.Mover;
import framework.problem.Problem;
import framework.problem.State;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a state space solver by extending the abstract
 * Solver class.
 * @author Sydney Hutchens
 */
public class StateSpaceSolver extends Solver {
    
    /**
     * Creates a BFS or DFS state space solver.
     * This constructor should set the queue to a double-ended queue 
     * (DEQUE) like the GraphSolver.
     * @param problem the problem being solved
     */
    public StateSpaceSolver(Problem problem) {
        super(problem);
        super.setQueue(new LinkedList<>());
    }
    
    /**
     * This method implements the expand operation required by the 
     * state space search algorithm.
     * @param u the vertex being expanded
     * @return the children of u in the state space search tree
     */
    @Override
    public List<Vertex> expand(Vertex u) { 
        List<Vertex> ret = new LinkedList<>();
        List<String> moveNames = getProblem().getMover().getMoveNames();
        for(String s: moveNames) {
            Vertex v = new Vertex(getProblem().getMover().doMove(s, (State)u.getData()));
            if(v.getData() != null) {
                ret.add(v);
            }

        }
        return ret;
    }
}