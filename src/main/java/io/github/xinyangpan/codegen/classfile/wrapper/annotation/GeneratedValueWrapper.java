package io.github.xinyangpan.codegen.classfile.wrapper.annotation;

import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import io.github.xinyangpan.codegen.classfile.wrapper.AnnotationWrapper;

public class GeneratedValueWrapper extends AnnotationWrapper {

	private GenerationType generationType;
	private String name;

	public GeneratedValueWrapper(GenerationType generationType, String name) {
		super(GeneratedValue.class);
		this.generationType = generationType;
		this.name = name;
	}

	@Override
	public String getDisplayString() {
		return String.format("@GeneratedValue(strategy=GenerationType.%s, generator=\"%s\")", generationType.name(), name);
	}

	@Override
	public void addImportsTo(Set<Class<?>> targetSet) {
		super.addImportsTo(targetSet);
		if (generationType != null) {
			targetSet.add(generationType.getClass());
		}
	}

}
