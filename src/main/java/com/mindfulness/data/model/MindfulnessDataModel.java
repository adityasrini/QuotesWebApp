package com.mindfulness.data.model;

import javax.persistence.*;

@Entity
@Table(name = "Mindfulness")
public class MindfulnessDataModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;
	
	private String secondId;
	
	@Column(name = "Quote")
	@Lob
	private String quote;
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getQuote() {
		return this.quote;
	}
	
	public void setQuote(String quote) {
		this.quote = quote;
	}
	
	public String getSecondId() {
		return secondId;
	}
	
	public void setSecondId(String secondId) {
		this.secondId = secondId;
	}
}