package composition;

public class ClassCatalog implements Catalog {

  Class<?> c;

  public ClassCatalog(Class<?> c) {
    this.c = c;
  }
}
