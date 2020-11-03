public class Account
{
private int accountNumber;
private int pin;
private double availableBalance;
private  Account(int theAccountNumber ,int thePIN,
     double theAvailableBalance,double theTotalBalance)
{
accountNumber=theAccount=theAccountNumber;
pin=thePIN;
availabilityBalance=theAvailablebalance;
totalBalance = theTotalBalance;
}
public boolean validatePIN(int userPIN)
{
if(userPIN==pin)
return true;
else
return false;
}
public double getAvailableBalance()
{
return availableBalance;
}
public double getTotalBalance()
{
return totalBalance;
}
public void credit(double amount)
{
total balance +=amount;
}
public void debit(double amount)
{
availableBalance-=amount;
totalBalance-=amount;
}
public int getAccountNumber()
{
return accountNumber;
}
}