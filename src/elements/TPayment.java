package elements;

public class TPayment {

	String StartDate,EndDate,cname,mobile;
	float cQtyTot,bQtyTot,Camt,Bamt,Total;
	
	TPayment(){}

	public TPayment(String startDate, String endDate, String cname, String mobile, float cQtyTot, float bQtyTot,
			float camt, float bamt, float total)
	{
		super();
		StartDate = startDate;
		EndDate = endDate;
		this.cname = cname;
		this.mobile = mobile;
		this.cQtyTot = cQtyTot;
		this.bQtyTot = bQtyTot;
		//System.out.println(cQtyTot+"    "+bQtyTot);
		Camt = camt;
		Bamt = bamt;
		Total = total;
	}

	public String getStartDate() {
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = startDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
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

	public float getCQtyTot() {
		return cQtyTot;
	}

	public void setCQtyTot(float cQtyTot) {
		this.cQtyTot = cQtyTot;
	}

	public float getBQtyTot() {
		return bQtyTot;
	}

	public void setBQtyTot(float bQtyTot) {
		this.bQtyTot = bQtyTot;
	}

	public float getCamt() {
		return Camt;
	}

	public void setCamt(float camt) {
		Camt = camt;
	}

	public float getBamt() {
		return Bamt;
	}

	public void setBamt(float bamt) {
		Bamt = bamt;
	}

	public float getTotal() {
		return Total;
	}

	public void setTotal(float total) {
		Total = total;
	}
	
	
}
