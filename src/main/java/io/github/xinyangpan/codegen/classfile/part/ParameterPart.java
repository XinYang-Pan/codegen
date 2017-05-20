package io.github.xinyangpan.codegen.classfile.part;

import io.github.xinyangpan.codegen.classfile.wrapper.AnnotationWrapper;
import io.github.xinyangpan.codegen.classfile.wrapper.ClassWrapper;

import java.util.List;

public class ParameterPart extends ClassPart {

	private boolean varargs;

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

	public boolean isVarargs() {
		return varargs;
	}

	public void setVarargs(boolean varargs) {
		this.varargs = varargs;
	}

}
