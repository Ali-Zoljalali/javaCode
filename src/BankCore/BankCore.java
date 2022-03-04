import java.util.Scanner;

public class BankCore {
	int userCounts=0;
	int accountCounts=0;
	UserAccount[] users=new UserAccount[1000000];
	Account[] accounts=new Account[1000000];
	String[] transactions=new String[1000000];
	int[][] transactionsStatus=new int[1000000][50];
	int transactionsCount=0;
	String[] transactionAccountIDs=new String[1000000];
	
	
	public void addCustomer(String name,String surname) {
		for(int i=0;i<this.userCounts;i++) {
			if(name.equals(this.users[i].userName)) {
				System.out.println("This user has been created!");
				return;
			}
		}
		
		this.users[this.userCounts]=new UserAccount(name,surname,this.userCounts);
		System.out.println(this.userCounts);
		this.userCounts++;
		
	}
	
	public void creatAccount(int[] a) {
		//System.out.println("HEllllofflskd");
		this.accounts[this.accountCounts]=new Account(a,this.accountCounts);
		System.out.println(this.accountCounts);
		this.accountCounts++;
		
		
	}
	
	public void addOwner(int accountID,int ownerID) {
		for(int i=0;i<this.accountCounts;i++) {
			if(accountID==this.accounts[i].getAccountID()) {
				for(int j=0;j<this.accounts[i].usersIDcount;j++) {
					if(ownerID==this.accounts[i].accountUserIDs[j]) {
						System.out.println("This owner has been ownered before!!!");
						return;
					}
				}
				this.accounts[i].accountUserIDs[this.accounts[i].usersIDcount]=ownerID;
				this.accounts[i].usersIDcount++;
				return;
			}
		}
		
		System.out.println("This accountID or ownerID does not exist!!!");
	}
	
	public void bookTransaction(int src_account,int dest_account,int amount) {
		int src_account_index=0;

		
		for(int i=0;i<this.accountCounts;i++) {
			if(this.accounts[i].getAccountID()==src_account) {
				src_account_index=i;
			}
		}
		
		System.out.println(this.transactionsCount);
		
		
		for(int i=0;i<this.transactionsStatus[this.accountCounts].length;i++) {
			this.transactionsStatus[this.transactionsCount][i]=-1;
		}
		
		this.transactionAccountIDs[this.transactionsCount]=src_account+"/"+dest_account+"/"+amount+"/"+this.accounts[src_account_index].usersIDcount;
		
		this.transactionsCount++;
		this.transactions[this.transactionsCount-1]="[" + this.transactionsCount + "] " + amount + " to " + dest_account + " -> pending";
		
		this.accounts[src_account_index].transactionsNum++;
		this.accounts[src_account_index].transactions[this.accounts[src_account_index].transactionsNum-1]=
				"[" + this.accounts[src_account_index].transactionsNum + "] " + amount + " to " + dest_account + " -> pending";
		return;
		
		
		
		

			
			/*for(int i=0;i<this.accounts[src_account_index].accountUserIDs.length;i++) {
				System.out.println("Do you agree with this transaction?" + this.accounts[src_account_index].accountUserIDs[i] + " :");
				if(permissionTransaction()==1) {
					continue;
				}else {
					
					this.transactionsCount++;
					System.out.println("[" + this.transactionsCount+ "] " + amount + " to " + dest_account + " -> rejected");
					this.transactions[this.transactionsCount-1]="[" + this.transactionsCount + "] " + amount + " to " + dest_account + " -> rejected";
					
					this.accounts[src_account_index].transactionsNum++;
					this.accounts[src_account_index].transactions[this.accounts[src_account_index].transactionsNum-1]=
							"[" + this.accounts[src_account_index].transactionsNum + "] " + amount + " to " + dest_account + " -> rejected";
					return;
				}
			}
			
						}*/
			
	}
	
	public void transactionsChecker(int transaction_id) {
		
	
		String[] x=this.transactionAccountIDs[transaction_id].split("/");
		int src_account_index=Integer.parseInt(x[0]);
		int dest_account_index=Integer.parseInt(x[1]);
		int amount=Integer.parseInt(x[2]);
		int transactionOwners=Integer.parseInt(x[3]);
		
		for(int i=0;i<transactionOwners;i++) {
			//System.out.println("     "+this.transactionsStatus[transaction_id][i]);
			if(this.transactionsStatus[transaction_id][i]==1) {
				continue;
			}else if(this.transactionsStatus[transaction_id][i]==0) {
				//System.out.println("[" + transaction_id + "] " + amount + " to " + x[1] + " -> rejected");
				this.transactions[transaction_id]="[" + transaction_id + "] " + amount + " to " + x[1] + " -> rejected";
				

				this.accounts[src_account_index].transactions[transaction_id]="[" + transaction_id + "] " + amount + " to " + x[1] + " -> rejected";
				return;
			}else if(this.transactionsStatus[transaction_id][i]==-1) {
				
				return;
			}
		}
		
		if(this.accounts[src_account_index].getAccountBankroll()<amount) {
			//System.out.println("[" + transaction_id + "] " + amount + " to " + x[1] + " -> rejected");
			this.transactions[transaction_id]="[" + transaction_id + "] " + amount + " to " + x[1] + " -> rejected";
			

			this.accounts[src_account_index].transactions[transaction_id]="[" + (transaction_id+1) + "] " + amount + " to " + x[1] + " -> rejected";
			return;
		}else {
			this.accounts[src_account_index].reduceBankroll(amount);
			this.accounts[dest_account_index].increaseBankroll(amount);
			
			//System.out.println("[" + transaction_id + "] " + amount + " to " + x[1] + " -> accepted");
			this.transactions[transaction_id]="[" + transaction_id + "] " + amount + " to " + x[1] + " -> accepted";
			

			this.accounts[src_account_index].transactions[transaction_id]="[" + (transaction_id+1) + "] " + amount + " to " + x[1] + " -> accepted";
			return;

	}
}
	
	
	public void approveTransaction(int transaction_id,int owner_id) {
		String[] x=this.transactionAccountIDs[transaction_id].split("/");
		int src_account_index=Integer.parseInt(x[0]);
	
		for(int i=0;i<this.accounts[src_account_index].usersIDcount;i++) {
			if(this.transactionsStatus[transaction_id][i]==-1) {
				this.transactionsStatus[transaction_id][i]=1;	
				break;
			}	
		}
		transactionsChecker(transaction_id);
	}
	
	public void declineTransaction(int transaction_id ,int owner_id) {
		String[] x=this.transactionAccountIDs[transaction_id].split("/");
		int src_account_index=Integer.parseInt(x[0]);
	
		
		for(int i=0;i<this.accounts[src_account_index].usersIDcount;i++) {
			if(this.transactionsStatus[transaction_id][i]==-1) {
				this.transactionsStatus[transaction_id][i]=0;	
				break;
			}
				
		}
		transactionsChecker(transaction_id);
	}
	
	public void showAccount(int account_id) {
		for(int i=0;i<this.accountCounts;i++) {
			if(account_id==this.accounts[i].getAccountID()) {
				System.out.println("Balance : " + this.accounts[i].getAccountBankroll());
				System.out.print("Owners : ");
				for(int j=0;j<this.accounts[i].usersIDcount;j++) {
				//	System.out.println(this.accounts[i].usersIDcount);
					for(int k=0;k<this.userCounts;k++) {
						if(j!=this.accounts[i].usersIDcount-1&&this.accounts[i].accountUserIDs[j]==this.users[k].userCode) {
							System.out.print(this.users[k].userName + " " + this.users[k].userSurname+" , ");
						}else if(j==this.accounts[i].usersIDcount-1&&this.accounts[i].accountUserIDs[j]==this.users[k].userCode){
							//System.out.println("helllllll");
							
							System.out.println(this.users[k].userName + " " + this.users[k].userSurname);
						}
					}
					
				}
				System.out.println("Transactions:");
				for(int l=0;l<this.accounts[i].transactionsNum;l++) {
					System.out.println(this.accounts[i].transactions[l]);
				}
				
			}
		}
	}
	
}
