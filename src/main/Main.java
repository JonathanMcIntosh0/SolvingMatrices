package main;

import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Fraction[][] matrix; //[eq][var]
        String[] answers;
        boolean answerExists = true;
        int numEquation;
        int numVar;

        System.out.println("Enter number of equations :");
        numEquation = scanner.nextInt();
        System.out.println("Enter number of variables :");
        numVar = scanner.nextInt() + 1;

        matrix = new Fraction[numEquation][numVar];
        answers = new String[numVar - 1];

        for (int indEq = 0; indEq < numEquation; indEq++) {
            System.out.println("Equation " + (indEq + 1));
            for (int indVar = 0; indVar < numVar; indVar++) {
                if (indVar == numVar - 1) System.out.println("Constant =");
                else System.out.println("X" + (indVar + 1) + " = ");
                matrix[indEq][indVar] = parseInput(scanner.next());
            }
        }

        System.out.println("Augmented Matrix :");
        for (int i = 0; i < numEquation; i++) {
            for (int j = 0; j < numVar; j++) {
                System.out.print(matrix[i][j] + " ");
                if (j == numVar - 1) System.out.println();
            }
        }

        //Turn matrix into Gaussian form
        int numOfLeading = 0;
        for (int indVar = 0; indVar < numVar - 1; indVar++) {
            boolean foundLeading = false;
            for (int indEq = numOfLeading; indEq < numEquation; indEq++) {
                if (!matrix[indEq][indVar].equals(0)) {
                    if (!foundLeading) {
                        Equations.switchEq(matrix, numOfLeading, indEq);
                        Equations.constantMulti(
                                matrix,
                                numOfLeading,
                                Fraction.divide(
                                        new Fraction(1),
                                        matrix[numOfLeading][indVar]
                                )
                        );
                        numOfLeading++;
                        foundLeading = true;
                        indEq = -1;
                    } else if (indEq != numOfLeading - 1) {
                        Equations.addEq(
                                matrix,
                                indEq,
                                numOfLeading - 1,
                                Fraction.multiply(
                                        new Fraction(-1),
                                        matrix[indEq][indVar]
                                )
                        );
                    }
                }
            }
        }

        int indAns = 0;
        for (int indEq = 0; indEq < numEquation && answerExists; indEq++) {
            boolean foundLeading = false;
            for (int indVar = indAns; indVar < numVar - 1; indVar++) {
                if (!matrix[indEq][indVar].equals(0)) {
                    if (!foundLeading) {
                        foundLeading = true;
                        indAns = indVar;
                        answers[indAns] = matrix[indEq][numVar - 1].toString();
                    } else {
                        if (answers[indVar] == null) {
                            answers[indVar] = "S" + (indVar + 1);
                        }
                        answers[indAns] += (
                                " + " +
                                Fraction.multiply(
                                        new Fraction(-1),
                                        matrix[indEq][indVar]
                                ).toString() +
                                "*" +
                                answers[indVar]
                        );
                    }
                }
            }
            if (!foundLeading && !matrix[indEq][numVar - 1].equals(0)) {
                answerExists = false;
            }
        }

        System.out.println("Gaussian Matrix :");
        for (int i = 0; i < numEquation; i++) {
            for (int j = 0; j < numVar; j++) {
                System.out.print(matrix[i][j] + " ");
                if (j == numVar - 1) System.out.println();
            }
        }

        if (answerExists) {
            System.out.println("General Solution :");
            for (int i = 0; i < answers.length; i++) {
                System.out.println("X" + (i + 1) + " = " + answers[i]);
            }
        } else System.out.println("No solutions exist!");
    }

    //ToDO optimize parsing of input
    private static Fraction parseInput(String str) {
        Fraction total;
        int posOfDiv = str.indexOf('/');
        if (posOfDiv != -1) {
            total = new Fraction(
                    Integer.parseInt(str.substring(0, posOfDiv)),
                    Integer.parseInt(str.substring(posOfDiv + 1, str.length()))
            );
        } else {
            total = new Fraction(
                    Integer.parseInt(str)
            );
        }
        return total;
    }
//    private
}
