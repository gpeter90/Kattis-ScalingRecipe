import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static int numberofCases;
    private static ArrayList<String> answerList = new ArrayList<>();

    public static void main(String[] args) {

        numberofCases = scanner.nextInt();
        for (int i = 0; i < numberofCases; i++) {
            answerList.add("Recipe # " + (i + 1));
            int numberOfIngredients = scanner.nextInt();
            int numberOfPortions = scanner.nextInt();
            int numberOfDesiredPortions = scanner.nextInt();
            double scaledWeight = 0.0;

            double scalingFactor = (double) numberOfDesiredPortions / (double) numberOfPortions;

            ArrayList<Ingredient> listOfIngredients = new ArrayList<>();

            for (int j = 0; j < numberOfIngredients; j++) {

                listOfIngredients.add(new Ingredient(scanner.next(), Double.valueOf(scanner.next()), Double.valueOf(scanner.next())));

                if (listOfIngredients.get(j).getPercent() == 100) {
                    scaledWeight = listOfIngredients.get(j).getWeight() * scalingFactor;
                }
            }

            for (int k = 0; k < listOfIngredients.size(); k++) {

                double newWeight = round(scaledWeight * (listOfIngredients.get(k).getPercent() / 100), 1);
                answerList.add("" + listOfIngredients.get(k).getName() + " " + newWeight);
            }
            answerList.add("----------------------------------------");
        }

        for (int i = 0; i < answerList.size(); i++) {
            System.out.println(answerList.get(i));
        }

    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();

    }

}

class Ingredient {

    String name;
    double weight;
    double percent;

    public Ingredient(String name, double weight, double percent) {
        this.name = name;
        this.weight = weight;
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getPercent() {
        return percent;
    }
}

