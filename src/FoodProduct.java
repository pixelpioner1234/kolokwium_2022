import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class FoodProduct extends  Product{
    private Double[][] prices;

    public FoodProduct(String name, Double[][] prices) {
        super(name);
        this.prices = prices;
    }


    public static FoodProduct fromCsv(Path path){
        try {
            Scanner scanner = new Scanner(path);
            String name = scanner.nextLine();
            scanner.nextLine();

            Double[][] pricesByRegion = new Double[16][];

            int rowIndex = 0;
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(";");
                Double[] provincePrices = new Double[parts.length - 1];
                for (int i = 1; i < parts.length; i++) {
                    provincePrices[i - 1] = Double.parseDouble(parts[i].replace(",","."));
                    /*provincePrices = Arrays.stream(parts)
                            .map(value -> value.replace(",","."))
                            .map(value -> Double.valueOf(value))
                            .toArray(size -> new Double[size]);*/
                }
                pricesByRegion[rowIndex] = provincePrices;
                rowIndex++;
            }

            scanner.close();
            return new FoodProduct(name, pricesByRegion);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public double getPrice(int year, int month, String province){
        if (year < 2010 || year > 2022 || month < 1 || month > 12) {
            throw new IndexOutOfBoundsException("Year or month out of range");
        }

        int index = (year - 2010) * 12 + month - 1;
        switch (province.toUpperCase()) {
            case "DOLNOŚLĄSKIE":
            case "KUJAWSKO-POMORSKIE":
            case "LUBELSKIE":
            case "LUBUSKIE":
            case "ŁÓDZKIE":
            case "MAŁOPOLSKIE":
            case "MAZOWIECKIE":
            case "OPOLSKIE":
            case "PODKARPACKIE":
            case "PODLASKIE":
            case "POMORSKIE":
            case "ŚLĄSKIE":
            case "ŚWIĘTOKRZYSKIE":
            case "WARMIŃSKO-MAZURSKIE":
            case "WIELKOPOLSKIE":
            case "ZACHODNIOPOMORSKIE":
                int provinceIndex = Arrays.asList(
                        "DOLNOŚLĄSKIE", "KUJAWSKO-POMORSKIE", "LUBELSKIE", "LUBUSKIE",
                        "ŁÓDZKIE", "MAŁOPOLSKIE", "MAZOWIECKIE", "OPOLSKIE",
                        "PODKARPACKIE", "PODLASKIE", "POMORSKIE", "ŚLĄSKIE",
                        "ŚWIĘTOKRZYSKIE", "WARMIŃSKO-MAZURSKIE", "WIELKOPOLSKIE", "ZACHODNIOPOMORSKIE")
                        .indexOf(province.toUpperCase());
                if (provinceIndex == -1) {
                    throw new IndexOutOfBoundsException("Invalid province");
                }
                return prices[provinceIndex][index];
            default:
                throw new IndexOutOfBoundsException("Invalid province");
        }
    }



    public double getPrice(int year, int month) {
        if(year < 2010 || year > 2022 || month < 1 || month > 12){
            throw new IndexOutOfBoundsException("Year or month out of range");
        }

        int index = (year - 2010) * 12 + month - 1;

        double totalPrices = 0;
        for (int i = 0; i < prices.length; i++) {
            totalPrices += prices[i][index];
        }

        return totalPrices / prices.length;
    }









}
