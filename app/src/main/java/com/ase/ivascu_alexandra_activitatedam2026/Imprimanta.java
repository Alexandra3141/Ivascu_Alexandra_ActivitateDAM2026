package com.ase.ivascu_alexandra_activitatedam2026;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Imprimanta implements Serializable, Parcelable {

    private String producator;
    private double pret;
    private boolean esteColor;
    private String tipConexiune;
    private String tehnologie;
    private float rating;
    private boolean areGarantieExtinsa;

    public Imprimanta() {
        this.producator = "Necunoscut";
        this.pret = 0.0;
        this.esteColor = false;
        this.tipConexiune = "USB";
        this.tehnologie = "Laser";
        this.rating = 3.0f;
        this.areGarantieExtinsa = false;
    }

    public Imprimanta(String producator, double pret, boolean esteColor,
                      String tipConexiune, String tehnologie, float rating,
                      boolean areGarantieExtinsa) {
        this.producator = producator;
        this.pret = pret;
        this.esteColor = esteColor;
        this.tipConexiune = tipConexiune;
        this.tehnologie = tehnologie;
        this.rating = rating;
        this.areGarantieExtinsa = areGarantieExtinsa;
    }

    protected Imprimanta(Parcel in) {
        producator = in.readString();
        pret = in.readDouble();
        esteColor = in.readByte() != 0;
        tipConexiune = in.readString();
        tehnologie = in.readString();
        rating = in.readFloat();
        areGarantieExtinsa = in.readByte() != 0;
    }

    public static final Creator<Imprimanta> CREATOR = new Creator<Imprimanta>() {
        @Override
        public Imprimanta createFromParcel(Parcel in) {
            return new Imprimanta(in);
        }

        @Override
        public Imprimanta[] newArray(int size) {
            return new Imprimanta[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(producator);
        dest.writeDouble(pret);
        dest.writeByte((byte) (esteColor ? 1 : 0));
        dest.writeString(tipConexiune);
        dest.writeString(tehnologie);
        dest.writeFloat(rating);
        dest.writeByte((byte) (areGarantieExtinsa ? 1 : 0));
    }

    public String getProducator() {
        return producator;
    }

    public void setProducator(String producator) {
        this.producator = producator;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public boolean isEsteColor() {
        return esteColor;
    }

    public void setEsteColor(boolean esteColor) {
        this.esteColor = esteColor;
    }

    public String getTipConexiune() {
        return tipConexiune;
    }

    public void setTipConexiune(String tipConexiune) {
        this.tipConexiune = tipConexiune;
    }

    public String getTehnologie() {
        return tehnologie;
    }

    public void setTehnologie(String tehnologie) {
        this.tehnologie = tehnologie;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isAreGarantieExtinsa() {
        return areGarantieExtinsa;
    }

    public void setAreGarantieExtinsa(boolean areGarantieExtinsa) {
        this.areGarantieExtinsa = areGarantieExtinsa;
    }

    @Override
    public String toString() {
        return producator + " (" + tehnologie + ") - " + pret + " RON\n" +
                "• Conexiune: " + tipConexiune +
                " | " + (esteColor ? "Color" : "Monocrom") +
                " | Rating: " + rating + "★" +
                " | Garanție: " + (areGarantieExtinsa ? "Da" : "Nu");
    }
}
