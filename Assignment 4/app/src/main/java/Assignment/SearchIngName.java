package Assignment;

// Implementations of strategy search by ingredient name.
public class SearchIngName implements SearchStrategy {
  ListIngredients ingredients;

  public void searchIngredient(String name){
// Implement code to search for ingredient.
// Import ingredients and search
TextMenu text = new TextMenu();
var savedRecipes = text.readRecipe();
var savedIngredients = text.readIngredients();

// if name match found else recipe does not exist.
//for (int i = 0; i < savedIngredients.size(); i++) {
//  if (savedIngredients.get(i).name.equals(name)) {
    for(int j = 0; j < savedRecipes.size(); j++) {
      if(savedRecipes.get(j).ingredients.contains(name)) {
      System.out.println(savedRecipes.get(j));
      }
   }

 //   System.out.println("Could not find a recipe with ingredient: " + name);

    //System.out.println("Found recipe: " + savedRecipes.get(i));
//System.out.println("Searching for " + name + " Ingredient name");
   { 
  }
}

  public void searchMaxPrice(int number) {
    
  }
}
