package POjO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CreateBookingResponse {

	@Override
	public String toString() {
		return "CreateBookingResponse [bookingid=" + bookingid + ", bookingDetails=" + booking + "]";
	}
	
	@Expose(serialize=true,deserialize=false)
	private int bookingid;
	
	@Expose(serialize=true,deserialize=true)
	private booking booking;
	
	
	


}
