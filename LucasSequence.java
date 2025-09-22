/**
 * Class representing the Lucas sequence.
 * Provides methods to access values and check if a number
 * can be represented in the form w^3 + 1.
 * @author Kunetes Dmytro "dmytro.kunets.oi.2024@lpnu.ua"
 * @version 1.0
 */
import java.util.Scanner;

public class LucasSequence {
    private int index; // The index of the number in the sequence
    private long value; // The value of the Lucas number

    /**
     * Constructor to create a LucasSequence object.
     * @param index the index of the number in the sequence
     */
    public LucasSequence(int index) {
        this.index = index;
        this.value = computeLucas(index);
    }

    /**
     * Compute Lucas number at given index.
     * Lucas sequence: 1, 3, 4, 7, 11, ...
     * @param n index (1-based)
     * @return Lucas number at index n
     */
    private long computeLucas(int n) {
        if (n == 1) return 1;
        if (n == 2) return 3;
        long a = 1, b = 3, c = 0;
        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    public int getIndex() {
        return index;
    }

    public long getValue() {
        return value;
    }

    /**
     * Check if the stored number equals w^3 + 1 for some integer w.
     * @return true if number = cube + 1, false otherwise
     */
    public boolean isCubePlusOne() {
        long candidate = value - 1;
        if (candidate < 0) return false;
        long cubeRoot = Math.round(Math.cbrt(candidate));
        return cubeRoot * cubeRoot * cubeRoot == candidate;
    }

    @Override
    public String toString() {
        return String.format("Lucas number at index %d is %d", index, value);
    }

    /**
     * Main program to compute and check Lucas numbers.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of terms (N > 0):");
        int N = scanner.nextInt();
        if (N <= 0) {
            System.out.println("N must be positive integer.");
            scanner.close();
            return;
        }

        LucasSequence[] numbers = new LucasSequence[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = new LucasSequence(i + 1);
        }

        System.out.println("Results:");
        for (LucasSequence num : numbers) {
            System.out.println(num);
        }

        System.out.println("\nAmong the first " + N + " Lucas numbers, those that equal w^3 + 1:");
        boolean foundAny = false;
        for (LucasSequence num : numbers) {
            if (num.isCubePlusOne()) {
                System.out.printf("Index %d: %d = %d^3 + 1%n",
                        num.getIndex(), num.getValue(), Math.round(Math.cbrt(num.getValue() - 1)));
                foundAny = true;
            }
        }
        if (!foundAny) {
            System.out.println("None of the first " + N + " Lucas numbers can be expressed as w^3 + 1.");
        }

        scanner.close();
    }
}
