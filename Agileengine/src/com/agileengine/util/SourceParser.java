package com.agileengine.util;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class SourceParser {

	public synchronized static Document getDocument(String source) {
		Document document = null;

		if (source.startsWith("http") || source.startsWith("ftp")) {
			try {
				document = Jsoup.connect(source).get();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (sourceIsFile(source)) {
			try {
				document = Jsoup.parse(new File(source), "UTF-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return document;
	}

	private static boolean sourceIsFile(String source) {

		Pattern pattern = Pattern.compile("[A-Z]:");
		Matcher matcher = pattern.matcher(source);

		if (matcher.lookingAt()) {
			return true;
		}

		if (source.startsWith("/home/") || source.startsWith("\\home\\")) {
			return true;
		}

		return false;
	}
}
