package dfa;

public class State {
  private final String name;

  public State(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj instanceof State){
      return this.name.equals(((State) obj).name);
    }
    return false;
  }
}
