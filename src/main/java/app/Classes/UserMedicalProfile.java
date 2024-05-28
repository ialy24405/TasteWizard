package app.Classes;

public class UserMedicalProfile {
    private int medical_profile_id;
    private int user_id;
    private String medical_conditions;
    private String allergies;
    private int anemia;
    private String cancer;
    private int crohns_disease;
    private String diabetes;
    private String heart_disease;
    private int hepatitis;
    private int high_blood_fats;
    private int high_blood_pressure;
    private String thyroid_disease;
    private String other_conditions;
    private int tobacco_use;
    private int willing_to_stop_smoking;
    private String special_dietary_plan;

    public int getMedical_profile_id() {
        return medical_profile_id;
    }

    public void setMedical_profile_id(int medical_profile_id) {
        this.medical_profile_id = medical_profile_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getMedical_conditions() {
        return medical_conditions;
    }

    public void setMedical_conditions(String medical_conditions) {
        this.medical_conditions = medical_conditions;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public int getAnemia() {
        return anemia;
    }

    public void setAnemia(int anemia) {
        this.anemia = anemia;
    }

    public String getCancer() {
        return cancer;
    }

    public void setCancer(String cancer) {
        this.cancer = cancer;
    }

    public int getCrohns_disease() {
        return crohns_disease;
    }

    public void setCrohns_disease(int crohns_disease) {
        this.crohns_disease = crohns_disease;
    }

    public String getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(String diabetes) {
        this.diabetes = diabetes;
    }

    public String getHeart_disease() {
        return heart_disease;
    }

    public void setHeart_disease(String heart_disease) {
        this.heart_disease = heart_disease;
    }

    public int getHepatitis() {
        return hepatitis;
    }

    public void setHepatitis(int hepatitis) {
        this.hepatitis = hepatitis;
    }

    public int getHigh_blood_fats() {
        return high_blood_fats;
    }

    public void setHigh_blood_fats(int high_blood_fats) {
        this.high_blood_fats = high_blood_fats;
    }

    public int getHigh_blood_pressure() {
        return high_blood_pressure;
    }

    public void setHigh_blood_pressure(int high_blood_pressure) {
        this.high_blood_pressure = high_blood_pressure;
    }

    public String getThyroid_disease() {
        return thyroid_disease;
    }

    public void setThyroid_disease(String thyroid_disease) {
        this.thyroid_disease = thyroid_disease;
    }

    public String getOther_conditions() {
        return other_conditions;
    }

    public void setOther_conditions(String other_conditions) {
        this.other_conditions = other_conditions;
    }

    public int getTobacco_use() {
        return tobacco_use;
    }

    public void setTobacco_use(int tobacco_use) {
        this.tobacco_use = tobacco_use;
    }

    public int getWilling_to_stop_smoking() {
        return willing_to_stop_smoking;
    }

    public void setWilling_to_stop_smoking(int willing_to_stop_smoking) {
        this.willing_to_stop_smoking = willing_to_stop_smoking;
    }

    public String getSpecial_dietary_plan() {
        return special_dietary_plan;
    }

    public void setSpecial_dietary_plan(String special_dietary_plan) {
        this.special_dietary_plan = special_dietary_plan;
    }
}
