package com.epam.lab.entity;

public abstract class Aircraft {
	private String model;
	private String origin;
	private String type;
	private int seats;
	private boolean weapons;
	private int missiles;
	private boolean hasRadar;
	private double length;
	private double width;
	private double height;
	private double priceAmount;
	private String priceCurrency;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public boolean isWeapons() {
		return weapons;
	}

	public void setWeapons(boolean weapons) {
		this.weapons = weapons;
	}

	public int getMissiles() {
		return missiles;
	}

	public void setMissiles(int missiles) {
		this.missiles = missiles;
	}

	public boolean isHasRadar() {
		return hasRadar;
	}

	public void setHasRadar(boolean hasRadar) {
		this.hasRadar = hasRadar;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getAmount() {
		return priceAmount;
	}

	public void setAmount(double priceAmount) {
		this.priceAmount = priceAmount;
	}

	public String getCurrency() {
		return priceCurrency;
	}

	public void setCurrency(String priceCurrency) {
		this.priceCurrency = priceCurrency;
	}

	@Override
	public String toString() {
		return "Aircraft{" + "model='" + model + '\'' + ", origin='" + origin + '\'' + ", type='" + type + '\''
				+ ", seats=" + seats + ", weapons=" + weapons + ", missiles=" + missiles + ", hasRadar=" + hasRadar
				+ ", length=" + length + ", width=" + width + ", height=" + height + ", priceAmount=" + priceAmount
				+ ", priceCurrency='" + priceCurrency + '\'' + '}';
	}
}
