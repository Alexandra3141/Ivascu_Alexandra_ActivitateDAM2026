package com.ase.lucru_individual;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Apartament implements Parcelable, Serializable {
    private String Adresa;
    private double Suprafata;
    private double Pret;
    private String zona;
    private Boolean esteDecomandat;

    public Apartament(String adresa, double suprafata, double pret, String zona, Boolean esteDecomandat) {
        Adresa = adresa;
        Suprafata = suprafata;
        Pret = pret;
        this.zona = zona;
        this.esteDecomandat = esteDecomandat;
    }

    protected Apartament(Parcel in) {
        Adresa = in.readString();
        Suprafata = in.readDouble();
        Pret = in.readDouble();
        zona = in.readString();
        byte tmpEsteDecomandat = in.readByte();
        esteDecomandat = tmpEsteDecomandat == 0 ? null : tmpEsteDecomandat == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Adresa);
        dest.writeDouble(Suprafata);
        dest.writeDouble(Pret);
        dest.writeString(zona);
        dest.writeByte((byte) (esteDecomandat == null ? 0 : esteDecomandat ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Apartament> CREATOR = new Creator<Apartament>() {
        @Override
        public Apartament createFromParcel(Parcel in) {
            return new Apartament(in);
        }

        @Override
        public Apartament[] newArray(int size) {
            return new Apartament[size];
        }
    };

    public String getAdresa() {
        return Adresa;
    }

    public void setAdresa(String adresa) {
        Adresa = adresa;
    }

    public double getSuprafata() {
        return Suprafata;
    }

    public void setSuprafata(double suprafata) {
        Suprafata = suprafata;
    }

    public double getPret() {
        return Pret;
    }

    public void setPret(double pret) {
        Pret = pret;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public Boolean getEsteDecomandat() {
        return esteDecomandat;
    }

    public void setEsteDecomandat(Boolean esteDecomandat) {
        this.esteDecomandat = esteDecomandat;
    }

    @Override
    public String toString() {
        return "Apartament{" +
                "Adresa='" + Adresa + '\'' +
                ", Suprafata=" + Suprafata +
                ", Pret=" + Pret +
                ", zona='" + zona + '\'' +
                ", esteDecomandat=" + esteDecomandat +
                '}';
    }
}
