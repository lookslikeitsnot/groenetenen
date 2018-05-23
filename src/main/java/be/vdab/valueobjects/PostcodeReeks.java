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

//	private final static int MIN_POSTCODE = 1000;
//	private final static int MAX_POSTCODE = 9999;

	public Integer getVanpostcode() {
		return vanpostcode;
	}

	public void setVanpostcode(Integer vanpostcode) {
		this.vanpostcode = vanpostcode;
	}

	public Integer getTotpostcode() {
		return totpostcode;
	}

	public void setTotpostcode(Integer totpostcode) {
		this.totpostcode = totpostcode;
	}

	public boolean bevat(Integer postcode) {
		return postcode >= vanpostcode && postcode <= totpostcode;
	}
}