import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public  static void main(String[] args){

        Path pathNonFood = Path.of("C:\\kolokwium1_2022\\data\\nonfood\\kolokwium1_2022\\data\\nonfood\\data\\nonfood\\bilet_do_kina.csv");
        NonFoodProduct productNonFood = NonFoodProduct.fromCsv(pathNonFood);
        double price = productNonFood.getPrice(2010, 5);
        System.out.println("Price for May 2010: " + price);


        Product.addProducts(NonFoodProduct::fromCsv,pathNonFood);




        Path pathFood = Path.of("C:\\kolokwium1_2022\\data\\nonfood\\kolokwium1_2022\\data\\nonfood\\data\\food\\cebula.csv");
        FoodProduct productFood = FoodProduct.fromCsv(pathFood);

        double price2 = productFood.getPrice(2010, 1,"DOLNOŚLĄSKIE");
        System.out.println("Price for Jan 2010: " + price2);

        double AVGprice2 = productFood.getPrice(2010, 1);
        System.out.println("AVG price for Jan 2010: " + AVGprice2);


        Product.addProducts(FoodProduct::fromCsv,pathFood);



        try {
            Product.getProducts("Ce");
        } catch (IndexOutOfBoundsException | AmbigiousProductException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n");



        Cart cart = new Cart();
        cart.addProduct(productNonFood,2);
        cart.addProduct(productFood,2);

        System.out.println("Cart contents:");
        for (int i = 0; i < cart.getProducts().size(); i++) {
            Product product = cart.getProducts().get(i);
            System.out.println(product.getName());
        }
        System.out.println("\n");



        System.out.println("Total Price: " + cart.getPrice(2010,1));

        double inflationRate = cart.getInflation(2014, 4, 2014, 5);
        System.out.println("Inflation Rate: " + inflationRate + "%");
    }
}
