package com.agileengine.util;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.jsoup.nodes.Element;

import com.agileengine.entity.HtmlAttributes;
import com.agileengine.entity.HtmlElementEntity;

public class ObjectConverter {

	private static final String PATH = System.getProperty("user.dir") +System.getProperty("file.separator") + "out" + System.getProperty("file.separator") + "out-file.xml";

	public synchronized static void convertToXml(HtmlElementEntity htmlElement) {
		try {

			File file = new File(PATH);
			JAXBContext jaxbContext = JAXBContext.newInstance(HtmlElementEntity.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(htmlElement, file);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	public synchronized static HtmlElementEntity convertElementToHtmlElement(Element element) {
		
		HtmlElementEntity htmlElement = new HtmlElementEntity();
		
		HtmlAttributes attributes = new HtmlAttributes();
		
		attributes.setClassName(element.attr("class"));
		attributes.setHref(element.attr("href"));
		attributes.setId(element.attr("id"));
		attributes.setTitle(element.attr("title"));

		htmlElement.setAttributes(attributes);
		htmlElement.setTagName(element.tagName());
		htmlElement.setText(element.text());
		htmlElement.setXpath(element.cssSelector());
		
		return htmlElement;
	}
}
