public class Board {
    private int numRows, numCols;
    private Cell cells[], rows[], cols[], blocks[];

    public String toString(){
        String res = "";
        for (int r = 0; r < numRows * numCols; r++){
            if (r % numRows == 0){
                res += horizLine() + "\n";
            }
            for (int c = 0; c < numRows * numCols; c++){
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
        for (int i = 0; i < numRows + 1 + (numRows * numCols * (String.valueOf(numRows * numCols).length() + 2)); i++){
            res += "-";
        }
        return res;
    }

    public void testLinks(){
        System.out.println("Rows forward");

        for (int r = 0; r < numRows * numCols; r++){
            System.out.print("Row " + r + "\t");
            Cell ptr = rows[r];
            while (ptr != null){
                System.out.print(indexOf(ptr) + " ");
                ptr = ptr.right;
            }
            System.out.println();
        }

        System.out.println("Cols forward");

        for (int c = 0; c < numRows * numCols; c++){
            System.out.print("Col " + c + "\t");
            Cell ptr = cols[c];
            while (ptr != null){
                System.out.print(indexOf(ptr) + " ");
                ptr = ptr.below;
            }
            System.out.println();
        }

        System.out.println("Blocks");
        for (int b = 0; b < numRows * numCols; b++){
            System.out.print("Block " + b + "\t");
            Cell ptr = blocks[b];
            while (ptr != null){
                System.out.print(indexOf(ptr) + " ");
                ptr = ptr.block;
            }
            System.out.println();
        }
    }

    public void testCells(){
        System.out.println("Cell\tCoord\tBlock\ttoString");
        for (Cell c : cells){
            System.out.println(indexOf(c) + "\t(" + c.r + "," + c.c + ")\t" + c.b + "\t" + c);
        }
    }

    public int indexOf(Cell c){
        for (int i = 0; i < numRows * numRows * numCols * numCols; i++){
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
            for(int current_head = 0; current_head < length_cell * length_cell; current_head += this.numCols){
                int temp_current_head = current_head;
                for(int temp_row = 0 ; temp_row < this.numRows; temp_row++, temp_current_head += this.numCols * (this.numRows - 1)){
                    for(int temp_col = 0; temp_col < this.numCols; temp_col++){
                        if(temp_col == this.numCols - 1 && temp_row == this.numRows - 1){
                            this.cells[temp_current_head].right = null;
                        }
                        else{
                            this.cells[temp_current_head].right = this.cells[temp_current_head + 1];
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
                this.cells[cellIndex] = new Cell(this.numRows, this.numCols, Character.toString(temp_BoardString.charAt(cellIndex)));
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

    /*
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
            int row_index = cell.r;
            for(int row_next = row_index; row_next < (length_cell * length_cell); row_next += length_cell){
                if(row_next < ((row_index + 1) * length_cell)){
                    for(int current_head = 0; current_head < length_cell; current_head++){
                        // Just to make sure we do not remove from a null object
                        if(this.cells[current_head + row_next] != null && this.cells[current_head + row_next].possibleValues != null){
                            this.cells[current_head + row_next].possibleValues.remove(cell.value);
                        }
                    }
                }
            }
            // * System.out.println("Col Propagate Automation Algorithm");
            int col_index = cell.c;
            for(int col_next = 0; col_next < length_cell; col_next++){
                if(col_index == col_next){
                    for(int current_head = 0; current_head < length_cell * length_cell; current_head += length_cell){
                        // Just to make sure we do not remove from a null object
                        if(this.cells[current_head + col_next] != null && this.cells[current_head + col_next].possibleValues != null){
                            this.cells[current_head + col_next].possibleValues.remove(cell.value);
                        }
                    }
                }
            }
            // * System.out.println("Block Propagate Automation Algorithm");
            int change_row = 0;
            int block_number = cell.b, counter_block = 0;
            for(int current_head = 0; current_head < length_cell * length_cell; current_head += this.numCols){
                if(block_number == counter_block){
                    int temp_current_head = current_head;
                    for(int temp_row = 0 ; temp_row < this.numRows; temp_row++, temp_current_head += this.numCols * (this.numRows - 1)){
                        for(int temp_col = 0; temp_col < this.numCols; temp_col++){
                            // Just to make sure we do not remove from a null object
                            if(this.cells[temp_current_head] != null && this.cells[temp_current_head].possibleValues != null){
                                this.cells[temp_current_head].possibleValues.remove(cell.value);
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
                counter_block += 1;
            }
        }
    }

    public boolean soleCandidate(){
        return false;
    }

    public boolean uniqueCandidate(){
        return false;
    }

    public boolean duplicateCells(){
        return false;
    }
}