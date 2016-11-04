package automata;

import java.util.HashSet;

class DFA implements FiniteAutomata {
  private final States states;
  private final HashSet<Alphabet> alphabets;
  private final Transition transition;
  private final State initialState;
  private final States finalStates;


  DFA(States states, HashSet<Alphabet> alphabets, Transition transition, State initialState, States finalStates) {
    this.states = states;
    this.alphabets = alphabets;
    this.transition = transition;
    this.initialState = initialState;
    this.finalStates = finalStates;
  }

  public boolean verify(String string) {
    State currentState = this.initialState;
    if (string.length() < 1) {
      return this.finalStates.contains(currentState);
    }
    for (String alphabet : string.split("")) {
      currentState = (State) this.transition.transit(currentState, new Alphabet(alphabet));
    }
    return this.finalStates.contains(currentState);
  }
}
