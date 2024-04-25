import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.nio.file.Paths;

class FoodProductTest {

    @Test
    void testGetPrice(){      // province
        Path path2 = Path.of("C:\\kolokwium1_2022\\data\\nonfood\\kolokwium1_2022\\data\\nonfood\\data\\food\\cebula.csv");
        FoodProduct productFood = FoodProduct.fromCsv(path2);
        double price2 = productFood.getPrice(2010, 2,"DOLNOŚLĄSKIE");
        assertEquals(1.94, price2,1.0);
    }
}