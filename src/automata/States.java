package automata;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

class States {

  private HashSet<State> states;

  States() {
    this.states = new HashSet<>();
  }

  States(HashSet<State> states) {
    this.states = states;
  }

  States add(State state) {
    this.states.add(state);
    return this;
  }

  void remove(State state) {
    this.states.remove(state);
  }

  void remove(States otherStates) {
    otherStates.states.stream().filter(state -> this.states.contains(state)).forEach(this::remove);
  }

  boolean contains(State state) {
    return this.states.contains(state);
  }

  boolean contains(States otherStates) {
    for (State state : otherStates.states) {
      if (!this.states.contains(state)) {
        return false;
      }
    }
    return true;
  }

  int length() {
    return this.states.size();
  }

  @Override
  public boolean equals(Object obj) {
//    System.out.println("this: "+this.states);
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

  States add(States newStates) {
    this.states.addAll(newStates.states);
    return this;
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

  States getPossibleCombinations() {
    HashSet<State> combinationStates = new HashSet<State>();
    combinationStates.addAll(this.states);
    combinationStates.add(new State("q0"));
    int length = this.states.size();
    List<State> listOfStates = new ArrayList<State>(this.states);
    for (State state : listOfStates) {
      String value = state.toString();
      for (int j = listOfStates.indexOf(state) + 1; j < length; j++) {
        String newValue = listOfStates.get(j).toString();
        value += newValue;
        combinationStates.add(new State(state.toString() + newValue));
        combinationStates.add(new State(value));
      }
    }
    return new States(combinationStates);
  }

  String joinStateValues(String delimiter) {
    return this.states.stream()
            .map(State::toString)
            .collect(Collectors.joining(""));
  }

  public State generateState() {
    String state = this.states.toString();
    return new State(state);
  }
}
