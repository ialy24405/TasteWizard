package app.Classes;

import app.Database.DB;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.abs;

public class Similarty {
    private Connection connection;

    public Similarty(Connection connection) {
        this.connection = connection;
    }

    public List<double[]> getAverageOfBeverages() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT product_id, AVG(color), AVG(clarity), AVG(consistency), AVG(appearance), " +
                            "AVG(aroma_intensity), AVG(aroma_sweetness), AVG(aroma_fruitiness), AVG(off_aromas), " +
                            "AVG(sweetness), AVG(sourness), AVG(bitterness), AVG(flavor_intensity), AVG(aftertaste), " +
                            "AVG(overall_flavor), AVG(body_thickness), AVG(carbonation), AVG(smoothness), AVG(acceptance) " +
                            "FROM beverages WHERE product_id IN (SELECT product_id FROM products WHERE category_id = 1) " +
                            "GROUP BY product_id");
            ResultSet resultSet = preparedStatement.executeQuery();

            List<double[]> matrix = new ArrayList<>();
            while (resultSet.next()) {
                double[] attributes = new double[19];
                attributes[0] = resultSet.getInt(1); // Store product_id as the first element
                for (int i = 1; i <= 18; i++) {
                    attributes[i] = resultSet.getDouble(i + 1);
                }
                matrix.add(attributes);
            }
            return matrix;
        } catch (SQLException e) {
            System.out.println("Error in server : " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<Double> getAverageOfUserBeverages(int userId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT AVG(color), AVG(clarity), AVG(consistency), AVG(appearance), " +
                            "AVG(aroma_intensity), AVG(aroma_sweetness), AVG(aroma_fruitiness), AVG(off_aromas), " +
                            "AVG(sweetness), AVG(sourness), AVG(bitterness), AVG(flavor_intensity), AVG(aftertaste), " +
                            "AVG(overall_flavor), AVG(body_thickness), AVG(carbonation), AVG(smoothness), AVG(acceptance) " +
                            "FROM beverages WHERE product_id IN (SELECT product_id FROM products WHERE category_id = 1) " +
                            "AND user_id = ?");
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Double> averages = new ArrayList<>();
            if (resultSet.next()) {
                for (int i = 1; i <= 18; i++) {
                    averages.add(resultSet.getDouble(i));
                }
            }
            return averages;
        } catch (SQLException e) {
            System.out.println("Error in server : " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private double cosineSimilarity(List<Double> vectorA, double[] vectorB) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 1; i <= vectorA.size() / 4; i++) {
            if (abs(vectorA.get(i)-vectorB[i - 1])>2.34)
            {
                normA += Math.pow(vectorA.get(i), 2);
                normB += Math.pow(vectorB[i - 1], 2);
                continue;
            }
            dotProduct += vectorA.get(i) * vectorB[i - 1];
            normA += Math.pow(vectorA.get(i), 2);
            normB += Math.pow(vectorB[i - 1], 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    public List<Recommendation> recommendBeverages(int userId, int topN) {
        List<double[]> globalAverages = getAverageOfBeverages();
        List<Double> userAverages = getAverageOfUserBeverages(userId);

        if (userAverages.isEmpty() || globalAverages.isEmpty()) {
            return Collections.emptyList();
        }

        List<Recommendation> recommendations = new ArrayList<>();
        for (int i = 0; i < globalAverages.size(); i++) {
            double similarity = cosineSimilarity(userAverages, globalAverages.get(i));
            int productId = (int) globalAverages.get(i)[0];
            recommendations.add(new Recommendation(productId, similarity));
        }
        recommendations.sort((r1, r2) -> Double.compare(r2.getSimilarityScore(), r1.getSimilarityScore()));
        return recommendations.subList(0, Math.min(topN, recommendations.size()));
    }

    public String getProductName(int productId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT name FROM products WHERE product_id = ?");
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("name");
            } else {
                return "Unknown Product";
            }
        } catch (SQLException e) {
            System.out.println("Error in server : " + e.getMessage());
            return "Unknown Product";
        }
    }

}
