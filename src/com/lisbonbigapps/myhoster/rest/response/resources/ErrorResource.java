package com.lisbonbigapps.myhoster.rest.response.resources;

import javax.xml.bind.annotation.XmlElement;

public class ErrorResource extends MessageResource {	
	@XmlElement(name = "error")
	public String getMessage() {
		return message;
	}
}