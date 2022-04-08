package com.hotel.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cuisine")
public class Cuisine {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	@Column(name="Cuisine_Name")
	private String	CuisineName;
	@Column(name="Cuisine_Price")
	private long	CuisinePrice;
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getCuisineName() {
		return CuisineName;
	}
	public void setCuisineName(String cuisineName) {
		CuisineName = cuisineName;
	}
	public long getCuisinePrice() {
		return CuisinePrice;
	}
	public void setCuisinePrice(long cuisinePrice) {
		CuisinePrice = cuisinePrice;
	}
	
	
}
