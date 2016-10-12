import java.util.HashSet;

class DFA {
  private final HashSet<State> states;
  private final HashSet<String> alphabets;
  private final Transition transition;
  private final State initialState;
  private final HashSet<State> finalStates;

  DFA(HashSet<State> states, HashSet<String> alphabets, Transition transition, State initialState, HashSet<State> finalStates) {
    this.states = states;
    this.alphabets = alphabets;
    this.transition = transition;
    this.initialState = initialState;
    this.finalStates = finalStates;
  }

  boolean isFinalState(State currentState){
    return this.finalStates.contains(currentState);
  }
  boolean Verify(String string) {
    State currentState = this.initialState;
    if (string.length() < 1) {
      return this.isFinalState(currentState);
    }
    for (String alphabet : string.split("")) {
      currentState = this.transition.Transit(currentState, alphabet);
    }
    return this.isFinalState(currentState);
  }


}
