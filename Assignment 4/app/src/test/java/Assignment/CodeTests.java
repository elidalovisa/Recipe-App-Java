package Assignment;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

public class CodeTests {
  // Test to add one item to arrayList
  @Test 
  public void testMethodAdd() {
  ArrayList<String> ingredients = new ArrayList<String>();
  ingredients.add("milk");
  assertEquals(1,ingredients.size());
  }
}
