package be.vdab.valueobjects;

import javax.validation.constraints.NotNull;

import be.vdab.constraints.Postcode;

public class PostcodeReeks {
	@NotNull
	@Postcode
	private Integer vanpostcode;
	@NotNull
	@Postcode
	private Integer totpostcode;

	public Integer getVanpostcode() {
		return vanpostcode;
	}

	public Integer getTotpostcode() {
		return totpostcode;
	}

	public boolean bevat(Integer postcode) {
		return postcode >= vanpostcode && postcode <= totpostcode;
	}
}