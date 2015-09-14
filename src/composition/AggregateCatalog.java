package composition;

import java.util.ArrayList;
import java.util.List;

public class AggregateCatalog implements Catalog {
  public List<Catalog> catalogs;

  public AggregateCatalog() {
    catalogs = new ArrayList<Catalog>();
  }
}
