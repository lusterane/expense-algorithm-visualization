package LineChart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import financeCalculation.CalculationBuilder;
import financeCalculation.ExpenseObject;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class LineChartApplication extends Application{
	@Override
    public void start(Stage primaryStage) throws Exception {
        // setting title
		primaryStage.setTitle("Expenses Visualization");
        
		// defining axis
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Months");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Amount of Expense (USD)");

		final LineChart<Number, Number> lineChart = 
				new LineChart<Number, Number>(xAxis, yAxis);

		// defining series
        XYChart.Series<Number, Number> dataSeries1 = new XYChart.Series<Number, Number>();
        dataSeries1.setName("Expense 1");
        
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
 		HashMap<Integer, Double> hashMapTotal = CalculationBuilder.buildCalculation(packedEO, ExpenseObject.MONTHLY, 60);
        
 		Iterator<Entry<Integer, Double>> iterator = hashMapTotal.entrySet().iterator();
 		
		Entry<Integer, Double> current = null;
		while(iterator.hasNext()) {
			current = iterator.next();
			
			dataSeries1.getData().add(new XYChart.Data<Number, Number>(current.getKey(), current.getValue()));
		}

        lineChart.getData().add(dataSeries1);

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
}
