package io.github.xinyangpan.codegen.classfile.wrapper.annotation;

import javax.persistence.SequenceGenerator;

import io.github.xinyangpan.codegen.classfile.wrapper.AnnotationWrapper;

public class SequenceGeneratorWrapper extends AnnotationWrapper {

	private String name;
	private String sequenceName;
	
	public SequenceGeneratorWrapper(String name, String sequenceName) {
		super(SequenceGenerator.class);
		this.name = name;
		this.sequenceName = sequenceName;
	}

	@Override
	public String getDisplayString() {
		String display = "@SequenceGenerator(name = \"%s\", sequenceName = \"%s\", allocationSize=1)";
		return String.format(display, name, sequenceName);
	}
	
}
