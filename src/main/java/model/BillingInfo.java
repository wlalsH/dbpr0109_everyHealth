package model;

public class BillingInfo {
	private String accountHolder;
	private String bankName;
	
	public String getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public BillingInfo(String accountHolder, String bankName) {
		super();
		this.accountHolder = accountHolder;
		this.bankName = bankName;
	}
	
	@Override
	public String toString() {
		return "BillingInfo [accountHolder=" + accountHolder + ", bankName=" + bankName + "]";
	}
}
