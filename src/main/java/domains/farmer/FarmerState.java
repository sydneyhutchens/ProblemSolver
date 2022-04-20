package domains.farmer;

import framework.problem.State;
import java.util.Objects;

/**
 *
 * @author Sydney Hutchens
 */
public class FarmerState extends State {
     public FarmerState(String f, String w, String g, String c) {
        this.f = f;
        this.w = w;
        this.g = g;
        this.c = c;
    }
    @Override
    public boolean equals(Object other) {
     
        if (other == null) {
            return false;
        }

        FarmerState otherState = (FarmerState) other;
        return this.f == otherState.f && this.w == otherState.w && this.g == otherState.g && this.c == otherState.c;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.f);
        hash = 97 * hash + Objects.hashCode(this.w);
        hash = 97 * hash + Objects.hashCode(this.g);
        hash = 97 * hash + Objects.hashCode(this.c);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("   |  |   \n");
        if (f == "West") {
            sb.append(" F |  |   \n");
        } 
        else if (f == "East") {
            sb.append("   |  | F \n");
        }
        if (w == "West") {
            sb.append(" W |  |   \n");
        } 
        else if (w == "East") {
            sb.append("   |  | W \n");
        }
        if (g == "West") {
            sb.append(" G |  |   \n");
        } 
        else if (g == "East") {
            sb.append("   |  | G \n");
        }
        if (c == "West") {
            sb.append(" C |  |   \n");
        } 
        else if (c == "East") {
            sb.append("   |  | C \n");
        }
        sb.append("   |  |   ");

        return sb.toString();
    }

    public String[] getPositions() {
        String[] s = {f, w, g, c};
        return s;
    }

    private String f, w, g, c;
}
