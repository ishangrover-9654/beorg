package com.ishan.rd.beorg.testdata;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Rev;
import org.springframework.data.annotation.Id;

import java.util.List;

@Document("customer")
public class IncompleteCustomer {
	@Id
	private String id;
	@Rev
	private String rev;

	private String name;
	private List<String> stringList;

	public IncompleteCustomer(final String name, final List<String> stringList) {
		this.name = name;
		this.stringList = stringList;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public List<String> getStringList() {
		return stringList;
	}

	public void setStringList(final List<String> stringList) {
		this.stringList = stringList;
	}

}
