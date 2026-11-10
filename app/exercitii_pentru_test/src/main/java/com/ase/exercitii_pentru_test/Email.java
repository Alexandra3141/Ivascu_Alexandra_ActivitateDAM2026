package com.ase.exercitii_pentru_test;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public class Email implements Serializable, Parcelable {
    public String From;
    public String To;
    public Integer Imagine;
    public Date DataTrimitere;
    public Boolean Citit;

    public Email(String from, String to, Integer imagine, Date dataTrimitere, Boolean citit) {
        From = from;
        To = to;
        Imagine = imagine;
        DataTrimitere = dataTrimitere;
        Citit = citit;
    }

    public Email() {
        this.From = "";
        this.To = "";
        this.Imagine = 0;
        this.DataTrimitere = new Date();
        this.Citit = false;
    }

    protected Email(Parcel in) {
        From = in.readString();
        To = in.readString();
        if (in.readByte() == 0) {
            Imagine = null;
        } else {
            Imagine = in.readInt();
        }
        long tmpDate = in.readLong();
        DataTrimitere = tmpDate != -1L ? new Date(tmpDate) : null;
        byte tmpCitit = in.readByte();
        Citit = tmpCitit == 0 ? null : tmpCitit == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(From);
        dest.writeString(To);
        if (Imagine == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(Imagine);
        }
        dest.writeLong(DataTrimitere != null ? DataTrimitere.getTime() : -1L);
        dest.writeByte((byte) (Citit == null ? 0 : Citit ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Email> CREATOR = new Creator<Email>() {
        @Override
        public Email createFromParcel(Parcel in) {
            return new Email(in);
        }

        @Override
        public Email[] newArray(int size) {
            return new Email[size];
        }
    };

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public Integer getImagine() {
        return Imagine;
    }

    public void setImagine(Integer imagine) {
        Imagine = imagine;
    }

    public Date getDataTrimitere() {
        return DataTrimitere;
    }

    public void setDataTrimitere(Date dataTrimitere) {
        DataTrimitere = dataTrimitere;
    }

    public Boolean getCitit() {
        return Citit;
    }

    public void setCitit(Boolean citit) {
        Citit = citit;
    }

    @Override
    public String toString() {
        return "Email{" +
                "From='" + From + '\'' +
                ", To='" + To + '\'' +
                ", Imagine=" + Imagine +
                ", DataTrimitere=" + DataTrimitere +
                ", Citit=" + Citit +
                '}';
    }
}
