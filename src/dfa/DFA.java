package dfa;

import common.FiniteAutomata;

import java.util.HashSet;

public class DFA implements FiniteAutomata {
  private final HashSet<String> states;
  private final HashSet<String> alphabets;
  private final Transition transition;
  private final String initialState;
  private final HashSet<String> finalStates;

  public DFA(HashSet<String> states, HashSet<String> alphabets, Transition transition, String initialState, HashSet<String> finalStates) {
    this.states = states;
    this.alphabets = alphabets;
    this.transition = transition;
    this.initialState = initialState;
    this.finalStates = finalStates;
  }

  boolean isStatePresent(HashSet<String> states, String currentState){
    return states.contains(currentState);
  }

  public boolean Verify(String string) {
    String currentState = this.initialState;
    if (!this.isStatePresent(this.states,currentState)){
      return false;
    }
    if (string.length() < 1) {
      return this.isStatePresent( this.finalStates,currentState);
    }
    for (String alphabet : string.split("")) {
      if(!this.alphabets.contains(alphabet)){
        return false;
      }
      currentState = this.transition.Transit(currentState, alphabet);
    }
    return this.isStatePresent(this.finalStates,currentState);
  }
}
