import java.util.HashSet;

/**
 * Created by vathsala on 08/10/16.
 */
public class Tuple {
    private final HashSet<State> States;
    private final HashSet<String> Alphabets;
    final Transition Transition;
    private final State InitialState;
    private final HashSet<State> FinalStates;

    public Tuple(HashSet<State> states,HashSet<String> alphabets,Transition transition,State initialState,HashSet<State> finalStates){
        this.States = states;
        this.Alphabets = alphabets;
        this.Transition = transition;
        this.InitialState = initialState;
        this.FinalStates = finalStates;
    }

    public State getInitialState() {
        return this.InitialState;
    }

    public boolean isFinalState(State currentState) {
        return FinalStates.contains(currentState);
    }
}
