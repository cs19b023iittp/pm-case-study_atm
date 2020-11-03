public class ATM {
    private boolean userAuthenticated;
    private int currentAccountNumber;
    private BankDatabase bankDatabase;

    private static final int BALANCE_INQUIRY = 1;
    private static final int WITHDRAWAL = 2;
    private static final int DEPOSIT = 3;
    private static final int EXIT = 4;

    public ATM() {
        userAuthenticated = false;
        currentAccountNumber = 0;
        bankDatabase = new BankDatabase();

    }

    public void run() {
        while (true) {
            while (!userAuthenticated) {
                System.out.println("\nwelcome!");
                authenticatingUser();

            }
            performTrasactions();
            userAuthenticated = false;
            currentAccountNumber = 0;
            System.out.println("\thank you! Goodbye!");
        }
    }

    public static void main(String[] args) {
        ATM theATM = new ATM();
        theATM.run();
    }

    private void authenticatingUser() {
        System.out.println("\nplease enter your account numbers:");
        int accountNumber = keypad.getInput();
        System.out.println("\nEnter your pin:");
        int pin = keypad.getInput();
        userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);
        if (userAuthenticated) {
            currentAccountableNumber = accountNumber;
        } else
            System.out.println("Invalid account number or pin ,please try again.");
    }

    private void performTransaction() {
        Transaction currentTransaction = null;
        boolean userExited = false;
        while (!userExited) {
            int mainMenuSelection = displayMainMenu();
            switch (mainMenuSelection) {
                case BALANCE_INQUIRY:
                case WITHDRAWAL:
                case DEPOSIT:
                    currentTransaction = createTransformation(mainMenuSelection);
                    currentTransformation.execuse();
                    break;
                case EXIT:
                    System.out.println("\nExisting the system....");
                    break;
            }
        }
    }

    private int displayMainMenu() {
        System.out.println("\nMain menu:");
        System.out.println("1 - View my balance");
        System.out.println("2 - Withdraw cash:");
        System.out.println("3 - Deposit funds");
        System.out.println("4 - Exit\n");
        System.out.println("Enter your choice:");
        return sc.nextline();

    }

    private Transaction createTransaction(int type) {
        Transaction temp = null;
        switch (type)

        {
            case BALANCE_INQUIRY:
                temp = new BalanceInquiry(currentAccountNumber, bankDatabase);
                break;
            case WITHDRAWAL:
                temp = new Withdrawal(currentAccountNumber, bankDatabase);

                break;
            case DEPOSIT:
                temp = new Deposit(currentAccountNumber, bankDatabase);
                break;

        }
        return temp;
    }
}