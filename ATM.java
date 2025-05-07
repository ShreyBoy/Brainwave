import java.util.Scanner;

class Account {
    private double balance;
    private final int pin;

    public Account(double initialBalance, int pin) {
        if (initialBalance > 0.0) {
            balance = initialBalance;
        }
        this.pin = pin;
    }
    public void credit(double amount) {
        balance += amount;
    }
    public boolean debit(double amount) {
        if (amount > balance) {
            System.out.println("Debit amount exceeded account balance.");
            return false;
        } else {
            balance -= amount;
            return true;
        }
    }
    public double getBalance() {
        return balance;
    }

    public boolean validatePin(int inputPin) {
        return this.pin == inputPin;
    }
}

public class ATM {
    private static Scanner scanner = new Scanner(System.in);
    private static Account account = new Account(1000.0, 1234); // Initial balance and PIN for the account

    public static void main(String[] args) 
    {
        boolean userAuthenticated = authenticateUser();

        if (userAuthenticated) 
        {
            boolean userExited = false;
            while (!userExited) 
            {
                displayMainMenu();
                int mainMenuSelection = scanner.nextInt();
                switch (mainMenuSelection) {
                    case 1:
                        viewBalance();
                        break;
                    case 2:
                        withdraw();
                        break;
                    case 3:
                        deposit();
                        break;
                    case 4:
                        userExited = true;
                        System.out.println("Exiting the system...");
                        break;
                    default:
                        System.out.println("Invalid selection. Please try again.");
                        break;
                }
            }
        } 
        else
        {
            System.out.println("Invalid PIN. Exiting the system...");
        }
    }
    private static boolean authenticateUser() 
    {
        System.out.print("Welcome!\nPlease enter your PIN: ");
        int inputPin = scanner.nextInt();

        return account.validatePin(inputPin);
    }
    private static void displayMainMenu()
    {
        System.out.println("\nMain menu:");
        System.out.println("1 - View my balance");
        System.out.println("2 - Withdraw cash");
        System.out.println("3 - Deposit funds");
        System.out.println("4 - Exit\n");
        System.out.print("Enter a choice: ");
    }
    private static void viewBalance() 
    {
        System.out.printf("\nBalance Information:\n - Available balance: $%.2f\n", account.getBalance());
    }
    private static void withdraw() 
    {
        System.out.print("\nEnter an amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount<=0) 
        {
            System.out.println("Withdrawal amount must be greater than zero.");
        } 
        else if(account.debit(amount)) 
        {
            System.out.printf("Successfully withdrawal $%.2f\n", amount);
        }
    }
    private static void deposit() 
    {
        System.out.print("\nEnter an amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount <= 0) 
        {
            System.out.println("Deposit amount must be greater than zero.");
        } 
        else 
        {
            account.credit(amount);
            System.out.printf("Successfully deposited $%.2f\n", amount);
        }
    }
}