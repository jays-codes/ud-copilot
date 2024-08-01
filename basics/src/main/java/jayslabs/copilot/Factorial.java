package jayslabs.copilot;

import java.math.BigInteger;

public class Factorial {

    // Main method to run the program
    public static void main(String[] args) {
        int number = -5;
        try {
            BigInteger factorial = calculateFactorial(number);
            System.out.println("Factorial of " + number + " is " + factorial);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Calculates the factorial of a non-negative integer.
     *
     * @param number the non-negative integer to calculate the factorial of
     * @return the factorial of the given number as a BigInteger
     * @throws IllegalArgumentException if the number is negative
     */

	public static BigInteger calculateFactorial(int number) {
		if (number < 0) {
			throw new IllegalArgumentException("Invalid input: " + number + ". Number must be non-negative");
		}
		BigInteger result = BigInteger.ONE;
		for (int i = 2; i <= number; i++) {
			result = result.multiply(BigInteger.valueOf(i));
		}
		return result;
	}

}





