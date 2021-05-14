package Assignment4;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
  String name;
  int portions;
  ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
  ArrayList<Double> amounts = new ArrayList<Double>();
  ArrayList<String> comments = new ArrayList<String>();
  String instruction;
  int totalCost;

  // Constructor for name
  public Recipe(String name) {
    this.name = name;
  }

  // Constructor
  public Recipe(String name, int portions, List<Ingredient> ingredients, List<Double> amounts, String instructions) {
  }

  public void setPortions(int portions) {
    this.portions = portions;
  }

  public void addIngredient(Ingredient ingredient) {
    ingredients.add(ingredient);
  }


// Calculate total cost of recipe.
  public void calculatePrice() {
    int sum = 0;
    for(int i = 0; i < ingredients.size(); i++) {
      sum += ingredients.get(i).price;
    }
    totalCost = sum;
  }
}
