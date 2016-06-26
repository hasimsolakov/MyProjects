package Application;


public class FirstTypeMatrixSolver {
    private Matrix matrix;
    public FirstTypeMatrixSolver(Matrix matrix) {
        this.matrix = matrix;
        this.setShouldBeNumbersOfCells();
    }

    public Matrix GetSolvedMatrix(){
        for (int column = 0; column <this.matrix.getColumnsCount() - 1; column++) {
            for (int row = 0; row <this.matrix.getRowsCount(); row++) {
                Cell currentCell = this.matrix.getCell(row, column);
                boolean nonSolvable = this.checkForNonSolvableRow(currentCell);
                if (nonSolvable){
                    System.out.println("The given matrix has no solution");
                    return null;
                }
                else if ((double)currentCell.getShouldBeNumber() == currentCell.getNumber().getResult()){
                    continue;
                }
                else if (currentCell.getShouldBeNumber() == -1){
                    continue;
                }
                else if (currentCell.getShouldBeNumber() == 1){
                    this.setCellNumberToOne(currentCell);
                }
                else if(currentCell.getShouldBeNumber() == 0){
                    this.setCellNumberToZero(currentCell);
                }

                this.printMatrix();
                System.out.println();
            }
        }

        return this.matrix;
    }

    private void printMatrix(){
        for (Iterable<Cell> row : this.matrix) {
            for (Cell cell:row) {
                System.out.print(cell.getNumber() + "  ");
            }

            System.out.println();
        }
    }
    private void setCellNumberToZero(Cell cell){
        for (int row = 0; row < this.matrix.getRowsCount(); row++) {
            Cell targetCell = this.matrix.getCell(row, cell.getColumn());
            if (row == cell.getRow()){
                continue;
            }
            else if (targetCell.getNumber().getResult() == 1d){
                this.matrix.substractRows(cell.getRow(), row, new Fraction(cell.getNumber().getNumerator(), cell.getNumber().getDenumerator()));
                return;
            }
        }
    }

    private boolean checkForNonSolvableRow(Cell cell){
        boolean nonSolvable = true;
        boolean endValueOfRowIsNotZero = this.matrix.getCell(cell.getRow(), this.matrix.getColumnsCount() - 1).getNumber().getResult() != 0d;
        for (int column = 0; column <this.matrix.getColumnsCount() - 1; column++) {
            if (this.matrix.getCell(cell.getRow(), column).getNumber().getResult() != 0d){
                nonSolvable = false;
            }
        }

        if (!endValueOfRowIsNotZero && nonSolvable){
            nonSolvable = false;
        }

        return nonSolvable;
    }

    private void setCellNumberToOne(Cell cell){
        if (cell.getNumber().getResult() != 0d){
            this.matrix.divideRow(cell.getRow(), cell.getNumber());
        }
        else {
            for (int row = 0; row <this.matrix.getRowsCount(); row++) {
                if (row == cell.getRow()){
                    continue;
                }
                double valueOfTheCell = this.matrix.getCell(row, cell.getColumn()).getNumber().getResult();
                if (valueOfTheCell == -1d){
                    this.matrix.substractRows(cell.getRow(), row);
                    return;
                }
                else if(valueOfTheCell == 1d){
                    this.matrix.sumRows(cell.getRow(), row);
                    return;
                }
            }

            int targetRow = cell.getRow() + 1;
            if (targetRow == this.matrix.getRowsCount()){
                targetRow = cell.getRow() - 1;
            }

            Cell tartgetCell = this.matrix.getCell(targetRow, cell.getColumn());
            if (tartgetCell.getNumber().getResult() == 0d){
                for (int row = 0; row <this.matrix.getRowsCount(); row++) {
                    if (cell.getRow() == row){
                        continue;
                    }

                    tartgetCell = this.matrix.getCell(row, cell.getColumn());
                    if (tartgetCell.getNumber().getResult() != 0d){
                        break;
                    }
                }
            }

            Fraction multiplicand = tartgetCell.getNumber();

            this.matrix.sumRows(cell.getRow(), targetRow, new Fraction(multiplicand.getDenumerator(), multiplicand.getNumerator()) );
        }
    }

    private void setShouldBeNumbersOfCells(){
        for (int row = 0, colstoMakeZeroCount = 0; row < matrix.getRowsCount(); row++, colstoMakeZeroCount++) {
            int col;
            for (col = 0; col < colstoMakeZeroCount; col++) {
                matrix.getCell(row, col).setShouldBeNumber(0);
            }

            this.matrix.getCell(row, col).setShouldBeNumber(1);
        }
    }

    private void CheckIfItIsSolved(){

    }
}
