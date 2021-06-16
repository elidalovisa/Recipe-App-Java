package Assignment;

// The context which determines the implementation.
public class SearchContext {
  SearchStrategy strategy;

  public void setSearchStrategy(SearchStrategy strategy) {
    this.strategy = strategy;
  }

  public void searchIngredient(String name) {
    strategy.searchIngredient(name);
  }

  public void searchMaxPrice(int number) {
    strategy.searchMaxPrice(number);
  }
}
