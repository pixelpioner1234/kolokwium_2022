import java.util.ArrayList;
import java.util.List;

public class Cart {

    public List<Product> cartOfProducts;

    public Cart() {
        this.cartOfProducts = new ArrayList<>();
    }
    public List<Product> getProducts() {
        return new ArrayList<>(cartOfProducts);
    }



    public void addProduct(Product product, int amount) {
        for(int i = 0; i< amount; i++){
            cartOfProducts.add(product);
        }
    }



    public double getPrice(int year, int month){
        double totalPrice = 0;
        for(int i = 0;i< cartOfProducts.size(); i++){
            totalPrice += cartOfProducts.get(i).getPrice(year,month);
        }
        return totalPrice;
    }



    public double getInflation(int year1, int month1, int year2, int month2){
        double price1 = getPrice(year1,month1);
        double price2 = getPrice(year2,month2);

        if (year1 > year2 || (year1 == year2 && month1 >= month2)) {
            throw new IllegalArgumentException("Invalid dates.");
        }

        int months = (year2 - year1) * 12 + (month2 - month1);
        double inflation = (price2 - price1) / price1 * 100 / months * 12;

        return inflation;
    }



}
