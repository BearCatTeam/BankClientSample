import java.util.Scanner;
import java.io.*;
import java.net.*;
public class BankClient {
	public static void main(String[] args)
	{
		boolean ENDED = false;
		final int BANK_PORT = 8888;
		final String BANK_HOST = "localhost";
		Scanner scanner = new Scanner(System.in);
		Socket s;
		InputStream instream;
		OutputStream outstream;
		try {
			s = new Socket(BANK_HOST,BANK_PORT);
			instream = s.getInputStream();
			outstream = s.getOutputStream();
			Scanner in = new Scanner(instream);
			PrintWriter out = new PrintWriter(outstream);
			while (ENDED==false)
			{
				System.out.println("\nChoose function: \n");
				System.out.println("(1) SHOW ACCOUNT INFO        : ");
				System.out.println("(2) CREATE ACCOUNT           : ");
				System.out.println("(3) DEPOSIT TO ACCOUNT       : ");
				System.out.println("(4) WITHDRAW FROM ACCOUNT    : ");
				System.out.println("(5) TRANSFER MONEY           : ");
				System.out.println("(6) CLOSE                    : ");
				System.out.println("(7) EMERGENCY SHUTDOWN SERVER: ");
				int function = scanner.nextInt(); scanner.nextLine();
				String src, dest;
				Double amount;
				switch(function)
				{
				case 1:
					System.out.println("Enter Account Number: ");
					src = scanner.nextLine();
					out.println("SHOW "+src);
					out.flush();
					while(!in.hasNext())
					{
						System.out.println("NO DATA");
					} 
					if (in.hasNext())
					{
						String status = in.nextLine();
						if (status.equals("success"))
						{
							System.out.println("Remaining balance: "+in.nextLine());
						} else {
							System.out.println("Account does not exist");
						}
					}
					break;
				case 2:
					System.out.println("Enter Account Number: ");
					src = scanner.nextLine();
					System.out.println("Enter Initial Balance: ");
					amount = scanner.nextDouble(); scanner.nextLine();
					out.println("CREATE "+src+" "+amount.toString());
					out.flush();
					while(!in.hasNext())
					{
						System.out.println("NO DATA");
					} 
					if (in.hasNext())
					{
						String status = in.nextLine();
						if (status.equals("success"))
						{
							System.out.println("Create account "+src+" has successed");
						} else {
							System.out.println("ERROR! Account has existed!");
						}
					}
					break;
				case 3:
					System.out.println("Enter Account Number: ");
					src = scanner.nextLine();
					System.out.println("Enter Depositing Amount: ");
					amount = scanner.nextDouble(); scanner.nextLine();
					out.println("DEPOSIT "+src+" "+amount.toString());
					out.flush();
					while(!in.hasNext())
					{
						System.out.println("NO DATA");
					} 
					if (in.hasNext())
					{
						String status = in.nextLine();
						if (status.equals("success"))
						{
							System.out.println("Deposit to account "+src+" has successed");
						} else {
							System.out.println("ERROR! Account does not existed!");
						}
					}
					break;
				case 4:
					System.out.println("Enter Account Number: ");
					src = scanner.nextLine();
					System.out.println("Enter Withdraw Amount: ");
					amount = scanner.nextDouble(); scanner.nextLine();
					out.println("WITHDRAW "+src+" "+amount.toString());
					out.flush();
					while(!in.hasNext())
					{
						System.out.println("NO DATA");
					} 
					if (in.hasNext())
					{
						String status = in.nextLine();
						if (status.equals("success"))
						{
							System.out.println("Withdraw from account "+src+" has successed");
						} else {
							System.out.println("ERROR! Account dose not existed or balance is not enough!");
						}
					}
					break;
				case 5:
					System.out.println("Enter Source Account Number: ");
					src = scanner.nextLine();
					System.out.println("Enter Destination Account Number: ");
					dest = scanner.nextLine();
					System.out.println("Enter Transfer Amount: ");
					amount = scanner.nextDouble(); scanner.nextLine();
					out.println("TRANSFER "+src+" "+dest+" "+amount.toString());
					out.flush();
					while(!in.hasNext())
					{
						System.out.println("NO DATA");
					} 
					if (in.hasNext())
					{
						String status = in.nextLine();
						if (status.equals("success"))
						{
							System.out.println("Transfer from account "+src+" to account "+dest+" has successed");
						} else {
							System.out.println("ERROR! Accounts do not existed or source balance is not enough!");
						}
					}
					break;
				case 6:
					System.out.println("Application Closing ...... ");
					out.println("CLOSE");
					out.flush();
					System.out.println("CLOSE command has been sent");
					s.close();
					in.close();
					out.close();
					instream.close();
					outstream.close();
					scanner.close();
					ENDED=true;
					System.out.println("Application has been CLOSED");
					break;
				case 7:
					System.out.println("Shuting down whole server ..... ");
					out.println("STOP");
					out.flush();
					System.out.println("STOP command has been sent");
					s.close();
					in.close();
					out.close();
					instream.close();
					outstream.close();
					scanner.close();
					ENDED=true;
					System.out.println("CLIENT has been CLOSED");
					System.out.println("SERVER has been CLOSED");
					break;
				}
			}
		} catch (IOException e)
		{
			System.out.println("CLIENT ERROR: \n"+e.getMessage());
		}
	}
}
