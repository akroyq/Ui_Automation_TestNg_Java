package com.demoapp.testauto.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FileUtil {

	public static final String PROPERTY_FILE_PATH = "src/main/resources/envconfig/";

	public static final String PROFILE_FILE_PATH = "src/main/resources/testdata/";

	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FileUtil.class);

	private FileUtil() {

	}

	public static Map<String, String> getConfigProperties(final String env) {

		return readProperties(FileUtil.PROPERTY_FILE_PATH + env + ".properties");
	}

	public static Map<String, String> getTestDataProperties(final String profile) {

		return readProperties(FileUtil.PROFILE_FILE_PATH + profile + ".properties");
	}

	/**
	 * <p>
	 * <b> Generic method to read any property file from any location within
	 * project for given path</b>
	 * </p>
	 * 
	 * @param path
	 * @return
	 */
	public static Map<String, String> readProperties(final String path) {
		Properties prop = new Properties();
		Map<String, String> propMap = new HashMap<String, String>();
		InputStream input = null;
		try {
			input = new FileInputStream(path);
			prop.load(input);
		} catch (IOException e) {
			FileUtil.logger.error("Exception at readproperties:", e);
		}

		for (java.util.Map.Entry<Object, Object> entries : prop.entrySet()) {

			propMap.put((String) entries.getKey(), (String) entries.getValue());

		}
		return propMap;
	}
}
