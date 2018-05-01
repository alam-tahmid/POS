package com.pos.lotto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@Column(name = "article_no")
	private String articleNo;

	@Column(name = "barcode")
	private String barcode;

	@Column(name = "description")
	private String description;

	@Column(name = "size")
	private int size;

	@Column(name = "color")
	private String color;

	@Column(name = "unit_price")
	private double unitPrice;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "sold_quantity")
	private int soldQuantity;

	@Column(name = "total_price")
	private double totalPrice;

	@Column(name = "discount")
	private double discount;

	@Column(name = "discount_percentage")
	private double discountPercentage;

	@Column(name = "offer")
	private String offer;

	public String getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSoldQuantity() {
		return soldQuantity;
	}

	public void setSoldQuantity(int soldQuantity) {
		this.soldQuantity = soldQuantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

}
