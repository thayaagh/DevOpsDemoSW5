package ch.zhaw.iwi.devops.demo;

public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed");
        }
        return a / b;
    }

    public int modulo(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed");
        }
        return a % b;
    }

    public int power(int a, int b) {
        return (int) Math.pow(a, b);
    }

    public int factorial(int a) {
        if (a < 0) {
            throw new IllegalArgumentException("Factorial of negative numbers is not allowed");
        }
        if (a == 0) {
            return 1;
        }
        return a * factorial(a - 1);
    }
}
