package com.ase.ex;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Asigurare implements Parcelable, Serializable {
    private Integer idAsigurare;
    private String titular;
    private double costPolita;
    private String tipAsigurare;
    private Boolean esteActivata;

    protected Asigurare(Parcel in) {
        if (in.readByte() == 0) {
            idAsigurare = null;
        } else {
            idAsigurare = in.readInt();
        }
        titular = in.readString();
        costPolita = in.readDouble();
        tipAsigurare = in.readString();
        byte tmpEsteActivata = in.readByte();
        esteActivata = tmpEsteActivata == 0 ? null : tmpEsteActivata == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (idAsigurare == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idAsigurare);
        }
        dest.writeString(titular);
        dest.writeDouble(costPolita);
        dest.writeString(tipAsigurare);
        dest.writeByte((byte) (esteActivata == null ? 0 : esteActivata ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Asigurare> CREATOR = new Creator<Asigurare>() {
        @Override
        public Asigurare createFromParcel(Parcel in) {
            return new Asigurare(in);
        }

        @Override
        public Asigurare[] newArray(int size) {
            return new Asigurare[size];
        }
    };

    @Override
    public String toString() {
        return "Asigurare{" +
                "idAsigurare=" + idAsigurare +
                ", titular='" + titular + '\'' +
                ", costPolita=" + costPolita +
                ", tipAsigurare='" + tipAsigurare + '\'' +
                ", esteActivata=" + esteActivata +
                '}';
    }

    public Integer getIdAsigurare() {
        return idAsigurare;
    }

    public void setIdAsigurare(Integer idAsigurare) {
        this.idAsigurare = idAsigurare;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getCostPolita() {
        return costPolita;
    }

    public void setCostPolita(double costPolita) {
        this.costPolita = costPolita;
    }

    public String getTipAsigurare() {
        return tipAsigurare;
    }

    public void setTipAsigurare(String tipAsigurare) {
        this.tipAsigurare = tipAsigurare;
    }

    public Boolean getEsteActivata() {
        return esteActivata;
    }

    public void setEsteActivata(Boolean esteActivata) {
        this.esteActivata = esteActivata;
    }

    public Asigurare(Integer idAsigurare, String titular, double costPolita, String tipAsigurare, Boolean esteActivata) {
        this.idAsigurare = idAsigurare;
        this.titular = titular;
        this.costPolita = costPolita;
        this.tipAsigurare = tipAsigurare;
        this.esteActivata = esteActivata;
    }
}
