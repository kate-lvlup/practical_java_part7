package task3;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

class Interactor {
    private int x = 0;
    private boolean isXInitialized = false;
    private final Object lock = new Object();

    public void serve(UnaryOperator<Integer> uo, int initializer) throws InterruptedException {
        synchronized (lock) {
            System.out.println("Serving thread running");
            x = uo.apply(initializer);
            System.out.println("Serving thread initializes the key");
            System.out.println("key = " + x);
            isXInitialized = true;
            Thread.sleep(100);
            lock.notify();
        }
    }

    public void consume(BinaryOperator<Integer> bo, int operand2) throws InterruptedException {
        synchronized (lock) {
            while (!isXInitialized) {
                lock.wait(3000);
                if (!isXInitialized) {
                    x = 0;
                    System.out.println("Consuming thread received the key. key = " + x);
                    System.out.println("Consuming thread changed the key. key = " + (x + operand2));
                    return;
                }
            }

            System.out.println("Consuming thread received the key. key = " + x);
            x = bo.apply(x, operand2);
            System.out.println("Consuming thread changed the key. key = " + x);
            System.out.println("Serving thread resumed");
        }
    }
}