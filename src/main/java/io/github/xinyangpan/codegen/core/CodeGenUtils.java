package io.github.xinyangpan.codegen.core;

import org.apache.commons.lang3.StringUtils;

public abstract class CodeGenUtils {

	public static String format(int prefixRepeat, String format, Object... args) {
		String content = String.format(format, args);
		return String.format("%s%s", StringUtils.repeat('\t', prefixRepeat), content);
	}

}
