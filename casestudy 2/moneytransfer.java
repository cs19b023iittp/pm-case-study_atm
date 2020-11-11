package banking;

public class Account {
    String name;
    double balance;

    public Account() {
        name = "";
        balance = 0.0;
    }

    public void setName(String newName) {
        name = newName;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void addFunds(double addedAmount) {
        balance = balance + addedAmount;
    }

    public void withdraw(double withdrawnAmount) {
        balance = balance - withdrawnAmount;        
    }

    public void transfer(double amount, Account from, Account to) { //here is the transfer method, if i can improve this or alter it in order to make it easier to run using user input from the main file, let me know
        if(from.balance >= amount){
            from.balance = from.balance - amount;
            to.balance = to.balance + amount;
            System.out.println("Funds successfully transfered.");
        } else {
                System.out.println("Insufficient funds");
            }
        }
    }

package banking;
import java.util.Scanner;

public class BankSimulator {
    public static void main(String[] args) {

            System.out.println("Hello and welcome to the banking system. Please enter a name to create an account, no spaces: ");
            Scanner scan = new Scanner(System.in);
            Account a1 = new Account();
            a1.setName(scan.next());
            System.out.println("Account name: " + a1.getName());

        int count = 0;
        while(count == 0) {

                System.out.println("What would you like to do next?"  + "\n" +
                "Change account name: press 1"  + "\n" +
                "See account name: press 2"  + "\n" +
                "Check balance: press 3"  + "\n" + 
                "Add money to balance: press 4"  + "\n" +
                "Withdraw money from balance: press 5" + "\n" +
                "Exit program: press 7: " + "\n" +
                "To transfer funds between accounts: press 6");

                int toDo = scan.nextInt();

            if(toDo == 1) {

                    System.out.println("Enter new account name: ");
                    a1.setName(scan.next());
                    System.out.println("Account name: " + a1.getName());
            }
            else if(toDo == 2) {
                System.out.println("Account name: " + a1.getName());
            }
            else if(toDo == 3) {
                System.out.println("Current balance: $" + a1.getBalance());
            }
            else if(toDo == 4) {
                System.out.println("Desired amount to add: $");
                a1.addFunds(scan.nextDouble());
                System.out.println("Money successfully added to balance."); 
            }
            else if(toDo == 5) {
                System.out.println("Desired amount to withdraw: $");
                a1.withdraw(scan.nextDouble());
                System.out.println("Money successfully withdrawn from balance.");
            }
            else if(toDo == 6) {
                System.out.println("Enter the account you would like to transfer money from:");
                String fromAccount = scan.next();
                System.out.println("Enter the account you would like to transfer money to:");
                String toAccount = scan.next();
                System.out.println("Enter the amount of money you would like to transfer: $");
                double moneyToTransfer = scan.nextDouble();
        //this is what i need help with, I don't know what to do with these three things, and since the first two arent accounts, i cant run the transfer method on them


            }
            else if(toDo == 7) {
                System.out.println("Thank you for using our banking system. Until next time.");
                count = 1;

            }
        }
    }
}
I can't do anything with the user input because I need the desired accounts in order to run the transfer method with them.

java
methods
share  edit  follow 
Get updates on questions and answers
edited Jul 22 '19 at 4:09

marc_s
655k146146 gold badges12321232 silver badges13651365 bronze badges
asked Jul 12 '19 at 6:06

Crispy Bagpipes
744 bronze badges
add a comment
3 Answers

1

I suggest you create dummy data (existing) Account first:

  List<Account> accountList = new ArrayList<>();
  Account acct1 = new Account("tester1", 100.0);
  Account acct2 = new Account("tester2", 100.0);
  accountList.add(acct1);
  accountList.add(acct2);
Add constructor for Account for easier adding of Accounts:

    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }
Store new account account in list:

accountList.add(a1);
For your procedure for "(toDo == 6)" which is for transfer:

Check first if accountList has at least 2 accounts because it won't make sense if there's only 1 account.

else if (toDo == 6) {
  if (accountList.size() > 2) {
    System.out.println("Enter the account you would like to transfer money from:");
    String fromAccount = scan.next();
    System.out.println("Enter the account you would like to transfer money to:");
    String toAccount = scan.next();
    System.out.println("Enter the amount of money you would like to transfer: $");
    double moneyToTransfer = scan.nextDouble();

    for (Account account : accountList) {
      if (account.getName().equals(fromAccount)) {
        account.withdraw(moneyToTransfer);
      }
      if (account.getName().equals(toAccount)) {
        account.addFunds(moneyToTransfer);
      }
    }
  } else 
    System.out.println("Cannot transfer.");
  }

class bankAccount {
String name;
private double balance;
private final long acctNum = ThreadLocalRandom.current().nextLong(100000000, 999999999);

public bankAccount(String name, double balance) {
    this.name = name;
    this.balance = balance;
    System.out.println("HELLO " + name + ", Your account number is: " + acctNum);
}
public void setName(String name) {
    this.name = name;
}
public void addFunds(int amount) {
    this.balance += amount;
}
public void withdrawFunds(int amount) {
    this.balance -= amount;
}
public double getBalance() {
    return balance;
}
public long getAcctNum() {
    return acctNum;
}
public void transfer(bankAccount name, double amount) {
    if(this.balance >= amount) {
        name.balance += amount;
        this.balance -= amount;
        System.out.println("Transaction Successful");
    }
    else {
        System.err.println("Insufficient Funds!");
    }
}
}

class BankSimulator {
static bankAccount John = new bankAccount("John", 50000);
static bankAccount James = new bankAccount("James", 3000);

public static void main(String[] args) {
    John.transfer(James, 300);
    System.out.println("John's new balance is "+ John.getBalance());
    System.out.println("James' new balance is "+ James.getBalance());
}

}