package io.github.xinyangpan.codegen.pojo.bo.wrapper.annotation.buildin;

import javax.persistence.SequenceGenerator;

import io.github.xinyangpan.codegen.pojo.bo.PojoField;
import io.github.xinyangpan.codegen.pojo.bo.wrapper.annotation.FieldAnnotationWrapper;

public class SequenceGeneratorWrapper extends FieldAnnotationWrapper {

	private String name;
	private String sequenceName;
	
	public SequenceGeneratorWrapper(String name, String sequenceName) {
		super(SequenceGenerator.class);
		this.name = name;
		this.sequenceName = sequenceName;
	}

	@Override
	public String getDisplayString(PojoField pojoField) {
		String display = "@SequenceGenerator(name = \"%s\", sequenceName = \"%s\", allocationSize=1)";
		return String.format(display, name, sequenceName);
	}
	
}
