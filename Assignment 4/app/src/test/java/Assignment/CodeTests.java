package Assignment;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

public class CodeTests {
  // Test to add one ingredient to ArrayList.
  @Test 
  public void testMethodAddOneIngredient() {
  ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
  Ingredient newIngredient = new Ingredient("milk", "Liter", 2, 1.5);
  ingredients.add(newIngredient);
  assertEquals(1,ingredients.size());
  }

    // Test to add one ingredient to ArrayList.
    @Test 
    public void testMethodAddFiveIngredients() {
    ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    Ingredient newIngredient = new Ingredient("milk", "Liter", 2, 1.5);
    ingredients.add(newIngredient);
    ingredients.add(newIngredient);
    ingredients.add(newIngredient);
    ingredients.add(newIngredient);
    ingredients.add(newIngredient);
    assertEquals(5,ingredients.size());
    }

      // Test to remove one ingredient to ArrayList.
      @Test 
      public void testMethodRemoveIngredient() {
      ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
      Ingredient newIngredient = new Ingredient("milk", "Liter", 2, 1.5);
      ingredients.add(newIngredient);
      ingredients.add(newIngredient);
      ingredients.add(newIngredient);
      ingredients.add(newIngredient);
      ingredients.remove(ingredients.get(1));
      assertEquals(3,ingredients.size());
      }
}
