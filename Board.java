public class Board {
    private int numRows, numCols;
    private Cell cells[], rows[], cols[], blocks[];

    public String toString(){
        String res = "";
        for(int r = 0; r < numRows * numCols; r++){
            if (r % numRows == 0){
                res += horizLine() + "\n";
            }
            for(int c = 0; c < numRows * numCols; c++){
                if (c % numCols == 0){
                    res += "|";
                }
                res += cells[r * numRows * numCols + c];
            }
            res += "|\n";
        }

        res += horizLine();
        return res;
    }

    public String horizLine(){
        String res = "";
        for(int i = 0; i < numRows + 1 + (numRows * numCols * (String.valueOf(numRows * numCols).length() + 2)); i++){
            res += "-";
        }
        return res;
    }

    public void testLinks(){
        System.out.println("Rows forward");

        for(int r = 0; r < numRows * numCols; r++){
            System.out.print("Row " + r + "\t");
            Cell ptr = rows[r];
            while(ptr != null){
                System.out.print(indexOf(ptr) + " ");
                ptr = ptr.right;
            }
            System.out.println();
        }

        System.out.println("Cols forward");

        for(int c = 0; c < numRows * numCols; c++){
            System.out.print("Col " + c + "\t");
            Cell ptr = cols[c];
            while(ptr != null){
                System.out.print(indexOf(ptr) + " ");
                ptr = ptr.below;
            }
            System.out.println();
        }

        System.out.println("Blocks");
        for(int b = 0; b < numRows * numCols; b++){
            System.out.print("Block " + b + "\t");
            Cell ptr = blocks[b];
            while(ptr != null){
                System.out.print(indexOf(ptr) + " ");
                ptr = ptr.block;
            }
            System.out.println();
        }
    }

    public void testCells(){
        System.out.println("Cell\tCoord\tBlock\ttoString");
        for(Cell c : cells){
            System.out.println(indexOf(c) + "\t(" + c.r + "," + c.c + ")\t" + c.b + "\t" + c);
        }
    }

    public int indexOf(Cell c){
        for(int i = 0; i < numRows * numRows * numCols * numCols; i++){
            if (cells[i].equals(c)){
                return i;
            }
        }
        return -1;
    }

    public Cell cellAt(int r, int c){
        if (r < 0 || r >= numRows * numCols || c < 0 || c >= numRows * numCols){
            return null;
        }
        return cells[r * numRows * numCols + c];
    }

    /*
     * Don't change anything above this line
    */

    public Board(int r, int c, String boardString) {
        numRows = r;
        numCols = c;
        String[] temp_array = boardString.split(" ");
        cells = new Cell[numRows * numCols * numRows * numCols];
        for(int i = 0; i < numRows * numCols * numRows * numCols; i++) {
            cells[i] = null;
        }
        int m = 0, x = 0, numRow = numRows - 1, v = 0;
        for (int i = 0; i < numRows * numCols; i++) {
            for (int j = 0; j < numRows * numCols; j++) {
                if (m < temp_array.length) {
                    int y = 1;
                    if (temp_array[m].contains("-") == false) {
                        cells[m] = new Cell(numRows, numCols, temp_array[m]);
                    }
                    else{
                        cells[m] = new Cell(numRows, numCols, "-");
                    }
                }
                cells[m].r = i;
                cells[m].c = j;
                m++;
            }
        }
        m = 0;
		for(int i = 0; i < numRows * numCols * numRows * numCols; i = i + numCols, v++) {
            int i2 = i;
            for(int j = 0 ; j < numRows; j++, i2 += numCols * numRow) {
    	        for(int k = 0; k < numCols; k++) {
    	            cells[i2].b = m;
                    i2++;
    	        }
            }
	        if ((x + 1) == numRows) {
                i = i + numRows * numCols * numRow;
    	        x = 0;
	        }
            else{
                x++;
            }
            m++;
		}
        /*
            * 1. Initializing the rows, cols and blocks 1D arrays.
            * 2. Then setting the links for below, right and block.
        */
        setLinks();
    }

    public void setLinks() {
        boolean change = false;
        int m = 0, v = numRows * numCols - 1, x = 0;
        cols = new Cell[numRows * numCols];
        rows = new Cell[numRows * numCols];
        blocks = new Cell[numRows * numCols];
        for(int i = 0; i < numRows * numCols; i++) {
            cols[i] = null;
            rows[i] = null;
            blocks[i] = null;
        }
        m = 0;
        for(int t = 0; t < numRows * numCols * numRows * numCols; t += numRows * numCols, m++) {
            rows[m] = cells[t];
            Cell cellPtr = rows[m];
            for(int i = 0; i < numRows * numCols; i++) {
                if (i < v) {
                    cellPtr.right = cells[i + t + 1];
                }
                cellPtr = cellPtr.right;
            }
        }
        m = 0;
        for(int t = 0; t < numRows * numCols; t++) {
            cols[m] = cells[m];
            Cell cellPtr = cols[m];
            for(int i = 0; i < numRows * numCols * numRows * numCols; i += numRows * numCols) {
                if ((i + t + numRows * numCols) < (numRows * numCols * numRows * numCols)) {
                    cellPtr.below = cells[i + t + numRows * numCols];
                }
                cellPtr = cellPtr.below;
            }
            m++;
        }

        m = 0;
        int temp = 0;
        for(int i = 0; i < numRows * numCols * numRows * numCols; i += numCols, temp++) {
            blocks[temp] = cells[i];
            int i2 = i;
            int numCol = numCols - 1;
            int numRow = numRows - 1;
            for(int j = 0 ; j < numRows; j++, i2 += numCols * numRow) {
                if (change == true) {
                    cells[m].block = cells[i2];
                    change = false;
                }
                for(int temp_col = 0; temp_col < numCols; temp_col++, i2++) {
                    if (temp_col == numCol && j == numRow) {
                        cells[i2].block = null;
                    }
                    else{
                        if (temp_col == numCol) {
                            change = true;
                            m = i2;
                        }
                        else{
                            cells[i2].block = cells[i2 + 1];
                        }
                    }
                }
            }
            if ((x + 1) == numRows) {
                i = i + numRows * numCols * numRow;
    	        x = 0;
	        }
            else{
                x++;
            }
        }
    }

    public void solve(){
        int counter = 0;
        while(soleCandidate() || uniqueCandidate() || duplicateCells()){
            counter++;
        }
        System.out.println("Number of moves: " + counter);
    }

     public void fullProp() {
        for (Cell c : cells) {
            propCell(c);
        }
    }

    public void propCell(Cell cell) {
        if (cell == null || cell.value == null) {
            return;
        }
        for (Cell r = rows[cell.r]; r != null; r = r.right) {
            r.removeVal(cell.value);
        }
        for (Cell c = cols[cell.c]; c != null; c = c.below) {
            c.removeVal(cell.value);
        }
        for (Cell b = blocks[cell.b]; b != null; b = b.block) {
            b.removeVal(cell.value);
        }
    }

    /*
        * UML DIAGRAM SUMMARY FOR QUICK REFERENCE
        * NODE METHODS ( public T data, public Node<T> next ):
            -String toString();
            -Node(T val);

        * LIST METHODS ( public int length, public Node<T> head ):
            -List();
            -String toString();
            -void append(T val);
            -boolean remove(T val);
            -boolean remove(List<T> val);
            -boolean contains(T search);
            -boolean equals(List<T> other)

        * CELL METHODS ( public int numRows, numCols, r, c, b,
                       public Cell below, right, block, public Integer value,
                       public List<Integer> possibleValues ):
            -String toString();
            -Cell(int nR, int nC, String inp);
            -void removeVal(int val);
            -void setVal(int val);
    */

    public boolean soleCandidate() {
        for (Cell c : cells) {
            if (c.possibleValues != null && c.possibleValues.length == 1) {
                Node<Integer> value = c.possibleValues.head;
                c.setVal(value.data);
                propCell(c);
                return true;
            }
        }
        return false;
    }

    public boolean uniqueCandidate() {
	// * ROWS
        for (Cell row : rows) {
            int counts[] = new int[numRows * numCols];
            for(int i = 0; i < numRows * numCols; i++) {
                counts[i] = 0;
            }
            Cell rowPtr = row;
            while(rowPtr != null) {
                if (rowPtr.possibleValues != null) {
                    Node<Integer> nodePtr = rowPtr.possibleValues.head;
                    while(nodePtr != null) {
                        counts[nodePtr.data - 1]++;
                        nodePtr = nodePtr.next;
                    }
                }
                rowPtr = rowPtr.right;
            }
            for(int i = 0; i < numRows * numCols; i++) {
                if (counts[i] == 1) {
                    rowPtr = row;
                    while(rowPtr != null) {
                        if (rowPtr.possibleValues != null && rowPtr.possibleValues.contains(i + 1)) {
                            rowPtr.setVal(i + 1);
                            propCell(rowPtr);
                        }
                        rowPtr = rowPtr.right;
                    }
                    return true;
                }
            }
        }
	// + COLS
        for (Cell col : cols) {
            int counts[] = new int[numRows * numCols];
            for(int i = 0; i < numRows * numCols; i++) {
                counts[i] = 0;
            }
            Cell colPtr = col;
            while(colPtr != null) {
                if (colPtr.possibleValues != null) {
                    Node<Integer> nodePtr = colPtr.possibleValues.head;
                    while(nodePtr != null) {
                        counts[nodePtr.data - 1]++;
                        nodePtr = nodePtr.next;
                    }
                }
                colPtr = colPtr.below;
            }
            for(int i = 0; i < numRows * numCols; i++) {
                if (counts[i] == 1) {
                    colPtr = col;
                    while(colPtr != null) {
                        if (colPtr.possibleValues != null && colPtr.possibleValues.contains(i + 1)) {
                            colPtr.setVal(i + 1);
                            propCell(colPtr);
                        }
                        colPtr = colPtr.below;
                    }
                    return true;
                }
            }
        }
	// * BLOCKS
        for (Cell block : blocks) {
            int counts[] = new int[numRows * numCols];
            for(int i = 0; i < numRows * numCols; i++) {
                counts[i] = 0;
            }
            Cell blockPtr = block;
            while(blockPtr != null) {
                if (blockPtr.possibleValues != null) {
                    Node<Integer> nodePtr = blockPtr.possibleValues.head;
                    while(nodePtr != null) {
                        counts[nodePtr.data - 1]++;
                        nodePtr = nodePtr.next;
                    }
                }
                blockPtr = blockPtr.block;
            }
            for(int i = 0; i < numRows * numCols; i++) {
                if (counts[i] == 1) {
                    blockPtr = block;
                    while(blockPtr != null) {
                        if (blockPtr.possibleValues != null && blockPtr.possibleValues.contains(i + 1)) {
                            blockPtr.setVal(i + 1);
                            propCell(blockPtr);
                        }
                        blockPtr = blockPtr.block;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean duplicateCells() {
        // * ROWS
    	for (Cell row : rows) {
            Cell rowPtr = row;
            while(rowPtr != null) {
                if (rowPtr.possibleValues != null && rowPtr.possibleValues.length == 2) {
                    Cell secondPtr = rowPtr.right;
                    while(secondPtr != null) {
                        if (rowPtr.possibleValues.equals(secondPtr.possibleValues) == true) {
                            Cell thirdPtr = row;
                            boolean change = false;
                            while(thirdPtr != null) {
                                if (thirdPtr.equals(secondPtr) == false && thirdPtr.equals(rowPtr) == false && thirdPtr.possibleValues != null) {
                                    change = change || (thirdPtr.possibleValues.remove(rowPtr.possibleValues));
                                }
                                thirdPtr = thirdPtr.right;
                            }
                            if (change == true) {
                                return true;
                            }
                        }
                        secondPtr = secondPtr.right;
                    }
                }
                rowPtr = rowPtr.right;
            }
        }
	// * COLS
        for (Cell col : cols) {
            Cell colPtr = col;
            while(colPtr != null) {
                if (colPtr.possibleValues != null && colPtr.possibleValues.length == 2) {
                    Cell secondPtr = colPtr.below;
                    while(secondPtr != null) {
                        if (colPtr.possibleValues.equals(secondPtr.possibleValues) == true) {
                            Cell thirdPtr = col;
                            boolean change = false;
                            while(thirdPtr != null) {
                                if (thirdPtr.equals(secondPtr) == false && thirdPtr.equals(colPtr) == false && thirdPtr.possibleValues != null) {
                                    change = change || (thirdPtr.possibleValues.remove(colPtr.possibleValues));
                                }
                                thirdPtr = thirdPtr.below;
                            }
                            if (change == true) {
                                return true;
                            }
                        }
                        secondPtr = secondPtr.below;
                    }
                }
                colPtr = colPtr.below;
            }
        }
	// * BLOCKS
        for (Cell block : blocks) {
            Cell blockPtr = block;
            while(blockPtr != null) {
                if (blockPtr.possibleValues != null && blockPtr.possibleValues.length == 2) {
                    Cell secondPtr = blockPtr.block;
                    while(secondPtr != null) {
                        if (blockPtr.possibleValues.equals(secondPtr.possibleValues) == true) {
                            Cell thirdPtr = block;
                            boolean change = false;
                            while(thirdPtr != null) {
                                if (thirdPtr.equals(secondPtr) == false && thirdPtr.equals(blockPtr) == false && thirdPtr.possibleValues != null) {
                                    change = change || (thirdPtr.possibleValues.remove(blockPtr.possibleValues));
                                }
                                thirdPtr = thirdPtr.block;
                            }
                            if (change == true) {
                                return true;
                            }
                        }
                        secondPtr = secondPtr.block;
                    }
                }
                blockPtr = blockPtr.block;
            }
        }
        return false;
    }
}
