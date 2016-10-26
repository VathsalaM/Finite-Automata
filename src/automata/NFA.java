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

  Transition addTransition(Transition currentTransition, States currentStates, State initialState, States prevStates){
    HashSet<State> key = currentStates.getStates();
    if (key.size() > 1) {
      key.remove(initialState);
    }
    String newKeyValue = currentStates.joinStateValues(",");
    HashMap<Alphabet, States> alphabetStatesHashMap = new HashMap<>();
    for (Alphabet alphabet : this.alphabets) {
      for (State individualState : key) {
        Object transit = this.transition.transit(individualState, alphabet);
        if (transit != null) {
          States tempStates = new States();
          if (alphabetStatesHashMap.containsKey(alphabet)) {
            tempStates = alphabetStatesHashMap.get(alphabet);
          }
          State destinationState = (State) ((States) transit).getStates().toArray()[0];
          tempStates.add(destinationState);
          alphabetStatesHashMap.put(alphabet, tempStates);
        }
      }
    }
    for (Map.Entry<Alphabet, States> entry : alphabetStatesHashMap.entrySet())
    {
      States destinationStates = (States) entry.getValue();
      State destinationState = new State( destinationStates.joinStateValues(""));
      currentTransition.add(new State(newKeyValue), entry.getKey(),destinationState);
      if(currentStates.equals(destinationStates)){
        return currentTransition;
      }
      if(destinationStates.equals(prevStates)){
        return currentTransition;
      }
      addTransition(currentTransition,destinationStates,null,currentStates);
    }
    return currentTransition;
  }

  Transition getTransition(Transition currentTransition,States newStates){
    HashSet<State> states = newStates.getStates();
    for (State state : states) {
      States tempState = new States();
      tempState.add(state);
      States currentStates = this.getCurrentStates(tempState, newStates);
      currentTransition = addTransition(currentTransition,currentStates,state,new States());
    }
    States currentTransitionStates = currentTransition.getStates();
    HashSet<State> currentStates = currentTransitionStates.getStates();
    State deadState = new State("q0");
    for (Alphabet alphabet : this.alphabets) {
      for (State currentState : currentStates) {
        State state =  (State) currentTransition.transit(currentState,alphabet);
        if(state==null){
          currentTransition.add(currentState,alphabet,deadState);
        }
      }
      currentTransition.add(deadState,alphabet,deadState);
    }
    return currentTransition;
  }

  public DFA toDFA() {
    System.out.println("finalStates: "+this.finalStates);
    System.out.println("NFA Transition: "+this.transition);
    States tempStates = new States();
    tempStates.add(this.initialState);
    Transition newTransition = getTransition(new Transition(),tempStates);

    System.out.println("DFA Transition: "+newTransition);
    States newInitialState = this.getCurrentStates(tempStates,new States());
    if (newInitialState.getStates().size() > 1) {
      newInitialState.remove(initialState);
    }
    String newFinalState = this.finalStates.joinStateValues(",");
    States newFinalStates = new States();
    newFinalStates.add(new State(newFinalState));
    newFinalStates.add(this.finalStates);
    System.out.println("newFinalState: "+newFinalStates);
    return new DFA(this.states, this.alphabets, newTransition, new State(newInitialState.joinStateValues(",")), newFinalStates);
  }
}