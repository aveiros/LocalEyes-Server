package com.lisbonbigapps.myhoster.rest.response.resources;

import javax.xml.bind.annotation.XmlElement;

public class ErrorRestResource extends MessageRestResource {	
	@XmlElement(name = "error")
	public String getMessage() {
		return message;
	}
}