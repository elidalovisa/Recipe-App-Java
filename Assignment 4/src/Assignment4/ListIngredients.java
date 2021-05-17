import java.util.ArrayList;
import java.util.stream.Collectors;

public class ListIngredients {

  ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
  ArrayList<Double> portionCost = new ArrayList<Double>(); // Array to store portion cost.
  ArrayList<Double> portionValue = new ArrayList<Double>(); // Array to store value of each ingredient.
  ArrayList<Double> onePortion = new ArrayList<Double>(); // Array to store one portion.
  ArrayList<Double> wholePortions = new ArrayList<Double>();

  ArrayList<String> portionsForRecipe = new ArrayList<String>(); // Array to store wanted portions for a recipe.
  ArrayList<Double> onePortionPrice = new ArrayList<Double>(); // Array to store price per ingredient for 1 portion.
  ArrayList<Double> wantedPortionsPrice = new ArrayList<Double>(); // Array to store price per ingredient for wanted
                                                                   // portions.
  ArrayList<String> recipeToReturn = new ArrayList<String>();

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
      portionValue.add(ingredients.get(i).value);
    }
    return portionValue;
  }

  public ArrayList<Double> getPrice(int number) {
    for (int i = 0; i < number; i++) {
      portionCost.add(ingredients.get(i).price);
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

  // Calculate amount and price of ingredients after amount of portions.
  public void calculateIng(int wantedPortions) {
    for (int i = 0; i < this.ingredients.size(); i++) {
      ingredients.get(i).value = ingredients.get(i).value / ingredients.get(i).value;
      Math.ceil(ingredients.get(i).value);
      ingredients.get(i).price = ingredients.get(i).price / ingredients.size();
    }
    for (int j = 0; j < this.ingredients.size(); j++) {
      ingredients.get(j).value = ingredients.get(j).value * wantedPortions;
      ingredients.get(j).price = ingredients.get(j).price * wantedPortions;
    }
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
}
