package Assignment;

public class SearchForRecipe {
  // Create search context to search for recipe.
  SearchContext context = new SearchContext();

  // Set serach by ingredient name strategy.

  public void searchIngredient(String name) {
    context.setSearchStrategy(new SearchIngName());
    context.searchIngredient(name);
  }

  // Set serach by max price strategy.
  public void searchMaxPrice(int number) {
    context.setSearchStrategy(new SearchMaxPrice());
    context.searchMaxPrice(number);
  }
}
