package com.epam.lab.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "planes")
public class Planes {

	@XmlElement(name = "plane", type = Plane.class)
	private List<Aircraft> planes = new ArrayList<>();

	public Planes() {
	}

	public Planes(List<Aircraft> books) {
		this.planes = books;
	}

	public List<Aircraft> getPlanes() {
		return planes;
	}

	public void setPlanes(List<Aircraft> planes) {
		this.planes = planes;
	}
}
