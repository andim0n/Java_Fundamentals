package optional_task;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

    public class OptionalTaskTwo {
        private Random random = new Random();
        private Scanner input;
        private double[][] matrix;
        private int rows;
        private int columns;
        private int rowMax;
        private int columnMax;
        private int rowZero;
        private int columnZero;

        public static void main(final String[] args) {

            OptionalTaskTwo classObject = new OptionalTaskTwo();
            classObject.startProgram();

        }

        private void startProgram() {

            rows = inputMatrixSize();
            columns = rows;

            // generate
            matrix = new double[rows][columns];
            matrix = fillMatrix(matrix, rows);
            System.out.print("\ngenerated matrix:\n");
            printMatrix(matrix);

            // round off
            System.out.print("\nrounded matrix:\n");
            matrix = roundOff(matrix);
            printMatrix(matrix);

            // delete columns rows with max
            double maxNumber = findMaxNumber(matrix);
            System.out.printf("\nmax number in matrix %f\n", maxNumber);
            System.out.println("\ndelete columns and rows with max\n");
            while (hasMax(matrix, maxNumber)) {
                matrix = deleteColumnAndRow(matrix);
            }
            printMatrix(matrix);

            // compact
            System.out.println("\ncompact matrix\n");
            matrix = compactMatrix(matrix);
            printMatrix(matrix);

            // sort by k
            int k = random.nextInt(rows);
            System.out.printf("\nsort rows by %d column:\n\n", k);
            matrix = sortRowsByColumn(matrix, k);
            printMatrix(matrix);

            // sub average
            double average = countAverage(matrix);
            System.out.printf("\nsubtract the average %f\n\n", average);
            matrix = subtractNum(matrix, average);
            printMatrix(matrix);

            // count between positive numbers
            double count = countNumBetweenPositive(matrix);
            System.out.printf("\ncount between positive numbers %f\n", count);

            // transpose
            System.out.println("\ntransposed matrix\n");
            matrix = transposeMatrix(matrix);
            printMatrix(matrix);
        }

        private int inputMatrixSize() {
            input = new Scanner(System.in);
            int inputNumber;

            System.out.println("Enter matrix size");
            do {
                System.out.println("Please enter a positive integer (2+):");
                while (!input.hasNextInt()) {
                    System.out.println("This not an integer! Try again: ");
                    input.next();
                }
                inputNumber = input.nextInt();
            } while (inputNumber < 2);
            return inputNumber;
        }

        private double[][] fillMatrix(final double[][] getMatrix, final int matrixSize) {
            for (int i = 0; i < getMatrix.length; i++) {
                for (int j = 0; j < getMatrix.length; j++) {
                    getMatrix[i][j] = generateRandomNum(matrixSize);
                }
            }
            return getMatrix;
        }

        private double generateRandomNum(final int scope) {
            double randomInt = (Math.random() * (scope * 2) - scope);
            return randomInt;
        }

        private void printMatrix(final double[][] getMatrix) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    System.out.printf("%f\t", getMatrix[i][j]);
                }
                System.out.print("\n");
            }
        }

        private double[][] roundOff(final double[][] getMatrix) {
            for (int i = 0; i < getMatrix.length; i++) {
                for (int j = 0; j < getMatrix.length; j++) {
                    getMatrix[i][j] = Math.round(getMatrix[i][j]);
                }
            }
            return getMatrix;
        }

        private double findMaxNumber(final double[][] getMatrix) {
            double maxNum = getMatrix[0][0];
            for (int i = 0; i < getMatrix.length; i++) {
                for (int j = 0; j < getMatrix.length; j++) {
                    double num = getMatrix[i][j];
                    if (num > maxNum) {
                        maxNum = num;
                    }
                }
            }
            return maxNum;
        }

        private double[][] deleteColumnAndRow(final double[][] getMatrix) {
            double[][] newMatrix = new double[rows - 1][columns - 1];
            int newI = 0, newJ = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (i != rowMax) {
                        if (j != columnMax) {
                            newMatrix[newI][newJ] = getMatrix[i][j];
                            newJ++;
                        }
                    } else {
                        newI--;
                        break;
                    }
                }
                newI++;
                newJ = 0;
            }
            columns--;
            rows--;
            return newMatrix;
        }

        private boolean hasMax(final double[][] getMatrix, final double max) {
            for (int i = 0; i < getMatrix.length; i++) {
                for (int j = 0; j < getMatrix.length; j++) {
                    double num = getMatrix[i][j];
                    if (num == max) {
                        rowMax = i;
                        columnMax = j;
                        return true;
                    }
                }
            }
            return false;
        }

        private double[][] deleteColumn(final double[][] getMatrix, final int colNum) {
            double[][] newMatrix = new double[getMatrix.length][getMatrix.length - 1];
            int newJ = 0;
            for (int i = 0; i < getMatrix.length; i++) {
                for (int j = 0; j < getMatrix.length; j++) {
                    if (j != colNum) {
                        newMatrix[i][newJ] = getMatrix[i][j];
                        newJ++;
                    }
                }
                newJ = 0;
            }
            columns--;
            return newMatrix;
        }

        private double[][] deleteRow(final double[][] getMatrix, final int rowNum) {
            double[][] newMatrix = new double[getMatrix.length - 1][getMatrix.length];
            int newI = 0;
            for (int i = 0; i < getMatrix.length; i++) {
                for (int j = 0; j < getMatrix.length - 1; j++) {
                    if (i != rowNum) {
                        newMatrix[newI][j] = getMatrix[i][j];
                    } else {
                        newI--;
                        break;
                    }
                }
                newI++;
            }
            rows--;
            return newMatrix;
        }

        private boolean rowFullOfZero(final double[][] getMatrix) {
            int zerosInRow = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (getMatrix[i][j] == 0) {
                        zerosInRow++;
                    }
                }
                if (zerosInRow == columns) {
                    rowZero = i;
                    return true;

                }
                zerosInRow = 0;
            }
            return false;
        }

        private boolean columnFullOfZero(final double[][] getMatrix) {
            int zerosInColumn = 0;
            for (int j = 0; j < rows; j++) {
                for (int i = 0; i < columns; i++) {
                    if (getMatrix[i][j] == 0) {
                        zerosInColumn++;
                    }
                }
                if (zerosInColumn == rows) {
                    columnZero = j;
                    return true;
                }
                zerosInColumn = 0;
            }
            return false;
        }

        private double[][] compactMatrix(final double[][] getMatrix) {
            while (columnFullOfZero(getMatrix)) {
                deleteColumn(getMatrix, columnZero);
            }
            while (rowFullOfZero(getMatrix)) {
                deleteRow(getMatrix, rowZero);
            }
            return getMatrix;
        }

        private double[][] sortRowsByColumn(final double[][] matrix, final int column) {
            double[][] sortedMatrix = new double[matrix.length][matrix.length];
            double[] array = new double[matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                array[i] = matrix[i][column];
            }
            Arrays.sort(array);
            int newI = 0;
            int i = 0;
            while (newI < array.length) {
                if (array[newI] == matrix[i][column]) {
                    for (int j = 0; j < matrix.length; j++) {
                        sortedMatrix[newI][j] = matrix[i][j];
                    }
                    newI++;
                }
                i++;
                if (!(i < matrix.length)) {
                    i = 0;
                }
            }
            return sortedMatrix;
        }

        private double countAverage(final double[][] getMatrix) {
            double sum = 0;
            int quantity = 0;
            for (int i = 0; i < getMatrix.length; i++) {
                for (int j = 0; j < getMatrix.length; j++) {
                    sum += getMatrix[i][j];
                    quantity++;
                }
            }
            double averageNum = sum / quantity;
            return averageNum;
        }

        private double[][] subtractNum(final double[][] getMatrix, final double num) {
            for (int i = 0; i < getMatrix.length; i++) {
                for (int j = 0; j < getMatrix.length; j++) {
                    getMatrix[i][j] = getMatrix[i][j] - num;
                }
            }
            return getMatrix;
        }

        private double countNumBetweenPositive(final double[][] getMatrix) {
            double sumAll = 0;
            double sumRow = 0;
            boolean hasFirst = false;
            boolean hasSecond = false;
            for (int i = 0; i < getMatrix.length; i++) {
                for (int j = 0; j < getMatrix.length; j++) {
                    double num = getMatrix[i][j];
                    if (num > 0) {
                        if (!hasFirst) {
                            hasFirst = true;
                            continue;
                        } else {
                            hasSecond = true;
                            break;
                        }
                    }
                    if (hasFirst) {
                        sumRow = sumRow + num;
                    }
                }
                if (hasFirst && hasSecond) {
                    hasFirst = false;
                    hasSecond = false;
                    sumAll = sumAll + sumRow;
                } else {
                    hasFirst = false;
                    hasSecond = false;
                    sumRow = 0;
                }

            }
            return sumAll;
        }

        private double[][] transposeMatrix(final double[][] getMatrix) {
            double[][] transposedMatrix = new double[columns][rows];
            for (int i = 0; i < transposedMatrix.length; i++) {
                for (int j = 0; j < transposedMatrix.length; j++) {
                    transposedMatrix[i][j] = getMatrix[j][i];
                }
            }
            return transposedMatrix;
        }

    }


