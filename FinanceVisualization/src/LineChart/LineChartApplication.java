package LineChart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Expenses Visualization");
        
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Months");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Amount of Expense (USD)");

        @SuppressWarnings("unchecked")
		LineChart lineChart = new LineChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
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
 		
 		// Provide list of ExpenseObject from previous step, interval of time for data to calculate, and length of time for data
 		List<Double> totalList = CalculationBuilder.buildCalculation(packedEO, ExpenseObject.MONTHLY, 60);
        
 		Iterator<Double> iterator = totalList.iterator();
		int counter = 1;
		double current = 0;
		while(iterator.hasNext()) {
			current = iterator.next();
			dataSeries1.getData().add(new XYChart.Data( counter, current));
			counter++;
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
