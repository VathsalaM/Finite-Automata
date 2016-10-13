package dfa;

import java.util.HashMap;

public class Transition {
  private HashMap<State, HashMap<String, State>> transitions;

  public Transition() {
    this.transitions = new HashMap<>();
  }

  public void Add(State currentState, String alphabet, State nextState) {
    if (this.transitions.containsKey(currentState)) {
      HashMap<String, State> oldValues = this.transitions.get(currentState);
      oldValues.put(alphabet, nextState);
      return;
    }
    HashMap<String, State> valueStatePair = new HashMap<>();
    valueStatePair.put(alphabet, nextState);
    this.transitions.put(currentState, valueStatePair);
  }

  State Transit(State currentState, String alphabet) {
    return this.transitions.get(currentState).get(alphabet);
  }
}
