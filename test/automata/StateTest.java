package automata;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class StateTest {
  @Test
  public void should_check_equality() throws Exception {
    State state = new State("q1");
    assertTrue(state.equals(new State("q1")));
  }
}