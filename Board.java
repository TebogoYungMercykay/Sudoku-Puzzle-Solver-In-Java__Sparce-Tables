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

    public void setLinks(){
        if(this.cells != null){
            // * MSetting up variables
            final int length_cell = this.numRows * this.numCols;
            this.cols = new Cell[length_cell];
            this.rows = new Cell[length_cell];
            this.blocks = new Cell[length_cell];

            // * System.out.println("Row Links Automation Algorithm");
            for(int row_next = 0; row_next < (length_cell * length_cell); row_next += length_cell){
                for(int current_head = 0; current_head < length_cell; current_head++){
                    if((current_head + 1) < length_cell){
                        this.cells[current_head + row_next].right = this.cells[current_head + row_next + 1];
                    }
                    else{
                        this.cells[current_head + row_next].right = null;
                    }
                }
            }
            // * System.out.println("Col Links Automation Algorithm");
            for(int col_next = 0; col_next < length_cell; col_next++){
                for(int current_head = 0; current_head < length_cell * length_cell; current_head += length_cell){
                    if((current_head + col_next + length_cell) < (length_cell * length_cell)){
                        this.cells[current_head + col_next].below = cells[current_head + col_next + length_cell];
                    }
                    else{
                        this.cells[current_head + col_next].below = null;
                    }
                }
            }
            // * System.out.println("Block Links Automation Algorithm");
            int change_row = 0;
            boolean temp_next_block = false;
            int temp_next_b = 0;
            for(int current_head = 0; current_head < length_cell * length_cell; current_head += this.numCols){
                int temp_current_head = current_head;
                for(int temp_row = 0 ; temp_row < this.numRows; temp_row++, temp_current_head += this.numCols * (this.numRows - 1)){
                    if(temp_next_block == true){
                        this.cells[temp_next_b].block = this.cells[temp_current_head];
                        temp_next_block = false;
                    }
                    for(int temp_col = 0; temp_col < this.numCols; temp_col++){
                        if(temp_col == this.numCols - 1 && temp_row == this.numRows - 1){
                            this.cells[temp_current_head].block = null;
                        }
                        else{
                            if(temp_col == this.numCols - 1){
                                temp_next_block = true;
                                temp_next_b = temp_current_head;
                            }
                            else{
                                this.cells[temp_current_head].block = this.cells[temp_current_head + 1];
                            }
                        }
                        temp_current_head++;
                    }
                }
                change_row += 1;
                if(change_row == this.numRows){
                    current_head += length_cell * (this.numRows - 1);
                    change_row = 0;
                }
            }

            // * Storing the Heads for each column, row and block.
            int counter_var = 0;
            change_row = 0;
            for(int index = 0; index < length_cell; index++){
                this.cols[counter_var] = this.cells[index];
                counter_var++;
            }
            counter_var = 0;
            for(int index = 0; index < length_cell * length_cell; index += length_cell){
                this.rows[counter_var] = this.cells[index];
                counter_var++;
            }
            counter_var = 0;
            for(int index = 0; index < length_cell * length_cell; index += this.numCols){
                this.blocks[counter_var] = this.cells[index];
                counter_var++; change_row += 1;
                if(change_row == this.numRows){
                    index += length_cell * (this.numRows - 1);
                    change_row = 0;
                }
            }
        }
    }

    public Board(int r, int c, String boardString){
        this.numCols = c;
        this.numRows = r;
        final int length_cell = this.numRows * this.numCols;
        this.cells = new Cell[length_cell*length_cell];
        String temp_BoardString = boardString.replaceAll("\\s", "");
        int cellIndex = 0;
        for(int rowIndex = 0; rowIndex < length_cell; rowIndex++){
            for(int colIndex = 0; colIndex < length_cell; colIndex++){
                String inp = "";
                if(temp_BoardString.charAt(cellIndex) == '-'){
                    inp = "-";
                }
                else{
                    inp = Character.toString(temp_BoardString.charAt(cellIndex));
                }
                this.cells[cellIndex] = new Cell(this.numRows, this.numCols, inp);
                this.cells[cellIndex].c = colIndex;
                this.cells[cellIndex].r = rowIndex;
                cellIndex++;
            }
        }
        // * Setting the b-blocks member variable for the cells
		int block_number = 0;
        int change_row = 0;
		for(int current_head = 0; current_head < length_cell * length_cell; current_head += this.numCols){
            int temp_current_head = current_head;
            for(int temp_row = 0 ; temp_row < this.numRows; temp_row++, temp_current_head += this.numCols * (this.numRows - 1)){
    	        for(int temp_col = 0; temp_col < this.numCols; temp_col++){
    	            this.cells[temp_current_head++].b = block_number;
    	        }
            }
	        change_row += 1;
	        if(change_row == this.numRows){
                current_head += length_cell * (this.numRows - 1);
    	        change_row = 0;
	        }
            block_number++;
		}
        /*
            * 1. Initializing the rows, cols and blocks 1D arrays.
            * 2. Then setting the links for below, right and block.
        */
        this.setLinks();
    }

    public void solve(){
        int counter = 0;
        while(soleCandidate () || uniqueCandidate () || duplicateCells ()){
            counter++;
        }
        System.out.println("Number of moves: " + counter);
    }

    public void fullProp(){
        final int length_cell = this.numRows * this.numCols;
        for(int cellIndex = 0; cellIndex < length_cell * length_cell; cellIndex++){
            this.propCell(cells[cellIndex]);
        }
    }

    public void propCell(Cell cell){
        if(cell == null || cell.value == null || this.cells == null){
            return;
        }
        else{
            final int length_cell = this.numRows * this.numCols;
            // * System.out.println("Row Propagate Automation Algorithm");
            for(int row_index = 0; row_index < length_cell; row_index++){
                if(row_index == cell.r){
                    Cell cellPointer = this.rows[row_index];
                    while(cellPointer != null){
                        if(cellPointer.possibleValues != null){
                            cellPointer.possibleValues.remove(cell.value);
                        }
                        cellPointer = cellPointer.right;
                    }
                }
            }
            // * System.out.println("Col Propagate Automation Algorithm");
            for(int col_index = 0; col_index < length_cell; col_index++){
                if(col_index == cell.c){
                    Cell cellPointer = this.cols[col_index];
                    while(cellPointer != null){
                        if(cellPointer.possibleValues != null){
                            cellPointer.possibleValues.remove(cell.value);
                        }
                        cellPointer = cellPointer.below;
                    }
                }
            }
            // * System.out.println("Block Propagate Automation Algorithm");
            for(int block_number = 0; block_number < length_cell; block_number++){
                if(block_number == cell.b){
                    Cell cellPointer = this.blocks[block_number];
                    while(cellPointer != null){
                        if(cellPointer.possibleValues != null){
                            cellPointer.possibleValues.remove(cell.value);
                        }
                        cellPointer = cellPointer.block;
                    }
                }
            }
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

    public boolean soleCandidate(){
        // * I FOLLOWED THE PROVIDED PSEUDOCODE FOR THIS METHOD
        final int length_cell = this.numRows * this.numCols;
        for(int row_index = 0; row_index < length_cell; row_index++){
            Cell cellPointer = this.rows[row_index];
            while(cellPointer != null){
                if(cellPointer.possibleValues != null && cellPointer.possibleValues.length == 1){
                    Integer val = cellPointer.possibleValues.head.data;
                    cellPointer.possibleValues.remove(val);
                    cellPointer.setVal(val);
                    this.propCell(cellPointer);
                    return true;
                }
                cellPointer = cellPointer.right;
            }
        }
        return false;
    }

    public boolean uniqueCandidate(){
        // * I FOLLOWED THE PROVIDED PSEUDOCODE FOR THIS METHOD
        final int length_cell = this.numRows * this.numCols;
        // * ROWS
        for(int row_index = 0; row_index < length_cell; row_index++){
            int counts[] = new int[length_cell];
            for(int index = 0; index < length_cell; index++){
                counts[index] = 0;
            }
            Cell rowPtr = this.rows[row_index];
            while(rowPtr != null){
                if(rowPtr.possibleValues != null){
                    Node<Integer> nodePtr = rowPtr.possibleValues.head;
                    while(nodePtr != null){
                        counts[nodePtr.data - 1] = counts[nodePtr.data - 1] + 1;
                        nodePtr = nodePtr.next;
                    }
                }
                rowPtr = rowPtr.right;
            }
            for(int i = 0; i < length_cell; i++){
                if(counts[i] == 1){
                    rowPtr = this.rows[i];
                    while(rowPtr != null){
                        if(rowPtr.possibleValues != null && rowPtr.possibleValues.contains(i + 1)){
                            rowPtr.setVal(i + 1);
                            this.propCell(rowPtr);
                        }
                        rowPtr = rowPtr.right;
                    }
                    return true;
                }
            }
        }
        // * COLS
        for(int col_index = 0; col_index < length_cell; col_index++){
            int counts[] = new int[length_cell];
            for(int index = 0; index < length_cell; index++){
                counts[index] = 0;
            }
            Cell colPtr = this.cols[col_index];
            while(colPtr != null){
                if(colPtr.possibleValues != null){
                    Node<Integer> nodePtr = colPtr.possibleValues.head;
                    while(nodePtr != null){
                        counts[nodePtr.data - 1] = counts[nodePtr.data - 1] + 1;
                        nodePtr = nodePtr.next;
                    }
                }
                colPtr = colPtr.below;
            }
            for(int i = 0; i < length_cell; i++){
                if(counts[i] == 1){
                    colPtr = this.rows[i];
                    while(colPtr != null){
                        if(colPtr.possibleValues != null && colPtr.possibleValues.contains(i + 1)){
                            colPtr.setVal(i + 1);
                            this.propCell(colPtr);
                        }
                        colPtr = colPtr.below;
                    }
                    return true;
                }
            }
        }
        // * BLOCKS
        for(int block_index = 0; block_index < length_cell; block_index++){
            int counts[] = new int[length_cell];
            for(int index = 0; index < length_cell; index++){
                counts[index] = 0;
            }
            Cell blockPtr = this.blocks[block_index];
            while(blockPtr != null){
                if(blockPtr.possibleValues != null){
                    Node<Integer> nodePtr = blockPtr.possibleValues.head;
                    while(nodePtr != null){
                        counts[nodePtr.data - 1] = counts[nodePtr.data - 1] + 1;
                        nodePtr = nodePtr.next;
                    }
                }
                blockPtr = blockPtr.block;
            }
            for(int i = 0; i < length_cell; i++){
                if(counts[i] == 1){
                    blockPtr = this.rows[i];
                    while(blockPtr != null){
                        if(blockPtr.possibleValues != null && blockPtr.possibleValues.contains(i + 1)){
                            blockPtr.setVal(i + 1);
                            this.propCell(blockPtr);
                        }
                        blockPtr = blockPtr.block;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean duplicateCells(){
        final int length_cell = this.numRows * this.numCols;
        // * ROWS
        for(int row_index = 0; row_index < length_cell; row_index++){
            Cell rowPtr = this.rows[row_index];
            while(rowPtr != null){
                if(rowPtr.possibleValues != null && rowPtr.possibleValues.length == 2){
                    Cell secondPtr = rowPtr.right;
                    while(secondPtr != null){
                        if(rowPtr.possibleValues.equals(secondPtr.possibleValues)){
                            Cell thirdPtr = this.rows[row_index];
                            boolean change = false;
                            while(thirdPtr != null){
                                if(thirdPtr != secondPtr && thirdPtr != rowPtr && thirdPtr.possibleValues != null){
                                    change = change || (thirdPtr.possibleValues.remove(rowPtr.possibleValues));
                                }
                                thirdPtr = thirdPtr.right;
                            }
                            if(change == true){
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
        for(int col_index = 0; col_index < length_cell; col_index++){
            Cell colPtr = this.cols[col_index];
            while(colPtr != null){
                if(colPtr.possibleValues != null && colPtr.possibleValues.length == 2){
                    Cell secondPtr = colPtr.below;
                    while(secondPtr != null){
                        if(colPtr.possibleValues.equals(secondPtr.possibleValues)){
                            Cell thirdPtr = this.cols[col_index];
                            boolean change = false;
                            while(thirdPtr != null){
                                if(thirdPtr != secondPtr && thirdPtr != colPtr && thirdPtr.possibleValues != null){
                                    change = change || (thirdPtr.possibleValues.remove(colPtr.possibleValues));
                                }
                                thirdPtr = thirdPtr.below;
                            }
                            if(change == true){
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
        for(int block_index = 0; block_index < length_cell; block_index++){
            Cell blockPtr = this.rows[block_index];
            while(blockPtr != null){
                if(blockPtr.possibleValues != null && blockPtr.possibleValues.length == 2){
                    Cell secondPtr = blockPtr.block;
                    while(secondPtr != null){
                        if(blockPtr.possibleValues.equals(secondPtr.possibleValues)){
                            Cell thirdPtr = this.rows[block_index];
                            boolean change = false;
                            while(thirdPtr != null){
                                if(thirdPtr != secondPtr && thirdPtr != blockPtr && thirdPtr.possibleValues != null){
                                    change = change || (thirdPtr.possibleValues.remove(blockPtr.possibleValues));
                                }
                                thirdPtr = thirdPtr.block;
                            }
                            if(change == true){
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