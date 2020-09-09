package com.rp4.fourzetaapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoriaParcelable implements Parcelable {

    @Expose
    @SerializedName("nome")
    private String nome;

    public CategoriaParcelable(String nome) {
        this.nome = nome;
    }

    protected CategoriaParcelable(Parcel in) {
        nome = in.readString();
    }

    public static final Creator<CategoriaParcelable> CREATOR = new Creator<CategoriaParcelable>() {
        @Override
        public CategoriaParcelable createFromParcel(Parcel in) {
            return new CategoriaParcelable(in);
        }

        @Override
        public CategoriaParcelable[] newArray(int size) {
            return new CategoriaParcelable[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nome);
    }
}
