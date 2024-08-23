package task1;

import java.util.function.BinaryOperator;

public class ParallelCalculator implements Runnable {

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