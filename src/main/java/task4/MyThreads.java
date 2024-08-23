package task4;

//class MyThreads {
//    public final static Object den = new Object();
//    public final static Object ada = new Object();
//
//    public static int n;
//    public static int m;
//
//    public static Thread t1 = new Thread() {
//        public void run() {
//            synchronized (den) {
//                for (int i = 0; i < 5; i++, n++) {
//                    System.out.println("Thread1 n = " + n);
//                }
//                Thread.yield(); // Yield to allow other threads to run
//                synchronized (ada) {
//                    for (int i = 0; i < 5; i++, m++) {
//                        System.out.println("Thread1 m = " + m);
//                    }
//                    System.out.println("Thread1 success!");
//                }
//            }
//        }
//    };
//
//    public static Thread t2 = new Thread() {
//        public void run() {
//            synchronized (den) { // Change order to match t1
//                synchronized (ada) {
//                    for (int i = 0; i < 5; i++, m++) {
//                        System.out.println("Thread2 m = " + m);
//                    }
//                    Thread.yield(); // Yield to allow other threads to run
//                    for (int i = 0; i < 5; i++, n++) {
//                        System.out.println("Thread2 n = " + n);
//                    }
//                    System.out.println("Thread2 success!");
//                }
//            }
//        }
//    };
//}



// FOR MOODLY

class MyThreads {
    public final static Object den = new Object();
    public final static Object ada = new Object();

    public static int n;
    public static int m;

    public static Thread t1 = new Thread() {
        public void run() {
            synchronized (den) {
                for (int i = 0; i < 5; i++, n++) {
                    System.out.println("Thread1 n = " + n);
                }
            }
            Thread.yield();  // Уступаем управление другому потоку

            try {
                Thread.sleep(100);  // Позволяем Thread2 выполнить свою часть
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (ada) {
                for (int i = 0; i < 5; i++, m++) {
                    System.out.println("Thread1 m = " + m);
                }
                System.out.println("Thread1 success!");
            }
        }
    };

    public static Thread t2 = new Thread() {
        public void run() {
            synchronized (ada) {
                for (int i = 0; i < 5; i++, m++) {
                    System.out.println("Thread2 m = " + m);
                }
            }
            Thread.yield();  // Уступаем управление другому потоку

            try {
                Thread.sleep(100);  // Позволяем Thread1 выполнить свою часть
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (den) {
                for (int i = 0; i < 5; i++, n++) {
                    System.out.println("Thread2 n = " + n);
                }
                System.out.println("Thread2 success!");
            }
        }
    };

    public static void main(String[] args) {
        t1.start();
        t2.start();
    }
}









//
//public class MyThreads {
//
//    public static int n;
//    public static int m;
//
//    private static final Object lock = new Object();
//    private static boolean isThread1FirstPartDone = false;
//    private static boolean isThread2Done = false;
//    private static boolean isThread1Done = false;
//
//    public static Thread t1 = new Thread(() -> {
//        synchronized (lock) {
//            // Output n values
//            for (int i = 0; i < 5; i++) {
//                System.out.println("Thread1 n = " + i);
//                n = i;
//            }
//
//            // Notify Thread2 to start
//            isThread1FirstPartDone = true;
//            lock.notifyAll();
//
//            // Wait for Thread2 to complete its work
//            while (!isThread2Done) {
//                try {
//                    lock.wait();
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt(); // Preserve interrupt status
//                }
//            }
//
//            // Output m values
//            for (int i = 5; i < 10; i++) {
//                System.out.println("Thread1 m = " + i);
//                m = i;
//            }
//            System.out.println("Thread1 success!");
//
//            // Notify Thread2 that Thread1 is done
//            isThread1Done = true;
//            lock.notifyAll();
//        }
//    });
//
//    public static Thread t2 = new Thread(() -> {
//        synchronized (lock) {
//            // Wait for Thread1 to finish its first part
//            while (!isThread1FirstPartDone) {
//                try {
//                    lock.wait();
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt(); // Preserve interrupt status
//                }
//            }
//
//            // Output m values
//            for (int i = 0; i < 5; i++) {
//                System.out.println("Thread2 m = " + i);
//                m = i;
//            }
//
//            // Output n values
//            for (int i = 5; i < 10; i++) {
//                System.out.println("Thread2 n = " + i);
//                n = i;
//            }
//            System.out.println("Thread2 success!");
//
//            // Notify Thread1 to continue
//            isThread2Done = true;
//            lock.notifyAll();
//
//            // Wait for Thread1 to finish its second part
//            while (!isThread1Done) {
//                try {
//                    lock.wait();
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt(); // Preserve interrupt status
//                }
//            }
//        }
//    });
//}































