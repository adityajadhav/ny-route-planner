package com.cs430.ajadhav4;

import org.junit.Assert;
import org.junit.Test;

import com.cs430.ajadhav4.nyrouteplanner.entity.PropertyReader;

public class PropertyReaderTest {

	@Test
	public void test() {
		Assert.assertTrue(PropertyReader.getInstance().getProperty("stop-duration").length() > 0);
	}

}
