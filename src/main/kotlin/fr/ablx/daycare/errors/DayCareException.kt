package fr.ablx.daycare.errors

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "This customer is not found in the system")
 class DayCareException(override val  message:String) : Exception()