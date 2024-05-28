package app.Classes;

public class SaltySnacks extends Snacks {
    private int bitterness;
    private String bitterness_comment;
    private int greasiness;
    private String greasiness_comment;
    private int saltiness;
    private String saltiness_comment;
    private int BalanceOfFlavors;
    private String BalanceOfFlavors_comment;
    private int tasteBitterness;
    private String tasteBitterness_comment;
    private int tanginess;
    private String tanginess_comment;
    private int OffFlavors;
    private String OffFlavors_comment;

    public int getBitterness() {
        return bitterness;
    }

    public void setBitterness(int bitterness) {
        this.bitterness = bitterness;
    }

    public String getBitterness_comment() {
        return bitterness_comment;
    }

    public void setBitterness_comment(String bitterness_comment) {
        this.bitterness_comment = bitterness_comment;
    }

    public int getGreasiness() {
        return greasiness;
    }

    public void setGreasiness(int greasiness) {
        this.greasiness = greasiness;
    }

    public String getGreasiness_comment() {
        return greasiness_comment;
    }

    public void setGreasiness_comment(String greasiness_comment) {
        this.greasiness_comment = greasiness_comment;
    }

    public int getSaltiness() {
        return saltiness;
    }

    public void setSaltiness(int saltiness) {
        this.saltiness = saltiness;
    }

    public String getSaltiness_comment() {
        return saltiness_comment;
    }

    public void setSaltiness_comment(String saltiness_comment) {
        this.saltiness_comment = saltiness_comment;
    }

    public int getBalanceOfFlavors() {
        return BalanceOfFlavors;
    }

    public void setBalanceOfFlavors(int balanceOfFlavors) {
        BalanceOfFlavors = balanceOfFlavors;
    }

    public String getBalanceOfFlavors_comment() {
        return BalanceOfFlavors_comment;
    }

    public void setBalanceOfFlavors_comment(String balanceOfFlavors_comment) {
        BalanceOfFlavors_comment = balanceOfFlavors_comment;
    }

    @Override
    public int getTasteBitterness() {
        return tasteBitterness;
    }

    @Override
    public void setTasteBitterness(int tasteBitterness) {
        this.tasteBitterness = tasteBitterness;
    }

    @Override
    public String getTasteBitterness_comment() {
        return tasteBitterness_comment;
    }

    @Override
    public void setTasteBitterness_comment(String tasteBitterness_comment) {
        this.tasteBitterness_comment = tasteBitterness_comment;
    }

    public int getTanginess() {
        return tanginess;
    }

    public void setTanginess(int tanginess) {
        this.tanginess = tanginess;
    }

    public String getTanginess_comment() {
        return tanginess_comment;
    }

    public void setTanginess_comment(String tanginess_comment) {
        this.tanginess_comment = tanginess_comment;
    }

    public int getOffFlavors() {
        return OffFlavors;
    }

    public void setOffFlavors(int offFlavors) {
        OffFlavors = offFlavors;
    }

    public String getOffFlavors_comment() {
        return OffFlavors_comment;
    }

    public void setOffFlavors_comment(String offFlavors_comment) {
        OffFlavors_comment = offFlavors_comment;
    }
}
