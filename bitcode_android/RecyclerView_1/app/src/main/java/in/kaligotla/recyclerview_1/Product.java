package in.kaligotla.recyclerview_1;

import java.io.Serializable;

public class Product implements Serializable {
    private String productName;
    private int productId;
    private double productPrice;

    public Product() {
        this.productName = "Mixer";
        this.productId = 101;
        this.productPrice = 3454.00;
    }

    public Product(String productName, int productId, double productPrice) {
        this.productName = productName;
        this.productId = productId;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productId=" + productId +
                ", productPrice=" + productPrice +
                '}';
    }
}
