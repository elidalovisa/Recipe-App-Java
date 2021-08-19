package Assignment;

import java.util.ArrayList;

// Implementations of strategy search by max price.
public class SearchMaxPrice implements SearchStrategy {
  public void searchMaxPrice(int number) {
    TextMenu text = new TextMenu();
    ArrayList<Recipe> savedRecipes = new ArrayList<Recipe>();
    savedRecipes = text.readRecipe();
    for (int i = 0; i < savedRecipes.size(); i++) {
      if (savedRecipes.get(i).totalCost == number) {
        System.out.println("Recipe with maxprice " + number + ":" + "\n" + savedRecipes.get(i) + "\n");
      }
    }
  }

  public void searchIngredient(String name) {
  }
}
