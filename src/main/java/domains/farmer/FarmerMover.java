package domains.farmer;

import framework.problem.Mover;
import framework.problem.State;

/**
 *
 * @author Sydney Hutchens
 */
public class FarmerMover extends Mover {
    public static final String GoesAlone = "Farmer Goes Alone";
    public static final String TakesWolf = "Farmer Takes Wolf";
    public static final String TakesGoat = "Farmer Takes Goat";
    public static final String TakesCabbage = "Farmer Takes Cabbage";
    
    public FarmerMover() {
        super.addMove(GoesAlone, s -> goesAlone(s));
        super.addMove(TakesWolf, s-> takesWolf(s));
        super.addMove(TakesGoat, s -> takesGoat(s));
        super.addMove(TakesCabbage, s -> takesCabbage(s));
    }
    
    private State goesAlone(State state) {
        String[] pos = getPreviousPositions(state);
        FarmerState fs = new FarmerState(move(pos[0]), pos[1], pos[2], pos[3]);
        if(isIllegal(fs)) {
            return null;
        }
        return fs;
    }
    
    private State takesWolf(State state) {
        String[] pos = getPreviousPositions(state);
        FarmerState fs = new FarmerState(move(pos[0]), move(pos[1]), pos[2], pos[3]);
        if (isIllegal(fs)) {
            return null;
        }
        return fs;
    }
    
    private State takesGoat(State state) {
        String[] pos = getPreviousPositions(state);
        FarmerState fs = new FarmerState(move(pos[0]), pos[1], move(pos[2]), pos[3]);
        if (isIllegal(fs)) {
            return null;
        }
        return fs;
    }

    private State takesCabbage(State state) {
        String[] pos = getPreviousPositions(state);
        FarmerState fs = new FarmerState(move(pos[0]), pos[1], pos[2], move(pos[3]));
        if (isIllegal(fs)) {
            return null;
        }
        return fs;
    }
    
    private boolean isIllegal(State state) {
        String[] pos = getPreviousPositions(state);
        boolean illegal = (pos[1] == pos[2] && pos[1] != pos[0]) || (pos[2] == pos[3] && pos[2] != pos[0]);
        return illegal;
    }

    private String move(String s) {
        if(s == "West") {
            return "East";
        }
        
        else if (s == "East") {
            return "West";
        }
        return "";
    }
    
    private String[] getPreviousPositions(State state) {
        FarmerState fs = (FarmerState) state;
        return fs.getPositions();
    }
}

