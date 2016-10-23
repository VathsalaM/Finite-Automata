package automata;

import java.util.HashMap;

class Transition {
  private HashMap<State, HashMap<Alphabet, Object>> transitions;

  Transition() {
    this.transitions = new HashMap<>();
  }

  void add(State currentState, Alphabet alphabet, Object nextState) {
    if (this.transitions.containsKey(currentState)) {
      HashMap<Alphabet, Object> oldValues = this.transitions.get(currentState);
      oldValues.put(alphabet, nextState);
      return;
    }
    HashMap<Alphabet, Object> valueStatePair = new HashMap<>();
    valueStatePair.put(alphabet, nextState);
    this.transitions.put(currentState, valueStatePair);
  }

  Object transit(Object currentState, Alphabet alphabet) {
    Object result;
    try {
      result = this.transitions.get(currentState).get(alphabet);
    } catch (NullPointerException e) {
      result = null;
    }
    return result;
  }

  @Override
  public String toString() {
    return this.transitions.toString();
  }

  HashMap<Alphabet, Object> getTransit(State state) {
//    System.out.println("state in transition: "+state);
//    System.out.println(this.transitions);
    return this.transitions.get(state);
  }
}
