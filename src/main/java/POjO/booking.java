package POjO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class booking {

	private String firstname;

	private String lastname;

	private int totalprice;

	private boolean depositpaid;

	private bookingdates bookingdates;

	private String additionalneeds;

	@Override
	public String toString() {
		return "booking [firstname=" + firstname + ", lastname=" + lastname + ", totalprice=" + totalprice
				+ ", depositpaid=" + depositpaid + ", bookingdates=" + bookingdates + ", additionalneeds="
				+ additionalneeds + "]";
	}

}
