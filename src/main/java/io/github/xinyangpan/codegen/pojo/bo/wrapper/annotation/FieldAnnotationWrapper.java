package io.github.xinyangpan.codegen.pojo.bo.wrapper.annotation;

import java.lang.annotation.Annotation;

import io.github.xinyangpan.codegen.pojo.bo.PojoField;

public class FieldAnnotationWrapper extends AnnotationWrapper<PojoField> {

	public FieldAnnotationWrapper(Class<? extends Annotation> annotationClass) {
		super(annotationClass);
	}

	@Override
	public String getDisplayString(PojoField t) {
		return String.format("@%s", classWrapper.getName());
	}

}
