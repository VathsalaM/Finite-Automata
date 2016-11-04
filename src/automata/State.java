package automata;

class State {
  private final String name;

  State(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof State && this.name.equals(((State) obj).name);
  }

  @Override
  public int hashCode() {
    return this.name.hashCode();
  }

  @Override
  public String toString() {
    return this.name;
  }
}
