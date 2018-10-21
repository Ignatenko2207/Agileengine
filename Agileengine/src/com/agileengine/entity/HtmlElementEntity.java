package com.agileengine.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "html-element")
public class HtmlElementEntity {

	private String xpath;
	private String tagName;
	private HtmlAttributes attributes;
	private String text;

	public String getXpath() {
		return xpath;
	}

	@XmlElement
	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

	public String getTagName() {
		return tagName;
	}

	@XmlElement(name = "tag")
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public HtmlAttributes getAttributes() {
		return attributes;
	}

	@XmlElement
	public void setAttributes(HtmlAttributes attributes) {
		this.attributes = attributes;
	}

	public String getText() {
		return text;
	}

	@XmlElement
	public void setText(String text) {
		this.text = text;
	}

}
