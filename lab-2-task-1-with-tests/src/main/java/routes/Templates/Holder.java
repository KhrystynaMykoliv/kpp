package routes.Templates;

public class Holder<T> {
  private T value;

  public T get() {
      return value;
  }

  public void set(T value) {
      this.value = value;
  }
}

