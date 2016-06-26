package Application;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.function.Consumer;

public class Matrix implements Iterable<List<Cell>> {
    private int rowsCount;
    private int columnsCount;
    private List<Cell>[] matrixTable;

    public Matrix(int rowsCount, int columnsCount) {
        this.setRowsCount(rowsCount);
        this.setColumnsCount(columnsCount);
        this.matrixTable = new List[this.rowsCount];
    }

    public void fillByRow(int indexOfRow, Fraction ... rowOfFractions){
        if (rowOfFractions.length != columnsCount){
            throw new InvalidParameterException("Given row is not valid to fill the matrix");
        }
        else{
            if (matrixTable[indexOfRow] == null){
                this.matrixTable[indexOfRow] = new ArrayList<Cell>(this.columnsCount + 2);
            }
             int column = 0;
            for (Fraction fraction: rowOfFractions) {
                this.matrixTable[indexOfRow].add(new Cell(indexOfRow,column, fraction ));
                column++;
            }
        }
    }

    public void fillByRow(int indexOfRow, int ... rowOfIntegers){
        if (rowOfIntegers.length != this.columnsCount){
            throw new InvalidParameterException("Given row is not valid");
        }
        else{
            if (matrixTable[indexOfRow] == null){
                this.matrixTable[indexOfRow] = new ArrayList<Cell>(this.columnsCount + 2);
            }
            int col = 0;
            for (int integer: rowOfIntegers) {
                this.matrixTable[indexOfRow].add(new Cell(indexOfRow, col, new Fraction(integer, 1)));
                col++;
            }
        }
    }

    public void multiplyRow(int rowIndex, int multiplicand){
        for (Cell cell: this.matrixTable[rowIndex]) {
            cell.getNumber().multiply(new Fraction(multiplicand, 1));
        }
    }

    public void multiplyRow(int rowIndex, Fraction multiplicandFraction){
        for (Cell cell : this.matrixTable[rowIndex]) {
            cell.getNumber().multiply(multiplicandFraction);
        }
    }

    public void divideRow(int rowIndex, Fraction dividerFraction){
        this.multiplyRow(rowIndex, new Fraction(dividerFraction.getDenumerator(), dividerFraction.getNumerator()));
    }

    public void divideRow(int rowIndex, int divider){
        this.multiplyRow(rowIndex, new Fraction(1, divider));
    }

    public void sumRows(int rowIndexOfSumRow, int rowIndexOfAddendRow, Fraction multiplicand){
        List<Cell> initialRow = new ArrayList<Cell>(this.matrixTable[rowIndexOfAddendRow].size());
        for (int col = 0; col < this.columnsCount; col++) {
            Cell cell = this.matrixTable[rowIndexOfAddendRow].get(col);
            initialRow.add(new Cell(rowIndexOfAddendRow, col, (new Fraction(cell.getNumber().getNumerator(), cell.getNumber().getDenumerator())), cell.getShouldBeNumber()));
        }

        this.multiplyRow(rowIndexOfAddendRow, multiplicand);
        this.sumRows(rowIndexOfSumRow, rowIndexOfAddendRow);
        this.matrixTable[rowIndexOfAddendRow] = initialRow;
    }

    public void sumRows(int rowIndexOfSumRow, int rowIndexOfAddendRow){
        for (int column = 0; column < this.columnsCount; column++) {
            this.matrixTable[rowIndexOfSumRow]
                    .get(column).getNumber()
                    .add(this.matrixTable[rowIndexOfAddendRow].get(column).getNumber());
        }
    }

    public void substractRows(int minuendRowIndex, int subtrahendRowIndex, Fraction multiplicand){
        multiplicand.multiply(new Fraction(-1, 1));
        this.sumRows(minuendRowIndex, subtrahendRowIndex, multiplicand);
    }

    public void substractRows(int minuendRowIndex, int subtrahendRowIndex){
        for (int column = 0; column <this.columnsCount; column++) {
            this.matrixTable[minuendRowIndex]
                    .get(column).getNumber()
                    .substract(this.matrixTable[subtrahendRowIndex].get(column).getNumber());
        }
    }

    public void setRowsCount(int rowsCount) {
        if (rowsCount < 2){
            throw new InvalidParameterException("Rows count cannot be less than 2");
        }

        this.rowsCount = rowsCount;
    }

    public int getRowsCount(){
        return this.rowsCount;
    }

    public Cell getCell(int row, int column){
        return this.matrixTable[row].get(column);
    }

    public void setColumnsCount(int columnsCount) {
        if (columnsCount < 2){
            throw new InvalidParameterException("Columns count cannot be less than 2");
        }

        this.columnsCount = columnsCount;
    }

    public int getColumnsCount(){
        return this.columnsCount;
    }

    @Override
    public Iterator<List<Cell>> iterator() {
        Iterator<List<Cell>> myIterator = Arrays.asList(this.matrixTable).iterator();
        return myIterator;
    }

    @Override
    public void forEach(Consumer<? super List<Cell>> action) {

    }

    @Override
    public Spliterator<List<Cell>> spliterator() {
        return null;
    }
}
