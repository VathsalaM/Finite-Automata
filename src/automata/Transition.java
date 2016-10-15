package automata;

import java.util.HashMap;

public class Transition {
  private HashMap<State, HashMap<Alphabet, IState>> transitions;

  Transition() {
    this.transitions = new HashMap<>();
  }

  void Add(State currentState, Alphabet alphabet, IState nextState) {
    if (this.transitions.containsKey(currentState)) {
      HashMap<Alphabet, IState> oldValues = this.transitions.get(currentState);
      oldValues.put(alphabet, nextState);
      return;
    }
    HashMap<Alphabet, IState> valueStatePair = new HashMap<>();
    valueStatePair.put(alphabet, nextState);
    this.transitions.put(currentState, valueStatePair);
  }

  IState Transit(State currentState, Alphabet alphabet) {
    return this.transitions.get(currentState).get(alphabet);
  }

  @Override
  public String toString() {
    return this.transitions.toString();
  }
}
