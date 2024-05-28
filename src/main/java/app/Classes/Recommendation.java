package app.Classes;
public class Recommendation {
    private final int productId;
    private final double similarityScore;

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
}
