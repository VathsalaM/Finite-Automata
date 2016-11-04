package automata;

import org.junit.Test;

import static org.junit.Assert.*;

public class TransitionTest {
  @Test
  public void add_adds_states_to_transition() throws Exception {

  }

  @Test
  public void transit() throws Exception {
    Transition transition = new Transition();
    States states1 = new States().add(new State("q1")).add(new State("q2")).add(new State("q5"));
    States state2 = new States().add(new State("q1")).add(new State("q2")).add(new State("q5"));
    States dst = new States().add(new State("q3")).add(new State("q5"));
    transition.add(states1,new Alphabet("0"),dst);
    Object transit = transition.transit(state2, new Alphabet("0"));
    System.out.println(transit);
    assertEquals((States)transit,dst);
  }

}