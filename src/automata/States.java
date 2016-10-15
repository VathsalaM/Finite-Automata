package automata;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

class States implements IState{

  private HashSet<State> states;

  States() {
    this.states = new HashSet<>();
  }

  void add(State state){
      this.states.add(state);
  }

  void remove(State state){
    this.states.remove(state);
  }

  boolean contains(State state){
    return this.states.contains(state);
  }
  boolean contains(States otherStates){
    boolean result = false;
    for (State state : otherStates.states) {
      result = this.states.contains(state);
    }
    return result;
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

  public void add(States newStates) {
    this.states.addAll(newStates.states);
  }

  public HashSet<State> getStates() {
    return states;
  }
}
