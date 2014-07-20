package com.innovez.rest.backend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@SuppressWarnings("serial")
public class Money implements Serializable {
	@Column(name="currency")
	private String currency;
	
	@Column(name="amount")
	private Double amount;

	public Money() {}
	public Money(String currency, Double amount) {
		this.currency = currency;
		this.amount = amount;
	}
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Money [currency=" + currency + ", amount=" + amount + "]";
	}
}
