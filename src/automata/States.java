package automata;

import java.util.HashSet;

public class States implements IState{

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
}
