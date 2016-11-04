package automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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

  public DFA toDFA(){
    System.out.println("==>1");
    States initialStates = new States();
    System.out.println("==>2");
    initialStates.add(this.getCurrentStates((new States()).add(this.initialState),new States()));
    System.out.println("==>3");
    System.out.println("NFATransition: "+this.transition);
    Transition DFATransitionWithMultipleStates = this.createDFATransition(new Transition(),initialStates,new States());
    System.out.println("==>4");
    HashSet<States> DFAMultipleFinalStates = this.createDFAFinalStates(DFATransitionWithMultipleStates,this.finalStates);
    System.out.println("==>5");
    HashMap<States,State> DFAStatesMap = this.createDFAStatesMap(DFATransitionWithMultipleStates);
    System.out.println("==>6");
    States DFAStates = this.createDFAStates(DFAStatesMap,DFATransitionWithMultipleStates.getStatesForMultipleState());
    System.out.println("==>7");
    States DFAFinalStates = this.createDFAStates(DFAStatesMap,DFAMultipleFinalStates);
    System.out.println("==>8");
    Transition DFATransition = this.convertTransitionToDFATransition(DFATransitionWithMultipleStates,DFAStatesMap);
    System.out.println("==>9");
    State DFAInitialState = DFAStatesMap.get(initialStates);
    System.out.println("==>10");
    return new DFA(DFAStates,this.alphabets,DFATransition,DFAInitialState,DFAFinalStates);
  }

  private Transition convertTransitionToDFATransition(Transition dfaTransitionWithMultipleStates, HashMap<States, State> dfaStatesMap) {
    Transition transition = new Transition();
    HashSet<States> statesForMultipleState = dfaTransitionWithMultipleStates.getStatesForMultipleState();
    for (States states1 : statesForMultipleState) {
      for (Alphabet alphabet : this.alphabets) {
        States dest = (States) dfaTransitionWithMultipleStates.transit(states1, alphabet);
        transition.add(dfaStatesMap.get(states1),alphabet,dfaStatesMap.get(dest));
      }
    }
    return transition;
  }

  private States createDFAStates(HashMap<States, State> dfaStatesMap, HashSet<States> dfaMultipleFinalStates) {
    States states = new States();
    for (States dfaMultipleFinalState : dfaMultipleFinalStates) {
      states.add(dfaStatesMap.get(dfaMultipleFinalState));
    }
    return states;
  }

  private HashMap<States, State> createDFAStatesMap(Transition transition) {
    HashMap<States,State> DFAStatesMap = new HashMap<>();
    HashSet<States> statesForMultipleState = transition.getStatesForMultipleState();
    for (States states : statesForMultipleState) {
      DFAStatesMap.put(states,states.generateState());
    }
    return DFAStatesMap;
  }

  private HashSet<States> createDFAFinalStates(Transition dfaTransition, States finalStates) {
    HashSet<States> DFAFinalStates = new HashSet<>();
    Set<Object> keys = dfaTransition.getKeys();
    for (Object key : keys) {
      HashSet<State> NFAFinalStates = finalStates.getStates();
      for (State nfaState :
              NFAFinalStates) {
        if(((States)key).contains(nfaState)){
          DFAFinalStates.add((States) key);
        }
      }
    }
    return DFAFinalStates;
  }

  private Transition createDFATransition(Transition transition, States initialStates,States prevStates) {
    System.out.println("=============================");
    System.out.println("transition: "+transition);
    System.out.println("initialStates: "+initialStates);
    System.out.println("prevStates: "+prevStates);
    HashSet<State> currentStates = initialStates.getStates();
    if(prevStates.contains(initialStates)){
      return transition;
    }
    for (State currentState : currentStates) {
      for (Alphabet alphabet : this.alphabets) {
        States transit = (States)this.transition.transit(currentState, alphabet);
        if(transit != null){
          transition.add(new States(currentStates),alphabet,this.getCurrentStates(new States().add(transit),new States()));
        }
      }
    }
    for (Alphabet alphabet : this.alphabets) {
      States destState = (States)transition.transit(initialStates, alphabet);
      if(destState != null){
       if(prevStates.equals(destState)) {
         return transition;
       }
        System.out.println("transit: "+destState);
        transition = createDFATransition(transition,destState,initialStates);
      }
    }
    return transition;
  }
}