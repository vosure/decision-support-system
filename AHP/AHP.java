package AHP;

import Math.Matrix;

import java.util.ArrayList;

public class AHP {
    public ArrayList<Criterion> criteriaList;
    public ArrayList<Alternative> alternativesList;

    public ArrayList<Matrix> alternativeComparisonMatrices;
    public Matrix criterionComparisonMatrix;

    public ArrayList<double[]> weightsList;

    public AHP(Matrix criterionComparisonMatrix, ArrayList<Matrix> alternativeComparisonMatrices) {
        criteriaList = new ArrayList<>();
        alternativesList = new ArrayList<>();
        weightsList = new ArrayList<>();
        this.criterionComparisonMatrix = criterionComparisonMatrix;
        this.alternativeComparisonMatrices = alternativeComparisonMatrices;
    }

    public void addCriteria(Criterion criterion) {
        criteriaList.add(criterion);
    }

    public void addAlternative(Alternative alternative) {
        alternativesList.add(alternative);
    }

    public void createMatrices() {
        for (int i = 0; i < criteriaList.size(); i++) {
            alternativeComparisonMatrices.add(new Matrix(alternativesList.size(), alternativesList.size()));
        }
        criterionComparisonMatrix = new Matrix(criteriaList.size(), criteriaList.size());
    }

    public void compareCriteria() {
        //Перебор всех элементов матрицы и присваивание нужных значений
    }

    public void compareAlternativesByCriteria() {
        //Перебор всех элементов каждой матрицы(1 матрица для каждого из критериев) и присваивания нужных значений
    }

    public void calculateWeights() {
        for (int i = 0; i < alternativeComparisonMatrices.size(); i++) {
            double[] vector = alternativeComparisonMatrices.get(i).getEigenvector();

            weightsList.add(vector);
        }
    }

    public double[] getResult() {
        double[] results = new double[alternativesList.size()];

        double[] criteriaWeights = criterionComparisonMatrix.getEigenvector();

        for (int i=0;i<criteriaWeights.length;i++){
            System.out.println(criteriaWeights[i]);
        }
        System.out.println("\n\n\n");
        for (int alternativeIndex = 0; alternativeIndex < alternativeComparisonMatrices.size(); alternativeIndex++) {
            for (int criterionIndex = 0; criterionIndex < weightsList.size(); criterionIndex++) {
                System.out.println( weightsList.get(criterionIndex)[alternativeIndex] + " * " + criteriaWeights[criterionIndex]);
                results[alternativeIndex] += weightsList.get(criterionIndex)[alternativeIndex] * criteriaWeights[criterionIndex];
                System.out.println(results[alternativeIndex] + "\n");
            }
        }
        return results;
    }

}
