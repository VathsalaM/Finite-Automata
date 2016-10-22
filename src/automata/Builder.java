package automata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Iterator;

public class Builder {

  private final States states;
  private final HashSet<Alphabet> alphabets;
  private final Transition transition;
  private final State initialState;
  private final States finalStates;

  private Builder(States states, HashSet<Alphabet> alphabets, Transition transition, State initialState, States finalStates) {

    this.states = states;
    this.alphabets = alphabets;
    this.transition = transition;
    this.initialState = initialState;
    this.finalStates = finalStates;
  }

  private static HashSet<Alphabet> createAlphabetSet(JSONArray jsonAlphabets) throws JSONException {
    HashSet<Alphabet> alphabets = new HashSet<>();
    for (int i = 0; i < jsonAlphabets.length(); i++) {
      alphabets.add(new Alphabet(jsonAlphabets.get(i).toString()));
    }
    return alphabets;
  }

  private static States createStates(JSONArray jsonStates) throws JSONException {
    States states = new States();
    for (int i = 0; i < jsonStates.length(); i++) {
      states.add(new State(jsonStates.get(i).toString()));
    }
    return states;
  }

  private static Transition createTransition(JSONObject jsonTransition, String type) throws JSONException {
    Transition transition = new Transition();
    Iterator<?> keys = jsonTransition.keys();
    while (keys.hasNext()) {
      String key = (String) keys.next();
      JSONObject states = jsonTransition.getJSONObject(key);
      Iterator<?> stateKeys = states.keys();
      while (stateKeys.hasNext()) {
        String alphabet = (String) stateKeys.next();
        Object nextState;
        if (type.equals("dfa")) {
          nextState = new State(states.get(alphabet).toString());
        } else {
          nextState = createStates(jsonTransition.getJSONObject(key).getJSONArray(alphabet));
        }
        transition.add(new State(key), new Alphabet(alphabet), nextState);
      }
    }
    return transition;
  }

  public static Builder New(JSONObject tuple, String type) throws JSONException {
    JSONArray jsonStates = tuple.getJSONArray("states");
    JSONArray jsonAlphabets = tuple.getJSONArray("alphabets");
    JSONArray jsonFinalStates = tuple.getJSONArray("final-states");
    JSONObject jsonTransition = tuple.getJSONObject("delta");
    String jsonInitialState = tuple.get("start-state").toString();

    States states = createStates(jsonStates);
    HashSet<Alphabet> alphabets = createAlphabetSet(jsonAlphabets);
    Transition transition = createTransition(jsonTransition,type);
    State initialState = new State(jsonInitialState);
    States finalStates = createStates(jsonFinalStates);

    return new Builder(states, alphabets, transition, initialState, finalStates);
  }

  public FiniteAutomata buildDFA() {
    return new DFA(this.states,this.alphabets,this.transition,this.initialState,this.finalStates);
  }

  public FiniteAutomata buildNFA() {
    return new NFA(this.states,this.alphabets,this.transition,this.initialState,this.finalStates);  }
}
