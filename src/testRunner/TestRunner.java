package testRunner;


import automata.Builder;
import automata.FiniteAutomata;
import automata.NFA;
import automata.Parser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class TestRunner {

  private boolean runPassCases(FiniteAutomata fa, JSONArray pass_cases) throws JSONException {
    for (int i = 0; i < pass_cases.length(); i++) {
      if (!fa.verify(pass_cases.get(i).toString())) {
        return false;
      }
    }
    return true;
  }

  private boolean runFailCases(FiniteAutomata fa, JSONArray fail_cases) throws JSONException {
    for (int i = 0; i < fail_cases.length(); i++) {
      if (fa.verify(fail_cases.get(i).toString())) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) throws IOException, JSONException {

    ArrayList<JSONObject> jsonObjects = new Parser().parse(args[0]);

    int totalPassCount = 0, totalFailCount = 0, count = 0;
    for (JSONObject rootObject : jsonObjects) {
      JSONArray pass_cases = rootObject.getJSONArray("pass-cases");
      JSONArray fail_cases = rootObject.getJSONArray("fail-cases");
      String type = rootObject.get("type").toString();
      String name = rootObject.get("name").toString();
      JSONObject tuple = rootObject.getJSONObject("tuple");

      if (args.length > 1 && !args[1].equals(type) && !args[1].equals("all")) {
        continue;
      }
      if (args.length > 2 && !args[2].equals("-i") && !args[2].equals(name)) {
        continue;
      }

      Builder builder = Builder.New(tuple, type);

      FiniteAutomata fa = null;
      if (type.equals("dfa")) {
        fa = builder.buildDFA();
      } else if (type.equals("nfa")) {
        fa = builder.buildNFA();
      } else {
        fa = ((NFA) builder.buildNFA()).toDFA();
      }


      TestRunner tr = new TestRunner();
      boolean result = false;
      try {
        result = tr.runPassCases(fa, pass_cases) && tr.runFailCases(fa, fail_cases);

      } catch (Error e) {
        System.out.println("\tError: "+e.toString()+"\n");
        result = false;
      }
      String message = (++count) + ". " + name + ": ";
      if (result) {
        totalPassCount++;
        System.out.println(message + "pass");
      } else {
        totalFailCount++;
        System.out.println(message + "fail");
      }
      if (args[args.length - 1].equals("-i")) {
        System.out.println("\ttest_type:  " + type);
        System.out.println("\tpass_cases: " + pass_cases);
        System.out.println("\tfail_cases: " + fail_cases);
      }
    }
    System.out.println("\nTotal tests passing: " + totalPassCount);
    System.out.println("Total tests failing: " + totalFailCount);
  }
}