package io.github.xinyangpan.codegen.classfile.wrapper.annotation;

import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import io.github.xinyangpan.codegen.classfile.wrapper.AnnotationWrapper;

public class EnumeratedWrapper extends AnnotationWrapper {

	private EnumType enumType;

	public EnumeratedWrapper(EnumType enumType) {
		super(Enumerated.class);
		this.enumType = enumType;
	}

	@Override
	public String getDisplayString() {
		String extra = "";
		if (enumType != null) {
			extra = String.format("(%s.%s)", EnumType.class.getSimpleName(), enumType.name());
		}
		return String.format("@%s%s", classWrapper.getName(), extra);
	}

	@Override
	public void addImportsTo(Set<Class<?>> targetSet) {
		super.addImportsTo(targetSet);
		if (enumType != null) {
			targetSet.add(enumType.getClass());
		}
	}

}
