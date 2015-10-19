package model;

/**
 * CCreated by adrien on 14/10/2015.
 */
public class Guide {
    public String name;

    public static final String[] noms = {"Adrien Chapelet", "Romain Braems", "Maxence Henneron", "Sofie Cha", "Léa Prtpl"};
    public static final String[] types = {"DiscoX", "DiscoPop", "DiscoPool", "DiscoPop", "DiscoX"};

    Guide(String name){
        this.name=name;
    }
}

