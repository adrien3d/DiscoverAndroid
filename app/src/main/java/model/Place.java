package model;

import android.content.Context;

/**
 * Created by adrien on 19/10/15.
 */
public class Place {

  public String id;
  public String name;
  public String imageName;
  public boolean isFav;

  public int getImageResourceId(Context context) {
    return context.getResources().getIdentifier(this.imageName, "drawable", context.getPackageName());
  }
}
