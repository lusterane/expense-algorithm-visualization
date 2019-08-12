package Form;


import LineChart.LineChartApplication;
import financeCalculation.DataProxyService;
import financeCalculation.ExpenseObject;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InformationForm extends Application {
	private Stage primaryStage;
	private GridPane grid;
	private DataProxyService dataProxyService;

	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			primaryStage.setTitle("Expense Visualizer");
			dataProxyService = new DataProxyService();
			
			showMainView();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void showMainView() {	
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		implementUI();

	
		Scene scene = new Scene(grid);
		// scene.getStylesheets().add(getClass().getResource("informationForm.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void implementUI() {
		Text scenetitle = new Text("Welcome");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);
		
		
		
		Label sn = new Label("Service Name:");
		grid.add(sn, 0, 1);

		TextField serviceName = new TextField();
		grid.add(serviceName, 1, 1);
		
		Label cra = new Label("Charge Rate Amount:");
		grid.add(cra, 0, 2);
		
		TextField chargeRateAmount = new TextField();
		grid.add(chargeRateAmount, 1, 2);
		
		Label cr = new Label("Charge Rate:");
		grid.add(cr, 0, 3);

		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			    	ExpenseObject.ONE_TIME,
			        ExpenseObject.DAILY,
			        ExpenseObject.MONTHLY,
			        ExpenseObject.YEARLY
			    );
			final ComboBox chargeRateDropDown = new ComboBox(options);
			grid.add(chargeRateDropDown, 1, 3);
		
			
		Button btn = new Button("Add Expense");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_LEFT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 5);
		
		Button btnSubmission = new Button("Visualize!");
		HBox hbBtnSubmission = new HBox(10);
		hbBtnSubmission.setAlignment(Pos.BOTTOM_LEFT);
		hbBtnSubmission.getChildren().add(btnSubmission);
		grid.add(hbBtnSubmission, 1, 6);

		final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 7);
        
        
        // adding
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	try {
	            	if(serviceName.textProperty().isEmpty().getValue() ||
	            		chargeRateAmount.textProperty().isEmpty().getValue() ||
	            		chargeRateDropDown.getValue() == null)
	            		{
		                actiontarget.setFill(Color.FIREBRICK);
		                actiontarget.setText("Please fill all information");
	            	}
	            	else {
	            		String name = serviceName.textProperty().getValue();
	            		double amount = Double.parseDouble(chargeRateAmount.textProperty().getValue());
	            		String rate = chargeRateDropDown.getValue().toString();
	            		System.out.println("Name: "+ name+", Amount: "+amount+", Rate:"+rate);
	            		dataProxyService.addExpenseObject(name, amount, rate);
	            		actiontarget.setFill(Color.LIMEGREEN);
	            		actiontarget.setText("Added!");
	            	}
            	}
            	catch(Exception e1) {
            		e1.printStackTrace();
            	}
            }
        });
        
        // submission
        btnSubmission.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @SuppressWarnings("unused")
			@Override
            public void handle(ActionEvent e) {
            	if(false) {
            		// check expense component exist logic
	                actiontarget.setText("Please enter at least one expense component");
            	}
            	else {
            		// visualize
            		LineChartApplication lca = new LineChartApplication(dataProxyService);
            		try {
						lca.start(new Stage());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	}	
            }
        });
        
        chargeRateAmount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    chargeRateAmount.setText(newValue.replaceAll("[^\\d.]", ""));
                }
            }

        });
        
        // text hover handler
        /*
        sn.setOnMouseMoved(new EventHandler<MouseEvent>() {
        	@Override
        	public void handle(MouseEvent e) {
        		actiontarget.setText("Incorrect Information");
        	}
        });*/
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

}
