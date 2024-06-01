package app.Classes;

public class UserPreferences {
    private int preference_id;
    private int user_id;
    private String cultural_dietary_restrictions;
    private String organic_food_awareness;
    private String food_preference;

    public UserPreferences(int userId, String culturalDietaryRestrictions, String organicFoodAwareness, String foodPreference) {
        this.cultural_dietary_restrictions = culturalDietaryRestrictions;
        this.organic_food_awareness = organicFoodAwareness;
        this.food_preference = foodPreference;
        this.user_id = userId;
    }

    public UserPreferences() {

    }

    public int getPreference_id() {
        return preference_id;
    }

    public void setPreference_id(int preference_id) {
        this.preference_id = preference_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCultural_dietary_restrictions() {
        return cultural_dietary_restrictions;
    }

    public void setCultural_dietary_restrictions(String cultural_dietary_restrictions) {
        this.cultural_dietary_restrictions = cultural_dietary_restrictions;
    }

    public String getOrganic_food_awareness() {
        return organic_food_awareness;
    }

    public void setOrganic_food_awareness(String organic_food_awareness) {
        this.organic_food_awareness = organic_food_awareness;
    }

    public String getFood_preference() {
        return food_preference;
    }

    public void setFood_preference(String food_preference) {
        this.food_preference = food_preference;
    }
}
