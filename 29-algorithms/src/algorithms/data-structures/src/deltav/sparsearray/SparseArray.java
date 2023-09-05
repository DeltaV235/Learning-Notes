package deltav.sparsearray;

import java.io.*;

public class SparseArray {
    private static final int ORIGINAL_ARR_ROW_NUMBER = 11;
    private static final int ORIGINAL_ARR_COL_NUMBER = 11;
    private static final String FILE_PATH = "data-structures/src/deltav/sparsearray/sparse-array";

    public static void main(String[] args) {
        // 1.create original array
        int[][] originalArray = new int[ORIGINAL_ARR_ROW_NUMBER][ORIGINAL_ARR_COL_NUMBER];
        originalArray[1][2] = 1;
        originalArray[2][3] = 2;
        originalArray[4][5] = 2;
        printArrayString(originalArray, "original array: ");

        // 2.1.covert it to sparse array
        // calculate count of non-zero element
        int sum = 0;
        for (int[] row : originalArray) {
            for (int data : row) {
                if (data != 0) {
                    sum++;
                }
            }
        }

        // 2.2 create sparse array
        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = ORIGINAL_ARR_ROW_NUMBER;
        sparseArray[0][1] = ORIGINAL_ARR_COL_NUMBER;
        sparseArray[0][2] = sum;
        int sparseCount = 0;
        for (int i = 0; i < originalArray.length; i++) {
            for (int j = 0; j < originalArray[i].length; j++) {
                if (originalArray[i][j] != 0) {
                    sparseCount++;
                    sparseArray[sparseCount][0] = i;
                    sparseArray[sparseCount][1] = j;
                    sparseArray[sparseCount][2] = originalArray[i][j];
                }
            }
        }
        printArrayString(sparseArray, "sparse array: ");
        writeArrayToFile(sparseArray, FILE_PATH);

        // 3.recover sparse array to 2-D array
        int[][] sparseArrayFromFile = readArrayFromFile(FILE_PATH);
        printArrayString(sparseArrayFromFile, "sparse array read from local filesystem: ");
        int targetArrayRowNumber = sparseArrayFromFile[0][0];
        int targetArrayColNumber = sparseArrayFromFile[0][1];
        int[][] targetArray = new int[targetArrayRowNumber][targetArrayColNumber];
        int nonZeroDataCount = sparseArrayFromFile[0][2];
        for (int i = 0; i < nonZeroDataCount; i++) {
            for (int j = 1; j < sparseArrayFromFile.length; j++) {
                int targetRow = sparseArrayFromFile[j][0];
                int targetCol = sparseArrayFromFile[j][1];
                int targetData = sparseArrayFromFile[j][2];
                targetArray[targetRow][targetCol] = targetData;
            }
        }
        printArrayString(targetArray, "target array: ");
    }

    private static void writeArrayToFile(int[][] array, String path) {
        try (OutputStream fileOutputStream = new FileOutputStream(path);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);) {
            objectOutputStream.writeObject(array);
            System.out.printf("%s saved successful\n", path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int[][] readArrayFromFile(String path) {
        try (InputStream fileInputStream = new FileInputStream(path);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
            return (int[][]) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printArrayString(int[][] outputArray, String tableTitle) {
        System.out.printf("%s\n", tableTitle);
        for (int[] row : outputArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        System.out.println();
    }
}
