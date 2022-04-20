package framework.graph;

import framework.problem.Problem;
import framework.problem.State;
import java.util.Stack;
import java.util.List;

/**
 * @author Sydney Hutchens
 */
public class GraphCreator {
    
    public Graph createGraphFor(Problem problem) {
        
        G = new Graph();
        S = new Stack();
        Vertex start = vertex(problem.getInitialState());
        S.push(start);
        moves = problem.getMover().getMoveNames();  
        while(!S.isEmpty()) {
           u = (Vertex) S.pop();
           for(int i = 0; i < moves.size(); i++) {
             next = problem.getMover().doMove(moves.get(i), (State) u.getData());
             if(next != null) {
                v = vertex(next);
                if(G.getVertices().containsKey(v)) {
                    v = reclaim(v);
                }       
                else {
                    S.push(v);
                    }   
                G.addEdge(u, v);
             }
             
         }
        }
        return G;
        
    }
    
    private Vertex vertex(State s) {
        Vertex state = new Vertex(s);
        return state;
    }
    
       
    private Vertex reclaim(Vertex v) {
       G.getVertices().containsKey(v);
       return G.getVertices().get(v);
    }
    
    private Vertex v;
    private State next;
    private List<String> moves;
    private Vertex u;
    private Graph G;
    private Stack S;

}