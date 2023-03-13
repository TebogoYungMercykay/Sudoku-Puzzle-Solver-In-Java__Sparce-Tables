public class Cell {
    public int numRows, numCols, r, c, b;
    public Cell below, right, block;
    public Integer value;
    public List<Integer> possibleValues;

    public String toString() {
        if (value == null) {

            String res = " ";
            for (int i = 0; i < String.valueOf(numRows * numCols).length(); i++) {
                res += "-";
            }
            res += " ";
            return res;
        }
        return " " + String.format("%" + String.valueOf(numRows * numCols).length() + "d", value).replace(" ", "0")
                + " ";
    }

    /*
     * Don't change anything above this line
     */

    public Cell(int nR, int nC, String inp) {
        this.numRows = nR;
        this.numCols = nC;
        if(inp == "-") {
            this.value = null;
            this.possibleValues = new List();
            for(Integer counter = 1; counter <= this.numCols * this.numRows; counter++) {
                this.possibleValues.append(counter);
            }
        }
        else {
            this.setVal(Integer.parseInt(inp));
        }
    }

    public void removeVal(int val) {
        if(this.possibleValues != null) {
            boolean tempRemove = this.possibleValues.remove(val);
            tempRemove = !(!tempRemove);
            return;
        }
        else {
            return;
        }
    }

    public void setVal(int val) {
        this.value = val;
        this.possibleValues = null;
    }
}