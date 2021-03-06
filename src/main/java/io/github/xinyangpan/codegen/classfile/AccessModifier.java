package io.github.xinyangpan.codegen.classfile;

import com.google.common.base.Preconditions;

import io.github.xinyangpan.commons.functions.text.string.ReadText;

public enum AccessModifier implements ReadText {
	PUBLIC, PROTECTED, DEFAULT, PRIVATE;

	@Override
	public String getText() {
		switch (this) {
		case PUBLIC:
			return "public";
		case PROTECTED:
			return "protected";
		case DEFAULT:
			return "";
		case PRIVATE:
			return "private";
		default:
			Preconditions.checkState(false);
			return "";
		}
	}

}
