package automata;

import java.util.HashSet;

public class DFA implements FiniteAutomata {
  private final States states;
  private final HashSet<Alphabet> alphabets;
  private final Transition transition;
  private final State initialState;
  private final States finalStates;

  public DFA(States states, HashSet<Alphabet> alphabets, Transition transition, State initialState, States finalStates) {
    this.states = states;
    this.alphabets = alphabets;
    this.transition = transition;
    this.initialState = initialState;
    this.finalStates = finalStates;
  }

  private boolean isStatePresent(States states, State currentState){
    return states.contains(currentState);
  }

  public boolean Verify(String string) {
    State currentState = this.initialState;
    if (!this.isStatePresent(this.states,currentState)){
      return false;
    }
    if (string.length() < 1) {
      return this.isStatePresent( this.finalStates,currentState);
    }
    for (String alphabet : string.split("")) {
      if(!this.alphabets.contains(new Alphabet(alphabet))){
        return false;
      }
      currentState = (State) this.transition.Transit(currentState, new Alphabet(alphabet));
    }
    return this.isStatePresent(this.finalStates,currentState);
  }
}
