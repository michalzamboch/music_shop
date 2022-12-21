package dataLayer;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Payment implements Identifiable {
	private int id;
	private float amount;
	private Timestamp timestamp;
	private boolean saveInfo;
	private CreditCard creditCard;

	/*---------------------------------------------------------------------------*/

	public Payment(int id, float amount, Timestamp timestamp, boolean saveInfo, CreditCard creditCard) {
		this.id = id;
		this.amount = amount;
		this.timestamp = timestamp;
		this.saveInfo = saveInfo;
		this.creditCard = creditCard;
	}

	public Payment(int id, Payment pa) {
		this(pa.amount, pa.timestamp, pa.saveInfo, pa.creditCard);
		this.id = id;
	}

	public Payment(Payment pa) {
		this(pa.id, pa.amount, pa.timestamp, pa.saveInfo, pa.creditCard);
	}

	public Payment(float amount, Timestamp timestamp, boolean saveInfo, CreditCard creditCard) {
		this.amount = amount;
		this.timestamp = timestamp;
		this.saveInfo = saveInfo;
		this.creditCard = creditCard;
	}

	public static Payment paymentNow(float amount) {
		LocalDateTime now = LocalDateTime.now();
		Timestamp time = Timestamp.valueOf(now);

		return new Payment(amount, time, false, CreditCard.genericCard());
	}

	/*----------------------------------------------------------*/

	public static class CreditCard {
		private int cardNumber;
		private Timestamp validationDate;
		private int securityCode;

		public CreditCard(int cardNumber, Timestamp validationDate, int securityCode) {
			this.cardNumber = cardNumber;
			this.validationDate = validationDate;
			this.securityCode = securityCode;
		}

		public static CreditCard genericCard() {
			LocalDateTime now = LocalDateTime.now();
			Timestamp time = Timestamp.valueOf(now);

			return new CreditCard(123456789, time, 123);
		}

		public int getCardNumber() {
			return cardNumber;
		}

		public void setCardNumber(int cardNumber) {
			this.cardNumber = cardNumber;
		}

		public Timestamp getValidationDate() {
			return validationDate;
		}

		public void setValidationDate(Timestamp validationDate) {
			this.validationDate = validationDate;
		}

		public String getValidationDateStr() {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
			String temp = sdf.format(this.validationDate);
			return temp;
		}

		public int getSecurityCode() {
			return securityCode;
		}

		public void setSecurityCode(int securityCode) {
			this.securityCode = securityCode;
		}

		public String toString() {
			String str = Integer.toString(this.cardNumber) + " - " + this.getValidationDateStr() + " - "
					+ Integer.toString(this.securityCode);

			return str;
		}
	}

	/*-------------------------------------------------*/

	public int getId() {
		return id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public void setCurrentTimestamp() {
		this.timestamp = new Timestamp(System.currentTimeMillis());
	}

	public String getTimestampStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH.mm dd.MM.yyyy");
		String temp = sdf.format(timestamp);
		return temp;
	}

	public boolean getSaveInfo() {
		return saveInfo;
	}

	public void setSaveInfo(boolean saveInfo) {
		this.saveInfo = saveInfo;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public void deleteCardInfo() {
		if (this.saveInfo == false) {
			this.creditCard = null;
		}
	}

	@Override
	public String toString() {
		String str = Integer.toString(this.id) + ". " + Float.toString(this.amount) + " " + this.getTimestampStr();

		return str;
	}

	@Override
	public String toStringAll() {
		String str = Integer.toString(this.id) + ". " + Float.toString(this.amount) + " " + this.getTimestampStr();

		if (saveInfo == true) {
			str += "\n" + this.creditCard.toString();
		}

		return str;
	}
}
