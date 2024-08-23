package task4;

class MyThreads {
    public static int n;
    public static int m;

    private static final Object lock = new Object();
    private static boolean t1FinishedPart1 = false;
    private static boolean t2FinishedPart1 = false;

    public static Thread t1 = new Thread() {
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 5; i++, n++) {
                    System.out.println("Thread1 n = " + n);
                }
                t1FinishedPart1 = true;
                lock.notifyAll();
            }

            synchronized (lock) {
                while (!t2FinishedPart1) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 5; i++, m++) {
                    System.out.println("Thread1 m = " + m);
                }
                System.out.println("Thread1 success!");
            }
        }
    };

    public static Thread t2 = new Thread() {
        public void run() {
            synchronized (lock) {
                while (!t1FinishedPart1) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 5; i++, m++) {
                    System.out.println("Thread2 m = " + m);
                }
                t2FinishedPart1 = true;
                lock.notifyAll();
            }

            synchronized (lock) {
                for (int i = 0; i < 5; i++, n++) {
                    System.out.println("Thread2 n = " + n);
                }
                System.out.println("Thread2 success!");
            }
        }
    };
}

























