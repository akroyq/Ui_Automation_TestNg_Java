package com.demoapp.testauto.library;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

/**
 * @author Amit
 *
 */
public class XmlUtil {

	private static DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(XmlUtil.class);

	private XmlUtil() {
	}

	private static Document getXmlDocument(final File xmlFile) {
		Document xmlDoc = null;
		try {
			xmlDoc = XmlUtil.builderFactory.newDocumentBuilder().parse(xmlFile);
		} catch (Exception e) {
			logger.error("Exception thrown at mapping xml parsing", e);

		}

		return xmlDoc;

	}

}
