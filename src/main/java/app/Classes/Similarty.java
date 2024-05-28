package app.Classes;

import app.Database.DB;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Similarty {
    private double[][] matrix;
    private double[][] userMatrix;
    private double[][] userSimilarityMatrix;
    private double[] userAverage;
    private static double[][] similarityMatrix;
    private ObservableList<Beverage> recommendationsList;
    private int NUM_PRODUCTS;
    private int NUM_ATTRIBUTES;
    private void generateMatrixOfUser(int user_id){
        DB db = new DB();
//        matrix = db.getAverageOfBeverages();
//        userMatrix = db.getAverageOfUserBeverages(user_id);
    }
    private double cosineSimilarity(double[] vectorA, double[] vectorB) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += Math.pow(vectorA[i], 2);
            normB += Math.pow(vectorB[i], 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
    public List<Recommendation> recommendBeverages(int userId, int topN) {
        DB db = new DB();

        List<double[]> globalAverages = db.getAverageOfBeverages();
        List<double[]> userAverages = db.getAverageOfUserBeverages(userId);

        if (userAverages.isEmpty() || globalAverages.isEmpty()) {
            return Collections.emptyList();
        }

        double[] userPreferences = userAverages.get(0); // Assuming a single user vector

        List<Recommendation> recommendations = new ArrayList<>();
        for (int i = 0; i < globalAverages.size(); i++) {
            double similarity = cosineSimilarity(userPreferences, globalAverages.get(i));
            recommendations.add(new Recommendation(i, similarity));
        }

        recommendations.sort((r1, r2) -> Double.compare(r2.getSimilarityScore(), r1.getSimilarityScore()));
        return recommendations.subList(0, Math.min(topN, recommendations.size()));
    }
}
