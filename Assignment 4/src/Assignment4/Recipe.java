//package Assignment4;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Recipe implements Serializable {
  String name;
  int portions;
  ListIngredients ingredients = new ListIngredients();
  ArrayList<Double> amounts = new ArrayList<Double>();
  String comment;
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
    ingredients.addIngredientRecipe(ingredient);
  }

  public void addComment(String comment) {
    this.comment = comment;
  }

  public void addInstructions(String instruction) {
    this.instruction = instruction;
  }

  public void addTotalCost() {
    this.totalCost = ingredients.getTotalCost(portions);
  }

  public String toString() {
    return String.format("%s; Portions: %d; Ingredients: %s; Comment: %s; Instructions: %s; totalCost: %d", name, portions, ingredients.toString(), comment, instruction, ingredients.getTotalCost(portions));
  }

  public void updatePortions(int wantedPortions) {
    var oldPortions = this.portions;
    ArrayList<Double> baseValueIngredients = new ArrayList<Double>();

    for(int i = 0; i < ingredients.size(); i++) {
      baseValueIngredients.add(ingredients.get(i).value / oldPortions);
    }
    for(int j = 0; j < ingredients.size(); j++) {
      ingredients.get(j).value = baseValueIngredients.get(j)* wantedPortions;
    }
    this.portions = wantedPortions;
  }
}
