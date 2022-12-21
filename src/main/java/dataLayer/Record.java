package dataLayer;

import java.io.Serializable;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class Record implements Identifiable, Serializable, Observable {

	private int idRecord;
	private String artist;
	private String recordCompany;
	private String title;
	private float price;
	private int shopMarge;
	private int count;
	private String carrierType;
	private String recordType;
	private String decription;
	private String genre;
	private int realeaseYear;
	private int reordLength;

	/*
	 * idRecord, artist, recordCompany, title, price, shopMarge, count, carrierType,
	 * recordType, decription, genre, realeaseYear, reordLength,
	 */

	public Record(String artist, String recordCompany, String title, float price, int shopMarge, int count,
			String carrierType, String recordType, String decription, String genre, int realeaseYear, int reordLength) {
		this(0, artist, recordCompany, title, price, shopMarge, count, carrierType, recordType, decription, genre,
				realeaseYear, reordLength);
	}

	public Record(int id, Record re) {
		this(id, re.artist, re.recordCompany, re.title, re.price, re.shopMarge, re.count, re.carrierType, re.recordType,
				re.decription, re.genre, re.realeaseYear, re.reordLength);
	}

	public Record(int idRecord, String artist, String recordCompany, String title, float price, int shopMarge,
			int count, String carrierType, String recordType, String decription, String genre, int realeaseYear,
			int reordLength) {
		this.idRecord = idRecord;
		this.artist = artist;
		this.recordCompany = recordCompany;
		this.title = title;
		this.price = price;
		this.shopMarge = shopMarge;
		this.count = count;
		this.carrierType = carrierType;
		this.recordType = recordType;
		this.decription = decription;
		this.genre = genre;
		this.realeaseYear = realeaseYear;
		this.reordLength = reordLength;
	}

	public int getId() {
		return this.idRecord;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getShopMarge() {
		return shopMarge;
	}

	public void setShopMarge(int shopMarge) {
		this.shopMarge = shopMarge;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCarrierType() {
		return carrierType;
	}

	public void setCarrierType(String carrierType) {
		this.carrierType = carrierType;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public int getRealeaseYear() {
		return realeaseYear;
	}

	public void setRealeaseYear(int realeaseYear) {
		this.realeaseYear = realeaseYear;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getReordLength() {
		return reordLength;
	}

	public void setReordLength(int reordLength) {
		this.reordLength = reordLength;
	}

	@Override
	public String toString() {
		return Integer.toString(this.idRecord) + " " + this.artist + " " + this.title + " " + Float.toString(this.price)
				+ " " + Integer.toString(this.count) + " " + this.carrierType + " " + this.recordType;
	}

	@Override
	public String toStringAll() {
		return Integer.toString(this.idRecord) + " " + this.artist + " " + this.recordCompany + " " + this.title + " "
				+ Float.toString(this.price) + " " + Integer.toString(this.shopMarge) + " "
				+ Integer.toString(this.count) + " " + this.carrierType + " " + this.recordType + " " + this.decription
				+ " " + this.genre + " " + Integer.toString(this.realeaseYear) + " "
				+ Integer.toString(this.reordLength);
	}

	@Override
	public void addListener(InvalidationListener listener) {

	}

	@Override
	public void removeListener(InvalidationListener listener) {

	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getRecordCompany() {
		return recordCompany;
	}

	public void setRecordCompany(String recordCompanyX) {
		this.recordCompany = recordCompanyX;
	}
}
