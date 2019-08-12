package LineChart;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import financeCalculation.CalculationBuilder;
import financeCalculation.DataProxyService;
import financeCalculation.ExpenseObject;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class LineChartApplication extends Application{
	
	private DataProxyService dataProxyService;
	public LineChartApplication(DataProxyService dataProxyService) {
		this.dataProxyService = dataProxyService;
	}
	@Override
    public void start(Stage primaryStage) throws Exception {
        // setting title
		primaryStage.setTitle("Expenses Visualization");
        
		// defining axis
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Months");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Amount of Expense (USD)");
        
        
        dataProxyService.buildExpensesHashMap(ExpenseObject.MONTHLY, 60);
        
		final LineChart lineChart = new LineChart(xAxis, yAxis, FXCollections.observableArrayList(
				new XYChart.Series("Expenses 1", FXCollections.observableArrayList(plot(dataProxyService.getExpensesHashMap())))));
		lineChart.setCursor(Cursor.CROSSHAIR);
		

        VBox vbox = new VBox(lineChart);
        Scene scene = new Scene(vbox, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.setHeight(300);
        primaryStage.setWidth(1200);

        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public ObservableList<XYChart.Data<Integer, Double>> plot(HashMap<Integer, Double> hm) {
        final ObservableList<XYChart.Data<Integer, Double>> dataset = FXCollections.observableArrayList();
        Entry<Integer, Double> current = null;
        Iterator<Entry<Integer, Double>> iterator = hm.entrySet().iterator();
		while(iterator.hasNext()) {
			current = iterator.next();
          final XYChart.Data<Integer, Double> data = new XYChart.Data<>(current.getKey(), current.getValue());
          data.setNode(
              new HoveredThresholdNode(
                  current.getKey(), current.getValue()
              )
          );

          dataset.add(data);
        }

        return dataset;
      }
    
    // temporary place holder to mimic logic abstraction
    public HashMap<Integer, Double> tempGetData(){
    	// create data 
        
        // Create Expense Objects based on the service name, billing amount, and billing times
 		ExpenseObject apple = new ExpenseObject("Apple Store", 99.00, ExpenseObject.YEARLY);
 		ExpenseObject android = new ExpenseObject("Android Store", 25.00, ExpenseObject.ONE_TIME);
 		ExpenseObject firebase = new ExpenseObject("Firebase", 25.00, ExpenseObject.MONTHLY);
 		
 		// Pack Expense Objects into list to be parsed and calculated
 		List<ExpenseObject> packedEO = new ArrayList<ExpenseObject>();
 		packedEO.add(apple);
 		packedEO.add(android);
 		packedEO.add(firebase);

 		// Aggregate expense object to algorithm for calculation
 		HashMap<Integer, Double> hashMapTotal = CalculationBuilder.buildCalculation(packedEO, ExpenseObject.MONTHLY, 30);
 		
 		return hashMapTotal;
    }

	/** a node which displays a value on hover, but is otherwise empty */
	class HoveredThresholdNode extends StackPane {
		HoveredThresholdNode(int priorValue, double value) {
			setPrefSize(15, 15);

			final Label label = createDataThresholdLabel(priorValue, value);

			setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					getChildren().setAll(label);
					setCursor(Cursor.NONE);
					toFront();
				}
			});
			setOnMouseExited(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					getChildren().clear();
					setCursor(Cursor.CROSSHAIR);
				}
			});
		}

		private Label createDataThresholdLabel(int priorValue, double value) {
			DecimalFormat df = new DecimalFormat("#.00");
			System.out.println(value);
			final Label label = new Label("$" + df.format(value) + "");
			label.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
			label.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

			if (priorValue == 0) {
				label.setTextFill(Color.DARKGRAY);
			} else if (value > priorValue) {
				label.setTextFill(Color.FORESTGREEN);
			} else {
				label.setTextFill(Color.FIREBRICK);
			}

			label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
			return label;
		}
	}
}
