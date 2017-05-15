package io.github.xinyangpan.codegen.classfile.part;

import java.util.List;

import io.github.xinyangpan.codegen.classfile.wrapper.AnnotationWrapper;
import io.github.xinyangpan.codegen.classfile.wrapper.ClassWrapper;

public class ParameterPart extends ClassPart {

	public ParameterPart() {
	}

	public ParameterPart(Class<?> type) {
		this(type, null);
	}

	public ParameterPart(Class<?> type, String name) {
		super(type, name);
	}

	public ParameterPart(ClassWrapper type, String name, List<? extends AnnotationWrapper> annotationWrapper) {
		super(type, name, annotationWrapper);
	}

	public ParameterPart(ClassWrapper type, String name) {
		super(type, name);
	}

}
