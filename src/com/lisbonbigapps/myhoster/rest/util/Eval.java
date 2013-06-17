package com.lisbonbigapps.myhoster.rest.util;

public class Eval {

    /**
     * Evaluates value parameter, return defaultValue if value is null
     * otherwise, return value
     * 
     * @param value
     * @param defaultValue
     * @return
     */
    public static <T extends Object> T value(T value, T defaultValue) {
	return value == null ? defaultValue : value;
    }
}
