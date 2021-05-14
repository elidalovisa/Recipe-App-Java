import java.util.ArrayList;

public class ListIngredients {
  ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

  // Constructor
  public ListIngredients() {
    //ingredients = new ArrayList<Ingredient>());
  }

  // Add ingredient and check so name is unique.
  public int addIngredient(Ingredient ingredient) {

    for (int i = 0; i < this.ingredients.size(); i++) {
      if (ingredients.get(i).name == ingredient.name) {
        return -1;
      }
    }
    ingredients.add(ingredient);
    return 0;
  }

  public boolean checkIfNameExist(String name) {
    for (int i = 0; i < this.ingredients.size(); i++) {
      if (ingredients.get(i).name == name) {
        return true;
      }
    }
    return false;
  }
}
