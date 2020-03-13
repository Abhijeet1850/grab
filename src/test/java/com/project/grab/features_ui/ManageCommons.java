package com.project.grab.features_ui;

import java.net.MalformedURLException;

import com.project.grab.TestBase.TestBase;
import com.project.grab.helper.configReader.PropertyReader;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class ManageCommons {

	TestBase testbase;
	PropertyReader reader = new PropertyReader();
	public final String driverPath = "\\src\\main\\java\\com\\project\\grab\\driver";

	@Before
	public void setUp() {
		testbase = new TestBase();
		try {
			testbase.setAndroidDriver();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		// testbase.setupDriver(reader.getBroswerType(), driverPath);
	}

	@After
	public void tearDown() {
		testbase.shutDownDriver();
	}

}
