package in.kaligotla.recyclerview_2;

public class Product {
    private int prodId;
    private String prodName;
    private Double prodPrice;

    public Product() {
        this.prodId = 101;
        this.prodName = "Mixer";
        this.prodPrice = 1001.00;
    }

    public Product(int prodId, String prodName, Double prodPrice) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.prodPrice = prodPrice;
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Double getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(Double prodPrice) {
        this.prodPrice = prodPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "prodId=" + prodId +
                ", prodName='" + prodName + '\'' +
                ", prodPrice=" + prodPrice +
                '}';
    }
}
