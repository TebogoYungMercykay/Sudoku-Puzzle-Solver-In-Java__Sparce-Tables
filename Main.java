public class Main {
    /*
     * Tips for using this Main
     * 
     * The output is in out.txt
     * 
     * Comment the tasks you are not currently testing
     * 
     * Look up "Text Comparison" for your IDE of choice. This way you can save your
     * output to a txt file and use that to see if there are differences between
     * your output and my output.
     * 
     * THESE TESTS ARE TRIVIAL. Make sure you expand and do more testing
     */
    public static void main(String[] args) {
        task1();
        task2();
        task3();
    }

    public static void task1() {
        System.out.println("==========\nTask1\n==========");
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
    }

    public static void task2() {
        System.out.println("==========\nTask2\n==========");
        SudokuSolver s1 = new SudokuSolver("2x3.txt");
        System.out.println("### Print Board ###");
        System.out.println(s1.board());
        System.out.println("### testLinks ###");
        s1.board().testLinks();
        System.out.println("### testCells ###");
        s1.board().testCells();
    }

    public static void task3() {
        System.out.println("==========\nTask3\n==========");
        SudokuSolver s1 = new SudokuSolver("2x3.txt");
        s1.solveBoard();
    }
}
