package model;

import java.util.ArrayList;

/**
 * Created by adrien on 19/10/15.
 */
public class PlaceData {

  public static String[] placeNameArray = {"Lens", "Lille", "Paris", "London"};

  public static ArrayList<Place> placeList() {
    ArrayList<Place> list = new ArrayList<>();
    for (int i = 0; i < placeNameArray.length; i++) {
      Place place = new Place();
      place.name = placeNameArray[i];
      place.imageName = placeNameArray[i].replaceAll("\\s+", "").toLowerCase();
      if (i == 2 || i == 5) {
        place.isFav = true;
      }
      list.add(place);
    }
    return (list);
  }

  public static Place getItem(String _id) {
    for (Place place : placeList()) {
      if (place.id.equals(_id)) {
        return place;
      }
    }
    return null;
  }
}
