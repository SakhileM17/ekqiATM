package bankAccounts;

public class Beneficiaries {
	//
	private String beneficiaryName;
	private long beneficiaryAccountNumber;
	private String beneficiaryBank;
	// beneficiary Constructor
	public Beneficiaries(String beneficiaryName, long beneficiaryAccountNumber, String beneficiaryBank) {
		//
		this.setBeneficiaryName(beneficiaryName);
		this.setBeneficiaryAccountNumber(beneficiaryAccountNumber);
		this.setBeneficiaryBank(beneficiaryBank);
		//
	}
	//
	/*
	 * Setters and getters
	 * */
	// setter and getters for beneficiary name
	public String getBeneficiaryName() {
		return beneficiaryName;
	}
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}
	// setter and getters for beneficiary account number
	public long getBeneficiaryAccountNumber() {
		return beneficiaryAccountNumber;
	}
	public void setBeneficiaryAccountNumber(long beneficiaryAccountNumber) {
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
	}
	// setter and getters for beneficiary bank
	public String getBeneficiaryBank() {
		return beneficiaryBank;
	}
	public void setBeneficiaryBank(String beneficiaryBank) {
		this.beneficiaryBank = beneficiaryBank;
	}
}
