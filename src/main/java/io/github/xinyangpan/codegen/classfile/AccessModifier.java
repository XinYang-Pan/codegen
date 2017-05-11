package io.github.xinyangpan.codegen.classfile;

import com.google.common.base.Preconditions;

public enum AccessModifier {
	PUBLIC, PROTECTED, DEFAULT, PRIVATE;

	public String getString() {
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
