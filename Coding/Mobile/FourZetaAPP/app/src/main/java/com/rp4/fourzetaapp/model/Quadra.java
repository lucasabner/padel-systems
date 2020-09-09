package com.rp4.fourzetaapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Quadra implements Serializable{
	@Expose
	@SerializedName("id")
	private int id;
	@Expose
	@SerializedName("num")
	private int num;

	public Quadra() {

	}

	public Quadra(int num) {
		this.num = num;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String toString() {
		return "Quadra " + num;
	}

}
