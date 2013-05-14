package alex.algorithms.math.projecteuler.level2;

public class Euler034 {
    public static void main(final String[] args) {
        int[] facts = new int[10];
        int result = 0;
        int fact = 1;
        facts[0] = 1;
        int sum = 1;
        for (int i = 1; i < 10; i++) {
            fact *= i;
            facts[i] = fact;
            sum += fact;
        }

        for (int i = 10; i < facts[9] * 9; i++) {
            int sumOfFacts = 0;
            int number = i;
            while (number > 0) {
                int d = number % 10;
                number /= 10;
                sumOfFacts += facts[d];
            }

            if (sumOfFacts == i) {
                result += i;
            }
        }
        System.out.printf("%d\n", result);
    }
}
