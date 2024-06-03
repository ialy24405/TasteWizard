package app.Classes;
public class Recommendation {
    private int productId;
    private double similarityScore;
    private String productName;
    public Recommendation(int productId, double similarityScore) {
        this.productId = productId;
        this.similarityScore = similarityScore;
    }

    public int getProductId() {
        return productId;
    }

    public double getSimilarityScore() {
        return similarityScore;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setSimilarityScore(double similarityScore) {
        this.similarityScore = similarityScore;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
