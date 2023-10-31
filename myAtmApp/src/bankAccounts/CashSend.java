package bankAccounts;

public class CashSend {
	//
	private String cashSendName;
	private long cashSendPhoneNumber;
	// beneficiary Constructor
	public CashSend (String cashSendName, long cashSendPhoneNumber) {
		//
		this.setCashSendName(cashSendName);
		this.setCashSendPhoneNumber(cashSendPhoneNumber);
		//
	}
	//
	/*
	 * Setters and getters
	 * */
	// setter and getters for beneficiary name
	public String getCashSendName() {
		return cashSendName;
	}
	public void setCashSendName(String cashSendName) {
		this.cashSendName = cashSendName;
	}
	// setter and getters for beneficiary bank
	public long getCashSendPhoneNumber() {
		return cashSendPhoneNumber;
	}
	public void setCashSendPhoneNumber(long cashSendPhoneNumber) {
		this.cashSendPhoneNumber = cashSendPhoneNumber;
	}
}
