/**
 * Created by vathsala on 08/10/16.
 */
public class DFA {
    private final Tuple tuple;

    public DFA(Tuple tuple){
        this.tuple = tuple;
    }

    public boolean Test(String string) {
        State currentState = this.tuple.getInitialState();
        if(string.length()<1){
            return this.tuple.isFinalState(currentState);
        }
        for (String alphabet : string.split("")) {
            currentState = this.tuple.Transition.Transit(currentState,alphabet);
        }
        return this.tuple.isFinalState(currentState);
    }


}
