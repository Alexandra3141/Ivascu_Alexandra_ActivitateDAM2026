package com.ase.seminar1;

public class Magazin {
    public int IdMagazin;
    public String Denumire;
    public String Adresa;
    public int NumarRaioane;

    public Magazin(int idMagazin, String denumire, String adresa, int numarRaioane) {
        IdMagazin = idMagazin;
        Denumire = denumire;
        Adresa = adresa;
        NumarRaioane = numarRaioane;
    }

    public int getIdMagazin() {
        return IdMagazin;
    }

    public void setIdMagazin(int idMagazin) {
        IdMagazin = idMagazin;
    }

    public String getDenumire() {
        return Denumire;
    }

    public void setDenumire(String denumire) {
        Denumire = denumire;
    }

    public String getAdresa() {
        return Adresa;
    }

    public void setAdresa(String adresa) {
        Adresa = adresa;
    }

    public int getNumarRaioane() {
        return NumarRaioane;
    }

    public void setNumarRaioane(int numarRaioane) {
        NumarRaioane = numarRaioane;
    }

    @Override
    public String toString() {
        return "Magazin{" +
                "IdMagazin=" + IdMagazin +
                ", Denumire='" + Denumire + '\'' +
                ", Adresa='" + Adresa + '\'' +
                ", NumarRaioane=" + NumarRaioane +
                '}';
    }
}
