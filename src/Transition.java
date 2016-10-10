import java.util.HashMap;

/**
 * Created by vathsala on 08/10/16.
 */

public class Transition {
    private HashMap<State, HashMap<String, State>> transitions;

    public Transition(HashMap<State, HashMap<String, State>> transitions) {
        this.transitions = transitions;
    }

    public Transition() {
        this.transitions = new HashMap<State, HashMap<String, State>>();
    }

    public void Add(State currentState, String alphabet, State nextState){
        if (this.transitions.containsKey(currentState)){
            HashMap<String,State> oldValues = this.transitions.get(currentState);
            oldValues.put(alphabet,nextState);
            return;
        }
        HashMap<String,State> valueStatePair = new HashMap<>();
        valueStatePair.put(alphabet,nextState);
        this.transitions.put(currentState,valueStatePair);
    }

    public State Transit(State currentState,String alphabet ){
        return this.transitions.get(currentState).get(alphabet);
    }
}
