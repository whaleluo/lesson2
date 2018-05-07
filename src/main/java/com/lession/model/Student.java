package com.lession.model;

import java.io.Serializable;

public class Student implements Serializable {
	private static final long serialVersionUID = 123456L;
	private String id;
	private String name;
	private String birthday;
	private String description;
	private Double score;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "student [id=" + id + ", name=" + name + ", birthday=" + birthday + ", description=" + description
				+ ", score=" + score + "]";
	}
	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
