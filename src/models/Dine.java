package models;

public class Dine {
    private String imageTag;
    private String description;
    private String currentPrice;
    private String originalPrice;

    public Dine(String imageTag, String description, String currentPrice, String originalPrice) {
        this.imageTag = imageTag;
        this.description = description;
        this.currentPrice = currentPrice;
        this.originalPrice = originalPrice;
    }

    public String getImageTag() {
        return imageTag;
    }

    public void setImageTag(String imageTag) {
        this.imageTag = imageTag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }
}
