package automata;

import org.junit.Test;

import static org.junit.Assert.*;

public class AlphabetTest {
  @Test
  public void equals_should_equal_two_alphabets_with_same_values() throws Exception {
    Alphabet alphabet = new Alphabet("1");
    assertTrue(alphabet.equals(new Alphabet("1")));
  }

}