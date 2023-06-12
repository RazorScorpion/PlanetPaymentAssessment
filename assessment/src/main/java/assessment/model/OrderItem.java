package assessment.model;

public class OrderItem {
	private long id;
	private String email;
	private String phone_number;
	private double weight;
	
	public OrderItem(long id, String email, String phone_number, double weight) {
		this.id = id;
		this.email = email;
		this.phone_number = phone_number;
		this.weight = weight;
	}
	
	public long getId() {
		return this.id;
	}
	
	public String getEmail() {
		return this.email.strip();
	}
	
	public String getPhoneNumber() {
		return this.phone_number.strip();
	}
	
	public double getWeigth() {
		return this.weight;
	}
	
	@Override
	public String toString() {
		return "{ \"id\": " + this.id + ", \"email\": \"" + this.email + "\", \"mobile\": \"" + this.phone_number + "\", \"weight\": " + this.weight + "}";
	}
}
