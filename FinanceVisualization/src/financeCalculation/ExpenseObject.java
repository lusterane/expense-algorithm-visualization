package financeCalculation;

public class ExpenseObject {
	private String name, timeInterval;
	private double amount;
	public static final String YEARLY = "YEARLY";
	public static final String MONTHLY = "MONTHLY";
	public static final String DAILY = "DAILY";
	public static final String ONE_TIME = "ONE_TIME";
	
	public ExpenseObject(String name, double amount, String timeInterval) {
		this.name = name;
		this.amount = amount;
		this.timeInterval = timeInterval;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTimeInterval() {
		return timeInterval;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
