package Assignment;

import java.util.ArrayList;

// Implementations of strategy search by ingredient name.
public class SearchIngName implements SearchStrategy {
  ListIngredients ingredients;

  public void searchIngredient(String name) {
    // Implement code to search for ingredient.
    // Import ingredients and search
    TextMenu text = new TextMenu();
    ArrayList<Recipe> savedRecipes = new ArrayList<Recipe>();
    savedRecipes = text.readRecipe();

    // if name match found else recipe does not exist.
    for (int j = 0; j < savedRecipes.size(); j++) {
      if (savedRecipes.get(j).ingredients.contains(name)) {
        System.out.println(savedRecipes.get(j));
      }
    }
    {
    }
  }

  public void searchMaxPrice(int number) {

  }
}
