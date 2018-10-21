package com.agileengine;

import com.agileengine.entity.HtmlElementEntity;
import com.agileengine.service.HtmlElementMapper;
import com.agileengine.util.ObjectConverter;

public class AppRunner {

	
	
	private static final String ORIGIN_PATH = System.getProperty("user.dir") +System.getProperty("file.separator") + "html" + System.getProperty("file.separator") + "sample-0-origin.html";

//	private static final String SOURCE_PATH = System.getProperty("user.dir") +System.getProperty("file.separator") + "html" + System.getProperty("file.separator") + "sample-1-evil-gemini.html";
//	private static final String SOURCE_PATH = System.getProperty("user.dir") +System.getProperty("file.separator") + "html" + System.getProperty("file.separator") + "sample-2-container-and-clone.html";
//	private static final String SOURCE_PATH = System.getProperty("user.dir") +System.getProperty("file.separator") + "html" + System.getProperty("file.separator") + "sample-3-the-escape.html";
	private static final String SOURCE_PATH = System.getProperty("user.dir") +System.getProperty("file.separator") + "html" + System.getProperty("file.separator") + "sample-4-the-mash.html";


	public static void main(String[] args) {
		String origin = null;
		String source = null;
		
		if (args.length == 2) {
			origin = args[0];
			source = args[1];
		} else {
			origin = ORIGIN_PATH;
			source = SOURCE_PATH;
		}
		
		String id = "make-everything-ok-button";
		
		HtmlElementEntity htmlElement = HtmlElementMapper.getSimilarElement(id, origin, source);
		
		ObjectConverter.convertToXml(htmlElement);

	}
}
