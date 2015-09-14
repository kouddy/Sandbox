package composition;

import java.lang.reflect.Field;
import java.util.Hashtable;

public class CompositionContainer {
  Catalog catalog;
  Hashtable<String, Object> initializedObject;

  public CompositionContainer(Catalog c) {
    this.catalog = c;
    initializedObject = new Hashtable<String, Object>();
  }

  public void Compose(Object o) {
    DiscoverAvailableParts();
    Field[] fields = o.getClass().getDeclaredFields();
    for (Field m : fields) {
      if (m.isAnnotationPresent(Import.class)) {
        try {
          m.set(o, initializedObject.get(ExportingClass.class.getName()));
        } catch (IllegalArgumentException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }

  }

  public void DiscoverAvailableParts() {
    try {
      Object o = Class.forName(ExportingClass.class.getName()).newInstance();
      initializedObject.put(ExportingClass.class.getName(), o);
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
