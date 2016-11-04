package automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class Transition {
  private HashMap<Object, HashMap<Alphabet, Object>> transitions;

  Transition() {
    this.transitions = new HashMap<>();
  }

  void add(Object currentState, Alphabet alphabet, Object nextState) {
    if (this.transitions.containsKey(currentState)) {
      HashMap<Alphabet, Object> oldValues = this.transitions.get(currentState);
      Object oldState = oldValues.get(alphabet);
      if (oldState != null && nextState.getClass() == States.class) {
        nextState = ((States) oldState).add((States) nextState);
      }
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

  States getStatesForSingleState() {
    States states = new States();
    Set<Object> tempStates = this.transitions.keySet();
    for (Object tempState : tempStates) {
      states.add((State) tempState);
    }
    return states;
  }

  HashSet<States> getStatesForMultipleState() {
    HashSet states = new HashSet();
    Set<Object> tempStates = this.transitions.keySet();
    for (Object tempState : tempStates) {
      states.add(tempState);
    }
    return states;
  }

  public Set<Object> getKeys() {
    return this.transitions.keySet();
  }
}
