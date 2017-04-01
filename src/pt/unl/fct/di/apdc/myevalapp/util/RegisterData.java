package pt.unl.fct.di.apdc.myevalapp.util;

public class RegisterData {
	
	public String username;
	public String email;
	public String homePhone;
	public String mobilePhone;
	//Morada
	public String street;
	public String locationDescription;
	public String city;
	public String cp;
	
	public String nif;
	public String cc;
	public String password;
	public String confirmation;
	
	public RegisterData(String username,String email,String homePhone,String mobilePhone,String street,String locationDescription,String city,String cp,String nif,String cc,String password,String confirmation) {
		this.username = username;
		this.email = email;
		this.homePhone = homePhone;
		this.mobilePhone = mobilePhone;
		this.street = street;
		this.locationDescription = locationDescription;
		this.city = city;
		this.cp = cp;
		this.nif = nif;
		this.cc = cc;
		this.password = password;
		this.confirmation = confirmation;
	}
	
	private boolean validField(String value) {
		return value != null && !value.equals("");
	}

	public boolean validRegistration() {
		return validField(username) &&
			   validField(email) &&
			   validField(homePhone) &&
			   validField(mobilePhone) &&
			   validField(street) &&
			   validField(locationDescription) &&
			   validField(city) &&
			   validField(cp) &&
			   validField(nif) &&
			   validField(cc) &&
			   password.equals(confirmation) &&
			   email.contains("@");	
	}

}
