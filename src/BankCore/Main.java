
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		BankCore b=new BankCore();
		String order=null;
		
		do {
			order=scanner.next();
			
			if(order.equalsIgnoreCase("create_customer")) {
				String name=scanner.next();
				String surname=scanner.next();
				b.addCustomer(name, surname);
			
			}else if(order.equalsIgnoreCase("create_account")) {
				String x=scanner.nextLine();
				String[] z=x.split(" ");
				//System.out.println(z.length);
				
				int[] a=new int[z.length-1];
				//System.out.println("HDGJDJSHDG");
				for(int i=0;i<a.length;i++) {
					a[i]=Integer.parseInt(z[i+1]);
				}
//				for(int i=0;i<a.length;i++) {
//					System.out.println(a[i]);
//				}
				//System.out.println("HDGJDJSHDG");
				b.creatAccount(a);
				
			}else if(order.equalsIgnoreCase("add_owner")) {
				int account_id=scanner.nextInt();
				int owner_id=scanner.nextInt();
				b.addOwner(account_id, owner_id);
				
			}else if(order.equalsIgnoreCase("book_transaction")) {
				int src_account_id=scanner.nextInt();
				int dest_account_id=scanner.nextInt();
				int amount=scanner.nextInt();
				b.bookTransaction(src_account_id, dest_account_id, amount);
				
			}else if(order.equalsIgnoreCase("approve_transaction")) {
				int transaction_id=scanner.nextInt();
				int owner_id=scanner.nextInt();
				b.approveTransaction(transaction_id, owner_id);
				
			}else if(order.equalsIgnoreCase("decline_transaction")) {
				int transaction_id=scanner.nextInt();
				int owner_id=scanner.nextInt();
				b.declineTransaction(transaction_id, owner_id);
				
			}else if(order.equalsIgnoreCase("show_account")) {
				int account_id=scanner.nextInt();
				b.showAccount(account_id);
			}
			
		}while(scanner.hasNext());

	}

}
