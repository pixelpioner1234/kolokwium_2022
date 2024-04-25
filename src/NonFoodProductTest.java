import org.junit.Test;
import java.nio.file.Path;
import static org.junit.Assert.assertEquals;

public class NonFoodProductTest {

    @Test
    public void getPrice() {
        Path path = Path.of("C:\\kolokwium1_2022\\data\\nonfood\\kolokwium1_2022\\data\\nonfood\\data\\nonfood\\bilet_do_kina.csv");
        NonFoodProduct product = NonFoodProduct.fromCsv(path);
        double price = product.getPrice(2010, 5);
        assertEquals(15.04, price, 0.01);
    }
}
