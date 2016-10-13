package dfa;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransitionTest {
  @Test
  public void Should_return_next_state_when_current_state_and_transition_is_provided() throws Exception {
    Transition transition = new Transition();
    State state0 = new State("q0");
    State state1 = new State("q1");
    String alphabet = "0";

    transition.Add(state0,alphabet,state1);

    State newState = transition.Transit(state0,alphabet);

    assertEquals(state1,newState);

  }
}
