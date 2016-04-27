package HomeWork.Lesson7;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

class Account {
	private int money;
    private ReentrantLock lock = new ReentrantLock(true);

	public Account(int money) {
		this.money = money;
	}

	public int get() {
		return money;
	}

	public void set(int money) {
		this.money = money;
	}

    public ReentrantLock getLock() {
        return lock;
    }
}

class Transaction extends Thread {
	private Account account;
	private int withdraw;
    private CountDownLatch countDownLatch;

	public Transaction(Account account, int withdraw, CountDownLatch countDownLatch) {
		this.account = account;
		this.withdraw = withdraw;
        this.countDownLatch = countDownLatch;
	}

	public void run() {
		try { // спим для эмуляции реального многопоточного доступа к ресурсу
			Thread.sleep(System.currentTimeMillis() % 50);
		} catch (InterruptedException e) {}

		account.getLock().lock();
        int total = account.get();
        if (total >= withdraw)  {
            account.set(total - withdraw);
            System.out.println("-" + withdraw);
        } else {
            System.out.println("Not enough money for this operation!");
        }
        account.getLock().unlock();
        countDownLatch.countDown();
	}
}

public class AccountSync {
	public static void main(String[] args) {
		Account acc = new Account(10000);
        int transactionsAmount = 10;
        CountDownLatch countDownLatch = new CountDownLatch(transactionsAmount);
        Random random = new Random();
        ArrayList<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < transactionsAmount; i++) {
            transactions.add(new Transaction(acc, random.nextInt(500) + 1, countDownLatch));
            transactions.get(i).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

		System.out.println("Total: " + acc.get());
	}
}
