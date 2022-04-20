package framework.ui;

import framework.graph.Vertex;
import framework.solution.SolvingAssistant;
import framework.problem.Problem;
import framework.problem.State;
import framework.solution.AStarSolver;
import framework.solution.BFSStateSpaceSolver;
import framework.solution.BestFirstSolver;
import framework.solution.DFSStateSpaceSolver;
import framework.solution.Solution;
import framework.solution.Solver;
import framework.solution.StateSpaceSolver;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import framework.problem.Benchmark;

/**
 *
 * @author Sydney Hutchens
 */
public class ProblemGUI extends VBox {

    public ProblemGUI(Problem problem, double width, double height) {

        // The root and the canvas.
        root = new VBox();
        canvas = new Pane();
        assistant = new SolvingAssistant(problem);
        solver = new StateSpaceSolver(problem);
        
         // Align everything to the center, create a window size and make padding.
        root.setAlignment(Pos.CENTER);
        canvas.setPrefSize(width, height);
        
        // Create a welcome message.
        welcomeMessage = new Label("Welcome to the " + problem.getName() + " problem.");
        welcomeMessage.setFont(Font.font ("Veranda", FontWeight.BOLD, 30));
        welcomeMessage.setTextAlignment(TextAlignment.CENTER);
        welcomeMessage.setPadding(new Insets(10, 10, 10, 10));
        welcomeMessage.setMaxWidth(width);
        
        // Create the introduction.
        introMessage = new Label(problem.getIntroduction());
        introMessage.setFont(Font.font ("Veranda", 15));
        introMessage.setPadding(new Insets(10, 10, 10, 10));
        introMessage.setWrapText(true);
        introMessage.setMaxWidth(width);
        
        // Create the main HBox, where 3 VBoxes will be constructed from.
        HBox1 = new HBox();
        HBox1.setPadding(new Insets(30, 10, 10, 50));
        VBox1 = new VBox();
        
        // Creates the congrats label. Shows when currentState == goalState.
        congratsLabel = new Label(" ");
        congratsLabel.setFont(Font.font ("Veranda", FontWeight.BOLD,  20));
        congratsLabel.setAlignment(Pos.CENTER);
        congratsLabel.setPadding(new Insets(20, 20, 20, 160));
        
        //Illegal move Label
        illegalLabel = new Label(" ");
        illegalLabel.setFont(Font.font ("Veranda", FontWeight.BOLD,  20));
        illegalLabel.setAlignment(Pos.CENTER);
        illegalLabel.setPadding(new Insets(20, 20, 20, 160));
        illegalLabel.setTextFill(Color.RED);
        
        // Creates a reset button.
        reset = new Button("Reset");
        reset.setFont(Font.font ("Veranda", FontWeight.BOLD,  20));
        reset.setAlignment(Pos.CENTER);
        
        // Create a StackPane for the current state.
        stackPane1 = new StackPane();
        stackPane1.setBorder(new Border(new BorderStroke(Color.BLACK, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        // Create a currentState interface.
        currentStateLabel = new Label("Current State:");
        currentStateLabel.setFont(Font.font ("Veranda", FontWeight.BOLD, 15));
        currentStateLabel.setPadding(new Insets(10, 10, 10, 10));
        
        currentState = new Label(problem.getCurrentState().toString());
        currentState.setFont(Font.font ("Courier New", FontWeight.BOLD, 15));
        currentState.setPadding(new Insets(10, 10, 5, 15));
        currentState.setTextAlignment(TextAlignment.CENTER);
        
        // Create a second VBox for the buttons.
        VBox2 = new VBox();
        VBox2.setPadding(new Insets(10, 10, 10, 10));
        VBox2.setSpacing(5.0);
        
        // Create the button labels and the buttons.
        buttonLabel = new Label("Moves: " + assistant.getMoveCount() + " so far");
        buttonLabel.setPadding(new Insets(0, 45, 0, 85));
        buttonLabel.setFont(Font.font ("Veranda", FontWeight.BOLD, 15));
        
        // Create an HBox for the extra buttons.
        HBox2 = new HBox();
        HBox2.setPadding(new Insets(10, 60, 0, 60));
        HBox2.setSpacing(15.0);
        
       
        //VBox for move buttons
        VBox4 = new VBox();
        VBox4.setPadding(new Insets(10, 10, 10, 10));
        VBox4.setSpacing(5);
        
       // Create the buttons:
        solve = new Button("Solve");
        next = new Button("Next");
        next.setDisable(true);
        
        //Implement move buttons
         List<String> names = problem.getMover().getMoveNames();
       for (String name: names) {
           Button button = new Button(name);
           button.setAlignment(Pos.CENTER);
           VBox4.getChildren().addAll(button); // add Button to button box
           button.setOnAction(e -> {
           illegalLabel.setText("");
           assistant.tryMove(name);
           if(!assistant.isMoveLegal()) {
               illegalLabel.setText("Illegal Move. Try again");
           }
           currentState.setText(problem.getCurrentState().toString());
           buttonLabel.setText("Moves: " + assistant.getMoveCount() + " so far");
           if (assistant.isProblemSolved()) {
                congratsLabel.setText("Congratulations! you solved the problem");
                next.setDisable(true);
            }});
           
       }
       
        statisticsInformation = new Label();
        statisticsInformation.setFont(Font.font ("Courier New", 10));
        statisticsInformation.setPadding(new Insets(10, 10, 5, 15));
        statisticsInformation.setTextAlignment(TextAlignment.CENTER);
        statisticsInformation.setMinSize(150, 150);
        statisticsInformation.setWrapText(true);

        // Implements the reset button.
        reset.setOnAction(e -> {
                assistant.reset();
                currentState.setText(problem.getInitialState().toString());
                buttonLabel.setText("Moves: " + assistant.getMoveCount() + " so far");
                congratsLabel.setText(" ");
                solve.setDisable(false);
                next.setDisable(true);
                statisticsInformation.setText(" "); // clear any statistics
        });
        
        // Create a third VBox for the goal state.
        VBox3 = new VBox();
        stackPane2 = new StackPane();
        stackPane2.setBorder(new Border(new BorderStroke(Color.BLACK, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        // Create the goal state interface.
        finalStateLabel = new Label("Goal State:");
        finalStateLabel.setFont(Font.font ("Veranda", FontWeight.BOLD, 15));
        finalStateLabel.setPadding(new Insets(10, 10, 10, 10));
        
        finalState = new Label(problem.getFinalState().toString());
        finalState.setFont(Font.font ("Courier New", FontWeight.BOLD, 15));
        finalState.setPadding(new Insets(10, 10, 5, 15));
        
        VBox5 = new VBox();
        VBox5.setPadding(new Insets(10, 10, 10, 10));
        VBox5.setSpacing(5.0);
        
        // Create the HBox and the VBoxes that will be inside of it:
        HBox3 = new HBox();
        HBox3.setPadding(new Insets(10, 10, 10, 10));
        HBox3.setSpacing(5);
        HBox3.setAlignment(Pos.CENTER);
        
        VBox6 = new VBox();
        VBox6.setPadding(new Insets(10, 10, 10, 10));
        VBox6.setSpacing(15);
        VBox6.setAlignment(Pos.CENTER);
        
        VBox7 = new VBox();
        VBox7.setPadding(new Insets(10, 10, 10, 10));
        VBox7.setSpacing(15);
        VBox7.setAlignment(Pos.CENTER);
        
        VBox8 = new VBox();
        VBox8.setPadding(new Insets(10, 10, 10, 10));
        VBox8.setSpacing(15);
        VBox8.setAlignment(Pos.CENTER);
        
        VBox9 = new VBox();
        VBox9.setPadding(new Insets(10, 10, 10, 10));
        VBox9.setSpacing(15);
        VBox9.setAlignment(Pos.CENTER);
        
        // Create the Search Type spinner:
        searchTypeLabel = new Label("Search Type:");
        ChoiceBox searchLabels = new ChoiceBox();
        searchLabels.getItems().addAll("BF State Space Search","DF State Space Search", "Best-First Search", "A* Search");
        
        searchType = new VBox();
        searchType.getChildren().addAll(searchLabels);

        //Implements Solve button
        solve.setOnAction(e -> {
            solve.setDisable(true);
            next.setDisable(false);
           
            switch ((String)searchLabels.getValue()) { 
                case "A* Search":
                    solver = new AStarSolver(solver.getProblem());
                    break;
                case "Best-First Search":
                    solver = new BestFirstSolver(solver.getProblem());
                    break;
                case "BF State Space Search":
                    solver = new BFSStateSpaceSolver(solver.getProblem());
                    break;
                case "DF State Space Search":
                    solver = new DFSStateSpaceSolver(solver.getProblem());
                    break;
                    
            }
            solver.solve();
            solution = solver.getSolution();
            statisticsInformation.setText(solver.getStatistics().toString());
            
        });
        
         next.setOnAction(e -> {
             if(solution.hasNext()) {
                 Vertex v = solution.next();
                 State s = (State)v.getData();
                 assistant.update(s);
                 currentState.setText(problem.getCurrentState().toString());
                 buttonLabel.setText("Moves: " + assistant.getMoveCount() + " so far");
                 if (assistant.isProblemSolved()) {
                   congratsLabel.setText("Congratulations! you solved the problem");
                   next.setDisable(true);
                 }
                                
           }
           
        });
        
        // Create the Statistics:
        statisticsLabel = new Label("Statistics:");
        statistics = new StackPane();
        statistics.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        // Create Benchmarks drop-down menu:
        benchmarkLabel = new Label("Benchmarks:");
        
       ChoiceBox benchmarkLabels = new ChoiceBox<>(FXCollections.observableArrayList(problem.getBenchmarks()));
       benchmarkLabels.setOnAction(e -> { //make benchmark active when selected
            Benchmark b = (Benchmark) benchmarkLabels.getValue();
            assistant.reset();
            problem.setCurrentState(b.getStart());
            currentState.setText(b.getStart().toString());
        });
      
        benchmarkMenu = new VBox();
        benchmarkMenu.getChildren().addAll(benchmarkLabels);
        
        
        // Start stacking everything.
        super.getChildren().addAll(welcomeMessage, introMessage, HBox1, congratsLabel, illegalLabel, HBox3, reset);        // Add the welcome message, intro, and HBox. Adds the congrats label.
        HBox1.getChildren().addAll(stackPane1, VBox2, stackPane2);              // Use the HBox to make the stack panes and VBox for the buttons.
        stackPane1.getChildren().addAll(VBox1);                                 // For the first stack pane, create the VBox.
        VBox1.getChildren().addAll(currentStateLabel, currentState);            // First VBox: Add the label and current state.
        VBox2.getChildren().addAll(buttonLabel, HBox2);                         // Second VBox: Add the label and buttons.
        HBox2.getChildren().addAll(VBox4);
        stackPane2.getChildren().addAll(VBox3);                                 // For the second stack pane, create the third VBox.
        VBox3.getChildren().addAll(finalStateLabel, finalState);                // Third VBox: Add the label and final state.
        HBox3.getChildren().addAll(VBox6, VBox7, VBox8, VBox9);
        VBox6.getChildren().addAll(solve, next);
        VBox7.getChildren().addAll(searchTypeLabel, searchType);
        VBox8.getChildren().addAll(statisticsLabel, statistics);
        statistics.getChildren().addAll(statisticsInformation);
        VBox9.getChildren().addAll(benchmarkLabel, benchmarkMenu);
          
    };
    
     public Problem getProblem() {
       return assistant.getProblem();
   }
     
    private final VBox root;
    private final Pane canvas;
    private final Label welcomeMessage, introMessage;
    private final HBox HBox1, HBox2, HBox3;
    private final StackPane stackPane1, stackPane2, statistics;
    private final VBox VBox1, VBox2, VBox3, VBox4, VBox5, VBox6, VBox7, VBox8, VBox9, searchType, benchmarkMenu;
    private final Label currentStateLabel, currentState;
    private final Label buttonLabel;
    private final Label searchTypeLabel, statisticsLabel, statisticsInformation, benchmarkLabel;
    private final Button solve, next;
    private final Label finalStateLabel, finalState;
    private final Label congratsLabel, illegalLabel;
    private final SolvingAssistant assistant;
    private final Button reset;
    private Solver solver;
    private Solution solution;
    
}