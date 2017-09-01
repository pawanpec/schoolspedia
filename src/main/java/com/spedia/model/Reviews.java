package com.spedia.model;



/**
 * Reviews entity. @author MyEclipse Persistence Tools
 */
public class Reviews extends GenericObject {

	// Fields

	private Integer _id;
	private Integer nid;
	private Integer uid;
	private String city;
	//location Id
	private Integer lid;
	private String review;
	private String name;
	private String email;

	private Integer a=3;
	private Integer b=3;
	private Integer c=3;
	private Integer d=3;
	private Integer e=3;
	private Integer status;
	private Long created;

	// Constructors

	/** default constructor */
	public Reviews() {
	}

	/** minimal constructor */
	public Reviews(Integer nid, Integer uid, Integer lid, String review,
			Integer a, Integer b, Integer c, Integer d, Integer e,
			Integer status, Long created) {
		this.nid = nid;
		this.uid = uid;
		this.lid = lid;
		this.review = review;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.status = status;
		this.created = created;
	}

	/** full constructor */
	public Reviews(Integer nid, Integer uid, String city, Integer lid,
			String review, Integer a, Integer b, Integer c, Integer d,
			Integer e, Integer status, Long created) {
		this.nid = nid;
		this.uid = uid;
		this.city = city;
		this.lid = lid;
		this.review = review;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.status = status;
		this.created = created;
	}

	// Property accessors
	

	public Integer getNid() {
		return this.nid;
	}

	public void setNid(Integer nid) {
		this.nid = nid;
	}

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getLid() {
		return this.lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}

	public String getReview() {
		return this.review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Integer getA() {
		return this.a;
	}

	public void setA(Integer a) {
		this.a = a;
	}

	public Integer getB() {
		return this.b;
	}

	public void setB(Integer b) {
		this.b = b;
	}

	public Integer getC() {
		return this.c;
	}

	public void setC(Integer c) {
		this.c = c;
	}

	public Integer getD() {
		return this.d;
	}

	public void setD(Integer d) {
		this.d = d;
	}

	public Integer getE() {
		return this.e;
	}

	public void setE(Integer e) {
		this.e = e;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCreated() {
		return this.created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	

}