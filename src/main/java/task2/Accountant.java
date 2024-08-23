package task2;

import java.util.function.BinaryOperator;

class ParallelCalculator implements Runnable {

    public int result;
    private BinaryOperator<Integer> operation;
    private int operand1;
    private int operand2;

    public ParallelCalculator(BinaryOperator<Integer> operation, int operand1, int operand2) {
        this.operation = operation;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public void run() {
        result = operation.apply(operand1, operand2);
    }
}

class Accountant {
    public static int sum(int operand1, int operand2) {
        ParallelCalculator calculator = new ParallelCalculator((a, b) -> Integer.sum(a, b), operand1, operand2);
        Thread thread = new Thread(calculator);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return calculator.result;
    }
}