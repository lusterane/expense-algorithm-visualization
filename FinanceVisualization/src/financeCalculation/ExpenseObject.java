package financeCalculation;

public class ExpenseObject {
	private String name, chargeInterval;
	private double amount;
	public static final String YEARLY = "YEARLY";
	public static final String MONTHLY = "MONTHLY";
	public static final String DAILY = "DAILY";
	public static final String ONE_TIME = "ONE_TIME";

	public ExpenseObject(String name, double amount, String chargeInterval) {
		this.name = name;
		this.amount = amount;
		this.chargeInterval = chargeInterval;
	}

	public String getName() {
		return name;
	}

	public String getChargeInterval() {
		return chargeInterval;
	}

	public double getAmount() {
		return amount;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setChargeInterval(String chargeInterval) {
		this.chargeInterval = chargeInterval;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
