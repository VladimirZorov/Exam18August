import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TreasureHunt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rowIndex = 0;
        int colIndex = 0;

        List<String> rightPath = new ArrayList<>();

        String[] fieldSize = scanner.nextLine().split(" ");
        int rows = Integer.parseInt(fieldSize[0]);
        int cols = Integer.parseInt(fieldSize[1]);

        String[][] field = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            String[] row = scanner.nextLine().split(" ");
            for (int j = 0; j < cols; j++) {
                field[i][j] = row[j];


                if (field[i][j].equals("Y")) {
                    rowIndex = i;
                    colIndex = j;
                }
            }
        }

        String command = scanner.nextLine();

        while (!command.equals("Finish")) {
            switch (command) {
                case "up":

                    if (rowIndex - 1 >= 0 && !field[rowIndex-1][colIndex].equals("T")) {
                        field[rowIndex][colIndex] = "-";
                        rowIndex--;
                        rightPath.add("up");
                    }
                    break;
                case "down":

                    if (rowIndex + 1 < rows && !field[rowIndex+1][colIndex].equals("T")) {
                        field[rowIndex][colIndex] = "-";
                        rowIndex++;
                        rightPath.add("down");
                    }
                    break;
                case "left":

                    if (colIndex - 1 >= 0 && !field[rowIndex][colIndex-1].equals("T")) {
                        field[rowIndex][colIndex] = "-";
                        colIndex--;
                        rightPath.add("left");
                    }
                    break;
                case "right":

                    if (colIndex + 1 < cols && !field[rowIndex][colIndex+1].equals("T")) {
                        field[rowIndex][colIndex] = "-";
                        colIndex++;
                        rightPath.add("right");
                    }
                    break;
            }

            command = scanner.nextLine();
        }

        if (field[rowIndex][colIndex].equals("X")) {
            field[rowIndex][colIndex] = "X/Y";
        } else {
            field[rowIndex][colIndex] = "Y";
        }

        if (field[rowIndex][colIndex].equals("X/Y")) {
            System.out.printf("I've found the treasure!%n");
            System.out.print("The right path is ");
            for (int i = 0; i < rightPath.size(); i++) {
                System.out.print(rightPath.get(i));
                if (i< rightPath.size()-1) {
                    System.out.print(", ");
                }
            }
        } else {
            System.out.println("The map is fake!");
        }
    }
}
