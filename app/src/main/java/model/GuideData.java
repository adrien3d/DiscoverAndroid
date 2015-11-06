package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrien on 22/10/2015.
 */
public class GuideData {
    public List<Guide> listeGuides= new ArrayList<Guide>();

    public void addGuide(Guide guide) {
        listeGuides.add(guide);
    }

    public void modifListe(int pos, Guide guide) {
        listeGuides.set(pos, guide);
    }

    public Guide getGuide(int pos) {
        return listeGuides.get(pos);
    }

    public List<Guide> getListeGuides() {
        return listeGuides;
    }

    public void setListeGuides(List<Guide> listeGuides) {
        this.listeGuides = listeGuides;
    }

    public boolean isEmpty() {
        return listeGuides.isEmpty();
    }

    public int size() {
        return listeGuides.size();
    }
}
