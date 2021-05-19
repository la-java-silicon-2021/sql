package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Item")
@Table(name="item")
public class Item {
	@Id
	@Column(name="code")
	private Integer code;

	@Column(name="category_code")
	private Integer categoryCode;
	
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private Integer price;
	
	public Integer getCode() {
		return code;
	}

	public Integer getCategoryCode() {
		return categoryCode;
	}

	public String getName() {
		return name;
	}

	public Integer getPrice() {
		return price;
	}
}
