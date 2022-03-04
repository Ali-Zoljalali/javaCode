
public class Account {
	private int accountID;
	private int accountBankroll;
	int[] accountUserIDs;
	int usersIDcount;
	String[] transactions;
	int transactionsNum=0;
	

	public Account(int[] a, int accountID) {
		
		this.accountUserIDs=new int[50];
		for(int i=0;i<a.length;i++) {
			this.accountUserIDs[i]=a[i];
		}
		this.accountBankroll=a[a.length-1];
		this.accountID=accountID;
		this.usersIDcount=a.length-1;
		this.transactions=new String[100];
		this.transactionsNum=0;
	}

	public int getAccountID() {
		return this.accountID;
	}

	public int getAccountBankroll() {
		return this.accountBankroll;
	}
	
	public void reduceBankroll(int amount) {
		this.accountBankroll-=amount;
	}
	
	public void increaseBankroll(int amount) {
		this.accountBankroll+=amount;
	}
}
