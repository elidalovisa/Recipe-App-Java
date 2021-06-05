// Implementations of strategy search by max price.
public class SearchMaxPrice implements SearchStrategy {
  public void searchMaxPrice(int number) {
    TextMenu text = new TextMenu();
    var savedRecipes = text.readRecipe();
    for (int i = 0; i < savedRecipes.size(); i++) {
      int foundRecipes = 0;
      if (savedRecipes.get(i).totalCost == number) {
          foundRecipes++;
          System.out.println("Recipe with maxprice " + number + ":" + "\n" + savedRecipes.get(i).name + "\n");
  } if (foundRecipes == 0) {
    System.out.println("No recipes with maxprice " + number + " found.");
  }
  
}
  }

  public void searchIngredient(String name){
  }
}
