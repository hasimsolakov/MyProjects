package Application;


public class Cell {
    private int row;
    private int column;
    private int shouldBeNumber;
    private Fraction number;

    public Cell(int row, int column, Fraction number) {
        this.setRow(row);
        this.setColumn(column);
        this.setShouldBeNumber(-1);
        this.setNumber(number);
    }

    public Cell(int row, int column, Fraction number, int shouldBeNumber)  {
        this(row, column, number);
        this.setShouldBeNumber(shouldBeNumber);
    }
    public Fraction getNumber() {
        return number;
    }

    public void setNumber(Fraction number) {
        this.number = number;
    }

    public int getShouldBeNumber() {
        return shouldBeNumber;
    }

    public void setShouldBeNumber(int shouldBeNumber) {
        this.shouldBeNumber = shouldBeNumber;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
