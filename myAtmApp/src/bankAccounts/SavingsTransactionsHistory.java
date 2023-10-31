package bankAccounts;

public class SavingsTransactionsHistory extends TransactionsHistory{
	//
	String transactionType;
	//
	public SavingsTransactionsHistory(String transactionDate, String transactionDescription, double transactionAmount,
			String transactionID, String transactionType) {
		//
		super (transactionDate, transactionDescription, transactionAmount, transactionID);
		//
		this.setTransactionType(transactionType);;
	}

	@Override
	public String getTransactionType() {
	
		return transactionType;
	}

	@Override
	public void setTransactionType(String transactionType) {
		
		this.transactionType = transactionType;
		
	}
	
}


