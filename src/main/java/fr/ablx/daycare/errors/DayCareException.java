package fr.ablx.daycare.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "This customer is not found in the system")
public class DayCareException extends Exception {

	/**
	 * SerialUID.
	 */
	private static final long serialVersionUID = -7020841972681326671L;

	private String message;

	public DayCareException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
