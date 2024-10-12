import java.time.*;
/************************************************************/
// * This Main Doesn't Guarantee Full Marks
/************************************************************/
public class Main {
    /*
     * Tips for using this Main
     *
     * Comment the tasks you are not currently testing
     *
     * THESE TESTS ARE TRIVIAL. Make sure you expand and do more testing
     */
    // your code here
    public static void main(String[] args) {
        System.out.println("----------------------- Elapsed time: 0 ms -------------------------");
        Instant start = Instant.now();
        // Task 1
        task1();
        // Task 2
        task21(); // 2 x 2
        task22(); // 2 x 3
        task23(); // 3 x 2
        task24(); // 3 x 3
        task25(); // 3 x 4
        // Task 3
        task31(); // 2 x 2
        task32(); // 2 x 3
        task33(); // 3 x 2
        task34(); // 3 x 3
        task35(); // 3 x 4
        task36(); // 3 x 4 Test 2
        task37(); // IncorrectSudoku Testing
        Instant end = Instant.now();
        Duration elapsed = Duration.between(start, end);
        System.out.println("----------------------- Elapsed time: " + elapsed.toMillis() + " ms -----------------------");
        System.out.println("------------- Elapsed time must be way less than 500ms -------------");
        System.out.println("------------------ NB: It's not a requirement But, -----------------");
        System.out.println("---- 1.It helps avoid problems with FF's Session Timeout Error! ----");
        System.out.println("-------------- 2.It also exposes infinite loops (*_*) --------------");
    }

    public static void task1() {
        System.out.println("------------- Task1 LIST --------------");
        List<Integer> l = new List<>();
        for (int i = 1; i <= 9; i++) {
            l.append(i);
        }
        System.out.println(l.length + "\t" + l);
        l.remove(2);
        System.out.println(l.length + "\t" + l);
        l.remove(1);
        System.out.println(l.length + "\t" + l);
        l.remove(9);
        System.out.println(l.length + "\t" + l);
        l.remove(4);
        System.out.println(l.length + "\t" + l);
        System.out.println("--------------- DONE ------------------");
    }

    public static void task21() {
        System.out.println("-------------- Task2 2x2 --------------");
        SudokuSolver s1 = new SudokuSolver("Puzzles/2x2.txt");
        System.out.println("### Print Board ###");
        System.out.println(s1.board());
        System.out.println("### testLinks ###");
        s1.board().testLinks();
        System.out.println("### testCells ###");
        s1.board().testCells();
        System.out.println("--------------- DONE ------------------");
    }

    public static void task22() {
        System.out.println("-------------- Task2 2x3 --------------");
        SudokuSolver s1 = new SudokuSolver("Puzzles/2x3.txt");
        System.out.println("### Print Board ###");
        System.out.println(s1.board());
        System.out.println("### testLinks ###");
        s1.board().testLinks();
        System.out.println("### testCells ###");
        s1.board().testCells();
        System.out.println("--------------- DONE ------------------");
    }

    public static void task23() {
        System.out.println("-------------- Task2 3x2 --------------");
        SudokuSolver s1 = new SudokuSolver("Puzzles/3x2.txt");
        System.out.println("### Print Board ###");
        System.out.println(s1.board());
        System.out.println("### testLinks ###");
        s1.board().testLinks();
        System.out.println("### testCells ###");
        s1.board().testCells();
        System.out.println("--------------- DONE ------------------");
    }

    public static void task24() {
        System.out.println("-------------- Task2 3x3 --------------");
        SudokuSolver s1 = new SudokuSolver("Puzzles/3x3.txt");
        System.out.println("### Print Board ###");
        System.out.println(s1.board());
        System.out.println("### testLinks ###");
        s1.board().testLinks();
        System.out.println("### testCells ###");
        s1.board().testCells();
        System.out.println("--------------- DONE ------------------");
    }

    public static void task25() {
        System.out.println("-------------- Task2 3x4 --------------");
        SudokuSolver s1 = new SudokuSolver("Puzzles/3x4.txt");
        System.out.println("### Print Board ###");
        System.out.println(s1.board());
        System.out.println("### testLinks ###");
        s1.board().testLinks();
        System.out.println("### testCells ###");
        s1.board().testCells();
        System.out.println("--------------- DONE ------------------");
    }

    public static void task31() {
        System.out.println("-------------- Task3 2x2 --------------");
        SudokuSolver s1 = new SudokuSolver("Puzzles/2x2.txt");
        System.out.println("-- Constructor Done ---");
        s1.solveBoard();
        System.out.println("--------------- DONE ------------------");
    }

    public static void task32() {
        System.out.println("-------------- Task3 2x3 --------------");
        SudokuSolver s1 = new SudokuSolver("Puzzles/2x3.txt");
        System.out.println("-- Constructor Done ---");
        s1.solveBoard();
        System.out.println("--------------- DONE ------------------");
    }

    public static void task33() {
        System.out.println("-------------- Task3 3x2 --------------");
        SudokuSolver s1 = new SudokuSolver("Puzzles/3x2.txt");
        System.out.println("-- Constructor Done ---");
        s1.solveBoard();
        System.out.println("--------------- DONE ------------------");
    }

    public static void task34() {
        System.out.println("-------------- Task3 3x3 --------------");
        SudokuSolver s1 = new SudokuSolver("Puzzles/3x3.txt");
        System.out.println("-- Constructor Done ---");
        s1.solveBoard();
        System.out.println("--------------- DONE ------------------");
    }

    public static void task35() {
        System.out.println("-------------- Task3 3x4 --------------");
        SudokuSolver s1 = new SudokuSolver("Puzzles/3x4.txt");
        System.out.println("-- Constructor Done ---");
        s1.solveBoard();
        System.out.println("--------------- DONE ------------------");
    }

    public static void task36() {
        System.out.println("-------- Task3 Second 3x4 Test --------");
        SudokuSolver s1 = new SudokuSolver("Puzzles/3x4_2.txt");
        System.out.println("-- Constructor Done ---");
        s1.solveBoard();
        System.out.println("--------------- DONE ------------------");
    }

    public static void task37() {
        System.out.println("-------- Task3 incorrectSudoku --------");
        SudokuSolver s1 = new SudokuSolver("Puzzles/incorrectSudoku.txt");
        System.out.println("-- Constructor Done ---");
        s1.solveBoard();
        System.out.println("--------------- DONE ------------------");
    }
}