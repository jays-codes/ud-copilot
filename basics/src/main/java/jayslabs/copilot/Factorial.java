package jayslabs.copilot;

public class Factorial {

//main method to run the program
    public static void main(String[] args) {
        int number = 5;
        int factorial = calculateFactorial(number);
        System.out.println("Factorial of " + number + " is " + factorial);
    }

    public static int calculateFactorial(int number) {
        if (number == 0 || number == 1) {
            return 1;
        } else {
            return number * calculateFactorial(number - 1);
        }
    }
}

//class that calculates factorial without using recursion
class FactorialWithoutRecursion {
	public static void main(String[] args) {
		int number = 5;
		int factorial = calculateFactorial(number);
		System.out.println("Factorial of " + number + " is " + factorial);
	}

	public static int calculateFactorial(int number) {
		int factorial = 1;
		for (int i = 1; i <= number; i++) {
			factorial = factorial * i;
		}
		return factorial;
	}
}





