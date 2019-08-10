package financeCalculation;

public class ExpenseObject {
	class Conditional {
		private String timeInterval;
		private int amount;
		public Conditional(String timeInterval, int amount) {
			this.timeInterval = timeInterval;
			this.amount = amount;
		}
		
		// make getters and setters
	}
	
	private String name, timeInterval;
	private double amount;
	protected static String YEARLY = "YEARLY";
	protected static String MONTHLY = "MONTHLY";
	protected static String DAILY = "DAILY";
	protected static String ONE_TIME = "ONE_TIME";
	
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
	/*
	public Conditional getConditional() {
		return conditional;
	}
	
	public void setConditional(Conditional conditional) {
		this.conditional = conditional;
	}*/
}
