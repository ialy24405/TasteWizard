package app.Database;

import app.Classes.Category;
import app.Classes.User;
import app.Classes.product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DB {
    private Connection connection;
    private PreparedStatement preparedStatement;
    public DB(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/TasteWizard", "root", "");
        } catch (SQLException e) {
            System.out.println("Error in server : "+e.getMessage());
        }
    }
    public int AddUser(User user){
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO users (username, email, password_hash, role, address, Gender,BirthDate,ProfilePhoto) VALUES (?, ?, ?, ?, ?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getGender());
            preparedStatement.setDate(7, java.sql.Date.valueOf(user.getBirthDate()));
            preparedStatement.setString(8, user.getProfilePicture());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            System.out.println("Error in server : "+e.getMessage());
            return -1;
        }
    }
    public boolean UpdateUser(User user){
        try {
            preparedStatement = connection.prepareStatement("UPDATE users SET username = ?, email = ?, password_hash = ?, role = ?, address = ? WHERE user_id = ?");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setInt(6, user.getUser_id());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error in server : "+e.getMessage());
            return false;
        }
    }
    public boolean DeleteUser(int user_id){
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM users WHERE user_id = ?");
            preparedStatement.setInt(1, user_id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error in server : "+e.getMessage());
            return false;
        }
    }
    public boolean login(User user){
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE user_id = ? AND password_hash = ?");
            preparedStatement.setInt(1, user.getUser_id());
            preparedStatement.setString(2, user.getPassword());
            return preparedStatement.executeQuery().next();
        } catch (SQLException e) {
            System.out.println("Error in server : "+e.getMessage());
            return false;
        }
    }

    public User getUser(User user) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?");
            preparedStatement.setInt(1, user.getUser_id());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password_hash"));
                user.setRole(resultSet.getString("role"));
                user.setAddress(resultSet.getString("address"));
                user.setGender(resultSet.getString("Gender"));
//                user.setBirthDate(resultSet.getDate("BirthDate").toLocalDate());
                user.setProfilePicture(resultSet.getString("ProfilePhoto"));
            }
            preparedStatement = connection.prepareStatement("SELECT * FROM userpreferences WHERE user_id = ?");
            preparedStatement.setInt(1, user.getUser_id());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.getPreferences().setCultural_dietary_restrictions(resultSet.getString("cultural_dietary_restrictions"));
                user.getPreferences().setOrganic_food_awareness(resultSet.getString("organic_food_awareness"));
                user.getPreferences().setFood_preference(resultSet.getString("food_preference"));
            }
            preparedStatement = connection.prepareStatement("SELECT * FROM usermedicalprofile WHERE user_id = ?");
            preparedStatement.setInt(1, user.getUser_id());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.getMedicalProfile().setMedical_conditions(resultSet.getString("medical_conditions"));
                user.getMedicalProfile().setAllergies(resultSet.getString("allergies"));
                user.getMedicalProfile().setAnemia(resultSet.getInt("anemia"));
                user.getMedicalProfile().setCancer(resultSet.getString("cancer"));
                user.getMedicalProfile().setCrohns_disease(resultSet.getInt("crohns_disease"));
                user.getMedicalProfile().setDiabetes(resultSet.getString("diabetes"));
                user.getMedicalProfile().setHeart_disease(resultSet.getString("heart_disease"));
                user.getMedicalProfile().setHepatitis(resultSet.getInt("hepatitis"));
                user.getMedicalProfile().setHigh_blood_fats(resultSet.getInt("high_blood_fats"));
                user.getMedicalProfile().setHigh_blood_pressure(resultSet.getInt("high_blood_pressure"));
                user.getMedicalProfile().setThyroid_disease(resultSet.getString("thyroid_disease"));
                user.getMedicalProfile().setTobacco_use(resultSet.getInt("tobacco_use"));
                user.getMedicalProfile().setSpecial_dietary_plan(resultSet.getString("special_dietary_plan"));
            }
            preparedStatement = connection.prepareStatement("SELECT * FROM userhealthgoals WHERE user_id = ?");
            preparedStatement.setInt(1, user.getUser_id());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.getHealthGoals().setWeight_goal(resultSet.getString("weight_goal"));
                user.getHealthGoals().setDigestive_symptoms(resultSet.getInt("digestive_symptoms"));
                user.getHealthGoals().setAdditional_information(resultSet.getString("additional_information"));
            }
            return user;
        } catch (SQLException e) {
            System.out.println("Error in server : " + e.getMessage());
            return null;
        }
    }

    public List<double[]> getAverageOfBeverages() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT AVG(color), AVG(clarity), AVG(consistency), AVG(appearance), " +
                            "AVG(aroma_intensity), AVG(aroma_sweetness), AVG(aroma_fruitiness), AVG(off_aromas), " +
                            "AVG(sweetness), AVG(sourness), AVG(bitterness), AVG(flavor_intensity), AVG(aftertaste), " +
                            "AVG(overall_flavor), AVG(body_thickness), AVG(carbonation), AVG(smoothness), AVG(acceptance) " +
                            "FROM beverages WHERE product_id IN (SELECT product_id FROM products WHERE category_id = 1) " +
                            "GROUP BY product_id");
            ResultSet resultSet = preparedStatement.executeQuery();

            List<double[]> matrix = new ArrayList<>();
            while (resultSet.next()) {
                double[] attributes = new double[18];
                for (int i = 0; i < 18; i++) {
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

    public List<double[]> getAverageOfUserBeverages(int userId) {
        try {
            List<double[]> globalAverages = getAverageOfBeverages();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT AVG(color), AVG(clarity), AVG(consistency), AVG(appearance), " +
                            "AVG(aroma_intensity), AVG(aroma_sweetness), AVG(aroma_fruitiness), AVG(off_aromas), " +
                            "AVG(sweetness), AVG(sourness), AVG(bitterness), AVG(flavor_intensity), AVG(aftertaste), " +
                            "AVG(overall_flavor), AVG(body_thickness), AVG(carbonation), AVG(smoothness), AVG(acceptance) " +
                            "FROM beverages WHERE product_id IN (SELECT product_id FROM products WHERE category_id = 1) " +
                            "AND user_id = ? GROUP BY product_id");
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<double[]> matrix = new ArrayList<>();
            while (resultSet.next()) {
                double[] attributes = new double[18];
                for (int i = 0; i < 18; i++) {
                    double userAvg = resultSet.getDouble(i + 1);
                    double globalAvg = globalAverages.get(matrix.size())[i];
                    attributes[i] = userAvg / globalAvg; // Normalization step
//                    System.out.println(attributes[i] + " = " + userAvg + " / " + globalAvg);
                }
                matrix.add(attributes);
//                System.out.println();
            }
            return matrix;
        } catch (SQLException e) {
            System.out.println("Error in server : " + e.getMessage());
            return Collections.emptyList();
        }
    }
    private product getProduct(int product_id){
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE product_id = ?");
            preparedStatement.setInt(1, product_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            product product = new product();
            if (resultSet.next()) {
                product.setProduct_id(resultSet.getInt("product_id"));
                product.setCategory_id(resultSet.getInt("category_id"));
                product.setProduct_name(resultSet.getString("product_name"));
//                product.setProduct_description(resultSet.getString("product_description"));
//                product.setProduct_price(resultSet.getDouble("product_price"));
//                product.setProduct_image(resultSet.getString("product_image"));
            }
            return product;
        } catch (SQLException e) {
            System.out.println("Error in server : " + e.getMessage());
            return null;
        }
    }
    private double[][] getAverageOfBeveragesOfUser(int userId) {
        try {
            preparedStatement = connection.prepareStatement("SELECT AVG(color),AVG(clarity),AVG(consistency)"+
                    ",AVG(appearance),AVG(aroma_intensity),AVG(aroma_sweetness),AVG(aroma_fruitiness),AVG(off_aromas)"+
                    ",AVG(sweetness),AVG(sourness),AVG(bitterness),AVG(flavor_intensity),AVG(aftertaste),AVG(overall_flavor)"+
                    ",AVG(body_thickness),AVG(carbonation),AVG(smoothness),AVG(acceptance) "+
                    "FROM beverages WHERE product_id in (SELECT product_id FROM products WHERE category_id = 1 "+
                    "AND product_id in (select product_id from beverages where user_id = ? ))  GROUP BY product_id");
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            double[][] matrix = new double[100][100];
            int column = 0;
            while (resultSet.next()) {
                for (int i = 0; i < 18; i++) {
                    matrix[column][i] = resultSet.getDouble(i + 1);
                    System.out.print(matrix[column][i]+" ");
                }
                column++;
                System.out.println();
            }
            return matrix;
        } catch (SQLException e) {
            System.out.println("Error in server : " + e.getMessage());
            return null;
        }
    }

    public boolean updateUser(User user) {
        try{
            preparedStatement = connection.prepareStatement("INSERT INTO userpreferences (user_id, cultural_dietary_restrictions, organic_food_awareness, food_preference) VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, user.getUser_id());
            preparedStatement.setString(2, user.getPreferences().getCultural_dietary_restrictions());
            preparedStatement.setString(3, user.getPreferences().getOrganic_food_awareness());
            preparedStatement.setString(4, user.getPreferences().getFood_preference());
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("INSERT INTO usermedicalprofile (user_id, medical_conditions, allergies, anemia, cancer, crohns_disease, diabetes, heart_disease, hepatitis, high_blood_fats, high_blood_pressure, thyroid_disease, tobacco_use, special_dietary_plan) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, user.getUser_id());
            preparedStatement.setString(2, user.getMedicalProfile().getMedical_conditions());
            preparedStatement.setString(3, user.getMedicalProfile().getAllergies());
            preparedStatement.setInt(4, user.getMedicalProfile().getAnemia());
            preparedStatement.setString(5, user.getMedicalProfile().getCancer());
            preparedStatement.setInt(6, user.getMedicalProfile().getCrohns_disease());
            preparedStatement.setString(7, user.getMedicalProfile().getDiabetes());
            preparedStatement.setString(8, user.getMedicalProfile().getHeart_disease());
            preparedStatement.setInt(9, user.getMedicalProfile().getHepatitis());
            preparedStatement.setInt(10, user.getMedicalProfile().getHigh_blood_fats());
            preparedStatement.setInt(11, user.getMedicalProfile().getHigh_blood_pressure());
            preparedStatement.setString(12, user.getMedicalProfile().getThyroid_disease());
            preparedStatement.setInt(13, user.getMedicalProfile().getTobacco_use());
            preparedStatement.setString(14, user.getMedicalProfile().getSpecial_dietary_plan());
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("INSERT INTO userhealthgoals (user_id, weight_goal, digestive_symptoms, additional_information) VALUES (?,?,?,?)");
            preparedStatement.setInt(1, user.getUser_id());
            preparedStatement.setString(2, user.getHealthGoals().getWeight_goal());
            preparedStatement.setInt(3, user.getHealthGoals().getDigestive_symptoms());
            preparedStatement.setString(4, user.getHealthGoals().getAdditional_information());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error in server : " + e.getMessage());
            return false;
        }
    }

    public Category getCategory(String category) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM categories WHERE name = ?");
            preparedStatement.setString(1, category);
            ResultSet resultSet = preparedStatement.executeQuery();
            Category category1 = new Category();
            if (resultSet.next()) {
                category1.setCategory_id(resultSet.getInt("category_id"));
                category1.setCategory_name(resultSet.getString("name"));
            }
            return category1;
        } catch (SQLException e) {
            System.out.println("Error in server : " + e.getMessage());
            return null;
        }
    }
}
