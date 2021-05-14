import java.util.List;

public class ListIngredients {
  List<Ingredient> ingredients;

  // Constructor
  public ListIngredients() {

  }
  
  // Add ingredient and check so name is unique.
  public int addIngredient(Ingredient ingredient) {

    for (int i = 0; i < this.ingredients.size(); i++) {
      if (ingredients.get(i).name == ingredient.name) {
        return -1;
      }
    }
    this.ingredients.add(ingredient);
    return 0;
  }
}
