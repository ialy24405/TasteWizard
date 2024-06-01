package app.Classes;

public class UserHealthGoals {
    private int health_goal_id;
    private int user_id;
    private String weight_goal;
    private int digestive_symptoms;
    private String digestive_symptoms_details;
    private String additional_information;

    public UserHealthGoals(int userId, String weightGoal, boolean digestiveSymptoms, String additionalInformation) {
        this.user_id = userId;
        this.weight_goal = weightGoal;
        this.digestive_symptoms = digestiveSymptoms ? 1 : 0;
        this.additional_information = additionalInformation;
    }

    public UserHealthGoals() {

    }

    public int getHealth_goal_id() {
        return health_goal_id;
    }

    public void setHealth_goal_id(int health_goal_id) {
        this.health_goal_id = health_goal_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getWeight_goal() {
        return weight_goal;
    }

    public void setWeight_goal(String weight_goal) {
        this.weight_goal = weight_goal;
    }

    public int getDigestive_symptoms() {
        return digestive_symptoms;
    }

    public void setDigestive_symptoms(int digestive_symptoms) {
        this.digestive_symptoms = digestive_symptoms;
    }

    public String getDigestive_symptoms_details() {
        return digestive_symptoms_details;
    }

    public void setDigestive_symptoms_details(String digestive_symptoms_details) {
        this.digestive_symptoms_details = digestive_symptoms_details;
    }

    public String getAdditional_information() {
        return additional_information;
    }

    public void setAdditional_information(String additional_information) {
        this.additional_information = additional_information;
    }
}
