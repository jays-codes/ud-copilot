package jayslabs.copilot;

//class that calculates factorial without using recursion
class FactorialWithoutRecursion {
	public static void main(String[] args) {
		int number = 5;
		int factorial = calculateFactorial(number);
		System.out.println("Factorial of " + number + " is " + factorial);
	}

	//explain the code
	public static int calculateFactorial(int number) {
		int factorial = 1;
		for (int i = 1; i <= number; i++) {
			factorial = factorial * i;
		}
		return factorial;
	}
}