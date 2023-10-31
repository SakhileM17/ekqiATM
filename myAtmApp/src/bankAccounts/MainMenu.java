package bankAccounts;
import java.text.DecimalFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
//
import java.util.Scanner;
/*
 * Basic ATM Application : EKQiFinancial Services ATM
 * 
 * 1. 	User can log in and out of application
 * 		To log into Application user must enter cheque account number , with the password being the savings account number from the chequeAccount and savingsAccount
 * 		instances created in public class MainMenu.
 * 2.	User can navigate between Cheque and Savings Account.
 * 3. 	User can do transactions on both accounts such transfer funds between accounts, make payments, purchase (airtime, play lotto, purchase elelctricty)
 *    	, send e-wallets and view transaction history of both the savings account and savings account.
 * 
 * Author - Sakhile Mkhalele, September 2023. 
 * 
 * */
public class MainMenu {
	/*
	 * Cheque Account and SavingsAccount objects(instances) 
	 * */
	// ChequeAccount(String userName, String userSname, String userPhone,String accountType, int chequeAccountNo, double chequeBalance
	ChequeAccount cheque = new ChequeAccount("Sakhile", "Mkhalele", "0834181424", "Cheque", 1995, 500);
	// SavingsAccount(String userName, String userSname, String userPhone,String accountType, int chequeAccountNo, double chequeBalance
	SavingsAccount savings = new SavingsAccount("Sakhile", "Mkhalele", "0834181424", "Savings", 1030, 800);
	
	//
	CashSend cashSend = new CashSend(null, 0);
	//
	Scanner grapes = new Scanner(System.in);
	
	//
	int choice; 
	//
	
	public void welcome() {
		System.out.println("\nWelcome to EKQiFinancial Services ATM");
		System.out.println("--------------------------------------");
		//
	}
	/*
	 * Login method used for logging into the application
	 * user only has 3 attempts to try log into application , if passowrd is wrong on 4th attempt user cant access application
	 * */ 
	public void login() {
		/*
		 * New object of the Beneficiaries class to the arraylist, the objects used to create default beneficiairies that user can delete within application
		 *
		 **/
		newBeneficiary.add(new Beneficiaries("Sakhile", 10111, "DiscoveryBank")); // beneficiary is Sakhile
		newBeneficiary.add(new Beneficiaries("Naledi", 20222, "First National Bank")); // beneficiary is Naledi
		// Objects created for default cashSend beneficiaires
		newCashSend.add(new CashSend("Naledi", 82101911));
		newCashSend.add(new CashSend("Mihle", 8101922));
		//
		welcome();
		//
		System.out.println("\nWelcome, please fill in your account details.");
		//
		int loginAttempts = 3;
		// do while loop for users login credentials

		do {
			System.out.println("Login Attempts remaining : " + loginAttempts);
			// if statement that checks how attempts has the customer tried
			if (loginAttempts == 1) {
				//
				System.out.println("Last Login Attempt or else account will be blocked");
				//
			} else if (loginAttempts == 0) {
				grapes.close();
				System.err.println("\nAccount Blocked, please visit your nearest EKQi Financail services branch. ");
				break;
			}
			// exception used for if the user enters a string value instead of an integer value
			try {
				//
				System.out.print("\nEnter your Account Number \t:");
				int userAccountNumber = grapes.nextInt();
				//
				System.out.print("Enter your Account Password \t:");
				int userAccountPassword = grapes.nextInt();
				//If statement used to verify login credentials
				//
				if (userAccountNumber == cheque.getChequeAccountNo() && userAccountPassword == savings.getSavingsAccountNo()) {
					// if user inputs correct login credentials, user will be taken to application accounts menuu
					System.out.println("\nLogin Successful");
					accountsMenu();
					break;
					
				} else {
					System.err.println("\nInvalid Login credentials : Account Number or Account Password incorrect ");// if the user inputs the wrong credenatials 
				}
				
			} catch (Exception e) {
				//
				System.err.println("\nMessage : " + e + " . Invalid entery, please integer values only. " );
				grapes.nextLine();
				//
				welcome();
			}
			//
			loginAttempts--; // Login attempts decrement everytime user gets login credentials wrong
			
		} while (loginAttempts >= 0);
		//
		grapes.close();
	}
	/*
	 * Accounts menu method, this method gives user an option to access  cheque account or savings account
	 * */
	public void accountsMenu(){
		//
		try {
		do {
			//
			welcome();
			//
			System.out.println("\nAccount Holder : " + cheque.getUserName());
			//
			System.out.println("\nWhich Bank account do you want to access :");
			System.out.println("\nEnter 1. Cheque Account\t\t: " + cheque.getChequeAccountNo());
			System.out.println("Enter 2. Savings Account\t: " + savings.getSavingsAccountNo());
			System.out.println("Enter 3. Cancel Transactions");
			//
			System.out.print("\nEnter your option :");
			choice = grapes.nextInt();
			//
			switch (choice) {
			// Case 1 : calls the cheque account method 
			case 1:
				//
				chequeAccountMenu();
				//
				break;
			// Case : Calls the savings account Method
			case 2: 
				savingsAccountMenu();
				break;
			case 3:
				System.out.println("Thank you for using EKQi Financial Services!\n");
				System.exit(choice);
				accountsMenu();
				// add login method
				break;
			//
			default:
				//
				System.err.println("\nOopps! You have chosen the wrong option, please choose correct option");
				accountsMenu();
				break;
			}
		
		} while (choice < 4);
		} catch (Exception e) {
			//
			System.err.println("\nMessage " + e + " please enter an integer value. ");
			grapes.nextLine();
			//
			accountsMenu();
		}
	
	}
	/*
	 * Cheque Account , the codes below are for the cheque account section
	 * 
	 * */
	int amount;
	int randomID = 0;  // this line of code creates a random transactionID
	//
	TransactionsHistory chequeTransactionsHistory = new ChequeTransactionsHistory(null, null, amount, null, null);
	Beneficiaries beneficiaries = new Beneficiaries(null, 0, null);
	// ArrayLists
	ArrayList<Beneficiaries> newBeneficiary = new ArrayList<>();
	ArrayList<ChequeTransactionsHistory> newChequeTransactions = new ArrayList<>();
	ArrayList<CashSend> newCashSend = new ArrayList<>();
	ArrayList<ChequeAccount> newChequeUser = new ArrayList<>();
	ArrayList<SavingsAccount> newSavingsUser = new ArrayList<>();
	//
	DecimalFormat moneyFormat = new DecimalFormat("'R'#,###,###,##0.00");
	//
	public void chequeAccountHeader() { //Cheque Account Branding
		//
		LocalDate transactionDate = LocalDate.now();//this line of code used get the current date
		LocalTime transactionTime = LocalTime.now();// this line of code is used to get current time
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm:ss");
		String formattedTime = transactionTime.format(timeFormat);
		//
		System.out.println("\nCheque Account Number : " + cheque.chequeAccountNo + " " + " \t\t\t\t\tBalance : " + moneyFormat.format(cheque.getChequeBalance()));
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("Current Date:" + transactionDate + "                  \t\t\tCurrent Time:" + formattedTime);
		//
	}
	// method used to ask user if they would like to do another transaction
	public void chequeContinueTransaction() {
		//
		System.out.println("\nWould you like to do another transaction : ");
		System.out.println("1. Yes");
		System.out.println("2. No.");
		//
		try {
			//
			System.out.print("\nEnter your option :");
			choice = grapes.nextInt();
			//
		} catch (Exception e) {
			//
			System.err.println("\nMessage : " + e + " Ooops invalid selection, please enter an integer value. ");
			grapes.nextLine();
			//
			chequeContinueTransaction();
			}
		switch(choice) {
		// case 1 : Takes user back to the main menu
		case 1:
			chequeAccountMenu();
			break;
			// case 2 : Takes user to login page
		case 2:
			//
			System.out.println("\nThank you for using EQKi Financial Services.\n");
			System.exit(2);;// sends user back to log in page
			break;
		//
		default:
			
			System.err.println("Oopps! You have chosen the wrong option, please choose correct option");
			break;
			}
		grapes.close();
		}
	/*
	 * Cheque Account Main Menu has all the available transactions for cheque account.
	 */
	public void chequeAccountMenu() { 
		
		do { 
			//
			chequeAccountHeader();// cheque Account Branding
			//Cheque Account main menu
			System.out.println("\nCheque Account");
			//
			System.out.println("\n1. Transfer Funds.");
			System.out.println("2. Pay Beneficiaires.");
			System.out.println("3. CashSend.");
			System.out.println("4. Purchase.");
			System.out.println("5. Transaction History.");
			System.out.println("6. Back to Accounts Menu");
			System.out.println("7. Exit Application.");
			try {
				//
				System.out.print("\nEnter your option :");
				choice = grapes.nextInt();
				//
			} catch (Exception e) {
				//
				System.err.println("\nMessage : " + e + "Ooops invalid selection, please enter an integer value. ");
				grapes.nextLine();
				//
				chequeAccountMenu();
				//
			}
			//Switch case used for cheque account main menu
			switch (choice) {
			// Case 1 : Transfers Fund between two accounts form cheque to Savings
			case 1:
				chequeTransfers();
				break;
			// Case 2 : Pay beneficiaries, for user to be able to pay beneficiaries
			case 2:
				//
				chequeBeneficiaries();
				break;
			// case 3: Option 3 is for Cashsend transactions
			case 3:
				chequeCashSend();
				break;
			// case 4 : Option 4 is for the user to purchase air time, lottery and electricity.
			case 4:
				chequePurchase();
				break;
			// case 5 : Option 5 user can check transaction history
			case 5:
				chequeTranstactionHistory();
				break;
			// case 6 : Option 6 takes user back to accounts page
			case 6:
				accountsMenu();
				break;
			// case : Option 7 takes user back to login page.
			case 7:
				login();
				break;
				//
			default: 
				System.err.println("Ooops, please choose correct option");
				chequeAccountMenu();
				break;
			}
			
		} while (choice < 8); 
		//
		grapes.close();
	}
	/*
	 * Transfers
	 * */
	public void chequeTransfers() {
		//
		do {
			//
			chequeAccountHeader();
			//Displays to user maximum amount that they can transfer
			System.out.println("\n\tMaximum Transferable Amount\t: " + moneyFormat.format(cheque.getChequeBalance()));
			
			// Exception handling for if user inputs a String instead of a Integer
			try {
				System.out.print("\nEnter amount that you want to transfer\t:" );
				amount = grapes.nextInt();
				
			} catch (Exception e) {
				//
				System.err.println("\nMessage : " + e + "Ooops invalid selection, please enter an integer value. ");
				grapes.nextLine();
				//
				chequeTransfers();
				//
			}
			
			//
			if (cheque.getChequeBalance() - amount >= 0) {// this line of code checks if the user is not trying to transfer larger than available amount
				// code below as user to confrim and verify the payment
				System.out.println("\nPlease confirm that you would like to Transfer\t: ");
				System.out.println("\n\tTransfer from Cheque Account\t: " + cheque.getChequeAccountNo());
				System.out.println("\tTransfer to Savings Account\t: " + savings.getSavingsAccountNo() );
				System.out.println("\tAmount\t\t\t\t: " + moneyFormat.format(amount));
				//
				System.out.println("\nEnter 1 to confrim transfer");
				System.out.println("Enter 2 to cancel transfer");
				//
				try {
					//
					System.out.print("\nEnter your option :");
					choice = grapes.nextInt();
					//
				} catch (Exception e) {
					//
					System.err.println("\nMessage : " + e + " Ooops invalid selection, please enter an integer value. ");
					grapes.nextLine();
					//
					chequeTransfers();
					//
				}
				switch (choice) {
				// Case 1 : User confirms that the would like to proceed with transferring funds to the savings account
				case 1:
					// code below credits the money out of the Cheque Account
					double chequeAccountBalance = cheque.getChequeBalance() - amount;// chequeBalance is used to new balance after user inputs amount they wish to transfer
					cheque.setChequeBalance(chequeAccountBalance);; // sets the new cheque account balance
					//
					System.out.println("\nTransaction successfull");//tells user know that the transaction was successful
					System.out.println("\nNew Cheque Account Balance : " + moneyFormat.format(cheque.getChequeBalance())); // diplays to user the new bank balance
					// code below debits the savings account
					double savingsAccountBalance = savings.getSavingsBalance() + amount;
					savings.setSavingsBalance(savingsAccountBalance);
					/*
					 * Transaction History
					 * */
					for (int i = 0; i < 1;i++) {
						//
						LocalDate localtransactionDate = LocalDate.now();//this line of code used get the current date
						String transactionDate = localtransactionDate.toString();//This line of code is used to convert the date to a string
						chequeTransactionsHistory.setTransactionDate(transactionDate);;//this line of code is used to set date to the array
						//
						String transactionDescription = "CashSend - Once of payement";// this line of code is the name of the transaction
						chequeTransactionsHistory.setTransactionDescription(transactionDescription);; // this line of sets the transaction name 
						//
						double transactionAmount = amount;//this line of code is the monetary value of the transaction, its set at R5 because lottery is a fixed amount
						chequeTransactionsHistory.setTransactionAmount(transactionAmount);;
						//
						String transactionID = "TRA00" + randomID + "CH";
						chequeTransactionsHistory.setTransactionID(transactionID);
						randomID++;
						//
						String transactionType = "Debit";
						chequeTransactionsHistory.setTransactionType(transactionType);
						// line of code below creates a new array
						newChequeTransactions.add(new ChequeTransactionsHistory(transactionDate, transactionDescription, transactionAmount, transactionID,transactionType)); 
						//
						for(ChequeTransactionsHistory chequeTrans : newChequeTransactions) {
							//
							System.out.println("\nTransaction Details : ");
							//
							System.out.println("\n\tTransaction ID\t\t: " + newChequeTransactions.get(i).getTransactionID());
							System.out.println("\tTransaction Date\t: " + newChequeTransactions.get(i).getTransactionDate());
							System.out.println("\tTransaction Name\t: " + newChequeTransactions.get(i).getTransactionDescription());
							System.out.println("\tDR or CR Bank\t\t: " + newChequeTransactions.get(i).getTransactionType());
							System.out.println("\tBeneficiary Account\t: " + moneyFormat.format(newChequeTransactions.get(i).getTransactionAmount()));
							//
							break;
							}
					}
					// Transaction history, this transaction be represented as a credit in the savings account
					for (int i = 0; i < 1;i++) {
						//
						LocalDate localtransactionDate = LocalDate.now();//this line of code used get the current date
						String transactionDate = localtransactionDate.toString();//This line of code is used to convert the date to a string
						savingsTransactionsHistory.setTransactionDate(transactionDate);;//this line of code is used to set date to the array
						//
						String transactionDescription = "CashSend - Once of payement";// this line of code is the name of the transaction
						savingsTransactionsHistory.setTransactionDescription(transactionDescription);; // this line of sets the transaction name 
						//
						double transactionAmount = amount;//this line of code is the monetary value of the transaction, its set at R5 because lottery is a fixed amount
						savingsTransactionsHistory.setTransactionAmount(transactionAmount);;
						//
						String transactionID = "TRA00" + randomID + "CH";
						savingsTransactionsHistory.setTransactionID(transactionID);
						randomID++;
						//
						String transactionType = "Credit";
						savingsTransactionsHistory.setTransactionType(transactionType);
						// line of code below creates a new array
						newSavingsTransactions.add(new SavingsTransactionsHistory(transactionDate, transactionDescription, transactionAmount, transactionID,transactionType)); 
						//
					}
					//
					chequeContinueTransaction();//This method asks the user if the want to continue with transactions or to close the app
					//
					break;
				// 	Case 2 : Case 2 (Option 2), takes the use back to the accounts menu.
				case 2:
					chequeAccountMenu();
					break;
				}
			// the line of code below is called if the amount that the user would like to transfer is less than the available balance amount
			} else if (cheque.getChequeBalance() -  amount < 0) { 
				//
				System.err.println("\nTransaction unsucessful : Insufficent funds");
				chequeTransfers();
				//
			}
		
		} while (choice < 5);
	}
	/*
	 * Beneficiaries
	 * 
	 * */
	
	// cheque beneficiaries transactions
	public void chequeBeneficiaries() {
		//
		do {
			//
			chequeAccountHeader();
			// Pay beneficiaries sub menu
			System.out.println("\nPay Beneficiaires");
			System.out.println("\n1. Once off Payment.");
			System.out.println("2. Pay exisiting benficiary.");
			System.out.println("3. Create new benefciary.");
			System.out.println("4. Remove a Beneficiary.");
			System.out.println("5. View all Beneficiaries");
			System.out.println("6. Back");
			//
			try {
				//
				System.out.print("\nEnter your option :");
				choice = grapes.nextInt();
				//
			} catch (Exception e) {
				//
				System.err.println("\nMessage : " + e + " Ooops invalid selection, please enter an integer value. ");
				grapes.nextLine();
				//
				chequeBeneficiaries();
				//
			}
			switch (choice ) {
			// case 1 : Once of Payment, for user to be able to make a once off payment
			case 1:
				onceOffBeneficiaryPayment();
				break;
			// case 2 : Pay existing beneficiar
			case 2:
				//
				payExistingBeneficiary();
				break;
			// case 3 : Create a new Beneficiary
			case 3:
				createNewBeneficiary();
				//
				break;
			// removes beneficiary
			case 4:
				removeBeneficiary();
				//
				break;
			// displays all the current beneficiaries
			case 5:
				chequeBeneficiaryDisplay();
				break;
			// takes user back to cheque account menu
			case 6:
				accountsMenu();
				break;
			default:
				System.err.println("Ooops! Please select option by entering the correct number,");
				chequeBeneficiaries();
				break;
			}
			
		} while(choice < 7);
	}
	// once off beneficiary payment
	public void onceOffBeneficiaryPayment() {
		//
		chequeAccountHeader();
		//
		System.out.println("\nOnce Off Payment");
		//Displays to user maximum amount that they can transfer
		System.out.println("\nPlease enter recipient account details : ");
		System.out.print("\nReccipient Account Number : ");
		long recipientAccountNumber = grapes.nextLong();
		//
		System.out.print("\nPlease enter recipient bank :");
		String recipientBank = grapes.next();
		//
		System.out.println("\n\tMaximum Amount payabale\t:" + moneyFormat.format(cheque.getChequeBalance()));
		System.out.print("\nEnter amount that you want to make once off payment :" );
		//
		try {
			System.out.print("\nEnter amount that you want to transfer\t:" );
			amount = grapes.nextInt();
			
		} catch (Exception e) {
			//
			System.err.println("\nMessage : " + e + "Ooops invalid selection, please enter an integer value. ");
			grapes.nextLine();
			//
			chequeBeneficiaries();
			//
		}
		//
		if (cheque.getChequeBalance() - amount >= 0) {// this line of code checks if the user is not trying to transfer larger than available amount
			// code below as user to confrim and verify the payment
			System.out.println("\nPlease confirm that you would like to Pay Beneficiary :");
			System.out.println("\n\tPay from Cheque Account\t\t: " + cheque.getChequeAccountNo());
			System.out.println("\tRecipient Account Number1\t: " + recipientAccountNumber);
			System.out.println("\tAmount\t\t\t\t: " + moneyFormat.format(amount));
			//
			System.out.println("\nEnter 1 to confrim payment");
			System.out.println("Enter 2 to cancel");
			//
			try {
				//
				System.out.print("\nEnter your option :");
				choice = grapes.nextInt();
				//
			} catch (InputMismatchException e) {
				//
				System.err.println("\nMessage : " + e + "Ooops invalid selection, please enter an integer value. ");
				grapes.nextLine();
				//
				chequeBeneficiaries();
				//
			}
			switch (choice) {
			// Case 1 : User confirms that the would like to proceed with transferring funds to the savings account
			case 1:
				// code below credits the money out of the Cheque Account
				double chequeAccountBalance = cheque.getChequeBalance() - amount;// chequeBalance is used to new balance after user inputs amount they wish to transfer
				cheque.setChequeBalance(chequeAccountBalance);; // sets the new cheque account balance
				//
				System.err.println("\nTransaction successfull");//tells user know that the transaction was successful
				//
				System.out.println("\nNew Cheque Account Balance : " + moneyFormat.format(cheque.getChequeBalance())); // diplays to user the new bank balance
				/*
				 * Transaction History
				 * */
				for (int i = 0; i < 1;i++) {
					//
					LocalDate localtransactionDate = LocalDate.now();//this line of code used get the current date
					String transactionDate = localtransactionDate.toString();//This line of code is used to convert the date to a string
					chequeTransactionsHistory.setTransactionDate(transactionDate);;//this line of code is used to set date to the array
					//
					String transactionDescription = "Beneficiary Payment - Once of payement";// this line of code is the name of the transaction
					chequeTransactionsHistory.setTransactionDescription(transactionDescription);; // this line of sets the transaction name 
					//
					double transactionAmount = amount;//this line of code is the monetary value of the transaction, its set at R5 because lottery is a fixed amount
					chequeTransactionsHistory.setTransactionAmount(transactionAmount);;
					//
					String transactionID = "BEN00" + randomID + "CH";
					chequeTransactionsHistory.setTransactionID(transactionID);
					randomID++;
					//
					String transactionType = "Debit";
					chequeTransactionsHistory.setTransactionType(transactionType);
					// line of code below creates a new array
					newChequeTransactions.add(new ChequeTransactionsHistory(transactionDate, transactionDescription, transactionAmount, transactionID,transactionType)); 
					//
					for(ChequeTransactionsHistory chequeTrans : newChequeTransactions) {
						//
						System.out.println("\nTransaction Details : ");
						//
						System.out.println("\n\tTransaction ID\t\t: " +chequeTrans.getTransactionID());
						System.out.println("\tTransaction Date\t: " + newChequeTransactions.get(i).getTransactionDate());
						System.out.println("\tTransaction Name\t: " + newChequeTransactions.get(i).getTransactionDescription());
						System.out.println("\tDR or CR Bank\t\t: " + newChequeTransactions.get(i).getTransactionType());
						System.out.println("\tBeneficiary Account\t: " + moneyFormat.format(newChequeTransactions.get(i).getTransactionAmount()));
						//
						break;
						}
				}
				//
				chequeContinueTransaction();//This method asks the user if the want to continue with transactions or to close the app
				//
				break;
			// 	Case 2 : Case 2 (Option 2), takes the use back to the accounts menu.
			case 2:
				chequeAccountMenu();
				break;
			}
		// the line of code below is called if the amount that the user would like to transfer is less than the available balance amount
		} else if (cheque.getChequeBalance() -  amount < 0) { 
			//
			System.err.println("\nTransaction unsucessfull : Insufficent funds");
			chequeAccountMenu();
		}
	}
	// Method below is used to pay existing beneficiaries
	public void payExistingBeneficiary() {
		//
		chequeAccountHeader();
		//
		System.out.println("\nPay Exisiting Beneficiary : ");//Header
		//
		System.out.println("\nBeneficiary List :");
		//code below is to represent the current beneficiary list
		for (int i = 0; i < newBeneficiary.size(); i++) { // for each loop to display the current beneficiary list
			//
			System.out.println("\nUnique Beneficiary code :" + i);
			System.out.println("Beneficiary Name\t\t: " + newBeneficiary.get(i).getBeneficiaryName());
			System.out.println("Beneficiary Account Number\t: " + newBeneficiary.get(i).getBeneficiaryAccountNumber());
			System.out.println("Beneficiary Bank\t\t: " + newBeneficiary.get(i).getBeneficiaryBank());
			//
			System.out.println("");	
			}		
		//Line of code below is a scanner for the user to choose the beneficiary that the would like to delete using the unique beneficiary code
		System.out.print("\nTo choose a beneficiary, enter beneficiary unqiue code: ");
		int beneficiaryCode = grapes.nextInt();
		// if the user enter 
		if (beneficiaryCode >= 0 && beneficiaryCode < newBeneficiary.size() ) {
			//The code below retrieves all the selected beneficiaries information
			newBeneficiary.get(beneficiaryCode);
			System.out.println("\n\tYou have choosen to pay\t\t: " + newBeneficiary.get(beneficiaryCode).getBeneficiaryName());
			System.out.println("\tBeneficiary Account\t\t: " + newBeneficiary.get(beneficiaryCode).getBeneficiaryAccountNumber());
			System.out.println("\tBeneficiary Bank\t\t: " + newBeneficiary.get(beneficiaryCode).getBeneficiaryBank());
			//
			try {
				//
				System.out.print("\nPlease enter amount that you would like to pay beneficiaty : ");
				int amount = grapes.nextInt();
				//
			} catch (Exception e) {
				//
				System.err.println("\nMessages : " + e + "Ooops invalid selection, please enter an integer value. ");
				grapes.nextLine();
				//
				payExistingBeneficiary();
				//
			}
			//
			if (cheque.getChequeBalance() - amount >= 0) {// this line of code checks if the user is not trying to transfer larger than available amount
				// code below as user to confrim and verify the payment
				chequeAccountHeader();
				//
				System.out.println("\nPlease confirm that you would like to Pay beneficiary:");
				System.out.println("\n\tYou have choosen to pay beneficiary\t: " + newBeneficiary.get(beneficiaryCode).getBeneficiaryName());
				System.out.println("\tBeneficiary Account Number\t\t: " + newBeneficiary.get(beneficiaryCode).getBeneficiaryAccountNumber());
				System.out.println("\tFrom Cheque Account\t\t\t: " + cheque.getChequeAccountNo());
				System.out.println("\tAmount\t\t\t\t\t: " + moneyFormat.format(amount));
				//
				System.out.println("\nEnter 1 to confrim to pay beneficiary");
				System.out.println("Enter 2 to cancel");
				//
				try {
					//
					System.out.print("\nEnter your option :");
					choice = grapes.nextInt();
					//
				} catch (Exception e) {
					//
					System.err.println("\nMessages : " + e + "Ooops invalid selection, please enter an integer value. ");
					grapes.nextLine();
					//
					payExistingBeneficiary();
					//
				}
				switch (choice) {
				// Case 1 : User confirms that the would like to proceed with transferring funds to the savings account
				case 1:
					//
					chequeAccountHeader();
					//
					// code below credits the money out of the Cheque Account
					double chequeAccountBalance = cheque.getChequeBalance() - amount;// chequeBalance is used to new balance after user inputs amount they wish to transfer
					cheque.setChequeBalance(chequeAccountBalance);;; // sets the new cheque account balance
					//
					System.out.println("\nTransaction successfull.");//tells user know that the transaction was successful
					System.out.println("New Cheque Account Balance : " + moneyFormat.format(cheque.getChequeBalance())); // Displays to user the new bank balance
					//
					/*
					 * Transaction History
					 * */
					for (int i = 0; i < 1;i++) {
						//
						LocalDate localtransactionDate = LocalDate.now();//this line of code used get the current date
						String transactionDate = localtransactionDate.toString();//This line of code is used to convert the date to a string
						chequeTransactionsHistory.setTransactionDate(transactionDate);;//this line of code is used to set date to the array
						//
						String transactionDescription = "CashSend - Once of payement";// this line of code is the name of the transaction
						chequeTransactionsHistory.setTransactionDescription(transactionDescription);; // this line of sets the transaction name 
						//
						double transactionAmount = amount;//this line of code is the monetary value of the transaction, its set at R5 because lottery is a fixed amount
						chequeTransactionsHistory.setTransactionAmount(transactionAmount);;
						//
						String transactionID = "TRA00" + randomID + "CH";
						chequeTransactionsHistory.setTransactionID(transactionID);
						randomID++;
						//
						String transactionType = "Debit";
						chequeTransactionsHistory.setTransactionType(transactionType);
						// line of code below creates a new array
						newChequeTransactions.add(new ChequeTransactionsHistory(transactionDate, transactionDescription, transactionAmount, transactionID,transactionType)); 
						//
						for(ChequeTransactionsHistory chequeTrans : newChequeTransactions) {
							//
							System.out.println("\nTransaction Details : ");
							//
							System.out.println("\n\tTransaction ID\t\t: " +chequeTrans.getTransactionID());
							System.out.println("\tTransaction Date\t: " + newChequeTransactions.get(i).getTransactionDate());
							System.out.println("\tTransaction Name\t: " + newChequeTransactions.get(i).getTransactionDescription());
							System.out.println("\tDR or CR Bank\t\t: " + newChequeTransactions.get(i).getTransactionType());
							System.out.println("\tBeneficiary Account\t: " + moneyFormat.format(newChequeTransactions.get(i).getTransactionAmount()));
							//
							break;
							}
					}
					//
					chequeContinueTransaction();//This method asks the user if the want to continue with transactions or to close the application
					//
					break;
				// 	Case 2 : Case 2 (Option 2), takes the use back to the accounts menu.
				case 2:
					chequeAccountMenu();
					break;
				default:
					
					System.err.println("Oooppps please choose correct option");
					payExistingBeneficiary();
					break;
					}
				// the line of code below is called if the amount that the user would like to transfer is less than the available balance amount
						} else if (cheque.getChequeBalance() -  amount < 0) { 
							//
							chequeAccountHeader();
							//
							System.err.println("\nTransaction unsucessful : Insufficent funds");
							payExistingBeneficiary();
							//
						}
						// code below is to notify the user that they inputed the wrong details
					} else {
						//
						System.err.println("Ooops! Invalid Beneficiary code. .");
					}
					//
					chequeContinueTransaction();
				}
	// creates a new beneficiary
	public void createNewBeneficiary() {
		//
		for (int i = 0; i < 1; i++) { // user can only create one beneficiary at a time.
			//
			chequeAccountHeader();
			System.out.print("\nCreate beneficiary ");
			//
			System.out.print("\n\nEnter Beneficiary name\t\t\t: ");
			String beneficiaryName = grapes.next();
			//
			System.out.print("Enter beneficiary account number\t: ");
			long beneficiaryAccountNumber = grapes.nextLong();
			//
			System.out.print("Enter beneficiary bank name\t\t: ");
			String beneficiaryBank = grapes.next();
			// Creating beneficiary confrimation
			System.out.println("\nWould you like to create a beneficiary : ");
			System.out.println("\n\tBeneficiary Name\t\t: " + beneficiaryName);
			beneficiaries.setBeneficiaryName(beneficiaryName);
			//
			System.out.println("\tBeneficiary Account Number\t:" + beneficiaryAccountNumber);
			beneficiaries.setBeneficiaryAccountNumber(beneficiaryAccountNumber);
			//
			System.out.println("\tBeneficiary Bank Name\t\t:" + beneficiaryBank);
			beneficiaries.setBeneficiaryAccountNumber(beneficiaryAccountNumber);
			//
			System.out.println("\nPlease ensure that details entered above are correct.");
			System.out.println("\nEnter 1: To confrim");
			System.out.println("Enter 2: To go Back");
			System.out.println("Enter 3: To cancel");
			//
			try {
				//
				System.out.print("\nEnter your option :");
				choice = grapes.nextInt();
				//
			} catch (Exception e) {
				//
				System.err.println("\nMessages : " + e +"Ooops invalid selection, please enter an integer value. ");
				grapes.nextLine();
				//
				createNewBeneficiary();
				//
			}
			//
			switch(choice) {
			// Case 1 To confirm : sets and creates the beneficiary
			case 1:
				// code below creates the beneficiary
				newBeneficiary.add(new Beneficiaries(beneficiaryName,beneficiaryAccountNumber, beneficiaryBank));
				// code below displays created beneficiary
				for(Beneficiaries beneficiary : newBeneficiary) {
					//
					System.out.println("Beneficary created successfully : ");
					//
					System.out.println("\n\tBeneficiary Name\t: " + beneficiary.getBeneficiaryName());
					System.out.println("\tBeneficiary Bank\t: " + beneficiary.getBeneficiaryBank());
					System.out.println("\tBeneficiary Account\t: " + beneficiary.getBeneficiaryAccountNumber());
					//
					break;
					}
				break;
				// Case 2 : To go back, user goes back to begging of beneficiary process
				case 2:
					//user will start the process from beginning again
					createNewBeneficiary();
					break;
				// case 3 : sends user back to cheque menu
				case 3:
					//
					chequeAccountMenu();
					break;
					//
				default:
					//
					System.err.println("Ooops! Please enter correct option");
					chequeAccountMenu();
					break;
					}
					break;
					//
				}
				//
			}
	//Remove beneficiary which allows user to remove a beneficiary
	public void removeBeneficiary() {
		//
		chequeAccountHeader();
		//
		System.out.println("\nRemove a beneficiary");
		//
		System.out.println("\nCurrent Beneficiary List :");
		System.out.println(" ");
		//
		for (int i = 0; i <newBeneficiary.size(); i++) { // for each loop to display the current beneficiary list
			//
			System.out.println("Unique Beneficiary code :" + i);
			System.out.println("Beneficiary Name\t\t: " + newBeneficiary.get(i).getBeneficiaryName());
			System.out.println("Beneficiary Account Number\t: " +newBeneficiary.get(i).getBeneficiaryAccountNumber());
			System.out.println("Beneficiary Bank\t\t: " + newBeneficiary.get(i).getBeneficiaryBank());
			//
			System.out.println("");
		}
		//
		try {
			//Line of code below is a scanner for the user to choose the beneficiary that the would like to delete using the unique beneficiary code
			System.out.print("To delete a beneficiary, enter beneficiary unqiue code: ");
			int beneficiaryCode = grapes.nextInt();
			// Line o
		
			if (beneficiaryCode >= 0 && beneficiaryCode < newBeneficiary.size() ) {
				//
				newBeneficiary.remove(beneficiaryCode);
				System.out.println("Beneficiary removed successfully");
				//
			} else {
				//
				System.err.println("Ooops! Invalid Beneficiary code, no beneficiary was removed.");
			}
			//
		} catch (Exception e) {
			//
			System.err.println("\nMessage : " + e + " Ooops invalid selection, please enter an integer value. ");
			grapes.nextLine();
			//
			removeBeneficiary();
			//
		}
		
		System.out.println("\nUpdated Beneficiary List :");
		//
		for (int i = 0; i < newBeneficiary.size(); i++) {
			//
			System.out.println("\nUnique Beneficiary code :" + i);
			System.out.println("Beneficiary Name\t\t: " + newBeneficiary.get(i).getBeneficiaryName());
			System.out.println("Beneficiary Account Number\t: " + newBeneficiary.get(i).getBeneficiaryAccountNumber());
			System.out.println("Beneficiary Bank\t\t: " + newBeneficiary.get(i).getBeneficiaryBank());
			//
			System.out.println("");
		}
		
	}
	// This method is to view all the beneficiaries
		public void chequeBeneficiaryDisplay() {
			//
			chequeAccountHeader();
			System.out.println("\nCurrent beneficiary list :");
			//
			
			for (int i = 0; i < newBeneficiary.size(); i++) { // for each loop to display the current beneficiary list
				//
				System.out.println("\nUnique Beneficiary code :" + i);
				System.out.println("Beneficiary Name\t\t: " + newBeneficiary.get(i).getBeneficiaryName());
				System.out.println("Beneficiary Account Number\t: " + newBeneficiary.get(i).getBeneficiaryAccountNumber());
				System.out.println("Beneficiary Bank\t\t: " + newBeneficiary.get(i).getBeneficiaryBank());
				//
				System.out.println("");
			}
			//
		}
	/*
	 * Cheque cashsend
	 * 
	 * */
		public void chequeCashSend() {
			//
			do {

				//
				chequeAccountHeader();
				//
				System.out.println("Cash Send ");
				//
				System.out.println("\n1. Once off CashSend.");
				System.out.println("2. Send myself a CashSend.");
				System.out.println("3. Pay existing a CashSend Beneficiary");
				System.out.println("4. Create CashSend Beneficiary");
				System.out.println("5. Remove a Beneficiary.");
				System.out.println("6. View CashSend Beneficiaries");
				System.out.println("7. Back");
				try {
					//
					System.out.print("\nEnter your option :");
					choice = grapes.nextInt();
					//
				} catch (Exception e) {
					//
					System.err.println("\nMessages : " + e + "Ooops invalid selection, please enter an integer value. ");
					grapes.nextLine();
					//
					chequeCashSend();
					//
				}
				switch(choice) {
				// case 1 : Option 1, Once off Cash Send 
				case 1:
					//
					onceOffCashSend();
					//
					break;
				// case 2 : Option 2, Cash send mySelf
				case 2:
					cashSendMyself();
					break;
				// case 3 : Option 3, pay a cash send beneficiary
				case 3:
					//
					payExistingCashSendBeneficiary();
					break;
				// case 4 : Option 4, create a cash send beneficiary
				case 4:
					createCashSendBeneficiary();
					break;
				// case 5: Option 5, remove a cash send beneficiary
				case 5:
					removeCashSendBeneficiary();
					break;
				// case 6 : Option 6, go back to previous menu
				case 6:
					displayCashSendBeneficiary();
					break;
				case 7: 
					chequeAccountMenu();
					break;
				default:
					//
					System.err.println("Ooopps, invalid selections. Please choose correct value. ");
					chequeCashSend();
					break;
				}
			
			} while (choice < 8);
		}
		// Once off cashSend
		public void onceOffCashSend() {
			//
			chequeAccountHeader();
			//
			System.out.println("\nOnce OffCashSend");
			//Displays to user maximum amount that they can transfer
			System.out.println("\nPlease enter recipient details\t:");
			//
			try {
				//
				System.out.print("\nReccipient Phone Number\t:");
				long recipientPhoneNumber = grapes.nextLong();
				//
				System.out.println("\n\tMaximum CashSend transferable Amount\t:" + moneyFormat.format(cheque.getChequeBalance()));
				System.out.print("\nEnter amount that you want to CashSend :" );
				amount = grapes.nextInt();
				//
				if (cheque.getChequeBalance() - amount >= 0) {// this line of code checks if the user is not trying to transfer larger than available amount
					// code below as user to confrim and verify the payment
					System.out.println("\nPlease confirm that you would like to CashSend :");
					System.out.println("\n\tCashSend from Cheque Account\t\t: " + cheque.getChequeAccountNo());
					System.out.println("\tCashSend to Recipient Phone Number\t: " + recipientPhoneNumber);
					System.out.println("\tAmount\t\t\t\t\t: " + moneyFormat.format(amount));
					//
					System.out.println("\nEnter 1 to confrim CashSend");
					System.out.println("Enter 2 to cancel CashSend");
					//
					System.out.print("\nEnter your choice : ");
					choice = grapes.nextInt();
					//
					switch (choice) {
					// Case 1 : User confirms that the would like to proceed with transferring funds to the savings account
					case 1:
						// code below credits the money out of the Cheque Account
						double chequeAccountBalance = cheque.getChequeBalance() - amount;// chequeBalance is used to new balance after user inputs amount they wish to transfer
						cheque.setChequeBalance(chequeAccountBalance);; // sets the new cheque account balance
						//
						System.out.println("\nTransaction successfull");//tells user know that the transaction was successful
						System.out.println("New Cheque Account Balance : " + moneyFormat.format(cheque.getChequeBalance())); // diplays to user the new bank balance
						//
						/*
						 * Transaction History
						 * */
						for (int i = 0; i < 1;i++) {
							//
							LocalDate localtransactionDate = LocalDate.now();//this line of code used get the current date
							String transactionDate = localtransactionDate.toString();//This line of code is used to convert the date to a string
							chequeTransactionsHistory.setTransactionDate(transactionDate);;//this line of code is used to set date to the array
							//
							String transactionDescription = "CashSend - Once of payement";// this line of code is the name of the transaction
							chequeTransactionsHistory.setTransactionDescription(transactionDescription);; // this line of sets the transaction name 
							//
							double transactionAmount = amount;//this line of code is the monetary value of the transaction, its set at R5 because lottery is a fixed amount
							chequeTransactionsHistory.setTransactionAmount(transactionAmount);;
							//
							String transactionID = "TRA00" + randomID + "CH";
							chequeTransactionsHistory.setTransactionID(transactionID);
							randomID++;
							//
							String transactionType = "Debit";
							chequeTransactionsHistory.setTransactionType(transactionType);
							// line of code below creates a new array
							newChequeTransactions.add(new ChequeTransactionsHistory(transactionDate, transactionDescription, transactionAmount, transactionID,transactionType)); 
							//
							for(ChequeTransactionsHistory chequeTrans : newChequeTransactions) {
								//
								System.out.println("\nTransaction Details : ");
								//
								System.out.println("\n\tTransaction ID\t\t: " +chequeTrans.getTransactionID());
								System.out.println("\tTransaction Date\t: " + newChequeTransactions.get(i).getTransactionDate());
								System.out.println("\tTransaction Name\t: " + newChequeTransactions.get(i).getTransactionDescription());
								System.out.println("\tDR or CR Bank\t\t: " + newChequeTransactions.get(i).getTransactionType());
								System.out.println("\tBeneficiary Account\t: " + moneyFormat.format(newChequeTransactions.get(i).getTransactionAmount()));
								//
								break;
								}
						}
						//
						chequeContinueTransaction();//This method asks the user if the want to continue with transactions or to close the application
						//
						break;
						//
					// 	Case 2 : Case 2 (Option 2), takes the use back to the accounts menu.
					case 2:
						chequeAccountMenu();
						break;
					}
				// the line of code below is called if the amount that the user would like to transfer is less than the available balance amount
				}else if (cheque.getChequeBalance() -  amount < 0) { 
					//
					chequeAccountHeader();
					//
					System.err.println("\nTransaction unsucessfull : Insufficent funds");
					chequeAccountMenu();
				}
			} catch (Exception e) {
				//
				System.err.println("\nMessages : " + e + "Ooops invalid selection, please enter an integer value. ");
				grapes.nextLine();
				//
				onceOffCashSend();
				//
			}
			
			
		}
		
		// User sends cash send to themselves 
		public void cashSendMyself() {
			//
			try {
				//
				chequeAccountHeader();
				//
				System.out.println("\nSend Myself CashSend");
				//
				System.out.println("\n\tMaximum Transferable Amount\t:" + moneyFormat.format(cheque.getChequeBalance()));
				System.out.print("\nEnter amount that you want to CashSend :" );
				amount = grapes.nextInt();
				//
				if (cheque.getChequeBalance() - amount >= 0) {// this line of code checks if the user is not trying to transfer larger than available amount
					// code below as user to confrim and verify the payment
					System.out.println("\nPlease confirm that you would like to CashSend :");
					System.out.println("\n\tCashSend from Cheque Account\t\t: " + cheque.getChequeAccountNo());
					System.out.println("\tCashSend to Recipient Phone Number\t: " + cheque.getUserPhone());
					System.out.println("\tAmount\t\t\t\t\t: " + moneyFormat.format(amount));
					//
					System.out.println("\nEnter 1 to confrim CashSend");
					System.out.println("Enter 2 to cancel CashSend");
					//
					System.out.print("\nEnter your choice : ");
					choice = grapes.nextInt();
					//
					switch (choice) {
					// Case 1 : User confirms that the would like to proceed with transferring funds to the savings account
					case 1:
						// code below credits the money out of the Cheque Account
						double chequeAccountBalance = cheque.getChequeBalance() - amount;// chequeBalance is used to new balance after user inputs amount they wish to transfer
						cheque.setChequeBalance(chequeAccountBalance);; // sets the new cheque account balance
						//
						System.out.println("\nTransaction successfull");//tells user know that the transaction was successful
						System.out.println("New Cheque Account Balance : " + moneyFormat.format(cheque.getChequeBalance())); // diplays to user the new bank balance
						/*
						 * Transaction History
						 * */
						for (int i = 0; i < 1;i++) {
							//
							LocalDate localtransactionDate = LocalDate.now();//this line of code used get the current date
							String transactionDate = localtransactionDate.toString();//This line of code is used to convert the date to a string
							chequeTransactionsHistory.setTransactionDate(transactionDate);;//this line of code is used to set date to the array
							//
							String transactionDescription = "CashSend - Myself";// this line of code is the name of the transaction
							chequeTransactionsHistory.setTransactionDescription(transactionDescription);; // this line of sets the transaction name 
							//
							double transactionAmount = amount;//this line of code is the monetary value of the transaction, its set at R5 because lottery is a fixed amount
							chequeTransactionsHistory.setTransactionAmount(transactionAmount);;
							//
							String transactionID = "CAS00" + randomID + "CH";
							chequeTransactionsHistory.setTransactionID(transactionID);
							randomID++;
							//
							String transactionType = "Debit";
							chequeTransactionsHistory.setTransactionType(transactionType);
							// line of code below creates a new array
							newChequeTransactions.add(new ChequeTransactionsHistory(transactionDate, transactionDescription, transactionAmount, transactionID,transactionType)); 
							//
							for(ChequeTransactionsHistory chequeTrans : newChequeTransactions) {
								//
								System.out.println("\nTransaction Details : ");
								//
								System.out.println("\n\tTransaction ID\t\t: " +chequeTrans.getTransactionID());
								System.out.println("\tTransaction Date\t: " + newChequeTransactions.get(i).getTransactionDate());
								System.out.println("\tTransaction Name\t: " + newChequeTransactions.get(i).getTransactionDescription());
								System.out.println("\tDR or CR Bank\t\t: " + newChequeTransactions.get(i).getTransactionType());
								System.out.println("\tBeneficiary Account\t: " + moneyFormat.format(newChequeTransactions.get(i).getTransactionAmount()));
								//
								break;
								}
						}
						 //
						chequeContinueTransaction();//This method asks the user if the want to continue with transactions or to close the app
						//
						break;
					// 	Case 2 : Case 2 (Option 2), takes the use back to the accounts menu.
					case 2:
						chequeAccountMenu();
						break;
					}
				// the line of code below is called if the amount that the user would like to transfer is less than the available balance amount
				} else if (cheque.getChequeBalance() -  amount < 0) { 
					//
					chequeAccountHeader();
					//
					System.err.println("\nTransaction unsucessfull : Insufficent funds");
					chequeAccountMenu();
				}
			
			
			} catch (Exception e) {
				//
				System.err.println("\nMessages : " + e + "Ooops invalid selection, please enter an integer value. ");
				grapes.nextLine();
				//
				cashSendMyself();
				//
			}
		}
		// pay existing cashsend beneficiary
		
		public void savingsPayExistingCashSendBeneficiary() {
			//
			try {
				//
				savingsAccountHeader();
				//
				System.out.println("\nPay Exisiting CashSend Beneficiary : ");//Header
				//
				System.out.println("\nCashSend Beneficiary List :");
				//code below is to represent the current beneficiary list
				for (int i = 0; i < newCashSend.size(); i++) { // for each loop to display the current beneficiary list
					//
					System.out.println("\nUnique Beneficiary code :" + i);
					System.out.println("Beneficiary Name\t\t: " + newCashSend.get(i).getCashSendName());
					System.out.println("Beneficiary Account Number\t: " + newCashSend.get(i).getCashSendPhoneNumber());
					//
					System.out.println("");
				}
				//Line of code below is a scanner for the user to choose the beneficiary that the would like to delete using the unique beneficiary code
				System.out.print("\nTo choose a CashSend beneficiary, enter CashSend beneficiary unqiue code: ");
				int beneficiaryCode = grapes.nextInt();
				//// if the user enter 
				if (beneficiaryCode >= 0 && beneficiaryCode < newCashSend.size() ) {
					//The code below retrieves all the selected beneficiaries information
					newCashSend.get(beneficiaryCode);
					System.out.println("\n\tYou have choosen to CashSend\t\t: " + newCashSend.get(beneficiaryCode).getCashSendName());
					System.out.println("\tCashSen Phone Number\t\t\t: 0" + newCashSend.get(beneficiaryCode).getCashSendPhoneNumber());
					//
					System.out.print("\nPlease enter amount that you would like to CashSend Beneficiary\t: ");
					int amount = grapes.nextInt();
					//
					if (cheque.getChequeBalance() - amount >= 0) {// this line of code checks if the user is not trying to transfer larger than available amount
						// code below as user to confrim and verify the payment
						//
						chequeAccountHeader();
						//
						System.out.println("\nPlease confirm that you would like to CashSend\t:");
						System.out.println("\n\tYou have choosen to CashSend beneficiary\t: " + newCashSend.get(beneficiaryCode).getCashSendName());
						System.out.println("\tBeneficiary Phone Number\t\t\t: 0" + newCashSend.get(beneficiaryCode).getCashSendPhoneNumber());
						System.out.println("\tFrom Cheque Account\t\t\t\t: " + savings.getSavingsAccountNo());
						System.out.println("\tAmount\t\t\t\t\t\t: " + moneyFormat.format(amount));
						//
						System.out.println("\nEnter 1 to confrim transfer");
						System.out.println("Enter 2 to cancel transfer");
						//
						System.out.print("\nEnter your choice : ");
						choice = grapes.nextInt();
						//
						switch (choice) {
						// Case 1 : User confirms that the would like to proceed with transferring funds to the savings account
						case 1:
							//
							chequeAccountHeader();
							//
							// code below credits the money out of the Cheque Account
							double savingsAccountBalance = savings.getSavingsBalance() - amount;// chequeBalance is used to new balance after user inputs amount they wish to transfer
							savings.setSavingsBalance(savingsAccountBalance);; // sets the new cheque account balance
							//
							System.out.println("\nTransaction successfull.");//tells user know that the transaction was successful
							System.out.println("New Cheque Account Balance : " + moneyFormat.format(savings.getSavingsBalance())); // Displays to user the new bank balance
							/*
							 * Transaction History
							 * */
							for (int i = 0; i < 1;i++) {
								//
								LocalDate localtransactionDate = LocalDate.now();//this line of code used get the current date
								String transactionDate = localtransactionDate.toString();//This line of code is used to convert the date to a string
								savingsTransactionsHistory.setTransactionDate(transactionDate);;//this line of code is used to set date to the array
								//
								String transactionDescription = "CashSend - Beneficiary";// this line of code is the name of the transaction
								savingsTransactionsHistory.setTransactionDescription(transactionDescription);; // this line of sets the transaction name 
								//
								double transactionAmount = amount;//this line of code is the monetary value of the transaction, its set at R5 because lottery is a fixed amount
								savingsTransactionsHistory.setTransactionAmount(transactionAmount);;
								//
								String transactionID = "CAS00" + randomID + "CH";
								savingsTransactionsHistory.setTransactionID(transactionID);
								randomID++;
								//
								String transactionType = "Debit";
								savingsTransactionsHistory.setTransactionType(transactionType);
								// line of code below creates a new array
								newSavingsTransactions.add(new SavingsTransactionsHistory(transactionDate, transactionDescription, transactionAmount, transactionID,transactionType)); 
								//
								for(SavingsTransactionsHistory savingsTrans : newSavingsTransactions) {
									//
									System.out.println("\nTransaction Details : ");
									//
									System.out.println("\n\tTransaction ID\t\t: " + newSavingsTransactions.get(i).getTransactionID());
									System.out.println("\tTransaction Date\t: " + newSavingsTransactions.get(i).getTransactionDate());
									System.out.println("\tTransaction Name\t: " + newSavingsTransactions.get(i).getTransactionDescription());
									System.out.println("\tDR or CR Bank\t\t: " + newSavingsTransactions.get(i).getTransactionType());
									System.out.println("\tBeneficiary Account\t: " + moneyFormat.format(newSavingsTransactions.get(i).getTransactionAmount()));
									//
									break;
									}
							}
							//
							chequeContinueTransaction();//This method asks the user if the want to continue with transactions or to close the app
							//
							break;
						// 	Case 2 : Case 2 (Option 2), takes the use back to the accounts menu.
						case 2:
							chequeAccountMenu();
							break;
						}
					// the line of code below is called if the amount that the user would like to transfer is less than the available balance amount
					} else if (cheque.getChequeBalance() -  amount < 0) { 
						//
						chequeAccountHeader();
						//
						System.err.println("\nTransaction unsucessful : Insufficent funds");
//						accounts.login();
					}
					// code below is to notify the user that they inputed the wrong details
				} else {
					//
					System.err.println("Ooops! Invalid CashSend Beneficiary code. ");
				}
				//
				chequeContinueTransaction();
			
			} catch (Exception e) {
				//
				System.err.println("\nMessages : " + e + "Invalid entry : Please enter an integer value. " );
				grapes.nextLine();
				//
				savingsPayExistingCashSendBeneficiary();
				//
			}
		}
		// create cash send beneficiaires
		public void createCashSendBeneficiary() {
			//
			try {
				//
				//
				chequeAccountHeader();
				//
				for (int i = 0; i < 1; i++) {
					//
					System.out.print("\nCreate CashSend Beneficiary ");
					//
					System.out.print("\n\nEnter CashSend Beneficiary name\t\t\t: ");
					String cashSendName = grapes.next();
					//
					System.out.print("Enter CashSend Beneficiary Phone number\t: ");
					long cashSendPhoneNumber = grapes.nextLong();
					// Creating beneficiary confirmation
					System.out.println("\nWould you like to create a CashSend Beneficiary : ");
					System.out.println("\n\tBeneficiary Name\t\t\t: " + cashSendName);
					beneficiaries.setBeneficiaryName(cashSendName);
					//
					System.out.println("\tCashSend Beneficiary Phone Number\t:" + cashSendPhoneNumber);
					beneficiaries.setBeneficiaryAccountNumber(cashSendPhoneNumber);
					//
					System.out.println("\nPlease ensure that details entered above are correct.");
					System.out.println("\nEnter 1: To confrim");
					System.out.println("Enter 2: To go Back");
					System.out.println("Enter 3: To cancel");
					//
					System.out.print("\nEnter your choice : ");
					int choice = grapes.nextInt();
					//
					switch(choice) {
					// Case 1 To confirm : sets and creates the beneficiary
					case 1:
						// code below creates the beneficiary
						newCashSend.add(new CashSend(cashSendName,cashSendPhoneNumber));
						// code below displays created beneficiary
						for(CashSend cashSend : newCashSend) {
							 //
							System.out.println("CashSend Beneficary created successfully : ");
							//
							 System.out.println("\n\tCashSend Beneficiary Name\t: " + cashSend.getCashSendName());
							 System.out.println("\tBeneficiary Account\t:" + cashSend.getCashSendPhoneNumber());
							 //
							 break;
						 }
						break;
					// Case 2 : To go back, user goes back to begging of beneficiary process
					case 2:
						//user will start the process from beginning again
						createCashSendBeneficiary();
						break;
					// case 3 : sends user back to cheque menu
					case 3:
						//
						chequeAccountMenu();
						break;
					//
					default:
						//
						System.err.println("Ooops! Please enter correct option");
						break;
					}
					break;
					//
				}
				
			} catch (Exception e) {
				//
				System.out.println("\nMessages : " + e + "Invalid entry : Please enter an integer value. " );
				grapes.nextLine();
				//
				createCashSendBeneficiary();
				//
			}
			
		
		}
		//
		public void displayCashSendBeneficiary() {
			//
			for (int i = 0; i < newCashSend.size(); i++) { // for each loop to display the current beneficiary list
				//
				System.out.println("\nUnique Beneficiary code :" + i);
				System.out.println("Beneficiary Name\t\t: " + newCashSend.get(i).getCashSendName());
				System.out.println("Beneficiary Account Number\t: " + newCashSend.get(i).getCashSendPhoneNumber());
				//
				System.out.println("");
			}
			//
			chequeAccountMenu();
			//
		}
		//
		public void removeCashSendBeneficiary() {
			//
			try {
				//
				//
				chequeAccountHeader();
				//
				System.out.println("\nRemove a beneficiary");
				//
				System.out.println("\nCurrent Beneficiary List :");
				System.out.println("\t");
				//
				for (int i = 0; i < newCashSend.size(); i++) { // for each loop to display the current beneficiary list
					//
					System.out.println("\nUnique Beneficiary code :" + i);
					System.out.println("Beneficiary Name\t\t: " + newCashSend.get(i).getCashSendName());
					System.out.println("Beneficiary Account Number\t: " + newCashSend.get(i).getCashSendPhoneNumber());
					//
					System.out.println("");
				}
				//Line of code below is a scanner for the user to choose the beneficiary that the would like to delete using the unique beneficiary code
				System.out.print("To delete a beneficiary, enter beneficiary unqiue code: ");
				int beneficiaryCode = grapes.nextInt();
				// Line o
				if (beneficiaryCode >= 1 && beneficiaryCode < newCashSend.size() ) {
					//
					newCashSend.remove(beneficiaryCode);
					System.out.println("Beneficiary removed successfully");
					//
				} else {
					//
					System.err.println("Ooops! Invalid Beneficiary code, no beneficiary was removed.");
				}
				//
				System.out.println("Updated Beneficiary List :");
				///
				for (int i = 0; i < newCashSend.size(); i++) { // for each loop to display the current beneficiary list
					//
					System.out.println("\nUnique Beneficiary code :" + i);
					System.out.println("Beneficiary Name\t\t\t: " + newCashSend.get(i).getCashSendName());
					System.out.println("Beneficiary Account Number\t: " + newCashSend.get(i).getCashSendPhoneNumber());
					
					//
					System.out.println("");
				}
				//
				chequeContinueTransaction();
				//
				
			} catch (Exception e) {
				//
				System.out.println("\nMessages : " + e + " Invalid entry : Please enter an integer value. " );
				grapes.nextLine();
				//
				createCashSendBeneficiary();
				//
			}
			
		}
		/*
		 *
		 * Purchases
		 * 
		 * */
		//Cheque purchases menu
		public void chequePurchase() {
			//
			try {
				//
				chequeAccountHeader();
				// Pay beneficiaries sub menu
				System.out.println("\nPurchases");
				System.out.println("\n1. Airtime");
				System.out.println("2. Electricty");
				System.out.println("3. Lotto");
				System.out.println("4. Back");
				//
				System.out.print("\nEnter your choice : ");
				choice = grapes.nextInt();
				//
				switch (choice) {
				// Air time
				case 1:
					chequePurchaseAirtime();
					break;
				// Electricity purchase
				case 2:
					chequePurchasesElectricity();
					break;
				//
				case 3:
					chequePurchaseLotto();
					break;
				//
				default:
					break;
				}
			} catch (Exception e) {
				//
				System.out.println("\nMessages : " + e + "Invalid entry : Please enter an integer value. " );
				grapes.nextLine();
				//
				chequePurchase();
				//
			}
			
		}
		//Airtime method 
		public void chequePurchaseAirtime() {
			//
			try {
				//
				chequeAccountHeader();
				//
				System.out.println("\nAirtime");
				//
				System.out.println("\nPlease choose a Network Provider : ");
				System.out.println("\n1. Mtn");
				System.out.println("2. Vodacom");
				System.out.println("3. Telkom");
				System.out.println("4. CellC");
				//
				System.out.print("\nEnter your choice : ");
				int choice = grapes.nextInt();
				//
				switch (choice ) {
				// Case 1 : Option 1 Vodacom air time 
				case 1:
					//
					chequeAccountHeader();
					//
					System.out.println("\nMTN");
					//
					airtimePurchaseProcess();
					//
					
				break;
				// Case 2: Option 2 purchase Vodacom
				case 2:
					//
					chequeAccountHeader();
					//
					System.out.println("\nVodacom");
					//
					airtimePurchaseProcess();
					//
					break;
				// case 3: option 3 purchase Telkom;
				case 3:
					//
					chequeAccountHeader();
					//
					System.out.println("\nTelkom");
					//
					airtimePurchaseProcess();
					//
					break;
				// case 4: Option 4 purchase CellC
				case 4:
					//
					chequeAccountHeader();
					//
					System.out.println("\nCellC");
					//
					airtimePurchaseProcess();
					//
					break;
				default:
					System.err.println("Opps, invalid selection");
					chequePurchaseAirtime();
					break;
				}
			}
			catch (Exception e) {
				//
				System.out.println("\nMessage : " + e + "Invalid entry : Please enter an integer value. " );
				grapes.nextLine();
				//
				chequePurchaseAirtime();
				//
			}
			
		}
		// Airtime purchase process
		public void airtimePurchaseProcess() {
			//
			try {
				//
				System.out.println("\nMaximum Airtime Purchase amount available\t:" + moneyFormat.format(cheque.getChequeBalance()));
				System.out.print("\nPlease enter Airtime Amount:");
				amount = grapes.nextInt();
				//
				if (cheque.getChequeBalance() - amount >= 0) {
					//
					System.out.print("\nPlease enter Recipients Phone Number : ");
					int phoneNumber = grapes.nextInt();
					//
					System.out.println("\nYou would like to purchase airtime:");
					System.out.println("\n\tPhone Number\t\t: 0" + phoneNumber);
					System.out.println("\tAmount\t\t\t: " + moneyFormat.format(amount));
					//
					System.out.println("\nEnter 1 to confrim Airtime");
					System.out.println("Enter 2 to cancel Airtime");
					//
					System.out.print("\nEnter your choice : ");
					choice = grapes.nextInt();
					//
					switch (choice) {
					// Case 1 : User confirms that the would like to proceed with transferring funds to the savings account
					case 1:
						// code below credits the money out of the Cheque Account
						double chequeAccountBalance = cheque.getChequeBalance() - amount;// chequeBalance is used to new balance after user inputs amount they wish to transfer
						cheque.setChequeBalance(chequeAccountBalance);; // sets the new cheque account balance
						//
						System.out.println("\nTransaction successfull");//tells user know that the transaction was successful
						System.out.println("New Cheque Account Balance : " + moneyFormat.format(cheque.getChequeBalance())); // diplays to user the new bank balance
						/*
						 * Transaction History
						 * */
						for (int i = 0; i < 1;i++) {
							//
							LocalDate localtransactionDate = LocalDate.now();//this line of code used get the current date
							String transactionDate = localtransactionDate.toString();//This line of code is used to convert the date to a string
							chequeTransactionsHistory.setTransactionDate(transactionDate);;//this line of code is used to set date to the array
							//
							String transactionDescription = "Airtime";// this line of code is the name of the transaction
							chequeTransactionsHistory.setTransactionDescription(transactionDescription);; // this line of sets the transaction name 
							//
							double transactionAmount = amount;//this line of code is the monetary value of the transaction, its set at R5 because lottery is a fixed amount
							chequeTransactionsHistory.setTransactionAmount(transactionAmount);;
							//
							String transactionID = "PUR00" + randomID + "CH";
							chequeTransactionsHistory.setTransactionID(transactionID);
							randomID++;
							//
							String transactionType = "Debit";
							chequeTransactionsHistory.setTransactionType(transactionType);
							// line of code below creates a new array
							newChequeTransactions.add(new ChequeTransactionsHistory(transactionDate, transactionDescription, transactionAmount, transactionID,transactionType)); 
							//
							for(ChequeTransactionsHistory chequeTrans : newChequeTransactions) {
								//
								System.out.println("\nTransaction Details : ");
								//
								System.out.println("\n\tTransaction ID\t\t: " + newChequeTransactions.get(i).getTransactionID());
								System.out.println("\tTransaction Date\t: " + newChequeTransactions.get(i).getTransactionDate());
								System.out.println("\tTransaction Name\t: " + newChequeTransactions.get(i).getTransactionDescription());
								System.out.println("\tDR or CR Bank\t\t: " + newChequeTransactions.get(i).getTransactionType());
								System.out.println("\tBeneficiary Account\t: " + moneyFormat.format(newChequeTransactions.get(i).getTransactionAmount()));
								//
								break;
								}
						}
						 //
						chequeContinueTransaction();//This method asks the user if the want to continue with transactions or to close the app
						//
						break;
					// 	Case 2 : Case 2 (Option 2), takes the use back to the accounts menu.
					case 2:
						chequeAccountMenu();
						break;
					}
					//
				} else if (cheque.getChequeAccountNo() - amount < 0 ) {
					//
					System.err.println("Transaction uncessful, Please enter correct amount");
					//
					chequePurchaseAirtime();
					//
				}
			} catch (Exception e) {
				//
				System.err.println("\nMessages : " + e + " Invalid entry : Please enter an integer value. " );
				grapes.nextLine();
				//
				airtimePurchaseProcess();
				//
			}
			
		}
		//Electricity
		public void chequePurchasesElectricity() {
			//
			try {
				//
				//
				chequeAccountHeader();
				//
				System.out.println("\nElectricity");
				//
				System.out.println("\nMaximum Airtime Purchase amount available\t:" + moneyFormat.format(cheque.getChequeBalance()));
				System.out.print("\nPlease enter Electricity Amount:");
				amount = grapes.nextInt();
				//
				if (cheque.getChequeBalance() - amount >= 0) {
					//
					System.out.print("\nPlease enter Recipients meter Number: ");
					int phoneNumber = grapes.nextInt();
					//
					System.out.println("\nYou would like to purchase electricity:");
					System.out.println("\n\tMeter Number\t\t: 0" + phoneNumber);
					System.out.println("\tAmount\t\t\t: " + moneyFormat.format(amount));
					System.out.println("\tMegawatts purchased\t:" + (amount/2));
					//
					System.out.println("\nEnter 1 to confrim to purchase Electricity");
					System.out.println("Enter 2 to cancel Electricity purchase");
					//
					System.out.print("\nEnter your choice : ");
					choice = grapes.nextInt();
					//
					switch (choice) {
					// Case 1 : User confirms that the would like to proceed with transferring funds to the savings account
					case 1:
						// code below credits the money out of the Cheque Account
						double chequeAccountBalance = cheque.getChequeBalance() - amount;// chequeBalance is used to new balance after user inputs amount they wish to transfer
						cheque.setChequeBalance(chequeAccountBalance);; // sets the new cheque account balance
						//
						int randomNum = (int)(Math.random() *99999);  // 0 to 100
						System.out.println("\nPurchase successfull:");//tells user know that the transaction was successful
						System.out.println("\n\tElectricity Token\t:"  + randomNum);
						System.out.println("\tMegawatts \t\t\t:" + (amount/2) + " kw");
						System.out.println("\nNew Cheque Account Balance : " + moneyFormat.format(cheque.getChequeBalance())); // diplays to user the new bank balance
						/*
						 * Transaction History
						 * */
						for (int i = 0; i < 1;i++) {
							//
							LocalDate localtransactionDate = LocalDate.now();//this line of code used get the current date
							String transactionDate = localtransactionDate.toString();//This line of code is used to convert the date to a string
							chequeTransactionsHistory.setTransactionDate(transactionDate);;//this line of code is used to set date to the array
							//
							String transactionDescription = "Electricity";// this line of code is the name of the transaction
							chequeTransactionsHistory.setTransactionDescription(transactionDescription);; // this line of sets the transaction name 
							//
							double transactionAmount = amount;//this line of code is the monetary value of the transaction, its set at R5 because lottery is a fixed amount
							chequeTransactionsHistory.setTransactionAmount(transactionAmount);;
							//
							String transactionID = "PUR00" + randomID + "CH";
							chequeTransactionsHistory.setTransactionID(transactionID);
							randomID++;
							//
							String transactionType = "Debit";
							chequeTransactionsHistory.setTransactionType(transactionType);
							// line of code below creates a new array
							newChequeTransactions.add(new ChequeTransactionsHistory(transactionDate, transactionDescription, transactionAmount, transactionID,transactionType)); 
							//
							for(ChequeTransactionsHistory chequeTrans : newChequeTransactions) {
								//
								System.out.println("\nTransaction Details : ");
								//
								System.out.println("\n\tTransaction ID\t\t: " +chequeTrans.getTransactionID());
								System.out.println("\tTransaction Date\t: " + newChequeTransactions.get(i).getTransactionDate());
								System.out.println("\tTransaction Name\t: " + newChequeTransactions.get(i).getTransactionDescription());
								System.out.println("\tDR or CR Bank\t\t: " + newChequeTransactions.get(i).getTransactionType());
								System.out.println("\tBeneficiary Account\t: " + moneyFormat.format(newChequeTransactions.get(i).getTransactionAmount()));
								//
								break;
								}
						}
						 //
						chequeContinueTransaction();//This method asks the user if the want to continue with transactions or to close the app
						//
						break;
					// 	Case 2 : Case 2 (Option 2), takes the use back to the accounts menu.
					case 2:
						chequeAccountMenu();
						break;
					}
					//
				} else if (cheque.getChequeAccountNo() - amount < 0 ) {
					//
					System.err.println("Transaction uncessful, Please enter correct amount");
					//
					chequePurchasesElectricity();
					//
				}
				
			}
			catch (Exception e) {
				//
				System.err.println("\nMessages : " + e + " Invalid entry : Please enter an integer value. " );
				grapes.nextLine();
				//
				airtimePurchaseProcess();
				//
			}
			
		}
		//
		public void chequePurchaseLotto() {
			//
			try {
				//
				//
				chequeAccountHeader();
				//
				System.out.println("\nLotto");
				//
				System.out.println("\nEnter 1 : Play Lotto QuickPick for R 5 per draw");
				System.out.println("Enter 2: To Quit");
				//
				System.out.print("\nEnter your choice ");
				choice = grapes.nextInt();
				//
				switch (choice) {
				// case 1 : Option 1, play randmly picked numbers
				case 1:
					// code below credits the money out of the Cheque Account
					double chequeAccountBalance = cheque.getChequeBalance() - 5;// chequeBalance is used to new balance after user inputs amount they wish to transfer
					cheque.setChequeBalance(chequeAccountBalance);; // sets the new cheque account balance
					//
					int amount = 5;
					//
					if (cheque.getChequeBalance() - amount >= 0) {
						//
						chequeAccountHeader();
						//
						System.out.println("\nLotto QuickPick");
						//
						ArrayList<Integer> lottoNumbers = new ArrayList<Integer>();
						//
						int randomNum = (int)(Math.random() * 101);  // 0 to 100
						int randomNum1 = (int)(Math.random() * 101);  // 0 to 100
						int randomNum2 = (int)(Math.random() * 101);  // 0 to 100
						int randomNum3 = (int)(Math.random() * 101);  // 0 to 100
						int randomNum4 = (int)(Math.random() * 101);  // 0 to 100
						int randomNum5 = (int)(Math.random() * 101);  // 0 to 100
						//
						lottoNumbers.add(randomNum);
						lottoNumbers.add(randomNum1);
						lottoNumbers.add(randomNum2);
						lottoNumbers.add(randomNum3);
						lottoNumbers.add(randomNum4);
						lottoNumbers.add(randomNum5);
						//
						System.out.println("\nYour Lotto numbers :\n");
						//
						 for (int i : lottoNumbers) {
							 //
							 System.out.print(i + " ");
						 }
						 //
						 System.out.println("\n\nYou have successfully played Lotto QuickPick");
						 /*
							 * Transaction History
							 * */
							for (int i = 0; i < 1;i++) {
								//
								LocalDate localtransactionDate = LocalDate.now();//this line of code used get the current date
								String transactionDate = localtransactionDate.toString();//This line of code is used to convert the date to a string
								chequeTransactionsHistory.setTransactionDate(transactionDate);;//this line of code is used to set date to the array
								//
								String transactionDescription = "Lotto";// this line of code is the name of the transaction
								chequeTransactionsHistory.setTransactionDescription(transactionDescription);; // this line of sets the transaction name 
								//
								double transactionAmount = amount;//this line of code is the monetary value of the transaction, its set at R5 because lottery is a fixed amount
								chequeTransactionsHistory.setTransactionAmount(transactionAmount);;
								//
								String transactionID = "PUR00" + randomID + "CH";
								chequeTransactionsHistory.setTransactionID(transactionID);
								randomID++;
								//
								String transactionType = "Debit";
								chequeTransactionsHistory.setTransactionType(transactionType);
								// line of code below creates a new array
								newChequeTransactions.add(new ChequeTransactionsHistory(transactionDate, transactionDescription, transactionAmount, transactionID,transactionType)); 
								//
								for(ChequeTransactionsHistory chequeTrans : newChequeTransactions) {
									//
									System.out.println("\nTransaction Details : ");
									//
									System.out.println("\n\tTransaction ID\t\t: " +chequeTrans.getTransactionID());
									System.out.println("\tTransaction Date\t: " + newChequeTransactions.get(i).getTransactionDate());
									System.out.println("\tTransaction Name\t: " + newChequeTransactions.get(i).getTransactionDescription());
									System.out.println("\tDR or CR Bank\t\t: " + newChequeTransactions.get(i).getTransactionType());
									System.out.println("\tBeneficiary Account\t: " + moneyFormat.format(newChequeTransactions.get(i).getTransactionAmount()));
									//
									break;
									}
							}
							 //
							chequeContinueTransaction();//This method asks the user if the want to continue with transactions or to close the app
							//
					} else if (cheque.getChequeAccountNo() - amount < 0 ) {
						//
						System.err.println("Transaction unsucessfull insufficuient funds, Lotto Quick pick costs R5,00  per draw");
						//
						chequePurchaseLotto();
						//
					}
					 //
					break;
				// case 2 : Option 2 quit
				case 2: 
					chequeAccountMenu();
					break;
				}
			}
			catch (Exception e) {
				//
				System.out.println("\nMessage : " + e + "Invalid entry : Please enter an integer value. " );
				grapes.nextLine();
				//
				chequePurchaseLotto();
				//
			}
			
		}
		//
		public void chequeTranstactionHistory() {
			//
			try {
				//
				chequeAccountHeader();
				//
				System.out.println("\nTransaction History");
				//Code below displays all of the users transactions
				for (int i = 0; i < newChequeTransactions.size(); i++) { // for each loop to display the current beneficiary list
					//
					System.out.println("\n\tTransaction ID\t\t: " + newChequeTransactions.get(i).getTransactionID());
					System.out.println("\tTransaction Date\t: " + newChequeTransactions.get(i).getTransactionDate());
					System.out.println("\tTransaction Name\t: " + newChequeTransactions.get(i).getTransactionDescription());
					System.out.println("\tTransaction Amount\t: " + moneyFormat.format(newChequeTransactions.get(i).getTransactionAmount()));
					System.out.println("\tDR or CR\t\t: " + newChequeTransactions.get(i).getTransactionType());
					//
					System.out.println("");
				}
				//
				LocalDate transactionDate = LocalDate.now();//this line of code used get the current date
				//
				System.out.println("\nTransaction History was last updated : " + transactionDate);
				//
				chequeContinueTransaction();
				//
				
			}
			catch (Exception e) {
				//
				System.err.println("\nMessage : " + e + "Invalid entry : Please enter an integer value. " );
				grapes.nextLine();
				//
			}
			
		}
		/*
		 * Saving Account Menu
		 * 
		 * */
		// Line of code below is the user cheque account information with user details(Name, surname, account type, account number, balance)
		//
		Main main = new Main();
		//
		SavingsTransactionsHistory savingsTransactionsHistory = new SavingsTransactionsHistory(null, null, amount, null, null);
		ArrayList<SavingsTransactionsHistory> newSavingsTransactions = new ArrayList<>();
		//
		public void savingsAccountHeader() { //Cheque Account Branding
			//
			LocalDate transactionDate = LocalDate.now();//this line of code used get the current date
			LocalTime transactionTime = LocalTime.now();// this line of code is used to get current time
			DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm:ss");
			String formattedTime = transactionTime.format(timeFormat);
			//
			System.out.println("\nSavings Account Number : " + savings.savingsAccountNo + " " + " \t\t\t\t\tBalance : " + moneyFormat.format(savings.getSavingsBalance()));
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.println("Current Date:" + transactionDate + "                  \t\t\tCurrent Time:" + formattedTime);
			//
		}
		// method used to ask user if they would like to do another transaction
		public void savingsContinueTransaction() {
			//
			try {
				//
				System.out.println("\nWould you like to do another transaction : ");
				System.out.println("1. Yes");
				System.out.println("2. No.");
				//
				System.out.print("\nEnter your choice : ");
				choice = grapes.nextInt();
				//
				switch(choice) {
				// case 1 : Takes user back to the main menu
				case 1:
					savingsAccountMenu();
					break;
					// case 2
				case 2:
					//
					System.out.println("Thank you for using EQKi Financial Services.\n");
					System.exit(2);
					//
					break;
				default:
					//
					System.err.println("Oopps! You have chosen the wrong option, please choose correct option");
					break;
					}
				
			} catch (Exception e) {
				//
				System.err.println("\nInvalid entry : Please enter an integer value. " );
				grapes.nextLine();
				//
			}
		}
		/*
		 * Savings Account Main Menu has all the available transactions for cheque account.
		 */
		public void savingsAccountMenu() { 
			
			do { 
				//
				try { savingsAccountHeader();// savings Account Branding
				//Savings Account main menu
				System.out.println("\nsavings Account");
				//
				System.out.println("\n1. Transfer Funds.");
				System.out.println("2. Pay Beneficiaires.");
				System.out.println("3. CashSend.");
				System.out.println("4. Purchase.");
				System.out.println("5. Transaction History.");
				System.out.println("6. Back to Accounts Menu");
				System.out.println("7. Exit Application.");
				//
				System.out.print("\nEnter your choice : ");
				choice = grapes.nextInt();
				//Switch case used for savings account main menu
				switch (choice) {
				// Case 1 : Transfers Fund between two accounts form savings to Savings
				case 1:
					savingsTransfers();
					break;
				// Case 2 : Pay beneficiaries, for user to be able to pay beneficiaries
				case 2:
					//
					savingsBeneficiaries();
					break;
				// case 3: Option 3 is for Cashsend transactions
				case 3:
					savingsCashSend();
					break;
				//
				case 4:
					savingsPurchase();
					break;
				//
				case 5:
					savingsTranstactionHistory();
				case 6:
					accountsMenu();
					break;
				case 7:
					login();
					break;
				default: 
					//
					System.err.println("Ooops invalid option");
					savingsAccountMenu();
					break;
					}
				}
				//
				catch (Exception e) {
					//
					System.err.println("\nMessage : " + e + "Invalid entry : Please enter an integer value. " );
					grapes.nextLine();
					//
				}
				
			} while (choice < 7); 
		}
		/*
		 * Transfers
		 * */
		public void savingsTransfers() {
			//
			try {
				//
				savingsAccountHeader();
				//Displays to user maximum amount that they can transfer
				System.out.println("\n\tMaximum Transferable Amount\t: " + moneyFormat.format(savings.getSavingsBalance()));
				System.out.print("\nEnter amount that you want to transfer\t:" );
				amount = grapes.nextInt();
				//
				if (savings.getSavingsBalance() - amount >= 0) {// this line of code checks if the user is not trying to transfer larger than available amount
					// code below as user to confrim and verify the payment
					System.out.println("\nPlease confirm that you would like to Transfer\t: ");
					System.out.println("\n\tTransfer from Savings Account\t: " + savings.getSavingsAccountNo() );
					System.out.println("\tTransfer to Cheque Account\t: " + cheque.getChequeAccountNo() );
					System.out.println("\tAmount\t\t\t\t: " + moneyFormat.format(amount));
					//
					System.out.println("\nEnter 1 to confrim transfer");
					System.out.println("Enter 2 to cancel transfer");
					//
					System.out.print("\nEnter your choice : ");
					choice = grapes.nextInt();
					//
					switch (choice) {
					// Case 1 : User confirms that the would like to proceed with transferring funds to the savings account
					case 1:
						// code below credits the money out of the Savings Account
						double savingsAccountBalance = savings.getSavingsBalance() - amount;// chequeBalance is used to new balance after user inputs amount they wish to transfer
						savings.setSavingsBalance(savingsAccountBalance);; // sets the new cheque account balance
						//
						System.out.println("\nTransaction successfull");//tells user know that the transaction was successful
						System.out.println("New Savings Account Balance : " + moneyFormat.format(savings.getSavingsBalance())); // diplays to user the new bank balance
						// code below debits the savings account
						double chequeAccountBalance = cheque.getChequeBalance() + amount;
						cheque.setChequeBalance(chequeAccountBalance);
						/*
						 * Transaction History
						 * */
						for (int i = 0; i < 1;i++) {
							//
							LocalDate localtransactionDate = LocalDate.now();//this line of code used get the current date
							String transactionDate = localtransactionDate.toString();//This line of code is used to convert the date to a string
							savingsTransactionsHistory.setTransactionDate(transactionDate);;//this line of code is used to set date to the array
							//
							String transactionDescription = "Transfer - To cheque account";// this line of code is the name of the transaction
							savingsTransactionsHistory.setTransactionDescription(transactionDescription);; // this line of sets the transaction name 
							//
							double transactionAmount = amount;//this line of code is the monetary value of the transaction, its set at R5 because lottery is a fixed amount
							savingsTransactionsHistory.setTransactionAmount(transactionAmount);;
							//
							String transactionID = "TRA00" + randomID + "SAV";
							savingsTransactionsHistory.setTransactionID(transactionID);
							randomID++;
							//
							String transactionType = "Debit";
							savingsTransactionsHistory.setTransactionType(transactionType);
							// line of code below creates a new array
							newSavingsTransactions.add(new SavingsTransactionsHistory(transactionDate, transactionDescription, transactionAmount, transactionID,transactionType)); 
							//
							for(SavingsTransactionsHistory savingsTrans : newSavingsTransactions) {
								//
								System.out.println("\nTransaction Details : ");
								//
								System.out.println("\n\tTransaction ID\t\t: " + newSavingsTransactions.get(i).getTransactionID());
								System.out.println("\tTransaction Date\t: " + newSavingsTransactions.get(i).getTransactionDate());
								System.out.println("\tTransaction Name\t: " + newSavingsTransactions.get(i).getTransactionDescription());
								System.out.println("\tDR or CR Bank\t\t: " + newSavingsTransactions.get(i).getTransactionType());
								System.out.println("\tBeneficiary Account\t: " + moneyFormat.format(newSavingsTransactions.get(i).getTransactionAmount()));
								//
								break;
								}
						}
						//
						for (int i = 0; i < 1;i++) {
							//
							LocalDate localtransactionDate = LocalDate.now();//this line of code used get the current date
							String transactionDate = localtransactionDate.toString();//This line of code is used to convert the date to a string
							chequeTransactionsHistory.setTransactionDate(transactionDate);;//this line of code is used to set date to the array
							//
							String transactionDescription = "CashSend - Once of payement";// this line of code is the name of the transaction
							chequeTransactionsHistory.setTransactionDescription(transactionDescription);; // this line of sets the transaction name 
							//
							double transactionAmount = amount;//this line of code is the monetary value of the transaction, its set at R5 because lottery is a fixed amount
							chequeTransactionsHistory.setTransactionAmount(transactionAmount);;
							//
							String transactionID = "TRA00" + randomID + "CH";
							chequeTransactionsHistory.setTransactionID(transactionID);
							randomID++;
							//
							String transactionType = "Credit";
							chequeTransactionsHistory.setTransactionType(transactionType);
							// line of code below creates a new array
							newChequeTransactions.add(new ChequeTransactionsHistory(transactionDate, transactionDescription, transactionAmount, transactionID,transactionType)); 
							//
						}
						//
						savingsContinueTransaction();//This method asks the user if the want to continue with transactions or to close the app
						//
						break;
					// 	Case 2 : Case 2 (Option 2), takes the use back to the accounts menu.
					case 2:
						accountsMenu();
						break;
					}
				// the line of code below is called if the amount that the user would like to transfer is less than the available balance amount
				} else if (savings.getSavingsBalance() -  amount < 0) { 
					//
					System.err.println("\nTransaction unsucessfull : Insufficent funds");
					savingsTransfers();
				}
			}
			catch (Exception e) {
				//
				System.err.println("\nMessages : " + e + " Invalid entry : Please enter an integer value. " );
				grapes.nextLine();
				//
				savingsTransfers();
				//
			}
			
		}
		/*
		 * Beneficiaries
		 * 
		 * */
		
		// savings beneficiaries transactions
		public void savingsBeneficiaries() {
			
			do {
				//
				savingsAccountHeader();
				// Pay beneficiaries sub menu
				System.out.println("\nPay Beneficiaires");
				System.out.println("\n1. Once off Payment.");
				System.out.println("2. Pay exisiting benficiary.");
				System.out.println("3. Create new benefciary.");
				System.out.println("4. Remove a Beneficiary.");
				System.out.println("5. View all Beneficiaries");
				System.out.println("6. Back");
				//
				System.out.print("\nEnter your choice\t: ");
				choice = grapes.nextInt();
				//
				switch (choice ) {
				// case 1 : Once of Payment, for user to be able to make a once off payment
				case 1:
					savingsOnceOffBeneficiaryPayment();
					break;
				// case 2 : Pay existing beneficiar
				case 2:
					//
					savingsPayExistingBeneficiary();
					break;
				// case 3 : Create a new Beneficiary
				case 3:
					createNewBeneficiary();
					//
					break;
				// removes beneficiary
				case 4:
					removeBeneficiary();
					//
					break;
				// displays all the current beneficiaries
				case 5:
					savingsBeneficiaryDisplay();
					break;
				// takes user back to cheque account menu
				case 6:
					savingsAccountMenu();
					break;
				default:
					System.err.println("Ooops! Please select option by entering the correct number,");
					savingsBeneficiaries();
					break;
				}
				try {
					
				} catch (Exception e) {
					//
					System.out.println("\nMessages : " + e + " invalid entry : Please enter an integer value. " );
					grapes.nextLine();
					//
					savingsBeneficiaries();
				}
				
			} while(choice < 7);
		}
		// once off beneficiary payment
		public void savingsOnceOffBeneficiaryPayment() {
			//
			try {
				//
				chequeAccountHeader();
				//
				System.out.println("\nOnce Off Payment");
				//Displays to user maximum amount that they can transfer
				System.out.println("\nPlease enter recipient account details : ");
				System.out.print("\nReccipient Account Number : ");
				long recipientAccountNumber = grapes.nextLong();
				//
				System.out.print("\nPlease enter recipient bank :");
				String recipientBank = grapes.next();
				//
				System.out.println("\n\tMaximum Amount payabale\t:" + moneyFormat.format(savings.getSavingsBalance()));
				System.out.print("\nEnter amount that you want to make once off payment :" );
				amount = grapes.nextInt();
				//
				if (savings.getSavingsBalance() - amount >= 0) {// this line of code checks if the user is not trying to transfer larger than available amount
					// code below as user to confrim and verify the payment
					System.out.println("\nPlease confirm that you would like to Pay Beneficiary :");
					System.out.println("\n\tPay from Savings Account\t\t: " + savings.getSavingsAccountNo());
					System.out.println("\tRecipient Account Number1\t: " + recipientAccountNumber);
					System.out.println("\tAmount\t\t\t\t: " + moneyFormat.format(amount));
					//
					System.out.println("\nEnter 1 to confrim payment");
					System.out.println("Enter 2 to cancel");
					//
					System.out.print("\nEnter your choice : ");
					choice = grapes.nextInt();
					//
					switch (choice) {
					// Case 1 : User confirms that the would like to proceed with transferring funds to the savings account
					case 1:
						// code below credits the money out of the Cheque Account
						double savingsAccountBalance = savings.getSavingsBalance() - amount;// chequeBalance is used to new balance after user inputs amount they wish to transfer
						savings.setSavingsBalance(savingsAccountBalance);; // sets the new cheque account balance
						//
						System.out.println("\nTransaction successfull");//tells user know that the transaction was successful
						System.out.println("\nNew Savings Account Balance : " + moneyFormat.format(savings.getSavingsBalance())); // diplays to user the new bank balance
						/*
						 * Transaction History
						 * */
						for (int i = 0; i < 1;i++) {
							//
							LocalDate localtransactionDate = LocalDate.now();//this line of code used get the current date
							String transactionDate = localtransactionDate.toString();//This line of code is used to convert the date to a string
							savingsTransactionsHistory.setTransactionDate(transactionDate);;//this line of code is used to set date to the array
							//
							String transactionDescription = "Beneficiary Payment - Once of payement";// this line of code is the name of the transaction
							savingsTransactionsHistory.setTransactionDescription(transactionDescription);; // this line of sets the transaction name 
							//
							double transactionAmount = amount;//this line of code is the monetary value of the transaction, its set at R5 because lottery is a fixed amount
							savingsTransactionsHistory.setTransactionAmount(transactionAmount);;
							//
							String transactionID = "BEN00" + randomID + "CH";
							savingsTransactionsHistory.setTransactionID(transactionID);
							randomID++;
							//
							String transactionType = "Debit";
							savingsTransactionsHistory.setTransactionType(transactionType);
							// line of code below creates a new array
							newSavingsTransactions.add(new SavingsTransactionsHistory(transactionDate, transactionDescription, transactionAmount, transactionID,transactionType)); 
							//
							for(SavingsTransactionsHistory savingsTrans : newSavingsTransactions) {
								//
								System.out.println("\nTransaction Details : ");
								//
								System.out.println("\n\tTransaction ID\t\t: " + newSavingsTransactions.get(i).getTransactionID());
								System.out.println("\tTransaction Date\t: " + newSavingsTransactions.get(i).getTransactionDate());
								System.out.println("\tTransaction Name\t: " + newSavingsTransactions.get(i).getTransactionDescription());
								System.out.println("\tDR or CR Bank\t\t: " + newSavingsTransactions.get(i).getTransactionType());
								System.out.println("\tBeneficiary Account\t: " + moneyFormat.format(newSavingsTransactions.get(i).getTransactionAmount()));
								//
								break;
								}
						}
						//
						savingsContinueTransaction();//This method asks the user if the want to continue with transactions or to close the app
						//
						break;
					// 	Case 2 : Case 2 (Option 2), takes the use back to the accounts menu.
					case 2:
//						accounts.accountMenu();
						break;
					}
				// the line of code below is called if the amount that the user would like to transfer is less than the available balance amount
				} else if (savings.getSavingsBalance() -  amount < 0) { 
					//
					System.err.println("\nTransaction unsucessfull : Insufficent funds");
//					accounts.login();
				}
			}
			catch (Exception e) {
				//
				System.out.println("\nMessages : " + e + " Invalid entry : Please enter an integer value. " );
				grapes.nextLine();
				//
				savingsOnceOffBeneficiaryPayment();
				//
			}
			
		}
		// Method below is used to pay existing beneficiaries
		public void savingsPayExistingBeneficiary() {
			//
			try {
				//
				//
				savingsAccountHeader();
				//
				System.out.println("\nPay Exisiting Beneficiary : ");//Header
				//
				System.out.println("\nBeneficiary List :");
				//code below is to represent the current beneficiary list
				for (int i = 0; i < newBeneficiary.size(); i++) { // for each loop to display the current beneficiary list
					//
					System.out.println("\nUnique Beneficiary code :" + i);
					System.out.println("Beneficiary Name\t\t: " + newBeneficiary.get(i).getBeneficiaryName());
					System.out.println("Beneficiary Account Number\t: " + newBeneficiary.get(i).getBeneficiaryAccountNumber());
					System.out.println("Beneficiary Bank\t\t: " + newBeneficiary.get(i).getBeneficiaryBank());
					//
					System.out.println("");	
					}		
				//Line of code below is a scanner for the user to choose the beneficiary that the would like to delete using the unique beneficiary code
				System.out.print("\nTo choose a beneficiary, enter beneficiary unqiue code: ");
				int beneficiaryCode = grapes.nextInt();
				// if the user enter 
				if (beneficiaryCode >= 0 && beneficiaryCode < newBeneficiary.size() ) {
					//The code below retrieves all the selected beneficiaries information
					newBeneficiary.get(beneficiaryCode);
					System.out.println("\n\tYou have choosen to pay\t\t: " + newBeneficiary.get(beneficiaryCode).getBeneficiaryName());
					System.out.println("\tBeneficiary Account\t\t: " + newBeneficiary.get(beneficiaryCode).getBeneficiaryAccountNumber());
					System.out.println("\tBeneficiary Bank\t\t: " + newBeneficiary.get(beneficiaryCode).getBeneficiaryBank());
					//
					System.out.print("\nPlease enter amount that you would like to pay beneficiaty : ");
					int amount = grapes.nextInt();
					//
					if (savings.getSavingsBalance() - amount >= 0) {// this line of code checks if the user is not trying to transfer larger than available amount
						// code below as user to confrim and verify the payment
						savingsAccountHeader();
						//
						System.out.println("\nPlease confirm that you would like to Pay beneficiary:");
						System.out.println("\n\tYou have choosen to pay beneficiary\t: " + newBeneficiary.get(beneficiaryCode).getBeneficiaryName());
						System.out.println("\tBeneficiary Account Number\t\t: " + newBeneficiary.get(beneficiaryCode).getBeneficiaryAccountNumber());
						System.out.println("\tFrom Savings Account\t\t\t: " + savings.getSavingsAccountNo());
						System.out.println("\tAmount\t\t\t\t\t: " + moneyFormat.format(amount));
						//
						System.out.println("\nEnter 1 to confrim to pay beneficiary");
						System.out.println("Enter 2 to cancel");
						//
						System.out.print("\nEnter your choice : ");
						choice = grapes.nextInt();
						//
						switch (choice) {
						// Case 1 : User confirms that the would like to proceed with transferring funds to the savings account
						case 1:
							//
							savingsAccountHeader();
							//
							// code below credits the money out of the Cheque Account
							double savingsAccountBalance = savings.getSavingsBalance() - amount;// chequeBalance is used to new balance after user inputs amount they wish to transfer
							savings.setSavingsBalance(savingsAccountBalance);;; // sets the new cheque account balance
							//
							System.out.println("\nTransaction successfull.");//tells user know that the transaction was successful
							System.out.println("New Savings Account Balance : " + moneyFormat.format(savings.getSavingsBalance())); // Displays to user the new bank balance
							//
							/*
							 * Transaction History
							 * */
							for (int i = 0; i < 1;i++) {
								//
								LocalDate localtransactionDate = LocalDate.now();//this line of code used get the current date
								String transactionDate = localtransactionDate.toString();//This line of code is used to convert the date to a string
								savingsTransactionsHistory.setTransactionDate(transactionDate);;//this line of code is used to set date to the array
								//
								String transactionDescription = "Beneficiary Payment ";// this line of code is the name of the transaction
								savingsTransactionsHistory.setTransactionDescription(transactionDescription);; // this line of sets the transaction name 
								//
								double transactionAmount = amount;//this line of code is the monetary value of the transaction, its set at R5 because lottery is a fixed amount
								savingsTransactionsHistory.setTransactionAmount(transactionAmount);;
								//
								String transactionID = "TRA00" + randomID + "SA";
								savingsTransactionsHistory.setTransactionID(transactionID);
								randomID++;
								//
								String transactionType = "Debit";
								savingsTransactionsHistory.setTransactionType(transactionType);
								// line of code below creates a new array
								newSavingsTransactions.add(new SavingsTransactionsHistory(transactionDate, transactionDescription, transactionAmount, transactionID,transactionType)); 
								//
								for(ChequeTransactionsHistory savingsTrans : newChequeTransactions) {
									//
									System.out.println("\nTransaction Details : ");
									//
									System.out.println("\n\tTransaction ID\t\t: " + newSavingsTransactions.get(i).getTransactionID());
									System.out.println("\tTransaction Date\t: " + newSavingsTransactions.get(i).getTransactionDate());
									System.out.println("\tTransaction Name\t: " + newSavingsTransactions.get(i).getTransactionDescription());
									System.out.println("\tDR or CR Bank\t\t: " + newSavingsTransactions.get(i).getTransactionType());
									System.out.println("\tBeneficiary Account\t: " + moneyFormat.format(newSavingsTransactions.get(i).getTransactionAmount()));
									//
									break;
									}
							}
							//
							savingsContinueTransaction();//This method asks the user if the want to continue with transactions or to close the application
							//
							break;
						// 	Case 2 : Case 2 (Option 2), takes the use back to the accounts menu.
						case 2:
//							accounts.login();
							break;
						default:
							System.err.println("Oooppps please choose correct option");
							break;
							}
						// the line of code below is called if the amount that the user would like to transfer is less than the available balance amount
							} else if (savings.getSavingsBalance() -  amount < 0) { 
								//
								savingsAccountHeader();
								//
								System.out.println("\nTransaction unsucessful : Insufficent funds");
//								accounts.login();
							}
							// code below is to notify the user that they inputed the wrong details
							} else {
								//
								System.err.println("Ooops! Invalid Beneficiary code. .");
							}
							//
							savingsContinueTransaction();
				
			}
			catch (Exception e) {
				//
				System.out.println("\nMessages : " + e + " Invalid entry : Please enter an integer value. " );
				grapes.nextLine();
				//
				savingsPayExistingBeneficiary();
				//
			}
			
		}
		//Remove beneficiary which allows user to remove a beneficiary
			public void savingsBeneficiaryDisplay() {
				//
				savingsAccountHeader();
				//
				System.out.println("\nCurrent beneficiary list :");
				//
				for (int i = 0; i < newBeneficiary.size(); i++) { // for each loop to display the current beneficiary list
					//
					System.out.println("\nUnique Beneficiary code :" + i);
					System.out.println("Beneficiary Name\t\t: " + newBeneficiary.get(i).getBeneficiaryName());
					System.out.println("Beneficiary Account Number\t: " + newBeneficiary.get(i).getBeneficiaryAccountNumber());
					System.out.println("Beneficiary Bank\t\t: " + newBeneficiary.get(i).getBeneficiaryBank());
					//
					System.out.println("");
					//
					savingsAccountMenu();
				}
				//
			}
		/*
		 * Cheque cashsend
		 * 
		 * */
			public void savingsCashSend() {
				//
				try {
					//
					savingsAccountHeader();
					//
					System.out.println("Cash Send ");
					//
					System.out.println("\n1. Once off CashSend.");
					System.out.println("2. Send myself a CashSend.");
					System.out.println("3. Pay existing a CashSend Beneficiary");
					System.out.println("4. Create CashSend Beneficiary");
					System.out.println("5. Remove a Beneficiary.");
					System.out.println("6. View CashSend Beneficiaries");
					System.out.println("7. Back");
					//
					System.out.println("\nEnter your choice");
					choice = grapes.nextInt();
					//
					switch(choice) {
					// case 1 : Option 1, Once off Cash Send 
					case 1:
						//
						savingsOnceOffCashSend();
						//
						break;
					// case 2 : Option 2, Cash send mySelf
					case 2:
						savingsCashSendMyself();
						break;
					// case 3 : Option 3, pay a cash send beneficiary
					case 3:
						//
						savingsPayExistingCashSendBeneficiary();
						break;
					// case 4 : Option 4, create a cash send beneficiary
					case 4:
						createCashSendBeneficiary();
						break;
					// case 5: Option 5, remove a cash send beneficiary
					case 5:
						removeCashSendBeneficiary();
						break;
					// case 6 : Option 6, go back to previous menu
					case 6:
						displayCashSendBeneficiary();
						break;
					case 7: 
						savingsAccountMenu();
						break;
					default:
						System.err.println("Opps you choose the wrong option, please choose correct option");
						break;
					}
					
				}
				catch (Exception e) {
					//
					System.out.println("\nMessages : " + e + " Invalid entry : Please enter an integer value. " );
					grapes.nextLine();
					//
					savingsCashSend();
				}
				
			}
			//
			public void savingsOnceOffCashSend() {
				//
				try {
					//
					//
					savingsAccountHeader();
					//
					System.out.println("\nOnce OffCashSend");
					//Displays to user maximum amount that they can transfer
					System.out.println("\nPlease enter recipient details\t:");
					System.out.print("\nReccipient Phone Number\t:");
					long recipientPhoneNumber = grapes.nextLong();
					//
					System.out.println("\n\tMaximum CashSend transferable Amount\t:" + moneyFormat.format(savings.getSavingsBalance()));
					System.out.print("\nEnter amount that you want to CashSend :" );
					amount = grapes.nextInt();
					//
					if (savings.getSavingsBalance() - amount >= 0) {// this line of code checks if the user is not trying to transfer larger than available amount
						// code below as user to confrim and verify the payment
						System.out.println("\nPlease confirm that you would like to CashSend :");
						System.out.println("\n\tCashSend from Savings Account\t\t: " + savings.getSavingsAccountNo());
						System.out.println("\tCashSend to Recipient Phone Number\t: " + recipientPhoneNumber);
						System.out.println("\tAmount\t\t\t\t\t: " + moneyFormat.format(amount));
						//
						System.out.println("\nEnter 1 to confrim CashSend");
						System.out.println("Enter 2 to cancel CashSend");
						//
						System.out.print("\nEnter your choice : ");
						choice = grapes.nextInt();
						//
						switch (choice) {
						// Case 1 : User confirms that the would like to proceed with transferring funds to the savings account
						case 1:
							// code below credits the money out of the Cheque Account
							double savingsAccountBalance = savings.getSavingsBalance() - amount;// chequeBalance is used to new balance after user inputs amount they wish to transfer
							savings.setSavingsBalance(savingsAccountBalance);; // sets the new cheque account balance
							//
							System.out.println("\nTransaction successfull");//tells user know that the transaction was successful
							System.out.println("New Savings Account Balance : " + moneyFormat.format(savings.getSavingsBalance())); // diplays to user the new bank balance
							//
							/*
							 * Transaction History
							 * */
							for (int i = 0; i < 1;i++) {
								//
								LocalDate localtransactionDate = LocalDate.now();//this line of code used get the current date
								String transactionDate = localtransactionDate.toString();//This line of code is used to convert the date to a string
								savingsTransactionsHistory.setTransactionDate(transactionDate);;//this line of code is used to set date to the array
								//
								String transactionDescription = "CashSend - Once of payement";// this line of code is the name of the transaction
								savingsTransactionsHistory.setTransactionDescription(transactionDescription);; // this line of sets the transaction name 
								//
								double transactionAmount = amount;//this line of code is the monetary value of the transaction, its set at R5 because lottery is a fixed amount
								savingsTransactionsHistory.setTransactionAmount(transactionAmount);;
								//
								String transactionID = "CASH00" + randomID + "SA";
								savingsTransactionsHistory.setTransactionID(transactionID);
								randomID++;
								//
								String transactionType = "Debit";
								savingsTransactionsHistory.setTransactionType(transactionType);
								// line of code below creates a new array
								newSavingsTransactions.add(new SavingsTransactionsHistory(transactionDate, transactionDescription, transactionAmount, transactionID,transactionType)); 
								//
								for(SavingsTransactionsHistory savingsTrans : newSavingsTransactions) {
									//
									System.out.println("\nTransaction Details : ");
									//
									System.out.println("\n\tTransaction ID\t\t: " + newSavingsTransactions.get(i).getTransactionID());
									System.out.println("\tTransaction Date\t: " + newSavingsTransactions.get(i).getTransactionDate());
									System.out.println("\tTransaction Name\t: " + newSavingsTransactions.get(i).getTransactionDescription());
									System.out.println("\tDR or CR Bank\t\t: " + newSavingsTransactions.get(i).getTransactionType());
									System.out.println("\tBeneficiary Account\t: " + moneyFormat.format(newSavingsTransactions.get(i).getTransactionAmount()));
									//
									break;
									}
							}
							//
							savingsContinueTransaction();//This method asks the user if the want to continue with transactions or to close the application
							//
							break;
							//
						// 	Case 2 : Case 2 (Option 2), takes the use back to the accounts menu.
						case 2:
							savingsAccountMenu();
							break;
						}
					// the line of code below is called if the amount that the user would like to transfer is less than the available balance amount
					} else if (savings.getSavingsBalance() -  amount < 0) { 
						//
						savingsAccountHeader();
						//
						System.err.println("\nTransaction unsucessfull : Insufficent funds");
						savingsAccountMenu();
					}
				}
				catch (Exception e) {
					//
					System.out.println("\nMessages : " + e + " Invalid entry : Please enter an integer value. " );
					grapes.nextLine();
					//
					savingsOnceOffCashSend() ;
				}
			}
			// User sends cash send to themselves 
			public void savingsCashSendMyself() {
				//
				try {
					//
					//
					chequeAccountHeader();
					//
					System.out.println("\nSend Myself CashSend");
					//
					System.out.println("\n\tMaximum Transferable Amount\t:" + moneyFormat.format(savings.getSavingsBalance()));
					System.out.print("\nEnter amount that you want to CashSend :" );
					amount = grapes.nextInt();
					//
					if (savings.getSavingsBalance() - amount >= 0) {// this line of code checks if the user is not trying to transfer larger than available amount
						// code below as user to confrim and verify the payment
						System.out.println("\nPlease confirm that you would like to CashSend :");
						System.out.println("\n\tCashSend from Savings Account\t\t: " + savings.getSavingsAccountNo());
						System.out.println("\tCashSend to Recipient Phone Number\t: " + savings.getUserPhone());
						System.out.println("\tAmount\t\t\t\t\t: " + moneyFormat.format(amount));
						//
						System.out.println("\nEnter 1 to confrim CashSend");
						System.out.println("Enter 2 to cancel CashSend");
						//
						System.out.print("\nEnter your choice : ");
						choice = grapes.nextInt();
						//
						switch (choice) {
						// Case 1 : User confirms that the would like to proceed with transferring funds to the savings account
						case 1:
							// code below credits the money out of the Cheque Account
							double savingsAccountBalance = savings.getSavingsBalance() - amount;// chequeBalance is used to new balance after user inputs amount they wish to transfer
							savings.setSavingsBalance(savingsAccountBalance);; // sets the new cheque account balance
							//
							System.out.println("\nTransaction successfull");//tells user know that the transaction was successful
							System.out.println("New Savings Account Balance : " + moneyFormat.format(savings.getSavingsBalance())); // diplays to user the new bank balance
							/*
							 * Transaction History
							 * */
							for (int i = 0; i < 1;i++) {
								//
								LocalDate localtransactionDate = LocalDate.now();//this line of code used get the current date
								String transactionDate = localtransactionDate.toString();//This line of code is used to convert the date to a string
								savingsTransactionsHistory.setTransactionDate(transactionDate);;//this line of code is used to set date to the array
								//
								String transactionDescription = "CashSend - Myself";// this line of code is the name of the transaction
								savingsTransactionsHistory.setTransactionDescription(transactionDescription);; // this line of sets the transaction name 
								//
								double transactionAmount = amount;//this line of code is the monetary value of the transaction, its set at R5 because lottery is a fixed amount
								savingsTransactionsHistory.setTransactionAmount(transactionAmount);;
								//
								String transactionID = "CAS00" + randomID + "CH";
								savingsTransactionsHistory.setTransactionID(transactionID);
								randomID++;
								//
								String transactionType = "Debit";
								savingsTransactionsHistory.setTransactionType(transactionType);
								// line of code below creates a new array
								newSavingsTransactions.add(new SavingsTransactionsHistory(transactionDate, transactionDescription, transactionAmount, transactionID,transactionType)); 
								//
								for(SavingsTransactionsHistory savingTrans : newSavingsTransactions) {
									//
									System.out.println("\nTransaction Details : ");
									//
									System.out.println("\n\tTransaction ID\t\t: " + newSavingsTransactions.get(i).getTransactionID());
									System.out.println("\tTransaction Date\t: " + newSavingsTransactions.get(i).getTransactionDate());
									System.out.println("\tTransaction Name\t: " + newSavingsTransactions.get(i).getTransactionDescription());
									System.out.println("\tDR or CR Bank\t\t: " + newSavingsTransactions.get(i).getTransactionType());
									System.out.println("\tBeneficiary Account\t: " + moneyFormat.format(newSavingsTransactions.get(i).getTransactionAmount()));
									//
									break;
									}
							}
							 //
							savingsContinueTransaction();//This method asks the user if the want to continue with transactions or to close the app
							//
							break;
						// 	Case 2 : Case 2 (Option 2), takes the use back to the accounts menu.
						case 2:
							savingsAccountMenu();
							break;
						}
					// the line of code below is called if the amount that the user would like to transfer is less than the available balance amount
					} else if (cheque.getChequeBalance() -  amount < 0) { 
						//
						chequeAccountHeader();
						//
						System.err.println("\nTransaction unsucessfull : Insufficent funds");
						chequeAccountMenu();
					}
				} catch (Exception e) {
					//
					System.err.println("\nMessage : " + e + " Invalid entry : Please enter an integer value. " );
					grapes.nextLine();
					//
					savingsCashSendMyself() ;
				}
				
			
			}
			// pay existing cashsend beneficiary
			public void payExistingCashSendBeneficiary() {
				//
				try {
					//
					System.out.println("\nPay Exisiting CashSend Beneficiary : ");//Header
					//
					System.out.println("\nCashSend Beneficiary List :");
					//code below is to represent the current beneficiary list
					for (int i = 0; i < newCashSend.size(); i++) { // for each loop to display the current beneficiary list
						//
						System.out.println("\nUnique Beneficiary code :" + i);
						System.out.println("Beneficiary Name\t\t: " + newCashSend.get(i).getCashSendName());
						System.out.println("Beneficiary Account Number\t: " + newCashSend.get(i).getCashSendPhoneNumber());
						//
						System.out.println("");
					}
					//Line of code below is a scanner for the user to choose the beneficiary that the would like to delete using the unique beneficiary code
					System.out.print("\nTo choose a CashSend beneficiary, enter CashSend beneficiary unqiue code: ");
					int beneficiaryCode = grapes.nextInt();
					//// if the user enter 
					if (beneficiaryCode >= 0 && beneficiaryCode < newCashSend.size() ) {
						//The code below retrieves all the selected beneficiaries information
						newCashSend.get(beneficiaryCode);
						System.out.println("\n\tYou have choosen to CashSend\t\t: " + newCashSend.get(beneficiaryCode).getCashSendName());
						System.out.println("\tCashSen Phone Number\t\t\t: 0" + newCashSend.get(beneficiaryCode).getCashSendPhoneNumber());
						//
						System.out.print("\nPlease enter amount that you would like to CashSend Beneficiary\t: ");
						int amount = grapes.nextInt();
						//
						if (cheque.getChequeBalance() - amount >= 0) {// this line of code checks if the user is not trying to transfer larger than available amount
							// code below as user to confrim and verify the payment
							//
							chequeAccountHeader();
							//
							System.out.println("\nPlease confirm that you would like to CashSend\t:");
							System.out.println("\n\tYou have choosen to CashSend beneficiary\t: " + newCashSend.get(beneficiaryCode).getCashSendName());
							System.out.println("\tBeneficiary Phone Number\t\t\t: 0" + newCashSend.get(beneficiaryCode).getCashSendPhoneNumber());
							System.out.println("\tFrom Cheque Account\t\t\t\t: " + cheque.getChequeAccountNo());
							System.out.println("\tAmount\t\t\t\t\t\t: " + moneyFormat.format(amount));
							//
							System.out.println("\nEnter 1 to confrim transfer");
							System.out.println("Enter 2 to cancel transfer");
							//
							System.out.print("\nEnter your choice : ");
							choice = grapes.nextInt();
							//
							switch (choice) {
							// Case 1 : User confirms that the would like to proceed with transferring funds to the savings account
							case 1:
								//
								chequeAccountHeader();
								//
								// code below credits the money out of the Cheque Account
								double chequeAccountBalance = cheque.getChequeBalance() - amount;// chequeBalance is used to new balance after user inputs amount they wish to transfer
								cheque.setChequeBalance(chequeAccountBalance);; // sets the new cheque account balance
								//
								System.out.println("\nTransaction successfull.");//tells user know that the transaction was successful
								System.out.println("New Cheque Account Balance : " + moneyFormat.format(cheque.getChequeBalance())); // Displays to user the new bank balance
								/*
								 * Transaction History
								 * */
								for (int i = 0; i < 1;i++) {
									//
									LocalDate localtransactionDate = LocalDate.now();//this line of code used get the current date
									String transactionDate = localtransactionDate.toString();//This line of code is used to convert the date to a string
									chequeTransactionsHistory.setTransactionDate(transactionDate);;//this line of code is used to set date to the array
									//
									String transactionDescription = "CashSend - Beneficiary";// this line of code is the name of the transaction
									chequeTransactionsHistory.setTransactionDescription(transactionDescription);; // this line of sets the transaction name 
									//
									double transactionAmount = amount;//this line of code is the monetary value of the transaction, its set at R5 because lottery is a fixed amount
									chequeTransactionsHistory.setTransactionAmount(transactionAmount);;
									//
									String transactionID = "CAS00" + randomID + "CH";
									chequeTransactionsHistory.setTransactionID(transactionID);
									randomID++;
									//
									String transactionType = "Debit";
									chequeTransactionsHistory.setTransactionType(transactionType);
									// line of code below creates a new array
									newChequeTransactions.add(new ChequeTransactionsHistory(transactionDate, transactionDescription, transactionAmount, transactionID,transactionType)); 
									//
									for(ChequeTransactionsHistory chequeTrans : newChequeTransactions) {
										//
										System.out.println("\nTransaction Details : ");
										//
										System.out.println("\n\tTransaction ID\t\t: " +chequeTrans.getTransactionID());
										System.out.println("\tTransaction Date\t: " + newChequeTransactions.get(i).getTransactionDate());
										System.out.println("\tTransaction Name\t: " + newChequeTransactions.get(i).getTransactionDescription());
										System.out.println("\tDR or CR Bank\t\t: " + newChequeTransactions.get(i).getTransactionType());
										System.out.println("\tBeneficiary Account\t: " + moneyFormat.format(newChequeTransactions.get(i).getTransactionAmount()));
										//
										break;
										}
								}
								//
								chequeContinueTransaction();//This method asks the user if the want to continue with transactions or to close the app
								//
								break;
							// 	Case 2 : Case 2 (Option 2), takes the use back to the accounts menu.
							case 2:
								chequeAccountMenu();
								break;
							}
						// the line of code below is called if the amount that the user would like to transfer is less than the available balance amount
						} else if (cheque.getChequeBalance() -  amount < 0) { 
							//
							chequeAccountHeader();
							//
							System.err.println("\nTransaction unsucessful : Insufficent funds");
//							accounts.login();
						}
						// code below is to notify the user that they inputed the wrong details
					} else {
						//
						System.err.println("Ooops! Invalid CashSend Beneficiary code. ");
					}
					//
					chequeContinueTransaction();
				}
				catch (Exception e) {
					//
					System.err.println("\nMessages : " + e + "Invalid entry : Please enter an integer value. " );
					grapes.nextLine();
					//
					payExistingCashSendBeneficiary() ;
				}
				//
				
			}
			/*
			 *
			 * Purchases
			 * 
			 * */
			//Cheque purchases menu
			public void savingsPurchase() {
				//
				try {
					//
					//
					savingsAccountHeader();
					// Pay beneficiaries sub menu
					System.out.println("\nPurchases");
					System.out.println("\n1. Airtime");
					System.out.println("2. Electricty");
					System.out.println("3. Lotto");
					System.out.println("4. Back");
					//
					System.out.print("\nEnter your choice : ");
					choice = grapes.nextInt();
					//
					switch (choice) {
					// Air time
					case 1:
						savingsPurchaseAirtime();
						break;
					// Electricity purchase
					case 2:
						savingsPurchasesElectricity();
						break;
					//
					case 3:
						savingsPurchaseLotto();
						break;
					//
					case 4:
						savingsAccountMenu();
						break;
					//
					default:
						System.out.println("Oooop, incorrect option");
						savingsPurchase();
						break;
					}
					
				} catch (Exception e) {
					//
					System.out.println("\nMessage : " + e + "Invalid entry : Please enter an integer value. " );
					grapes.nextLine();
					//
					savingsPurchase() ;
				}
				
			}
			//Airtime method 
			public void savingsPurchaseAirtime() {
				//
				try {
					//
					savingsAccountHeader();
					//
					System.out.println("\nAirtime");
					//
					System.out.println("\nPlease choose a Network Provider : ");
					System.out.println("\n1. Mtn");
					System.out.println("2. Vodacom");
					System.out.println("3. Telkom");
					System.out.println("4. CellC");
					//
					System.out.print("\nEnter your choice : ");
					int choice = grapes.nextInt();
					//
					switch (choice ) {
					// Case 1 : Option 1 Vodacom air time 
					case 1:
						//
						savingsAccountHeader();
						//
						System.out.println("\nMTN");
						//
						airtimePurchaseProcess();
						//
						
					break;
					// Case 2: Option 2 purchase Vodacom
					case 2:
						//
						savingsAccountHeader();
						//
						System.out.println("\nVodacom");
						//
						airtimePurchaseProcess();
						//
						break;
					// case 3: option 3 purchase Telkom;
					case 3:
						//
						savingsAccountHeader();
						//
						System.out.println("\nTelkom");
						//
						airtimePurchaseProcess();
						//
						break;
					// case 4: Option 4 purchase CellC
					case 4:
						//
						savingsAccountHeader();
						//
						System.out.println("\nCellC");
						//
						airtimePurchaseProcess();
						//
						break;
					default:
						System.out.println("Opps, invalid selection");
						savingsPurchaseAirtime();
						break;
					}
				}
				catch (Exception e) {
					//
					System.out.println("\nInvalid entry : Please enter an integer value. " );
					grapes.nextLine();
					//
					savingsPurchaseAirtime() ;
				}
				
			}
			//Electricity
			public void savingsPurchasesElectricity() {
				//
				try {
					//
					//
					savingsAccountHeader();
					//
					System.out.println("\nElectricity");
					//
					System.out.println("\nMaximum Airtime Purchase amount available\t:" + moneyFormat.format(cheque.getChequeBalance()));
					System.out.print("\nPlease enter Electricity Amount:");
					amount = grapes.nextInt();
					//
					if (cheque.getChequeBalance() - amount >= 0) {
						//
						System.out.print("\nPlease enter Recipients meter Number: ");
						int phoneNumber = grapes.nextInt();
						//
						System.out.println("\nYou would like to purchase electricity:");
						System.out.println("\n\tMeter Number\t\t: " + phoneNumber);
						System.out.println("\tAmount\t\t\t: " + moneyFormat.format(amount));
						System.out.println("\tMegawatts purchased\t:" + (amount/2));
						//
						System.out.println("\nEnter 1 to confrim to purchase Electricity");
						System.out.println("Enter 2 to cancel Electricity purchase");
						//
						System.out.print("\nEnter your choice : ");
						choice = grapes.nextInt();
						//
						switch (choice) {
						// Case 1 : User confirms that the would like to proceed with transferring funds to the savings account
						case 1:
							// code below credits the money out of the Cheque Account
							double chequeAccountBalance = cheque.getChequeBalance() - amount;// chequeBalance is used to new balance after user inputs amount they wish to transfer
							cheque.setChequeBalance(chequeAccountBalance);; // sets the new cheque account balance
							//
							int randomNum = (int)(Math.random() *99999);  // 0 to 100
							System.out.println("\nPurchase successfull:");//tells user know that the transaction was successful
							System.out.println("\n\tElectricity Token\t:"  + randomNum);
							System.out.println("\tMegawatts purchased\t:" + (amount/2) + "kw");
							System.out.println("\nNew Cheque Account Balance : " + moneyFormat.format(cheque.getChequeBalance())); // diplays to user the new bank balance
							/*
							 * Transaction History
							 * */
							for (int i = 0; i < 1;i++) {
								//
								LocalDate localtransactionDate = LocalDate.now();//this line of code used get the current date
								String transactionDate = localtransactionDate.toString();//This line of code is used to convert the date to a string
								savingsTransactionsHistory.setTransactionDate(transactionDate);;//this line of code is used to set date to the array
								//
								String transactionDescription = "Electricity ";// this line of code is the name of the transaction
								savingsTransactionsHistory.setTransactionDescription(transactionDescription);; // this line of sets the transaction name 
								//
								double transactionAmount = amount;//this line of code is the monetary value of the transaction, its set at R5 because lottery is a fixed amount
								savingsTransactionsHistory.setTransactionAmount(transactionAmount);;
								//
								String transactionID = "TRA" + randomID + "SA";
								savingsTransactionsHistory.setTransactionID(transactionID);
								randomID++;
								//
								String transactionType = "Debit";
								savingsTransactionsHistory.setTransactionType(transactionType);
								// line of code below creates a new array
								newSavingsTransactions.add(new SavingsTransactionsHistory(transactionDate, transactionDescription, transactionAmount, transactionID,transactionType)); 
								//
								for(SavingsTransactionsHistory chequeTrans : newSavingsTransactions) {
									//
									System.out.println("\nTransaction Details : ");
									//
									System.out.println("\n\tTransaction ID\t\t: " + newSavingsTransactions.get(i).getTransactionID());
									System.out.println("\tTransaction Date\t: " + newSavingsTransactions.get(i).getTransactionDate());
									System.out.println("\tTransaction Name\t: " + newSavingsTransactions.get(i).getTransactionDescription());
									System.out.println("\tDR or CR Bank\t\t: " + newSavingsTransactions.get(i).getTransactionType());
									System.out.println("\tBeneficiary Account\t: " + moneyFormat.format(newSavingsTransactions.get(i).getTransactionAmount()));
									//
									break;
									}
							}
							//
							savingsContinueTransaction();//This method asks the user if the want to continue with transactions or to close the app
							//
							break;
						// 	Case 2 : Case 2 (Option 2), takes the use back to the accounts menu.
						case 2:
							savingsAccountMenu();
							break;
						}
						//
					} else if (savings.getSavingsAccountNo() - amount < 0 ) {
						//
						System.err.println("Transaction unsucessfull, Please enter correct amount");
						//
						savingsPurchaseAirtime();
						//
					}
					
				}
				catch (Exception e) {
					//
					System.err.println("\nMessages : " + e + "Invalid entry : Please enter an integer value. " );
					grapes.nextLine();
					//
					savingsPurchaseAirtime() ;
				}
				
			}
			//
			public void savingsPurchaseLotto() {
				//
				try {
					//
					//
					savingsAccountHeader();
					//
					int amount = 5;
					//
					System.out.println("\nLotto");
					//
					System.out.println("\nEnter 1 : Play Lotto QuickPick for R 5 per draw");
					System.out.println("Enter 2: To Quit");
					//
					System.out.print("\nEnter your choice ");
					choice = grapes.nextInt();
					//
					switch (choice) {
					// case 1 : Option 1, play randmly picked numbers
					case 1:
						// code below credits the money out of the Cheque Account
						double savingsAccountBalance = savings.getSavingsBalance() - 5;// chequeBalance is used to new balance after user inputs amount they wish to transfer
						savings.setSavingsBalance(savingsAccountBalance);; // sets the new cheque account balance
						//
						//
						if (cheque.getChequeBalance() - amount >= 0) {
							//
							savingsAccountHeader();
							//
							System.out.println("\nLotto QuickPick");
							//
							ArrayList<Integer> lottoNumbers = new ArrayList<Integer>();
							//
							int randomNum = (int)(Math.random() * 101);  // 0 to 100
							int randomNum1 = (int)(Math.random() * 101);  // 0 to 100
							int randomNum2 = (int)(Math.random() * 101);  // 0 to 100
							int randomNum3 = (int)(Math.random() * 101);  // 0 to 100
							int randomNum4 = (int)(Math.random() * 101);  // 0 to 100
							int randomNum5 = (int)(Math.random() * 101);  // 0 to 100
							//
							lottoNumbers.add(randomNum);
							lottoNumbers.add(randomNum1);
							lottoNumbers.add(randomNum2);
							lottoNumbers.add(randomNum3);
							lottoNumbers.add(randomNum4);
							lottoNumbers.add(randomNum5);
							//
							System.out.println("\nYour Lotto numbers :\n");
							//
							 for (int i : lottoNumbers) {
								 //
								 System.out.print(i + " ");
							 }
							 //
							 System.out.println("\n\nYou have successfully played Lotto QuickPick");
							 /*
								 * Transaction History
								 * */
								for (int i = 0; i < 1;i++) {
									//
									LocalDate localtransactionDate = LocalDate.now();//this line of code used get the current date
									String transactionDate = localtransactionDate.toString();//This line of code is used to convert the date to a string
									savingsTransactionsHistory.setTransactionDate(transactionDate);;//this line of code is used to set date to the array
									//
									String transactionDescription = "Transfer";// this line of code is the name of the transaction
									savingsTransactionsHistory.setTransactionDescription(transactionDescription);; // this line of sets the transaction name 
									//
									double transactionAmount = amount;//this line of code is the monetary value of the transaction, its set at R5 because lottery is a fixed amount
									savingsTransactionsHistory.setTransactionAmount(transactionAmount);;
									//
									String transactionID = "TRA" + randomID + "SA";
									savingsTransactionsHistory.setTransactionID(transactionID);
									randomID++;
									//
									String transactionType = "Debit";
									savingsTransactionsHistory.setTransactionType(transactionType);
									// line of code below creates a new array
									newSavingsTransactions.add(new SavingsTransactionsHistory(transactionDate, transactionDescription, transactionAmount, transactionID,transactionType)); 
									//
									for(SavingsTransactionsHistory savingsTrans : newSavingsTransactions) {
										//
										System.out.println("\nTransaction Details : ");
										//
										System.out.println("\n\tTransaction ID\t\t: " +newSavingsTransactions.get(i).getTransactionID());
										System.out.println("\tTransaction Date\t: " + newSavingsTransactions.get(i).getTransactionDate());
										System.out.println("\tTransaction Name\t: " + newSavingsTransactions.get(i).getTransactionDescription());
										System.out.println("\tDR or CR Bank\t\t: " + newSavingsTransactions.get(i).getTransactionType());
										System.out.println("\tBeneficiary Account\t: " + moneyFormat.format(newSavingsTransactions.get(i).getTransactionAmount()));
										//
										break;
										}
								}
								//
							 //
							 savingsContinueTransaction();//This method asks the user if the want to continue with transactions or to close the app
							 //
						} else if (savings.getSavingsAccountNo() - amount < 0 ) {
							//
							System.err.println("Transaction unsucessfull insufficuient funds, Lotto Quick pick costs R5,00  per draw");
							//
							savingsPurchaseLotto();
							//
						}
						 //
						break;
					// case 2 : Option 2 quit
					case 2: 
						savingsPurchaseLotto();
						break;
					default:
						//
						System.err.println("Oooppps, you have chosen incorrect option. ");
						break;
					}
					
				} catch (Exception e) {
					//
					System.err.println("\nMessage : " + e + " invalid entry : Please enter an integer value. " );
					grapes.nextLine();
					//
					savingsPurchaseAirtime() ;
				}
				
			}
			//
			public void savingsTranstactionHistory() {
				//
				try {
					//
					//
					savingsAccountHeader();
					//
					System.out.println("\nTransaction History");
					//Code below displays all of the users transactions
					for (int i = 0; i < newSavingsTransactions.size(); i++) { // for each loop to display the current beneficiary list
						//
						System.out.println("\n\tTransaction ID\t\t: " + newSavingsTransactions.get(i).getTransactionID());
						System.out.println("\tTransaction Date\t: " + newSavingsTransactions.get(i).getTransactionDate());
						System.out.println("\tTransaction Name\t: " + newSavingsTransactions.get(i).getTransactionDescription());
						System.out.println("\tTransaction Amount\t: " + moneyFormat.format(newSavingsTransactions.get(i).getTransactionAmount()));
						System.out.println("\tDR or CR\t\t: " + newSavingsTransactions.get(i).getTransactionType());
						//
						System.out.println("");
					}
					//
					LocalDate transactionDate = LocalDate.now();//this line of code used get the current date
					//
					System.out.println("\nTransaction History was last updated : " + transactionDate);
					//
					savingsContinueTransaction();
					
				}
				catch (InputMismatchException e) {
					//
					System.out.println("\nInvalid entry : Please enter an integer value. " );
					grapes.nextLine();
					//
					savingsTranstactionHistory() ;
				}
				
			}
			

}
