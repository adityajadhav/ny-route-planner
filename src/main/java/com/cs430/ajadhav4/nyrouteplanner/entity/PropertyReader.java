package com.cs430.ajadhav4.nyrouteplanner.entity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

	private static PropertyReader propertyReader;
	private Properties props;

	private PropertyReader() {
	};

	public static PropertyReader getInstance() {
		if (propertyReader == null) {
			propertyReader = new PropertyReader();
			propertyReader.loadProperties();
			return propertyReader;
		}
		return propertyReader;
	}

	void loadProperties() {
		try {
			InputStream stream = PropertyReader.class.getResourceAsStream("/config.properties");
			props = new Properties();
			props.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getProperty(String key) {
		return props.getProperty(key);
	}

}
