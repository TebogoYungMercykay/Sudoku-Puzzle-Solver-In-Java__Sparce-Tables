import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuSolver {
    private int numRows, numCols;
    private Board sudokuBoard;

    public SudokuSolver(String fileName) {
        File f = new File(fileName);
        try (Scanner reader = new Scanner(f)) {
            String dimension = reader.nextLine();
            numRows = Integer.parseInt(dimension.substring(0, dimension.indexOf('x')));
            numCols = Integer.parseInt(dimension.substring(dimension.indexOf('x') + 1));

            String boardString = "";
            while (reader.hasNext()) {
                boardString += reader.nextLine() + " ";
            }
            reader.close();
            sudokuBoard = new Board(numRows, numCols, boardString);
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND!");
        }
    }

    public Board board() {
        return sudokuBoard;
    }

    public void solveBoard() {
        System.out.println(sudokuBoard);
        sudokuBoard.fullProp();
        sudokuBoard.solve();
        System.out.println(sudokuBoard);
    }
}