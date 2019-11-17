package com.demoapp.testauto.library;

import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtil {

	/**
	 * Generates alphabetic numeric text for given length
	 * 
	 * @param length
	 * @return alphanumeric
	 */
	public static String generateAlphaNumeric(final int length) {

		return RandomStringUtils.randomAlphabetic(length);
	}

	/**
	 * 
	 * Generates String of Integer numbers for given length
	 * 
	 * @param length
	 * @return Numeric
	 */
	public static String generateIntNumber(final int length) {

		return RandomStringUtils.randomNumeric(length);
	}

	/**
	 * 
	 * Generates alphabetic string for given length
	 * 
	 * @param length
	 * @return alphabetic
	 */
	public static String generateAlphabatic(final int length) {

		return RandomStringUtils.randomAlphabetic(length);
	}

	/**
	 * 
	 * Generates Integer numbers randomly between start and end length
	 * 
	 * @param start
	 * @param end
	 * @return Integer
	 */
	public static int generateIntNumber(final int start_range, final int end_range) {

		return ThreadLocalRandom.current().nextInt(start_range, end_range);
	}

	/**
	 * 
	 * Generates double numbers randomly between start and end length
	 * 
	 * @param start
	 * @param end
	 * @return double
	 */
	public static double generateDoubeNumber(final int start_range, final int end_range) {

		return ThreadLocalRandom.current().nextDouble(start_range, end_range);
	}

}
