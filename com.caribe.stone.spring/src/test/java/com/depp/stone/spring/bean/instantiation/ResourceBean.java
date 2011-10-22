package com.depp.stone.spring.bean.instantiation;

import org.springframework.core.io.Resource;

public class ResourceBean {

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	private Resource resource;
}
