package automata;

import java.util.HashSet;

class States implements IState {

  private HashSet<State> states;

  States() {
    this.states = new HashSet<>();
  }

  void add(State state) {
    this.states.add(state);
  }

  void remove(State state) {
    this.states.remove(state);
  }

  boolean contains(State state) {
    return this.states.contains(state);
  }

  int length() {
    return this.states.size();
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof States && this.states.equals(((States) obj).states);
  }

  @Override
  public int hashCode() {
    return this.states.hashCode();
  }

  @Override
  public String toString() {
    return this.states.toString();
  }

  void add(States newStates) {
    this.states.addAll(newStates.states);
  }

  HashSet<State> getStates() {
    return states;
  }

  boolean containsAtLeastOne(States otherStates) {
    for (State state : otherStates.states) {
      if (this.states.contains(state)) {
        return true;
      }
    }
    return false;
  }

  States removeCommon(States prevStates) {
    for (State state : prevStates.states) {
      if (this.states.contains(state)) {
        this.remove(state);
      }
    }
    return this;
  }
}
