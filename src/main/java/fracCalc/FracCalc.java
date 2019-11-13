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
		String test = "";
		test = test + "1";
		String answer = produceAnswer(userInput);
		test = answer;
		System.out.println(test);
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
		String  secNum = input.substring(space + 3);
		return secNum;
		
		// TODO: Implement this function to produce the solution to the input
		/*int space = 0;
		int firstDash = 0;
		int secondDash = 0;
		String answer = "hhh";
		space = input.indexOf(" ");
		String first = input.substring(0, space);
		String second = input.substring(space + 3, input.length());
		String operator = input.substring(space + 1, space + 2);
		int operatorcheck = 0;
		if (operator.indexOf("+") == 0) {
			operatorcheck = 1;
		}
		if (operator.indexOf("-") == 0) {
			operatorcheck = 2;
		}
		if (operator.indexOf("*") == 0) {
			operatorcheck = 3;
		}
		if (operator.indexOf("/") == 0) {
			operatorcheck = 4;
		}
		firstDash = first.indexOf("/");
		secondDash = second.indexOf("/");
		int firstNum = Integer.parseInt(first.substring(0, firstDash));
		int firstDeno = Integer.parseInt(first.substring(firstDash + 1, first.length()));
		int secondNum = Integer.parseInt(second.substring(0, secondDash));
		int secondDeno = Integer.parseInt(second.substring(secondDash + 1, second.length()));
		int impNum = 0;
		int impWhole = 0;
		if (operatorcheck == 1) {
			int newNum = (firstNum * secondDeno) + (secondNum * firstDeno);
			int newDeno = secondDeno * firstDeno;
			if (newNum > newDeno) {
				
				impNum = newNum % newDeno;
				impWhole = newNum / newDeno;
				impNum = simplifyNum(newNum, newDeno);
				newDeno = simplifyDeno(newNum, newDeno);
				impNum = impNum % newDeno;
				answer = impWhole + "_" + impNum + "/" + newDeno;
				if (impNum / newDeno == 0) {
					answer = impWhole + "";
				}
			}
			if (newNum < newDeno) {
				newDeno = simplifyDeno(newNum, newDeno);
				newNum = simplifyNum(newNum, newDeno);
				answer = newNum + "/" + newDeno;
				if (newNum == 0) {
					answer = "0";
				}

			}
			if (newNum == newDeno) {
				answer = "1";
			}
		}
		if (operatorcheck == 2) {
			int newNum = (firstNum * secondDeno) - (secondNum * firstDeno);
			int newDeno = secondDeno * firstDeno;
			int oldDeno = newDeno;
			if (newNum > newDeno) {
				
				impNum = newNum % newDeno;
				impWhole = newNum / newDeno;
				impNum = simplifyNum(newNum, newDeno);
				newDeno = simplifyDeno(newNum, newDeno);
				impNum = impNum % newDeno;
				answer = impWhole + "_" + impNum + "/" + newDeno;
				if (impNum / newDeno == 0) {
					answer = impWhole + "";
				}
			}
			if (newNum < newDeno) {
				newDeno = simplifyDeno(newNum, newDeno);
				int newnewNum = simplifyNum(newNum, oldDeno);
				answer = newnewNum + "/" + newDeno;
				if (newNum == 0) {
					answer = "0";
				}

			}
			if (newNum == oldDeno) {
				answer = "1";
			}
		}
		return answer;
		*/}
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
