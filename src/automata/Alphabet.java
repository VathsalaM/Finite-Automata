package automata;

class Alphabet {

  private final String value;

  Alphabet(String alphabet) {
    this.value = alphabet;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Alphabet && this.value.equals(((Alphabet) obj).value);
  }

  @Override
  public int hashCode() {
    return this.value.hashCode();
  }

  @Override
  public String toString() {
    return this.value;
  }
}
