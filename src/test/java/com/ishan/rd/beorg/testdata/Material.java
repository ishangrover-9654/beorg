package com.ishan.rd.beorg.testdata;

import com.arangodb.springframework.annotation.Rev;
import org.springframework.data.annotation.Id;

/**
 * Created by markmccormick on 24/08/2017.
 */
public class Material {

	@Id
	private String id;
	@Rev
	private String rev;
	private String name;

	public Material(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}
}
