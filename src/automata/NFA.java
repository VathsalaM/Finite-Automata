package automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class NFA implements FiniteAutomata {

  private final States states;
  private final HashSet<Alphabet> alphabets;
  private final Transition transition;
  private final State initialState;
  private final States finalStates;

  NFA(States states, HashSet<Alphabet> alphabets, Transition transition, State initialState, States finalStates) {
    this.states = states;
    this.alphabets = alphabets;
    this.transition = transition;
    this.initialState = initialState;
    this.finalStates = finalStates;
  }

  States getCurrentStates(States currentStates, States visitedStates) {
    Alphabet epsilon = new Alphabet("e");
    States newStates = new States();
    newStates.add(currentStates);
    for (State state : currentStates.getStates()) {
      States tempStates = (States) this.transition.transit(state, epsilon);
      if (tempStates != null) {
        tempStates.remove(visitedStates);
        newStates.add(this.getCurrentStates(tempStates, currentStates));
      }
    }
    return newStates;
  }

  public boolean verify(String string) {
    System.out.println(this.transition);
    States currentStates = new States();
    currentStates.add(this.initialState);
    currentStates = this.getCurrentStates(currentStates, new States());
    if (string.length() < 1) {
      return this.finalStates.containsAtLeastOne(currentStates);
    }
    String[] alphabets = string.split("");
    for (String alphabet : alphabets) {
      States newStates = new States();
      for (State state : currentStates.getStates()) {
        States nextStates = (States) this.transition.transit(state, new Alphabet(alphabet));
        if (nextStates != null) {
          newStates.add(nextStates);
        }
      }
      currentStates = this.getCurrentStates(newStates, new States());
    }
    return this.finalStates.containsAtLeastOne(currentStates);
  }

  public DFA toDFA() {
//    States possibleCombinations = this.states.getPossibleCombinations();
    States newStates = new States();
    newStates.add(initialState);
    Transition newTransition = new Transition();
    String newInitialState = this.getCurrentStates(newStates, new States()).joinStateValues("");
    System.out.println("NFA transition: "+this.transition);
    for (State state : this.states.getStates()) {
//      System.out.println("==============================================================");
//      System.out.println("newState: " + state);
      States newState = new States();
      newState.add(state);
      States start = getCurrentStates(newState, new States());
      String startingKey = start.joinStateValues("");
      States end = null;
      HashSet<State> key = start.getStates();
      if (key.size() > 1) {
        key.remove(state);
      }
//      System.out.println("key: " + key);
      for (State state1 : key) {
//        System.out.println("==========================================");
//        System.out.println("state1: "+state1);

        HashMap<Alphabet,Object> transit = this.transition.getTransit(state1);
//        System.out.println("transit: "+transit);
        for (Map.Entry<Alphabet, Object> entry : transit.entrySet())
        {
          State destinationState = (State) ((States) entry.getValue()).getStates().toArray()[0];
          newTransition.add(new State(startingKey), entry.getKey(), destinationState);
//          System.out.println(entry.getKey() + "/" + entry.getValue());
        }




//        for (Alphabet alphabet : this.alphabets) {
//          System.out.println("===================");
//          System.out.println("Alphabet: "+alphabet);
//          Object transit = this.transition.transit(state1, alphabet);
//          State destinationState = new State("q0");
//          if (transit != null) {
//            System.out.println("transit: "+transit);
//            destinationState = (State) ((States) transit).getStates().toArray()[0];
//          }
//          System.out.println("start: "+start);
//          newTransition.add(new State(startingKey), alphabet, destinationState);
//          System.out.println("newTransiton: "+newTransition);
//        }
      }
    }
//    System.out.println("end: newTransition: "+newTransition);
//    Transition transition = this.transition.createDFATransitions(possibleCombinations, this.initialState);
    return new DFA(this.states, this.alphabets, newTransition, new State(newInitialState), this.finalStates);
  }
}
