package paper;

import java.util.*;

public class CollectionsAndViews {

    public static void main(String[] args) {
        var map = new HashMap<String, String>();
        map.put("Peter", "Schweizer");
        map.put("Hansa", "Müller");
        map.put("Sarah", "Meier");

        var set = map.keySet();

        /* HIER */

        map.put("Peter", "Schmid");
        System.out.println(map);


        map.keySet().add("Ueli");
        System.out.println(map);


        set.remove("Peter");
        System.out.println(set);
        System.out.println(map.values());


        var list = List.of(map.get("Hansa"));
        list.add("Müller");
        System.out.println(list);


//        Collection<String> c = map;
//        System.out.println(c);


        map.remove("Sarah");
        map.remove("Hansa");
        var i = map.entrySet().iterator();
        System.out.println(i.next().getValue());
    }
}
