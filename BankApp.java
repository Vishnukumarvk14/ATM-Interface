import java.util.*;

class AccountManager {
    static void registerAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------------------------");
        System.out.println("Enter your full name:");
        BankApp.customerName = scanner.nextLine();
        System.out.println("Create a username:");
        String username = scanner.nextLine();
        System.out.println("Create a password:");
        String password = scanner.nextLine();
        System.out.println("Enter your account number:");
        BankApp.accountNumber = scanner.nextLine();
        System.out.println("REGISTRATION SUCCESSFUL!");
        System.out.println("---------------------------");
        BankApp.showUserMenu();
        while (true) {
            showOptions(BankApp.customerName);
            int option = scanner.nextInt();
            if (option == 1) {
                login(username, password);
                break;
            } else if (option == 2) {
                System.exit(0);
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }

    static void showOptions(String name) {
        System.out.println("Hello " + name + ", please choose an option:");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
    }

    static void login(String username, String password) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username:");
        String inputUsername = scanner.nextLine();
        System.out.println("Enter password:");
        String inputPassword = scanner.nextLine();
        if (inputUsername.equals(username) && inputPassword.equals(password)) {
            BankApp.showUserMenu();
        } else {
            System.out.println("Invalid credentials. Please try again.");
            login(username, password);
        }
        scanner.close();
    }
}

class TransactionService {
    static void withdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------");
        System.out.println("Enter amount to withdraw:");
        int withdrawalAmount = scanner.nextInt();
        if (withdrawalAmount <= BankApp.balance) {
            BankApp.balance -= withdrawalAmount;
            BankApp.transactionHistory.add("Withdrawn: Rs " + withdrawalAmount);
            System.out.println("Rs " + withdrawalAmount + " withdrawn successfully.");
            System.out.println("---------------------------");
        } else {
            System.out.println("Insufficient balance.");
            System.out.println("---------------------------");
        }
        BankApp.showUserMenu();
        scanner.close();
    }

    static void deposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------");
        System.out.print("Enter amount to deposit:");
        int depositAmount = scanner.nextInt();
        BankApp.updateBalance(depositAmount);
        BankApp.transactionHistory.add("Deposited: Rs " + depositAmount);
        System.out.println("Rs " + depositAmount + " deposited successfully!");
        System.out.println("---------------------------");
        BankApp.showUserMenu();
        scanner.close();
    }

    static void transfer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the recipient's name:");
        String recipientName = scanner.nextLine();
        System.out.println("Enter the recipient's account number:");
        String recipientAccountNumber = scanner.nextLine();
        System.out.println("Enter the amount to transfer:");
        int transferAmount = scanner.nextInt();
        if (transferAmount <= BankApp.balance) {
            BankApp.balance -= transferAmount;
            BankApp.transactionHistory.add("Transferred: Rs " + transferAmount + " to " + recipientName);
            System.out.println("Rs " + transferAmount + " transferred successfully.");
            System.out.println("---------------------------");
        } else {
            System.out.println("Insufficient balance.");
            System.out.println("---------------------------");
        }
        BankApp.showUserMenu();
        scanner.close();
    }
}

class BalanceChecker {
    static void checkBalance() {
        System.out.println("------------------");
        System.out.println("Available balance:");
        BankApp.displayBalance();
        System.out.println("---------------------------");
        BankApp.showUserMenu();
    }
}

class TransactionHistoryViewer {
    static void showTransactionHistory() {
        System.out.println("---------------------");
        System.out.println("Transaction History:");
        if (!BankApp.transactionHistory.isEmpty()) {
            for (String record : BankApp.transactionHistory) {
                System.out.println(record);
                System.out.println("---------------------");
            }
        } else {
            System.out.println("No transactions available.");
        }
        BankApp.showUserMenu();
    }
}

public class BankApp {
    public static String customerName;
    public static int balance = 0;
    public static String accountNumber;
    public static ArrayList<String> transactionHistory = new ArrayList<>();

    static void updateBalance(int amount) {
        balance += amount;
    }

    static void displayBalance() {
        System.out.println(balance);
    }

    public static void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("WELCOME TO THE BANKING SYSTEM");
        System.out.println("--------------------------");
        System.out.println("Select an option:");
        System.out.println("1. Create Account");
        System.out.println("2. Exit");
        System.out.println("Enter your choice:");
        int choice = scanner.nextInt();
        if (choice == 1) {
            AccountManager.registerAccount();
        } else if (choice == 2) {
            System.exit(0);
        } else {
            System.out.println("Invalid choice! Please try again.");
            showMainMenu();
        }
        scanner.close();
    }

    static void showUserMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("WELCOME, " + BankApp.customerName + "!");
        System.out.println("---------------------");
        System.out.println("Select an option:");
        System.out.println("1. Withdraw Money");
        System.out.println("2. Deposit Money");
        System.out.println("3. Transfer Funds");
        System.out.println("4. Check Balance");
        System.out.println("5. View Transaction History");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                TransactionService.withdraw();
                break;
            case 2:
                TransactionService.deposit();
                break;
            case 3:
                TransactionService.transfer();
                break;
            case 4:
                BalanceChecker.checkBalance();
                break;
            case 5:
                TransactionHistoryViewer.showTransactionHistory();
                break;
            case 6:
                System.exit(0);
        }
        scanner.close();
    }

    public static void main(String[] args) {
        showMainMenu();
    }
}
