package framework.ui;

import domains.arithmetic.ArithmeticProblem;
import domains.dummy.DummyProblem;
import domains.farmer.FarmerProblem;
import domains.puzzle.PuzzleProblem;
import domains.puzzle.PuzzleState;
import framework.problem.Problem;
import framework.ui.ProblemTabPane;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

/**
 * Tests the ProblemTabPane class.
 * @author Sydney Hutchens
 */
public class ProblemTabPaneTest extends Application {

    private Tab DummyTab;
    private Tab ArithmeticTab;
    private Tab FWGCTab;
    private Tab PuzzleTab;
    
    @Override
    public void start(Stage primaryStage) {
       
        ProblemGUI dummyGUI = new ProblemGUI(new DummyProblem(), 600, 750);
        ProblemGUI arithmeticGUI = new ProblemGUI(new ArithmeticProblem(), 600, 750);
        ProblemGUI farmerGUI = new ProblemGUI(new FarmerProblem(), 600, 750);
        ProblemGUI puzzleGUI = new ProblemGUI(new PuzzleProblem(), 600, 750);
        
        ProblemTabPane pane = new ProblemTabPane();
        
        DummyTab = new Tab();
        DummyTab.setText("Dummy");
        DummyTab.setContent(dummyGUI);
        
        ArithmeticTab = new Tab();
        ArithmeticTab.setText("Arithmetic");
        ArithmeticTab.setContent(arithmeticGUI);
        
        FWGCTab = new Tab();
        FWGCTab.setText("Farmer, Wolf, Goat, & Cabbage");
        FWGCTab.setContent(farmerGUI);
        
        PuzzleTab = new Tab();
        PuzzleTab.setText("8-Puzzle");
        PuzzleTab.setContent(puzzleGUI);
        
        // Add the GUI objects to the pane here...
        pane.getTabs().add(DummyTab);
        pane.getTabs().add(ArithmeticTab);
        pane.getTabs().add(FWGCTab);
        pane.getTabs().add(PuzzleTab);
        
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Testing Problem Tab Pane");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Test
    public void testTabPane() {
        launch();
    }
    
}
