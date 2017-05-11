package io.github.xinyangpan.codegen.pojo.bo.wrapper.annotation;

import java.lang.annotation.Annotation;

import io.github.xinyangpan.codegen.pojo.bo.wrapper.annotation.buildin.ColumnWrapper;
import io.github.xinyangpan.codegen.pojo.bo.wrapper.annotation.buildin.TableWrapper;

public abstract class AnnotationWrapperUtils {

	public static final ColumnWrapper COLUMN_WRAPPER = new ColumnWrapper();
	public static final TableWrapper TABLE_WRAPPER = new TableWrapper();

	public static <T> AnnotationWrapper<T> simpleWrapper(Class<? extends Annotation> annotationClass) {
		return new AnnotationWrapper<T>(annotationClass);
	}

}
