package ClassWork.Lesson6;

import java.math.BigInteger;

class Main {
    static interface IFinish {
        void done(BigInteger result);
    }

    static class Finish implements IFinish {
        @Override
        public void done(BigInteger result) {
            System.out.println(result);
        }
    }

    static class Task extends Thread {
        IFinish finish;
        BigInteger n;

        public Task(BigInteger n, IFinish finish) {
            this.n = n;
            this.finish = finish;
        }

        private BigInteger fact(BigInteger x) {
            if (x.equals(BigInteger.ZERO))
                return BigInteger.ONE;
            else
                return x.multiply(fact(x.subtract(BigInteger.ONE)));
        }

        public void run() {
            BigInteger res = fact(n);
            if (finish != null)
                finish.done(res);
        }
    }

    public static void main(String[] args) {
        Task t = new Task(new BigInteger("5"), new Finish());
        t.start();
        System.out.println("Done!");
    }
}