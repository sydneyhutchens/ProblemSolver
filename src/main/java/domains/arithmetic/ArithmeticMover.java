package domains.arithmetic;

import framework.problem.Mover;
import framework.problem.State;

/**
 *
 * @author Sydney Hutchens
 */
public class ArithmeticMover extends Mover {
    public static final String addThree = "Add 3";
    public static final String subtractFive = "Subtract 5";
    public static final String divideTwo = "Divide by 2";
    public static final String multiplyTwo = "Multiply by 2";
  public ArithmeticMover() {
            super.addMove(addThree, s -> tryAdd((ArithmeticState) s));
            super.addMove(subtractFive, s -> trySubtract((ArithmeticState) s));
            super.addMove(divideTwo, s -> tryDivide((ArithmeticState) s));
            super.addMove(multiplyTwo, s -> tryMultiply((ArithmeticState) s));
  }
        private State tryAdd(ArithmeticState state) {
            int moverValue = state.getValue() + 3;    
            
            return new ArithmeticState(moverValue);
        }

        private State trySubtract(ArithmeticState state) {
            int moverValue = state.getValue() - 5;
            
            return new ArithmeticState(moverValue);
        }

        private State tryMultiply(ArithmeticState state) {
           int moverValue = state.getValue() * 2; 
            return new ArithmeticState(moverValue);
        }

        private State tryDivide(ArithmeticState state) {
            int moverValue = state.getValue() / 2;
            return new ArithmeticState(moverValue);
        }
        
        
}