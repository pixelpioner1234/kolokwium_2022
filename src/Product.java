import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class Product {
    private String name;
    private  static List<Product> products = new ArrayList<>();

    public Product(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }


    public abstract double getPrice(int year, int month);


    public static void clearProducts(){
        products.clear();
    }


    public static void addProducts(Function<Path, ? extends Product> fromCsvFunction , Path path){
        Product product = fromCsvFunction.apply(path);
        products.add(product);
        //System.out.println("Success");
    }


    public static void getProducts(String prefix) throws AmbigiousProductException,IndexOutOfBoundsException{
        List<Product> list = new ArrayList<>();

        for(Product product : products){
            if(product.getName().startsWith(prefix)){
                list.add(product);
            }
        }

        if(list.isEmpty()){
            throw new IndexOutOfBoundsException("No products with this prefix.");
        }

        if(list.size() == 1){
            Product product = list.get(0);
            System.out.println(product.getName());
        }

        if(list.size() > 1){
            List<String> names = new ArrayList<>();
            for(Product product : list){
                names.add(product.getName());
            }
            throw new AmbigiousProductException("Many products found with this prefix.", names);
        }

    }






}
