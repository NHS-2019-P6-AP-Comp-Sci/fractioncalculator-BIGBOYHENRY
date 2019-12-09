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
		while (userInput.equals("quit") != true) {//checks for quit
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
		if (firstNum.indexOf("/") == -1) {//checks if its integer
			firstWhole = Integer.parseInt(firstNum);
			firstDeno = 1;
		} else {//separating input
			if (firstNum.indexOf("_") != -1) {
				firstWhole = Integer.parseInt(firstNum.substring(0, firstNum.indexOf("_")));
				firstNumer = Integer.parseInt(firstNum.substring(firstNum.indexOf("_") + 1, firstNum.indexOf("/")));
				firstDeno = Integer.parseInt(firstNum.substring(firstNum.indexOf("/") + 1));
			} else if (firstNum.indexOf("/") != -1) {
				firstNumer = Integer.parseInt(firstNum.substring(0, firstNum.indexOf("/")));
				firstDeno = Integer.parseInt(firstNum.substring(firstNum.indexOf("/") + 1));
			} else {
				firstWhole = Integer.parseInt(firstNum);
				firstDeno = 1;
				firstNumer = firstWhole;
			}
		}
		int secWhole = 0;
		int secNumer = 0;
		int secDeno = 0;
		if (secNum.indexOf("/") == -1) {//checking for integers
			secWhole = Integer.parseInt(secNum);
			secNumer = 0;
			secDeno = 1;
		} else {//separating input
			if (secNum.indexOf("_") != -1) {
				secWhole = Integer.parseInt(secNum.substring(0, secNum.indexOf("_")));
				secNumer = Integer.parseInt(secNum.substring(secNum.indexOf("_") + 1, secNum.indexOf("/")));
				secDeno = Integer.parseInt(secNum.substring(secNum.indexOf(("/")) + 1));
			} else if (secNum.indexOf("/") != -1) {
				secWhole = 0;
				secNumer = Integer.parseInt(secNum.substring(0, secNum.indexOf("/")));
				secDeno = Integer.parseInt(secNum.substring(secNum.indexOf("/") + 1));
			} else {
				secWhole = Integer.parseInt(secNum);
				secDeno = 1;
				secNumer = secWhole;
				secWhole = 0;
			}
		}
		String answer = "temp";
		if (firstDeno == 0 || secDeno == 0) {//error checking
			answer = "Error: Cannot divide by 0";
			return answer;
		}
		String operator = input.substring(space + 1, space + 2);//operator defined
		int operatorcheck = 0;
		if (operator.indexOf("+") == 0) {//check operator
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
		int impNum = 0;
		int impWhole = 0;
		if (operatorcheck == 1) {//if operator is plus
			int newDeno = secDeno * firstDeno;//new denominator
			if (firstWhole < 0) {//checking for negatives
				firstNumer += Math.abs(firstWhole * firstDeno);
				firstNumer *= -1;
			} else {
				firstNumer += firstWhole * firstDeno;//new numerator
			}
			if (secWhole < 0) {//checking for negatives
				secNumer += Math.abs(secWhole * secDeno);
				secNumer *= -1;
			} else {
				secNumer += secWhole * secDeno;//new numerator
			}
			int newNum = (firstNumer * secDeno) + (secNumer * firstDeno);//adds
			if (Math.abs(newNum) > newDeno) {//checks if it's going to be a mixed fraction
				impNum = newNum % newDeno;//takes the new numerator
				impWhole = newNum / newDeno;//takes the new whole
				int oldNum = impNum;//temporary storage
				impNum = simplifyNum(impNum, newDeno);
				newDeno = simplifyDeno(oldNum, newDeno);
				if (impNum < 0 && impWhole < 0) {//negative checking
					impNum *= -1;
				} else if (impNum < 0 && impWhole > 0) {
					impWhole *= -1;
				}
				answer = impWhole + "_" + impNum + "/" + newDeno;
				double bigCheck = (double) impNum / newDeno;//checking for wholes
				if (bigCheck == 0) {
					answer = impWhole + "";
				}
			}
			if (Math.abs(newNum) < newDeno) {//checks if it's not going to be a mixed fraction
				int oldNum = newNum;//temporary storage
				newNum = simplifyNum(newNum, newDeno);
				newDeno = simplifyDeno(oldNum, newDeno);
				if (impWhole != 0) {//mixed return
					answer = impWhole + "_" + newNum + "/" + newDeno;
				} else {//non-mixed return
					answer = newNum + "/" + newDeno;
				}
				if (newNum == 0) {//zero checking
					answer = "0";
				}

			}
			if (Math.abs(newNum) == newDeno) {//checks if it's equal to 1
				if (newNum < 0) {
					answer = Integer.toString(-1);
				} else {
					answer = Integer.toString(1);
				}
			}
		}
		if (operatorcheck == 2) {//code is exactly the same as the plus, with an operator difference
			int newDeno = secDeno * firstDeno;
			if (firstNumer < 0 || firstWhole < 0) {
				firstNumer = Math.abs(firstWhole * newDeno) + (firstNumer * secDeno);
				firstNumer *= -1;
			} else {
				firstNumer = (firstWhole * newDeno) + (firstNumer * secDeno);
			}
			if (secNumer < 0 || secWhole < 0) {
				secNumer = Math.abs(secWhole * newDeno) + (secNumer * firstDeno);
				secNumer *= -1;
			} else {
				secNumer = (secWhole * newDeno) + (secNumer * firstDeno);
			}
			int newNum = (firstNumer) - (secNumer);//subtracts
			if (Math.abs(newNum) > newDeno) {//mixed checking
				if (impWhole == 0) {//mixed fraction
					impNum = newNum % newDeno;
				} else {
					impNum = Math.abs(newNum % newDeno);
				}
				impWhole = newNum / newDeno;
				int oldNum = impNum;//temporary storage
				impNum = simplifyNum(impNum, newDeno);
				newDeno = simplifyDeno(oldNum, newDeno);
				impNum = impNum % newDeno;
				if (impNum < 0 && impWhole < 0) {//negative checking, making sure "-1_-1/4" doesn't happen
					impNum *= -1;
				} else if (impNum < 0 && impWhole > 0) {
					impWhole *= -1;
				}
				answer = impWhole + "_" + impNum + "/" + newDeno;
				double bigCheck = (double) impNum / newDeno;
				if (bigCheck == 0) {
					answer = impWhole + "";
				}
			}
			if (Math.abs(newNum) < newDeno) {
				int oldDeno = newDeno;//temporary storage
				newDeno = simplifyDeno(newNum, newDeno);
				newNum = simplifyNum(newNum, oldDeno);
				if (impWhole != 0) {

					answer = impWhole + "_" + newNum + "/" + newDeno;
				} else {
					answer = newNum + "/" + newDeno;
				}
				if (newNum == 0) {
					answer = "0";
				}
			}
			if (Math.abs(newNum) == Math.abs(newDeno)) {//checks for 1 or -1
				answer = Integer.toString(newNum / newDeno);
			}
		}
		if (operatorcheck == 3) {//checks if multiplication
			if (firstNumer == 0) {//checks if it's an integer
				firstNumer = firstWhole;
			} else {
				firstNumer += Math.abs(firstWhole) * firstDeno;//multiplies the whole into the numerator
			}
			if (firstWhole < 0 && firstNumer > 0) {//negative checking
				firstNumer *= -1;
			}
			if (secNumer == 0) {//does the same for the second numerator
				secNumer = secWhole;
			} else {
				secNumer += Math.abs(secWhole) * secDeno;
			}
			if (secWhole < 0 && secNumer > 0) {
				secNumer *= -1;
			}
			int newDeno = secDeno * firstDeno;
			int newNum = (firstNumer * secNumer);//multiplies
			if (Math.abs(newNum) > newDeno) {//checks if it's going to be a mixed fraction
				impNum = newNum % newDeno;
				impWhole = newNum / newDeno;
				int oldNum = impNum;//temporary storage
				impNum = simplifyNum(impNum, newDeno);
				newDeno = simplifyDeno(oldNum, newDeno);
				if ((firstWhole < 0 && secWhole > 0) || (secWhole < 0 && firstWhole > 0)) {//checks for negatives
					impNum -= impNum * 2;
				}
				if (impNum < 0 && impWhole < 0) {
					impNum *= -1;
				} else if (impNum < 0 && impWhole > 0) {
					impWhole *= -1;
				}
				answer = impWhole + "_" + impNum + "/" + newDeno;
				double bigCheck = (double) impNum / newDeno;
				if (bigCheck == 0) {
					answer = impWhole + "";
				}
			}
			if (Math.abs(newNum) < newDeno) {//checks if it's not going to be a mixed
				int oldDeno = newDeno;
				newDeno = simplifyDeno(newNum, newDeno);
				newNum = simplifyNum(newNum, oldDeno);
				if (impWhole != 0) {
					answer = impWhole + "_" + newNum + "/" + newDeno;
				} else {
					answer = newNum + "/" + newDeno;
				}
				if (newNum == 0) {//zero checking
					answer = "0";
				}

			}
			if (Math.abs(newNum) == newDeno) {//1 and -1 checking
				if (newNum < 0) {
					answer = Integer.toString(-1);
				} else {
					answer = Integer.toString(1);
				}
			}
		}
		if (operatorcheck == 4) {//the same as multiplication, but with the second numerator and denominator flipped
			if (firstNumer == 0) {//integer checking
				firstNumer = firstWhole;
			} else {
				firstNumer += Math.abs(firstWhole) * firstDeno;//multiplies whole into the numerator
				if (firstWhole < 0) {//negative checking
					firstNumer *= -1;
				}
			}
			if (secNumer == 0) {//does the same for the second part
				secNumer = secWhole;
			} else {
				secNumer += Math.abs(secWhole) * secDeno;
				if (secWhole < 0) {
					secNumer *= -1;
				}
			}
			int newDeno = (secNumer * firstDeno);//flips the second numerator and the second denominator
			int newNum = secDeno * firstNumer;
			if (newDeno < 0 && newNum < 0) {//checking if both numerator and denominators are negatives
				newDeno = Math.abs(newDeno);
				newNum = Math.abs(newNum);
			} else if (newDeno < 0 && newNum > 0) {//negative checking
				newNum *= -1;
				newDeno *= -1;
			}
			if (Math.abs(newNum) > newDeno) {//checks if it's going to be a mixed fraction
				impNum = newNum % newDeno;
				impWhole = newNum / newDeno;
				int oldNum = impNum;
				impNum = simplifyNum(impNum, newDeno);
				newDeno = simplifyDeno(oldNum, newDeno);
				if ((firstWhole < 0 && secWhole > 0) || (secWhole < 0 && firstWhole > 0)) {//negative checking
					impNum -= impNum * 2;
				}
				if (impNum < 0 && impWhole < 0) {//checking if both numerator and whole are negatives
					impNum *= -1;
				} else if (impNum < 0 && impWhole > 0) {//checking if only one is a negative
					impWhole *= -1;
					impNum *= -1;
				}
				answer = impWhole + "_" + impNum + "/" + newDeno;
				double bigCheck = (double) impNum / newDeno;
				if (bigCheck == 0) {
					answer = impWhole + "";
				}
			} else if (Math.abs(newNum) < newDeno) {
				int bigDeno = newDeno;
				newDeno = simplifyDeno(newNum, newDeno);
				newNum = simplifyNum(newNum, bigDeno);
				if (impWhole != 0) {
					answer = impWhole + "_" + newNum + "/" + newDeno;
				} else {
					answer = newNum + "/" + newDeno;
				}
				if (newNum == 0) {
					answer = "0";
				}

			}
			if (Math.abs(newNum) == newDeno) {//checking for negative 1
				if (newNum < 0) {
					answer = Integer.toString(-1);
				} else {
					answer = Integer.toString(1);
				}
			}
		}
		return answer;
	}
	public static int simplifyNum(int newNum, int newDeno) {
		for (int i = 1; i < newDeno; i++) {
			int checkNum = newNum % i;//checking if both the numerator and the denominator can be divided by something
			int checkDeno = newDeno % i;
			if (checkDeno == 0 && checkNum == 0) {//if so, then they both become that divided by i
				newNum = newNum / i;
				newDeno = newDeno / i;
				i = 1;//i resets
			}
		}
		return newNum;
	}

	public static int simplifyDeno(int newNum, int newDeno) {//exactly the same as the method above, different return
		for (int i = 1; i < newDeno; i++) {
			int checkNum = newNum % i;
			int checkDeno = newDeno % i;
			if (checkDeno == 0 && checkNum == 0) {
				newNum = newNum / i;
				newDeno = newDeno / i;
				i = 1;
			}
		}

		return newDeno;
	}
}
// TODO: Fill in the space below with any helper methods that you think you will
// need
