package io.github.xinyangpan.codegen.classfile;

import com.google.common.base.Preconditions;

import io.github.xinyangpan.commons.functions.text.string.ReadText;

public enum Type implements ReadText {

	CLASS, INTERFACE, ENUM, ANNOTATION;

	@Override
	public String getText() {
		switch (this) {
		case CLASS:
			return "class";
		case INTERFACE:
			return "interface";
		case ENUM:
			return "enum";
		case ANNOTATION:
			return "@interface";
		default:
			Preconditions.checkState(false);
			return "";
		}
	}

}
