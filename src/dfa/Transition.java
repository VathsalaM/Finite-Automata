package dfa;

import java.util.HashMap;

public class Transition {
  private HashMap<String, HashMap<String, String>> transitions;

  public Transition() {
    this.transitions = new HashMap<>();
  }

  public void Add(String currentState, String alphabet, String nextState) {
    if (this.transitions.containsKey(currentState)) {
      HashMap<String, String> oldValues = this.transitions.get(currentState);
      oldValues.put(alphabet, nextState);
      return;
    }
    HashMap<String, String> valueStatePair = new HashMap<>();
    valueStatePair.put(alphabet, nextState);
    this.transitions.put(currentState, valueStatePair);
  }

  String Transit(String currentState, String alphabet) {
    return this.transitions.get(currentState).get(alphabet);
  }
}
