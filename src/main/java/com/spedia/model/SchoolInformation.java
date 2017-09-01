package com.spedia.model;

import org.springframework.web.multipart.MultipartFile;

import com.mongodb.DBObject;

public class SchoolInformation {
	private MultipartFile logoFile;
	private MultipartFile[] imageGallery;
	private MultipartFile backgroundImage;
	private Integer sid;
	private String schoolName;
	private String schoolContent;
	private String schoolSummary;
	private String email;
	private String contactNumber;
	private SchoolData schoolData;
	private DBObject content;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public MultipartFile getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(MultipartFile logoFile) {
		this.logoFile = logoFile;
	}

	public MultipartFile[] getImageGallery() {
		return imageGallery;
	}

	public void setImageGallery(MultipartFile[] imageGallery) {
		this.imageGallery = imageGallery;
	}

	public MultipartFile getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(MultipartFile backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolContent() {
		return schoolContent;
	}

	public void setSchoolContent(String schoolContent) {
		this.schoolContent = schoolContent;
	}
	public String getSchoolSummary() {
		return schoolSummary;
	}

	public void setSchoolSummary(String schoolSummary) {
		this.schoolSummary = schoolSummary;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public SchoolData getSchoolData() {
		return schoolData;
	}

	public void setSchoolData(SchoolData schoolData) {
		this.schoolData = schoolData;
	}

	public DBObject getContent() {
		return content;
	}

	public void setContent(DBObject content) {
		this.content = content;
	}
}
