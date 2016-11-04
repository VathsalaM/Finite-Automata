package automata;

import org.junit.Test;

import static org.junit.Assert.*;

public class StatesTest {
  @Test
  public void should_add_states() throws Exception {
    States states = new States();
    states.add(new State("q1"));
    assertEquals(1, states.length());
    assertTrue(states.contains(new State("q1")));
  }

  @Test
  public void should_remove_states() throws Exception {
    States states = new States();
    states.add(new State("q1"));
    states.add(new State("q2"));
    states.remove(new State("q1"));
    assertEquals(1, states.length());
    assertTrue(states.contains(new State("q2")));
  }

  @Test
  public void contains_should_check_wether_the_state_is_present_in_set() throws Exception {
    States states = new States();
    states.add(new State("q1"));
    assertTrue(states.contains(new State("q1")));
    assertFalse(states.contains(new State("q2")));
  }

  @Test
  public void check_equality() throws Exception {
    States states1 = new States();
    states1.add(new State("q1")).add(new State("q2")).add(new State("q5"));

    States states2 = new States();
    states2.add(new State("q1")).add(new State("q2")).add(new State("q5"));
    assertEquals(states1, states2);
  }

}