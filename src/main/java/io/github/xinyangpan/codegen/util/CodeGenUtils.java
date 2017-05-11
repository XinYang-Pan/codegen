package io.github.xinyangpan.codegen.util;

import java.lang.reflect.Method;

import org.apache.commons.lang3.ArrayUtils;

public abstract class CodeGenUtils {

	public static boolean isSetMethod(Method method) {
		String methodName = method.getName();
		Class<?>[] parameters = method.getParameterTypes();
		return methodName.startsWith("set")
			&& ArrayUtils.getLength(parameters) == 1
			&& (void.class == method.getReturnType() || Void.class == method.getReturnType());
	}

	public static boolean isGetMethod(Method method) {
		String methodName = method.getName();
		Class<?>[] parameters = method.getParameterTypes();
		if (ArrayUtils.getLength(parameters) != 0) {
			return false;
		}
		if (void.class == method.getReturnType() || Void.class == method.getReturnType()) {
			return false;
		}
		if (methodName.startsWith("get")) {
			return true;
		} else if (methodName.startsWith("is") && boolean.class == method.getReturnType()) {
			return true;
		}
		return false;
	}

}
