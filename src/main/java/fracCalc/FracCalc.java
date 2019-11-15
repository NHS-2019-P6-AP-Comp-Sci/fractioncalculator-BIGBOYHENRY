/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

public class FracCalc {

	public static void main(String[] args) {
		// TODO: Read the input from the user and call produceAnswer with an equation
		Scanner c = new Scanner(System.in);
		String userInput = c.nextLine();
		while (userInput.equals("quit") != true) {
			String answer = produceAnswer(userInput);
			System.out.println(answer);
			userInput = c.nextLine();
		}
		c.close();
	}

	// ** IMPORTANT ** DO NOT DELETE THIS FUNCTION. This function will be used to
	// test your code
	// This function takes a String 'input' and produces the result
	//
	// input is a fraction string that needs to be evaluated. For your program, this
	// will be the user input.
	// e.g. input ==> "1/2 + 3/4"
	//
	// The function should return the result of the fraction after it has been
	// calculated
	// e.g. return ==> "1_1/4"
	public static String produceAnswer(String input) {
		int space = input.indexOf(" ");
		String firstNum = input.substring(0, space);
		String secNum = input.substring(space + 3);
		int firstWhole = 0;
		int firstDeno = 0;
		int firstNumer = 0;
		if (firstNum.indexOf("/") == -1) {
			firstWhole = Integer.parseInt(firstNum);
		}
		else {
		if (firstNum.indexOf("_") != -1) {
			firstWhole = Integer.parseInt(firstNum.substring(0, firstNum.indexOf("_")));
				firstNumer = Integer.parseInt(firstNum.substring(firstNum.indexOf("_") + 1, firstNum.indexOf("/")));
				firstDeno = Integer.parseInt(firstNum.substring(firstNum.indexOf("/") + 1));
		}
		else if (firstNum.indexOf("/") != -1) {
			firstNumer = Integer.parseInt(firstNum.substring(0, firstNum.indexOf("/")));
			firstDeno = Integer.parseInt(firstNum.substring(firstNum.indexOf("/") + 1));
		}
		else {
			firstWhole = Integer.parseInt(firstNum);
			firstDeno = 1;
			firstNumer = firstWhole;
		}
	}
		int secWhole = 0;
		int secNumer = 0;
		int secDeno = 0;
		if (secNum.indexOf("/") == -1) {
			secWhole = Integer.parseInt(secNum);
			secNumer = 0;
			secDeno = 1;
		} else {
			if (secNum.indexOf("_") != -1) {
				secWhole = Integer.parseInt(secNum.substring(0, secNum.indexOf("_")));
				secNumer = Integer.parseInt(secNum.substring(secNum.indexOf("_") + 1, secNum.indexOf("/")));
				secDeno = Integer.parseInt(secNum.substring(secNum.indexOf(("/")) + 1));
			} else if (secNum.indexOf("/") != -1){
				secWhole = 0;
				secNumer = Integer.parseInt(secNum.substring(0, secNum.indexOf("/")));
				secDeno = Integer.parseInt(secNum.substring(secNum.indexOf("/") + 1));
			}
			else {
				secWhole = Integer.parseInt(secNum);
				secDeno = 1;
				secNumer = secWhole;
			}
		}
		secNum = "whole:" + secWhole + " numerator:" + secNumer + " denominator:" + secDeno;
		return secNum;
	}

	public static int simplifyNum(int newNum, int newDeno) {
		for (int i = 1; i < newDeno; i++) {
			int checkNum = newNum % i;
			int checkDeno = newDeno % i;
			if (checkDeno == 0 && checkNum == 0) {
				newNum = newNum / i;
				newDeno = newDeno / i;
			}
		}
		return newNum;
	}

	public static int simplifyDeno(int newNum, int newDeno) {
		for (int i = 1; i < newDeno; i++) {
			int checkNum = newNum % i;
			int checkDeno = newDeno % i;
			if (checkDeno == 0 && checkNum == 0) {
				newNum = newNum / i;
				newDeno = newDeno / i;
			}
		}

		return newDeno;
	}
	// TODO: Fill in the space below with any helper methods that you think you will
	// need

}
