package com.ostn.triangulation.services;

import com.ostn.triangulation.request.Coordinates;
import com.ostn.triangulation.request.TriangulationRequest;

import java.util.List;
import java.util.ArrayList;

import org.apache.commons.math3.linear.*;

public class TriangulationService {




    public static Coordinates triangulate(List<TriangulationRequest> requests) {
        // Create lists to hold equations and constants
        List<RealVector> equations = new ArrayList<>();
        List<Double> constants = new ArrayList<>();

        // Populate equations and constants based on the TriangulationRequest objects
        for (TriangulationRequest request : requests) {
            Coordinates refCoordinates = request.getCoordinates();
            double distance = request.getDistance();

            double x = refCoordinates.getX();
            double y = refCoordinates.getY();

            // Equations for trilateration
            double[] equation = {2 * (x - requests.get(0).getCoordinates().getX()),
                    2 * (y - requests.get(0).getCoordinates().getY())};
            RealVector eqVector = new ArrayRealVector(equation);
            double constant = Math.pow(x, 2) + Math.pow(y, 2)
                    - Math.pow(requests.get(0).getCoordinates().getX(), 2)
                    - Math.pow(requests.get(0).getCoordinates().getY(), 2)
                    - Math.pow(distance, 2);

            equations.add(eqVector);
            constants.add(constant);
        }

        // Convert lists to arrays for linear equation solver
        RealMatrix coefficients = listToMatrix(equations);
        RealVector constantsVector = new ArrayRealVector(constants.stream()
                .mapToDouble(Double::doubleValue)
                .toArray());

        // Solve the system of linear equations
        DecompositionSolver solver = new SingularValueDecomposition(coefficients).getSolver();
        RealVector solution = solver.solve(constantsVector);

        double xResult = solution.getEntry(0);
        double yResult = solution.getEntry(1);

        return new Coordinates(xResult, yResult);
    }

    public static RealMatrix listToMatrix(List<RealVector> vectorList) {
        int numRows = vectorList.size();
        int numCols = vectorList.get(0).getDimension(); // Assuming all vectors have the same dimension

        RealMatrix matrix = new Array2DRowRealMatrix(numRows, numCols);

        for (int i = 0; i < numRows; i++) {
            RealVector vector = vectorList.get(i);
            matrix.setRow(i, vector.toArray());
        }

        return matrix;
    }
}
