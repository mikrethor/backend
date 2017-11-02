package fr.ablx.daycare.objects

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class DefaultErrorMessage(
    val code: String,
    var status: String,
    val errors: List<String>)