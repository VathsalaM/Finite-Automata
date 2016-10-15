package automata;

import java.util.HashSet;

public class NFA implements FiniteAutomata {

  private final States states;
  private final HashSet<Alphabet> alphabets;
  private final Transition transition;
  private final State initialState;
  private final States finalStates;

  NFA(States states, HashSet<Alphabet> alphabets, Transition transition, State initialState, States finalStates) {
    this.states = states;
    this.alphabets = alphabets;
    this.transition = transition;
    this.initialState = initialState;
    this.finalStates = finalStates;
  }

  public boolean Verify(String string){
    return false;
  }

}
