package com.ase.seminar6;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Automobil implements Parcelable, Serializable {
    private String marca;
    private Integer anFabricatie;
    private double pret;
    private String combustibil;
    private Boolean esteInGarantie;

    public Automobil(String marca, Integer anFabricatie, double pret, String combustibil, Boolean esteInGarantie) {
        this.marca = marca;
        this.anFabricatie = anFabricatie;
        this.pret = pret;
        this.combustibil = combustibil;
        this.esteInGarantie = esteInGarantie;
    }

    protected Automobil(Parcel in) {
        marca = in.readString();
        if (in.readByte() == 0) {
            anFabricatie = null;
        } else {
            anFabricatie = in.readInt();
        }
        pret = in.readDouble();
        combustibil = in.readString();
        byte tmpEsteInGarantie = in.readByte();
        esteInGarantie = tmpEsteInGarantie == 0 ? null : tmpEsteInGarantie == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(marca);
        if (anFabricatie == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(anFabricatie);
        }
        dest.writeDouble(pret);
        dest.writeString(combustibil);
        dest.writeByte((byte) (esteInGarantie == null ? 0 : esteInGarantie ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Automobil> CREATOR = new Creator<Automobil>() {
        @Override
        public Automobil createFromParcel(Parcel in) {
            return new Automobil(in);
        }

        @Override
        public Automobil[] newArray(int size) {
            return new Automobil[size];
        }
    };

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getAnFabricatie() {
        return anFabricatie;
    }

    public void setAnFabricatie(Integer anFabricatie) {
        this.anFabricatie = anFabricatie;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public String getCombustibil() {
        return combustibil;
    }

    public void setCombustibil(String combustibil) {
        this.combustibil = combustibil;
    }

    public Boolean getEsteInGarantie() {
        return esteInGarantie;
    }

    public void setEsteInGarantie(Boolean esteInGarantie) {
        this.esteInGarantie = esteInGarantie;
    }

    @Override
    public String toString() {
        return "Automobil{" +
                "marca='" + marca + '\'' +
                ", anFabricatie=" + anFabricatie +
                ", pret=" + pret +
                ", combustibil='" + combustibil + '\'' +
                ", esteInGarantie=" + esteInGarantie +
                '}';
    }
}
