package Application;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Main {

    public static void main(String[] args) {
       Matrix matrix = new Matrix(4, 5);
        matrix.fillByRow(0, 2, -1, 1, -1, 1);
        matrix.fillByRow(1, 2, -1, 0, -3, 2);
        matrix.fillByRow(2, 3, 0, -1, 1, -3);
        matrix.fillByRow(3, 2, 2, -2, 5, -6);
        FirstTypeMatrixSolver solver  = new FirstTypeMatrixSolver(matrix);
        solver.GetSolvedMatrix();
    }
}
