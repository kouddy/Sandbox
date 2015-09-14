package composition;

public class ImportingClass {
  @Import(type = ExportingClass.class)
  public ExportingClass c;

  public ImportingClass() {
    AggregateCatalog c = new AggregateCatalog();
    c.catalogs.add(new ClassCatalog(ExportingClass.class));
    CompositionContainer container = new CompositionContainer(c);

    container.Compose(this);
  }
}
