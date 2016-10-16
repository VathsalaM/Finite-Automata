package automata;

import java.util.HashSet;

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

  private States getCurrentStates(States currentStates,States prevStates) {
    Alphabet epsilon = new Alphabet("e");
    States newStates = new States();
    newStates.add(currentStates);
    for (State state : currentStates.getStates()) {
      States tempStates = (States) this.transition.Transit(state,epsilon);
      if(tempStates!=null){
        newStates.add(this.getCurrentStates(tempStates.removeCommon(prevStates),currentStates));
      }
    }
    return newStates;
  }

  public boolean verify(String string) {
    States currentStates = new States();
    currentStates.add(this.initialState);
    currentStates = this.getCurrentStates(currentStates,new States());
    if (string.length() < 1) {
      return this.finalStates.containsAtLeastOne(currentStates);
    }
    for (String alphabet : string.split("")) {
      States newStates = new States();
      for (State state : currentStates.getStates()) {
        States nextStates = (States) this.transition.Transit(state,new Alphabet(alphabet));
        if(nextStates!=null){
          newStates.add(nextStates);
        }
      }
      currentStates = getCurrentStates(newStates,new States());
    }
    return this.finalStates.containsAtLeastOne(currentStates);
  }

  public DFA toDFA() {
    return new DFA(this.states,this.alphabets,this.transition,this.initialState,this.finalStates);
  }
}
