package be.vdab.restclients;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rates {
	@JsonProperty("USD")
	private BigDecimal USD;

	public BigDecimal getUSD() {
		return USD;
	}

	public void setUSD(BigDecimal USD) {
		this.USD = USD;
	}
}
