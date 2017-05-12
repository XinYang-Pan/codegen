package io.github.xinyangpan.codegen.classfile.wrapper.annotation;

import javax.persistence.Table;

import com.google.common.base.Preconditions;

import io.github.xinyangpan.codegen.classfile.wrapper.AnnotationWrapper;

public class TableWrapper extends AnnotationWrapper {

	public TableWrapper() {
		super(Table.class);
	}

	public TableWrapper(String tableName) {
		super(Table.class);
		this.setSource(tableName);
	}

	@Override
	public String getDisplayString() {
		Object tableName = this.getSource();
		Preconditions.checkNotNull(tableName);
		return String.format("@%s(name = \"%s\")", classWrapper.getName(), tableName);
	}

}
