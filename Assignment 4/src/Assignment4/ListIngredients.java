import java.util.ArrayList;
import java.util.stream.Collectors;

public class ListIngredients {

  ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
  ArrayList<Double> portionCost = new ArrayList<Double>(); // Array to store portion cost.
  ArrayList<Double> onePortion = new ArrayList<Double>(); // Array to store one portion.
  ArrayList<Double> portionsForRecipe = new ArrayList<Double>(); // Array to store wanted portions for a recipe.

  // Constructor
  public ListIngredients() {
  }

  // Add ingredient and check so name is unique.
  public int addIngredient(Ingredient ingredient) {
    if (checkIfNameExist(ingredient.name)) {
      return -1;
    }
    ingredients.add(ingredient);
    return 0;
  }

  public boolean checkIfNameExist(String name) {
    for (int i = 0; i < this.ingredients.size(); i++) {
      System.out.println(name);
      if (ingredients.get(i).name.equals(name)) {
        return true;
      }
    }
    return false;
  }

  // Iterate and return all names.
  public ArrayList<String> getAllNames() {
    return ingredients.stream().map(s -> s.name).collect(Collectors.toCollection(ArrayList<String>::new));
  }

  // Iterate and return selected ingredient.
  public void getIngredient(String name) {
    for (int i = 0; i < this.ingredients.size(); i++) {
      if (ingredients.get(i).name.equals(name)) {
        System.out.println(ingredients.get(i).name + " " + ingredients.get(i).unit + " " + ingredients.get(i).value
            + " " + ingredients.get(i).price);
        // make a toString()
      }
    }
  }

  // Iterate and return value for ingredients in recipe.
  public ArrayList<Double> getValue(int number) {
    for (int i = 0; i < number; i++) {
      System.out.println(ingredients.get(i).value);
      portionCost.add(ingredients.get(i).value);
    }
    return portionCost;
  }

  // Find out how many ingredients a recipe has.
  public int numberOfIngredients() {
    int result = 0;
    for (int i = 0; i < this.ingredients.size(); i++) {
      result++;
    }
    return result;
  }

  // Iterate and return value of each ingredient.
  public void updatePortions(int number, ArrayList<Double> ingredientsValue, double wantedPortions,
      double actualPortions) {
    for (int i = 0; i < number; i++) {
      // Find out how much is needed for 1 portion
      onePortion.add(ingredientsValue.get(i) / actualPortions);
    }
    for (int j = 0; j < number; j++) {
      // Find out how much is needed for wanted portions
      portionsForRecipe.add(onePortion.get(j) * wantedPortions);
    }

    System.out.println(onePortion);
    System.out.println(portionsForRecipe);

    /*
     * portionCost.stream().map(d -> d /
     * actualPortions).collect(Collectors.toList());
     * System.out.println(portionCost);
     */
  }

  public String toString() {
    StringBuilder str = new StringBuilder();
    if (!this.ingredients.isEmpty()) { // If there is no ingredients return empty string.
      str.append(this.ingredients.get(0));
    }
    for (int i = 0; i < this.ingredients.size(); i++) {
      str.append(" ");
      str.append(ingredients.get(i).name);
      str.append(" ");
      str.append(ingredients.get(i).value);
      str.append(" ");
      str.append(ingredients.get(i).unit);
      str.append(" ");
      str.append("Price:" + ingredients.get(i).price);
    }
    return str.toString();
  }

  public void removeIngredient(String name) {
    for (int i = 0; i < this.ingredients.size(); i++) {
      if (ingredients.get(i).name.equals(name)) {
        ingredients.remove(ingredients.get(i));
        return; // return true and false
      }
    }
    return;
  }

  // Calculate total cost of recipe.
  public int getTotalCost() {
    int sum = 0;
    for (int i = 0; i < ingredients.size(); i++) {
      sum += ingredients.get(i).price;
    }
    return sum;
  }

  // Update ingredients after portions.
  public void updateIngredients(double wantedPortions) {
    double result;
    for (int i = 0; i < ingredients.size(); i++) {
      // find out how much is needed for 1 portion
      double onePortion = ingredients.get(i).value / ingredients.get(i).value;
      // result = this.ingredients.get(i).value / wantedPortions;
      System.out.println(this.ingredients.get(i).value);
    }
  }
}
