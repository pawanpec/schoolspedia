package com.spedia.model;

/**
 * Model object which defines all 
 * subscription related aspects
 */
public class Subscription {
	
	/**
	 * Id of the device from which the user has subscribed
	 */
	private String di;
	
	/**
	 * Type of the device for which the user has subscribed to will be used to send request to apple or android server
	 * 
	 */
	private String dt;
	
	/**
	 * Device Name
	 */
	private String dn;
	
	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	/**
	 * Login Id of the candidate
	 */
	private String li;
	
	/**
	 * Updation time
	 */
	private long ut;
	
	/**
	 * Creation time
	 */
	private long ct;
	
	/**
	 * registration id
	 */
	private String ri;
	
	
	/**
	 * Channel enabled or not
	 * Possible values y or n
	 */
	private String e;
	
	public String getDi() {
		return di;
	}

	public void setDi(String di) {
		this.di = di;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public String getLi() {
		return li;
	}

	public void setLi(String li) {
		this.li = li;
	}

	public long getUt() {
		return ut;
	}

	public void setUt(long ut) {
		this.ut = ut;
	}

	public long getCt() {
		return ct;
	}

	public void setCt(long ct) {
		this.ct = ct;
	}

	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getRi() {
		return ri;
	}

	public void setRi(String ri) {
		this.ri = ri;
	}


}
