package sample;

import AHP.AHP;
import AHP.Criterion;
import AHP.Alternative;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import Math.Matrix;

import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        //Матрица сравнения критериев по значимости
        double[][] criteria = {{1.0f, 2.0f, 3.0f}, {0.5f, 1.0f, 0.5f}, {0.33f, 2f, 1f}};
        Matrix criteriaMatrix = new Matrix(criteria);

        ArrayList<Matrix> alternativesByCriteria = new ArrayList<>(3);

        double[][] alternativesByFirstCriterion = {{1.0f, 4.0f, 3.0f}, {0.25f, 1.0f, 2.0f}, {0.33f, 0.5f, 1f}};
        Matrix matrix1 = new Matrix(alternativesByFirstCriterion);
        alternativesByCriteria.add(matrix1);

        double[][] alternativesBySecondCriterion = {{1.0f, 0.25f, 6.0f}, {4.0f, 1.0f, 0.5f}, {0.17f, 2.0f, 1f}};
        Matrix matrix2 = new Matrix(alternativesBySecondCriterion);
        alternativesByCriteria.add(matrix2);

        double[][] alternativesByThirdCriterion = {{1.0f, 3f, 0.2f}, {0.33f, 1.0f, 0.33f}, {5.0f, 3.0f, 1.0f}};
        Matrix matrix3 = new Matrix(alternativesByThirdCriterion);
        alternativesByCriteria.add(matrix3);

        AHP ahp = new AHP(criteriaMatrix, alternativesByCriteria);

        ahp.addCriteria(new Criterion("Критерий 1"));
        ahp.addCriteria(new Criterion("Критерий 2"));
        ahp.addCriteria(new Criterion("Критерий 3"));

        ahp.addAlternative(new Alternative("Альтернатива 1"));
        ahp.addAlternative(new Alternative("Альтернатива 2"));
        ahp.addAlternative(new Alternative("Альтернатива 3"));

        ahp.calculateWeights();
        double[] results = ahp.getResult();
        for (int i = 0; i < results.length; i++) {
            System.out.println(ahp.alternativesList.get(i).name + " ---> " + results[i]);
        }

    }

    public static void main(String[] args) {
        launch(args);

    }
}
