package elements;

public class TMilk {

	String sDate;
	float cMilkQty,bMilkQty,totalQty;
	
	TMilk(){}

	public TMilk(String sDate, float cMilkQty, float bMilkQty, float totalQty) {
		super();
		this.sDate = sDate;
		this.cMilkQty = cMilkQty;
		this.bMilkQty = bMilkQty;
		this.totalQty = totalQty;
	}

	public String getSDate() {
		return sDate;
	}

	public void setSDate(String sDate) {
		this.sDate = sDate;
	}

	public float getCMilkQty() {
		return cMilkQty;
	}

	public void setCMilkQty(float cMilkQty) {
		this.cMilkQty = cMilkQty;
	}

	public float getBMilkQty() {
		return bMilkQty;
	}

	public void setBMilkQty(float bMilkQty) {
		this.bMilkQty = bMilkQty;
	}

	public float getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(float totalQty) {
		this.totalQty = totalQty;
	}
	
	
}
