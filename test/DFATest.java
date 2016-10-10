import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by vathsala on 08/10/16.
 */

public class DFATest {
    @Test
    public void Should_recognise_all_strings_that_begin_with_1_and_contain_the_string_001() throws Exception {
        State state0 = new State("q0");
        State state1 = new State("q1");
        State state2 = new State("q2");
        State state3 = new State("q3");
        State state4 = new State("q4");
        State state5 = new State("q5");
        HashSet<State> states = new HashSet<>(Arrays.asList(state0,state1));
        String alphabet0 = "0";
        String alphabet1 = "1";
        HashSet<String> alphabets = new HashSet<>(Arrays.asList(alphabet0,alphabet1));

        Transition transition = new Transition();
        transition.Add(state0,alphabet0,state1);
        transition.Add(state0,alphabet1,state2);
        transition.Add(state1,alphabet0,state1);
        transition.Add(state1,alphabet1,state1);
        transition.Add(state2,alphabet0,state3);
        transition.Add(state2,alphabet1,state2);
        transition.Add(state3,alphabet0,state4);
        transition.Add(state3,alphabet1,state2);
        transition.Add(state4,alphabet0,state4);
        transition.Add(state4,alphabet1,state5);
        transition.Add(state5,alphabet0,state5);
        transition.Add(state5,alphabet1,state5);

        State initialState = state0;
        HashSet<State> finalStates = new HashSet<>(Arrays.asList(state5));
        Tuple tuple = new Tuple(states,alphabets,transition,initialState,finalStates);
        DFA dfa = new DFA(tuple);
        assertEquals(true,dfa.Test("1001"));
        assertEquals(true,dfa.Test("100101001"));
        assertEquals(false,dfa.Test("10000"));
        assertEquals(false,dfa.Test("000001"));
    }

    @Test
    public void Should_recognise_all_strings_whose_length_is_greater_than_three_and_has_1_as_the_third_alphabet_in_the_sequence() throws Exception {
        State state0 = new State("q0");
        State state1 = new State("q1");
        State state2 = new State("q2");
        State state3 = new State("q3");
        State state4 = new State("q4");
        State state5 = new State("q5");
        HashSet<State> states = new HashSet<>(Arrays.asList(state0,state1));
        String alphabet0 = "0";
        String alphabet1 = "1";
        HashSet<String> alphabets = new HashSet<>(Arrays.asList(alphabet0,alphabet1));

        Transition transition = new Transition();
        transition.Add(state0,alphabet0,state1);
        transition.Add(state0,alphabet1,state1);
        transition.Add(state1,alphabet0,state2);
        transition.Add(state1,alphabet1,state2);
        transition.Add(state2,alphabet0,state3);
        transition.Add(state2,alphabet1,state4);
        transition.Add(state3,alphabet0,state3);
        transition.Add(state3,alphabet1,state3);
        transition.Add(state4,alphabet0,state5);
        transition.Add(state4,alphabet1,state5);
        transition.Add(state5,alphabet0,state5);
        transition.Add(state5,alphabet1,state5);

        State initialState = state0;
        HashSet<State> finalStates = new HashSet<>(Arrays.asList(state5));
        Tuple tuple = new Tuple(states,alphabets,transition,initialState,finalStates);
        DFA dfa = new DFA(tuple);
        assertEquals(true,dfa.Test("1010"));
        assertEquals(false,dfa.Test("101"));
        assertEquals(true,dfa.Test("111110"));
    }

    @Test
    public void Should_recognise_all_strings_whose_decimal_representation_is_an_even_number() throws Exception {
        State state0 = new State("q0");
        State state1 = new State("q1");
        HashSet<State> states = new HashSet<>(Arrays.asList(state0,state1));
        String alphabet0 = "0";
       String alphabet1 = "1";
        HashSet<String> alphabets = new HashSet<>(Arrays.asList(alphabet0,alphabet1));
        Transition transition = new Transition();
        transition.Add(state0,alphabet1,state0);
        transition.Add(state0,alphabet0,state1);
        transition.Add(state1,alphabet1,state0);
        transition.Add(state1,alphabet0,state1);
        State initialState = state0;
        HashSet<State> finalStates = new HashSet<>(Arrays.asList(state1));
        Tuple tuple = new Tuple(states,alphabets,transition,initialState,finalStates);
        DFA dfa = new DFA(tuple);
        assertEquals(true,dfa.Test("010"));
        assertEquals(false,dfa.Test("1"));
    }

    @Test
    public void Should_recognise_all_strings_whose_decimal_representation_is_a_power_of_two() throws Exception {
        State state0 = new State("q0");
        State state1 = new State("q1");
        State state2 = new State("q2");
        HashSet<State> states = new HashSet<>(Arrays.asList(state0,state1));
        String alphabet0 = "0";
        String alphabet1 = "1";
        HashSet<String> alphabets = new HashSet<>(Arrays.asList(alphabet0,alphabet1));

        Transition transition = new Transition();
        transition.Add(state0,alphabet1,state1);
        transition.Add(state0,alphabet0,state0);
        transition.Add(state1,alphabet1,state2);
        transition.Add(state1,alphabet0,state1);
        transition.Add(state2,alphabet0,state2);
        transition.Add(state2,alphabet1,state2);

        State initialState = state0;
        HashSet<State> finalStates = new HashSet<>(Arrays.asList(state1));
        Tuple tuple = new Tuple(states,alphabets,transition,initialState,finalStates);
        DFA dfa = new DFA(tuple);
        assertEquals(true,dfa.Test("10"));
        assertEquals(true,dfa.Test("01"));
        assertEquals(false,dfa.Test("011"));
    }

    @Test
    public void Should_recognise_all_strings_other_than_the_strings_11_and_111() throws Exception {
        State state0 = new State("q0");
        State state1 = new State("q1");
        State state2 = new State("q2");
        State state3 = new State("q3");
        State state4 = new State("q4");
        HashSet<State> states = new HashSet<>(Arrays.asList(state0,state1));
        String alphabet0 = "0";
        String alphabet1 = "1";
        HashSet<String> alphabets = new HashSet<>(Arrays.asList(alphabet0,alphabet1));

        Transition transition = new Transition();
        transition.Add(state0,alphabet0,state4);
        transition.Add(state0,alphabet1,state1);
        transition.Add(state1,alphabet0,state0);
        transition.Add(state1,alphabet1,state2);
        transition.Add(state2,alphabet0,state4);
        transition.Add(state2,alphabet1,state3);
        transition.Add(state3,alphabet0,state4);
        transition.Add(state3,alphabet1,state4);
        transition.Add(state4,alphabet0,state4);
        transition.Add(state4,alphabet1,state4);

        State initialState = state0;
        HashSet<State> finalStates = new HashSet<>(Arrays.asList(state1,state4));
        Tuple tuple = new Tuple(states,alphabets,transition,initialState,finalStates);
        DFA dfa = new DFA(tuple);
        assertEquals(false,dfa.Test("11"));
        assertEquals(false,dfa.Test("111"));
        assertEquals(true,dfa.Test("0111"));
        assertEquals(true,dfa.Test("1"));
        assertEquals(true,dfa.Test("1111"));
    }

    @Test
    public void Should_recognise_the_empty_set() throws Exception {
        State state0 = new State("q0");
        State state1 = new State("q1");
        HashSet<State> states = new HashSet<>(Arrays.asList(state0,state1));
        String alphabet0 = "0";
       String alphabet1 = "1";
        HashSet<String> alphabets = new HashSet<>(Arrays.asList(alphabet0,alphabet1));

        Transition transition = new Transition();
        transition.Add(state0,alphabet1,state1);
        transition.Add(state0,alphabet0,state1);
        transition.Add(state1,alphabet1,state1);
        transition.Add(state1,alphabet0,state1);

        State initialState = state0;
        HashSet<State> finalStates = new HashSet<>(Arrays.asList(state0));
        Tuple tuple = new Tuple(states,alphabets,transition,initialState,finalStates);
        DFA dfa = new DFA(tuple);
        assertEquals(true,dfa.Test(""));
        assertEquals(false,dfa.Test("01"));
    }

    @Test
    public void Should_recognise_all_strings_except_the_empty_set() throws Exception {
        State state0 = new State("q0");
        State state1 = new State("q1");
        HashSet<State> states = new HashSet<>(Arrays.asList(state0,state1));
        String alphabet0 = "0";
       String alphabet1 = "1";
        HashSet<String> alphabets = new HashSet<>(Arrays.asList(alphabet0,alphabet1));

        Transition transition = new Transition();
        transition.Add(state0,alphabet0,state1);
        transition.Add(state0,alphabet1,state1);
        transition.Add(state1,alphabet0,state1);
        transition.Add(state1,alphabet1,state1);

        State initialState = state0;
        HashSet<State> finalStates = new HashSet<>(Arrays.asList(state1));
        Tuple tuple = new Tuple(states,alphabets,transition,initialState,finalStates);
        DFA dfa = new DFA(tuple);
        assertEquals(false,dfa.Test(""));
        assertEquals(true,dfa.Test("10110"));
        assertEquals(true,dfa.Test("1010"));
        assertEquals(true,dfa.Test("1"));
    }

    @Test
    public void Should_recognise_strings_starting_with_110() throws Exception {
        State state0 = new State("q0");
        State state1 = new State("q1");
        State state2 = new State("q2");
        State state3 = new State("q3");
        State state4 = new State("q4");
        HashSet<State> states = new HashSet<>(Arrays.asList(state0,state1));
        String alphabet0 = "0";
       String alphabet1 = "1";
        HashSet<String> alphabets = new HashSet<>(Arrays.asList(alphabet0,alphabet1));

        Transition transition = new Transition();
        transition.Add(state0,alphabet0,state1);
        transition.Add(state0,alphabet1,state2);
        transition.Add(state1,alphabet0,state1);
        transition.Add(state1,alphabet1,state1);
        transition.Add(state2,alphabet0,state1);
        transition.Add(state2,alphabet1,state3);
        transition.Add(state3,alphabet0,state4);
        transition.Add(state3,alphabet1,state1);
        transition.Add(state4,alphabet0,state4);
        transition.Add(state4,alphabet1,state4);

        State initialState = state0;
        HashSet<State> finalStates = new HashSet<>(Arrays.asList(state4));
        Tuple tuple = new Tuple(states,alphabets,transition,initialState,finalStates);
        DFA dfa = new DFA(tuple);
        assertEquals(true,dfa.Test("11000010"));
        assertEquals(false,dfa.Test("0110"));
    }

    @Test
    public void Should_recognise_strings_containing_the_pattern_100_at_least_twice() throws Exception {
        State state0 = new State("q0");
        State state1 = new State("q1");
        State state2 = new State("q2");
        State state3 = new State("q3");
        State state4 = new State("q4");
        State state5 = new State("q5");
        State state6 = new State("q6");

        HashSet<State> states = new HashSet<>(Arrays.asList(state0,state1));
        String alphabet0 = "0";
        String alphabet1 = "1";
        HashSet<String> alphabets = new HashSet<>(Arrays.asList(alphabet0,alphabet1));

        Transition transition = new Transition();
        transition.Add(state0,alphabet0,state0);
        transition.Add(state0,alphabet1,state1);
        transition.Add(state1,alphabet0,state2);
        transition.Add(state1,alphabet1,state1);
        transition.Add(state2,alphabet0,state3);
        transition.Add(state2,alphabet1,state1);
        transition.Add(state3,alphabet0,state3);
        transition.Add(state3,alphabet1,state4);
        transition.Add(state4,alphabet0,state5);
        transition.Add(state4,alphabet1,state4);
        transition.Add(state5,alphabet0,state6);
        transition.Add(state5,alphabet1,state4);
        transition.Add(state6,alphabet0,state6);
        transition.Add(state6,alphabet1,state6);

        State initialState = state0;
        HashSet<State> finalStates = new HashSet<>(Arrays.asList(state6));
        Tuple tuple = new Tuple(states,alphabets,transition,initialState,finalStates);
        DFA dfa = new DFA(tuple);
        assertEquals(true,dfa.Test("0100100"));
        assertEquals(false,dfa.Test("010010"));
    }
}
