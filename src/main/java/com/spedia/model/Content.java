package com.spedia.model;

import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

public class Content {
	private Integer _id;
	private Long created;
	private Long changed;
	//rss news id
	private String rid;
	private String uid;
	private String updatedBy;
	private ContentBody body;
	private FieldGroup field_group;
	private int status = 1;
	private String meta_description;
	private String meta_keywords;
	private String robots;

	public String getMeta_description() {
		return meta_description;
	}

	public void setMeta_description(String meta_description) {
		this.meta_description = meta_description;
	}

	public String getMeta_keywords() {
		return meta_keywords;
	}

	public void setMeta_keywords(String meta_keywords) {
		this.meta_keywords = meta_keywords;
	}

	public String getRobots() {
		return robots;
	}

	public void setRobots(String robots) {
		this.robots = robots;
	}

	private String body_content;

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}

	public Long getChanged() {
		return changed;
	}

	public void setChanged(Long changed) {
		this.changed = changed;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	private String title;
	private String type;
	private String[] tags;
	private MultipartFile imageFile;
	private String imagePath;
	private String alias;
	private String sid;

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	

	public ContentBody getBody() {
		return body;
	}

	public void setBody(ContentBody body) {
		this.body = body;
	}

	public String getBody_content() {
		return body_content;
	}

	public void setBody_content(String body_content) {
		this.body_content = body_content;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public FieldGroup getField_group() {
		return field_group;
	}

	public void setField_group(FieldGroup field_group) {
		this.field_group = field_group;
	}

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

}