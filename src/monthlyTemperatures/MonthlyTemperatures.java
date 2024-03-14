package monthlyTemperatures;
import java.util.Scanner;

public class MonthlyTemperatures {

	public static void main(String[] args) {
		String[] months = {"January", "February", "March",
				"April", "May", "June", "July", "August", 
				"September", "October", "November", "December"};
		double[] averageTemperatureByMonths = {32.0, 38.4, 40.7,
				54.1, 60.7, 78.4, 87.6, 93.4, 88.4, 69.2, 64.5, 45.6};
		boolean shouldContinueProgram = true;
		
		
		try(Scanner scnr = new Scanner(System.in)) {
			// Prompt User
			String userInput = printOpeningStatement(scnr);
			
			// Run loop until user inputs correct string or terminates program
			while(shouldContinueProgram) {
				// Check user input for year, otherwise find month.
				if(userInput.toLowerCase().equals("year")) {
					printYearlyTemperatureStats(months, averageTemperatureByMonths);
					shouldContinueProgram = false;
				} else {
					shouldContinueProgram = findAndPrintMonthTemperatureStats(months, averageTemperatureByMonths, userInput);
				}
				
				if(!shouldContinueProgram) {
					// Prompts the user to continue the program or quit (returns boolean).
					if(getUserRequestToContinue(scnr)) {
						shouldContinueProgram = true;
						userInput = printOpeningStatement(scnr);
					}
				} else {
					// If no month or year was input fallback onto the error message and run the loop again
					System.out.println("Not a valid option try a month or enter 'year': ");
					userInput = scnr.next();
				}
			}
			
			System.out.println("\n***********************************");
			System.out.println("***********************************");
			System.out.println("**** Exiting program, goodbye! ****");
			System.out.println("***********************************");
			System.out.println("***********************************");
			
		}
		

	}
	
	// A reusable function to get the user input for the program
	public static String printOpeningStatement(Scanner scnr) {
		System.out.println("Enter a month for average temperature or 'year' for yearly average: ");
		String userInput = scnr.next();
		
		return userInput;
	}
	
	// Receives a list of temperatures and returns an average.
	public static double getAverageTemperature(double[] temperatures) {
		double temperatureSum = 0.0;
		// Add each temperature to the total
		for(int i = 0; i < temperatures.length; i++) {
			temperatureSum += temperatures[i];
		}
		// Average
		return temperatureSum / temperatures.length;
	}
	
	// Reusable function to return a minimum or maximum number 
	public static double getMinOrMaxNumber(double[] temperatures, boolean isMin) {
		// Set the initial min/max
		double result = temperatures[0];
		
		// Iterate through all numbers
		for(int i = 0; i < temperatures.length; i++) {
			double currentTempElement = temperatures[i];
			// if isMin is passed return the minimum number otherwise return maximum
			if(isMin) {	
				if(currentTempElement < result) {
					result = currentTempElement;
				}
			} else {
				if(currentTempElement > result) {
					result = currentTempElement;
				}
			}
		}
		
		return result;
	}
	
	// Will print yearly stats for temperatures
	public static void printYearlyTemperatureStats(String[] months, double[] temperatures) {
		double yearlyAverageTemperature = getAverageTemperature(temperatures);
		double minimumAverageTemperature = getMinOrMaxNumber(temperatures, true);
		double maximumAverageTemperature = getMinOrMaxNumber(temperatures, false);
		
		System.out.println("\nAverage Temperature by Month:");
		
		// Print out each month
		for(int i = 0; i < months.length; i++) {
			System.out.println("  * " + months[i] +" - " + temperatures[i]);
		}
		
		// Print average and min/ max integers
		System.out.println("\nThe yearly average temperature was " + String.format("%.2f", yearlyAverageTemperature));
		System.out.println("The minimum monthly temperature was " + minimumAverageTemperature);
		System.out.println("The maximum monthly temperature was " + maximumAverageTemperature);
		
	}
	
	// Looks for user input to match a month and prints content. return a boolean to continue the program
	public static boolean findAndPrintMonthTemperatureStats(String[] months, double[] temperatures, String userInput) {
		boolean shouldContinue = true;
		
		for(int i = 0; i <months.length; i++) {
			if(months[i].toLowerCase().equals(userInput.toLowerCase())) {
				System.out.println("The average temperture for " + months[i] + " was " + temperatures[i]);
				shouldContinue = false;
				break;
			}
		}
		
		return shouldContinue;	
	}
	
	
	// A helper function to ask the user if they would like to check another month
	public static boolean getUserRequestToContinue(Scanner scnr) {
		boolean shouldContinue = false;
		
		// Loop with a prompt asking user to answer yes/no question
		while(true) {
			System.out.println("\nWould you like to enter another input? (y/n)");
			String userInput = scnr.next().trim().toLowerCase();
			// If the user enters the correct string set boolean and break out of the while loop
			if(userInput.toLowerCase().equals("y")) {
				shouldContinue = true;
				break;
			} else if(userInput.toLowerCase().equals("n")) {
				break;
			}
		}
		
		return shouldContinue;
	}
}
