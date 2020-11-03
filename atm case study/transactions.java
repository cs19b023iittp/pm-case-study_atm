public class BalanceInquiry extends Transactions
{
public BalanceInquiry(int userAccountNumber,Screen atmScreen,
      BankDatabase atmBankDatabase)
{
super(user AccountNumber,atmScreen,atmBankDatabase);
}
public void execute()
{
BankDatabase bankDatabase=getBankDatabase();
Screen screen = getScreen();
double availableBalance=
bankDatabase.getAvailableBalance(getAccounted());
double totalBalance=
bankDatabase.getTotalBalance(getAccountedNumber());
screen.displayMessageLine(\nBalance Information:");
screen.displayMessage(" -Available balance);
screen.displayDollarAmount(availableBalance);
screen.displayMessage(\n -Total balance:  ");
screen.displayDollarAmount(totalBalance);
screen.displayMessageLine("");
}
}

