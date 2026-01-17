package Resourse;

import Pojo.AddPlace;
import Pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class Testdata_addPlace {
    public AddPlace addPlacePayload() {
        AddPlace ap=new AddPlace();
        ap.setAccuracy(50);
        ap.setName("Pune");
        ap.setPhone_number("9999999999");
        ap.setAddress("123 main street");
        List<String> types=new ArrayList<String>();
        types.add("Mall");
        types.add("Mart");
        ap.setTypes(types);
        ap.setWebsite("www.abc.com");
        ap.setLanguage("Kannada");
        Location loc=new Location();
        loc.setLat(3.3333);
        loc.setLng(8.8);
        ap.setLocation(loc);
        return ap;
    }
}
