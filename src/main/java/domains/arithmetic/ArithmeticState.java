package domains.arithmetic;

import framework.problem.State;

/**
 *
 * @author Sydney Hutchens
 */
public class ArithmeticState extends State {
    
    public ArithmeticState(int value) {
        this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
        ArithmeticState otherArithmeticState = (ArithmeticState) other;
        return value == otherArithmeticState.value;
    }
    
    @Override
    public String toString() {
        String valueIs = "The value is: ";
        String valueString = valueIs + Integer.toString(value);
        return valueString;
    }
    
    public int getValue() {
        return value;
    }
 
   
    
    private final int value;
}
