package elements;

public class TCustomers
{
	String cname,mobile,address,city,timings,date;
	float cow,buffalo;

	TCustomers(){}

	public TCustomers(String cname, String mobile, String address, String city, String timings, String date, float cow,
			float buffalo) {
		super();
		this.cname = cname;
		this.mobile = mobile;
		this.address = address;
		this.city = city;
		this.timings = timings;
		this.date = date;
		this.cow = cow;
		this.buffalo = buffalo;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTimings() {
		return timings;
	}

	public void setTimings(String timings) {
		this.timings = timings;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getCow() {
		return cow;
	}

	public void setCow(float cow) {
		this.cow = cow;
	}

	public float getBuffalo() {
		return buffalo;
	}

	public void setBuffalo(float buffalo) {
		this.buffalo = buffalo;
	}

	
}
