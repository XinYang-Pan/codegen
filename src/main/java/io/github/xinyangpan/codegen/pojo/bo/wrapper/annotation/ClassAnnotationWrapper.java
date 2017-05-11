package io.github.xinyangpan.codegen.pojo.bo.wrapper.annotation;

import java.lang.annotation.Annotation;

import io.github.xinyangpan.codegen.pojo.bo.PojoClass;

public class ClassAnnotationWrapper extends AnnotationWrapper<PojoClass> {

	public ClassAnnotationWrapper(Class<? extends Annotation> annotationClass) {
		super(annotationClass);
	}

	@Override
	public String getDisplayString(PojoClass t) {
		return String.format("@%s", classWrapper.getName());
	}

}
