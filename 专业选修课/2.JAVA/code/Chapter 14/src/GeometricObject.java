//import java.text.SimpleDateFormat;

import java.util.Date;

public class GeometricObject<GeometricObject> implements Comparable<GeometricObject> {
    public static Comparable max(Comparable object1, Comparable object2) {
        if (object1.compareTo(object2) > 0)
            return object1;
        else
            return object2;
    }

    @Override
    public int compareTo(GeometricObject o) {
        return 0;
    }

}

