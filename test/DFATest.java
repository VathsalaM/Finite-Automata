import org.junit.Test;

import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class DFATest {

  @Test
  public void Should_recognise_alphabets_only_from_alphabet_set() throws Exception {
    State state0 = new State("q0");
    HashSet<State> states = new HashSet<>(asList(state0));
    String alphabet0 = "0";
    String alphabet1 = "1";
    HashSet<String> alphabets = new HashSet<>(asList(alphabet0, alphabet1));
    Transition transition = new Transition();
    transition.Add(state0, alphabet0, state0);
    transition.Add(state0, alphabet1, state0);
    HashSet<State> finalStates = new HashSet<>(asList(state0));
    DFA dfa = new DFA(states, alphabets, transition, state0, finalStates);
    assertEquals(true, dfa.Verify("10"));
    assertEquals(false, dfa.Verify("1a0"));
  }

  @Test
  public void Should_recognise_all_strings_that_begin_with_1_and_contain_the_string_001() throws Exception {
    State state0 = new State("q0");
    State state1 = new State("q1");
    State state2 = new State("q2");
    State state3 = new State("q3");
    State state4 = new State("q4");
    State state5 = new State("q5");
    HashSet<State> states = new HashSet<>(asList(state0, state1, state2, state3, state4, state5));
    String alphabet0 = "0";
    String alphabet1 = "1";
    HashSet<String> alphabets = new HashSet<>(asList(alphabet0, alphabet1));

    Transition transition = new Transition();
    transition.Add(state0, alphabet0, state1);
    transition.Add(state0, alphabet1, state2);
    transition.Add(state1, alphabet0, state1);
    transition.Add(state1, alphabet1, state1);
    transition.Add(state2, alphabet0, state3);
    transition.Add(state2, alphabet1, state2);
    transition.Add(state3, alphabet0, state4);
    transition.Add(state3, alphabet1, state2);
    transition.Add(state4, alphabet0, state4);
    transition.Add(state4, alphabet1, state5);
    transition.Add(state5, alphabet0, state5);
    transition.Add(state5, alphabet1, state5);

    HashSet<State> finalStates = new HashSet<>(asList(state5));

    DFA dfa = new DFA(states, alphabets, transition, state0, finalStates);
    assertEquals(true, dfa.Verify("1001"));
    assertEquals(true, dfa.Verify("100101001"));
    assertEquals(false, dfa.Verify("10000"));
    assertEquals(false, dfa.Verify("000001"));
  }

  @Test
  public void Should_recognise_all_strings_whose_length_is_greater_than_three_and_has_1_as_the_third_alphabet_in_the_sequence() throws Exception {
    State state0 = new State("q0");
    State state1 = new State("q1");
    State state2 = new State("q2");
    State state3 = new State("q3");
    State state4 = new State("q4");
    State state5 = new State("q5");
    HashSet<State> states = new HashSet<>(asList(state0, state1, state2, state3, state4, state5));

    String alphabet0 = "0";
    String alphabet1 = "1";
    HashSet<String> alphabets = new HashSet<>(asList(alphabet0, alphabet1));

    Transition transition = new Transition();
    transition.Add(state0, alphabet0, state1);
    transition.Add(state0, alphabet1, state1);
    transition.Add(state1, alphabet0, state2);
    transition.Add(state1, alphabet1, state2);
    transition.Add(state2, alphabet0, state3);
    transition.Add(state2, alphabet1, state4);
    transition.Add(state3, alphabet0, state3);
    transition.Add(state3, alphabet1, state3);
    transition.Add(state4, alphabet0, state5);
    transition.Add(state4, alphabet1, state5);
    transition.Add(state5, alphabet0, state5);
    transition.Add(state5, alphabet1, state5);

    HashSet<State> finalStates = new HashSet<>(asList(state5));

    DFA dfa = new DFA(states, alphabets, transition, state0, finalStates);
    assertEquals(true, dfa.Verify("1010"));
    assertEquals(false, dfa.Verify("101"));
    assertEquals(true, dfa.Verify("111110"));
  }

  @Test
  public void Should_recognise_all_strings_whose_decimal_representation_is_an_even_number() throws Exception {
    State state0 = new State("q0");
    State state1 = new State("q1");
    HashSet<State> states = new HashSet<>(asList(state0, state1));
    String alphabet0 = "0";
    String alphabet1 = "1";

    HashSet<String> alphabets = new HashSet<>(asList(alphabet0, alphabet1));
    Transition transition = new Transition();
    transition.Add(state0, alphabet1, state0);
    transition.Add(state0, alphabet0, state1);
    transition.Add(state1, alphabet1, state0);
    transition.Add(state1, alphabet0, state1);
    HashSet<State> finalStates = new HashSet<>(asList(state1));

    DFA dfa = new DFA(states, alphabets, transition, state0, finalStates);
    assertEquals(true, dfa.Verify("010"));
    assertEquals(false, dfa.Verify("1"));
  }

  @Test
  public void Should_recognise_all_strings_whose_decimal_representation_is_a_power_of_two() throws Exception {
    State state0 = new State("q0");
    State state1 = new State("q1");
    State state2 = new State("q2");
    HashSet<State> states = new HashSet<>(asList(state0, state1));
    String alphabet0 = "0";
    String alphabet1 = "1";
    HashSet<String> alphabets = new HashSet<>(asList(alphabet0, alphabet1));

    Transition transition = new Transition();
    transition.Add(state0, alphabet1, state1);
    transition.Add(state0, alphabet0, state0);
    transition.Add(state1, alphabet1, state2);
    transition.Add(state1, alphabet0, state1);
    transition.Add(state2, alphabet0, state2);
    transition.Add(state2, alphabet1, state2);

    HashSet<State> finalStates = new HashSet<>(asList(state1));
    DFA dfa = new DFA(states, alphabets, transition, state0, finalStates);
    assertEquals(true, dfa.Verify("10"));
    assertEquals(true, dfa.Verify("01"));
    assertEquals(false, dfa.Verify("011"));
  }

  @Test
  public void Should_recognise_all_strings_other_than_the_strings_11_and_111() throws Exception {
    State state0 = new State("q0");
    State state1 = new State("q1");
    State state2 = new State("q2");
    State state3 = new State("q3");
    State state4 = new State("q4");
    HashSet<State> states = new HashSet<>(asList(state0, state1, state2, state3, state4));

    String alphabet0 = "0";
    String alphabet1 = "1";
    HashSet<String> alphabets = new HashSet<>(asList(alphabet0, alphabet1));

    Transition transition = new Transition();
    transition.Add(state0, alphabet0, state4);
    transition.Add(state0, alphabet1, state1);
    transition.Add(state1, alphabet0, state0);
    transition.Add(state1, alphabet1, state2);
    transition.Add(state2, alphabet0, state4);
    transition.Add(state2, alphabet1, state3);
    transition.Add(state3, alphabet0, state4);
    transition.Add(state3, alphabet1, state4);
    transition.Add(state4, alphabet0, state4);
    transition.Add(state4, alphabet1, state4);

    HashSet<State> finalStates = new HashSet<>(asList(state1, state4));
    DFA dfa = new DFA(states, alphabets, transition, state0, finalStates);
    assertEquals(false, dfa.Verify("11"));
    assertEquals(false, dfa.Verify("111"));
    assertEquals(true, dfa.Verify("0111"));
    assertEquals(true, dfa.Verify("1"));
    assertEquals(true, dfa.Verify("1111"));
  }

  @Test
  public void Should_recognise_the_empty_set() throws Exception {
    State state0 = new State("q0");
    State state1 = new State("q1");
    HashSet<State> states = new HashSet<>(asList(state0, state1));
    String alphabet0 = "0";
    String alphabet1 = "1";
    HashSet<String> alphabets = new HashSet<>(asList(alphabet0, alphabet1));

    Transition transition = new Transition();
    transition.Add(state0, alphabet1, state1);
    transition.Add(state0, alphabet0, state1);
    transition.Add(state1, alphabet1, state1);
    transition.Add(state1, alphabet0, state1);

    HashSet<State> finalStates = new HashSet<>(asList(state0));
    DFA dfa = new DFA(states, alphabets, transition, state0, finalStates);
    assertEquals(true, dfa.Verify(""));
    assertEquals(false, dfa.Verify("01"));
  }

  @Test
  public void Should_recognise_all_strings_except_the_empty_set() throws Exception {
    State state0 = new State("q0");
    State state1 = new State("q1");
    HashSet<State> states = new HashSet<>(asList(state0, state1));
    String alphabet0 = "0";
    String alphabet1 = "1";
    HashSet<String> alphabets = new HashSet<>(asList(alphabet0, alphabet1));

    Transition transition = new Transition();
    transition.Add(state0, alphabet0, state1);
    transition.Add(state0, alphabet1, state1);
    transition.Add(state1, alphabet0, state1);
    transition.Add(state1, alphabet1, state1);

    HashSet<State> finalStates = new HashSet<>(asList(state1));
    DFA dfa = new DFA(states, alphabets, transition, state0, finalStates);
    assertEquals(false, dfa.Verify(""));
    assertEquals(true, dfa.Verify("10110"));
    assertEquals(true, dfa.Verify("1010"));
    assertEquals(true, dfa.Verify("1"));
  }

  @Test
  public void Should_recognise_strings_starting_with_110() throws Exception {
    State state0 = new State("q0");
    State state1 = new State("q1");
    State state2 = new State("q2");
    State state3 = new State("q3");
    State state4 = new State("q4");
    HashSet<State> states = new HashSet<>(asList(state0, state1, state2, state3, state4));

    String alphabet0 = "0";
    String alphabet1 = "1";
    HashSet<String> alphabets = new HashSet<>(asList(alphabet0, alphabet1));

    Transition transition = new Transition();
    transition.Add(state0, alphabet0, state1);
    transition.Add(state0, alphabet1, state2);
    transition.Add(state1, alphabet0, state1);
    transition.Add(state1, alphabet1, state1);
    transition.Add(state2, alphabet0, state1);
    transition.Add(state2, alphabet1, state3);
    transition.Add(state3, alphabet0, state4);
    transition.Add(state3, alphabet1, state1);
    transition.Add(state4, alphabet0, state4);
    transition.Add(state4, alphabet1, state4);

    HashSet<State> finalStates = new HashSet<>(asList(state4));
    DFA dfa = new DFA(states, alphabets, transition, state0, finalStates);
    assertEquals(true, dfa.Verify("11000010"));
    assertEquals(false, dfa.Verify("0110"));
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

    HashSet<State> states = new HashSet<>(asList(state0, state1, state2, state3, state4, state5, state6));

    String alphabet0 = "0";
    String alphabet1 = "1";
    HashSet<String> alphabets = new HashSet<>(asList(alphabet0, alphabet1));

    Transition transition = new Transition();
    transition.Add(state0, alphabet0, state0);
    transition.Add(state0, alphabet1, state1);
    transition.Add(state1, alphabet0, state2);
    transition.Add(state1, alphabet1, state1);
    transition.Add(state2, alphabet0, state3);
    transition.Add(state2, alphabet1, state1);
    transition.Add(state3, alphabet0, state3);
    transition.Add(state3, alphabet1, state4);
    transition.Add(state4, alphabet0, state5);
    transition.Add(state4, alphabet1, state4);
    transition.Add(state5, alphabet0, state6);
    transition.Add(state5, alphabet1, state4);
    transition.Add(state6, alphabet0, state6);
    transition.Add(state6, alphabet1, state6);

    HashSet<State> finalStates = new HashSet<>(asList(state6));
    DFA dfa = new DFA(states, alphabets, transition, state0, finalStates);
    assertEquals(true, dfa.Verify("0100100"));
    assertEquals(false, dfa.Verify("010010"));
  }
}
