package com.agileengine.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "attributes")
public class HtmlAttributes {

	private String id;
	private String className;
	private String title;
	private String href;

	public String getId() {
		return id;
	}

	@XmlElement
	public void setId(String id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	@XmlElement(name = "class-name")
	public void setClassName(String className) {
		this.className = className;
	}

	public String getTitle() {
		return title;
	}

	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}

	public String getHref() {
		return href;
	}

	@XmlElement
	public void setHref(String href) {
		this.href = href;
	}

}
