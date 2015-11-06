package model;

/**
 * CCreated by adrien on 14/10/2015.
 */
public class Guide {
    public static final String[] fake_villes = {"Lille", "Lens", "London", "Paris", "Lille"};
    public static final String[] fake_noms = {"Adrien Chapelet", "Romain Braems", "Maxence Henneron", "Sofie Cha", "Léa Prtpl"};
    public static final String[] fake_types = {"DiscoX", "DiscoPop", "DiscoPool", "DiscoPop", "DiscoX"};
    /*public static final int[] capa = {12, 6, 2, 1, 8};
    public static final String[] cale = {};
    public static final String[] lang = {"FR-GB-ES", "FR-GB-AL", "FR-GB-AL", "FR", "FR-GB-ES"};
    public static final String[] circ = {"Découverte du Vieux Lille", "Visite de la place Cauchy", "Billfeld festival", "Maubeuge","Visite de Cormontaigne"};
    public static final double[] note = {4.5, 5, 4, 3.5, 5};*/

    public int id;
    public String ville;
    public String nom;
    public int capacite;
    public int[] avail;
    public String[] lang;
    public String[] circuits;
    public double note;

    public Guide (int id, String ville,String nom, int capacite,int[] avail,String[] lang, String[] circuits,double note) {
        this.id=id;
        this.ville=ville;
        this.nom=nom;
        this.capacite=capacite;
        this.avail=avail;
        this.lang=lang;
        this.circuits=circuits;
        this.note=note;
    }

}

