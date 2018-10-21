package com.agileengine.service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.agileengine.entity.HtmlElementEntity;
import com.agileengine.util.ObjectConverter;
import com.agileengine.util.SourceParser;

public class HtmlElementMapper {

	public static HtmlElementEntity getSimilarElement(String id, String origin, String source) {

		Document originDocument = SourceParser.getDocument(origin);
		if (originDocument == null) {
			throw new RuntimeException("Document was not parsed");
		}
		
		Element originElement = originDocument.getElementById(id);

		if (originElement == null) {
			throw new RuntimeException("Element missed in origin document");
		}

		HtmlElementEntity htmlElement = tryToFindElementInSource(originElement, source);

		return htmlElement;
	}

	private static HtmlElementEntity tryToFindElementInSource(Element originElement, String source) {
		HtmlElementEntity htmlElement = null;

		String className = originElement.attr("class");
		String title = originElement.attr("title");
		String href = originElement.attr("href");
		String tag = originElement.tagName();
		String text = originElement.text();

		Document document = SourceParser.getDocument(source);

		if (document != null) {

			htmlElement = tryToFindByTitle(document, tag, title);

			if (htmlElement == null) {
				htmlElement = tryToFindByHref(document, tag, href);
			}

			if (htmlElement == null) {
				htmlElement = tryToFindByClass(document, tag, className);
			}

			if (htmlElement == null) {
				htmlElement = tryToFindByText(document, tag, text);
			}

		}

		return htmlElement;
	}

	private static HtmlElementEntity tryToFindByText(Document document, String tag, String text) {

		String[] textWords = text.split(" ");
		if (textWords.length > 0) {
			String textStart = textWords[0];
			String textEnd = textWords[textWords.length - 1];

			Elements elements = document.getElementsByTag(tag);
			for (Element element : elements) {
				if (!element.text().isEmpty() && element.text().startsWith(textStart)
						&& element.text().endsWith(textEnd)) {
					return ObjectConverter.convertElementToHtmlElement(element);
				}
			}
		}

		return null;
	}

	private static HtmlElementEntity tryToFindByHref(Document document, String tag, String href) {
		Elements elements = document.getElementsByTag(tag);
		for (Element element : elements) {
			if (element.attr("href") != null && element.attr("href").endsWith(href.replaceAll("#", ""))) {
				return ObjectConverter.convertElementToHtmlElement(element);
			}
		}

		return null;
	}

	private static HtmlElementEntity tryToFindByTitle(Document document, String tag, String title) {

		Elements elements = document.getElementsByTag(tag);
		for (Element element : elements) {
			if (element.attr("title") != null && element.attr("title").endsWith(title)) {
				return ObjectConverter.convertElementToHtmlElement(element);
			}
		}

		return null;
	}

	private static HtmlElementEntity tryToFindByClass(Document document, String tag, String className) {

		String[] classValues = className.split(" ");
		if (classValues.length > 0) {
			String classEnd = classValues[classValues.length - 1];

			Elements elements = document.getElementsByTag(tag);
			for (Element element : elements) {
				if (element.attr("class") != null && element.attr("class").endsWith(classEnd)) {
					return ObjectConverter.convertElementToHtmlElement(element);
				}
			}
		}

		return null;
	}
}
