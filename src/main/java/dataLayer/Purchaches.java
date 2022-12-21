package dataLayer;

import java.sql.*;
import java.time.LocalDateTime;

public class Purchaches implements Identifiable {
	private int id;
	private Timestamp timestamp;
	private String purchachesType;
	private int id_zakaznik;
	private int id_zamestnanec;
	private int id_platba;
	private int id_zbozi;
	private int count;

	private String composition;

	/*
	 * id, timestamp, purchachesType, id_zakaznik, id_zamestnanec, id_platba
	 */

	public Purchaches(int id, Timestamp timestamp, String purchachesType, int id_zakaznik, int id_zamestnanec,
			int id_platba, int id_zbozi, int count) {
		this.id = id;
		this.timestamp = timestamp;
		this.purchachesType = purchachesType;
		this.id_zakaznik = id_zakaznik;
		this.id_zamestnanec = id_zamestnanec;
		this.id_platba = id_platba;
		this.id_zbozi = id_zbozi;
		this.count = count;
	}

	public Purchaches(Timestamp timestamp, String purchachesType, int id_zakaznik, int id_zamestnanec, int id_platba,
			int id_zbozi, int count) {
		this(0, timestamp, purchachesType, id_zakaznik, id_zamestnanec, id_platba, id_zbozi, count);
	}

	public static Purchaches PurchachesNow(int id_zakaznik, int id_zamestnanec, int id_platba, int id_zbozi,
			int count) {
		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);
		return new Purchaches(0, timestamp, "v_obchode", id_zakaznik, id_zamestnanec, id_platba, id_zbozi, count);
	}

	public Purchaches(int id, Purchaches pu) {
		this(id, pu.timestamp, pu.purchachesType, pu.id_zakaznik, pu.id_zamestnanec, pu.id_platba, pu.id_zbozi,
				pu.count);
	}

	public int getId() {
		return this.id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public void setTimestampNow() {
		LocalDateTime now = LocalDateTime.now();
		this.timestamp = Timestamp.valueOf(now);
	}

	public String getPurchachesType() {
		return purchachesType;
	}

	public void setPurchachesType(String purchachesType) {
		this.purchachesType = purchachesType;
	}

	public int getId_zakaznik() {
		return id_zakaznik;
	}

	public void setId_zakaznik(int id_zakaznik) {
		this.id_zakaznik = id_zakaznik;
	}

	public int getId_zamestnanec() {
		return id_zamestnanec;
	}

	public void setId_zamestnanec(int id_zamestnanec) {
		this.id_zamestnanec = id_zamestnanec;
	}

	public int getId_platba() {
		return id_platba;
	}

	public void setId_platba(int id_platba) {
		this.id_platba = id_platba;
	}

	@Override
	public String toString() {
		return Integer.toString(this.id) + " " + Integer.toString(this.id_zakaznik) + " "
				+ Integer.toString(this.id_zamestnanec) + " " + Integer.toString(this.id_platba);
	}

	@Override
	public String toStringAll() {
		return Integer.toString(this.id) + " " + this.purchachesType + " " + Integer.toString(this.id_zakaznik) + " "
				+ Integer.toString(this.id_zamestnanec) + " " + Integer.toString(this.id_platba);
	}

	public int getId_zbozi() {
		return id_zbozi;
	}

	public void setId_zbozi(int id_zbozi) {
		this.id_zbozi = id_zbozi;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public void printComposition() {
		System.out.println(this.composition);
	}
}
