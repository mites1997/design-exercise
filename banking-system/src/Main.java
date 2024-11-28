public class Main {
    public static void main(String[] args) {
        Account account = new Account("Mites", "mites123@mail.com");

        EmailSender emailNotifier = new EmailSender();
        account.addObserver(emailNotifier);

        account.deposit(50000);
        account.deposit(40000);
        account.withdraw(20000);
        account.deposit(40000);

        System.out.println("Final Balance: $" + account.getBalance());
        System.out.println("Final Tier: " + account.getTier());
    }
}