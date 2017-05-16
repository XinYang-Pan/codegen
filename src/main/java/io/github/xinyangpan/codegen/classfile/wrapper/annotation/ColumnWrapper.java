package io.github.xinyangpan.codegen.classfile.wrapper.annotation;

import javax.persistence.Column;

import com.google.common.base.Preconditions;

import io.github.xinyangpan.codegen.classfile.wrapper.AnnotationWrapper;

public class ColumnWrapper extends AnnotationWrapper {

	public ColumnWrapper() {
		super(Column.class);
	}

	public ColumnWrapper(String columnName) {
		super(Column.class);
		this.setSource(columnName);
	}

	@Override
	public String getDisplayString() {
		Object columnName = this.getSource();
		Preconditions.checkNotNull(columnName);
		return String.format("@%s(name = \"%s\")", classWrapper.getName(), columnName);
	}

}
