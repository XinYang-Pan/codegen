package io.github.xinyangpan.codegen.converter.converter;

import java.lang.reflect.Method;
import java.util.Formatter;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import com.google.common.base.Defaults;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import io.github.xinyangpan.codegen.core.CodeGenUtils;
import io.github.xinyangpan.commons.FormatterWrapper;

public class SetGetMethodBased extends SetGetGenerator {

	public SetGetMethodBased(Class<?> setClass, Class<?> getClass, String setParamName, String getParamName) {
		super();
		this.setClass = setClass;
		this.getClass = getClass;
		this.setParamName = setParamName;
		this.getParamName = getParamName;
	}

	@Override
	public void generate(Formatter formatter) {
		getParamName = ObjectUtils.firstNonNull(getParamName, StringUtils.uncapitalize(getClass.getSimpleName()));
		setParamName = ObjectUtils.firstNonNull(setParamName, StringUtils.uncapitalize(setClass.getSimpleName()));
		if (Objects.equal(getParamName, setParamName)) {
			getParamName = getParamName + "1";
		}
		String setClazzName = setClass.getSimpleName();
		String getClazzName = getClass.getSimpleName();
		//
		FormatterWrapper formatterWrapper = new FormatterWrapper(new Formatter(System.out));
		formatterWrapper.formatln(0, "public %s build%s(%s %s) {", setClazzName, setClazzName, getClazzName, getParamName);
		formatterWrapper.formatln(1, "if (%s == null) {", getParamName);
		formatterWrapper.formatln(2, "return null;");
		formatterWrapper.formatln(1, "}");
		formatterWrapper.formatln(1, "%s %s = new %s();", setClazzName, setParamName, setClazzName);
		//
		Method[] methods = setClass.getMethods();
		for (Method method : methods) {
			Class<?>[] parameters = method.getParameterTypes();
			if (CodeGenUtils.isSetMethod(method)) {
				Method getOrIsMethod = setToGet(method, getClass);
				Object value;
				if (getOrIsMethod == null) {
					value = Defaults.defaultValue(parameters[0]);
				} else {
					value = String.format("%s.%s()", getParamName, getOrIsMethod.getName());
				}
				formatterWrapper.formatln(1, "%s.%s(%s);", setParamName, method.getName(), value);
			}
		}
		formatterWrapper.formatln(1, "return %s;", setParamName);
		//
		formatterWrapper.formatln("}");
		formatterWrapper.close();
	}

	public List<String> generateMethodContents() {
		List<String> contents = Lists.newArrayList();
		getParamName = ObjectUtils.firstNonNull(getParamName, StringUtils.uncapitalize(getClass.getSimpleName()));
		setParamName = ObjectUtils.firstNonNull(setParamName, StringUtils.uncapitalize(setClass.getSimpleName()));
		if (Objects.equal(getParamName, setParamName)) {
			getParamName = getParamName + "1";
		}
		String setClazzName = setClass.getSimpleName();
		//
		contents.add(format(0, "if (%s == null) {", getParamName));
		contents.add(format(1, "return null;"));
		contents.add(format(0, "}"));
		contents.add(format(0, "%s %s = new %s();", setClazzName, setParamName, setClazzName));
		//
		Method[] methods = setClass.getMethods();
		for (Method method : methods) {
			Class<?>[] parameters = method.getParameterTypes();
			if (CodeGenUtils.isSetMethod(method)) {
				Method getOrIsMethod = setToGet(method, getClass);
				Object value;
				if (getOrIsMethod == null) {
					value = Defaults.defaultValue(parameters[0]);
				} else {
					value = String.format("%s.%s()", getParamName, getOrIsMethod.getName());
				}
				contents.add(format(0, "%s.%s(%s);", setParamName, method.getName(), value));
			}
		}
		contents.add(format(0, "return %s;", setParamName));
		return contents;
	}

	public String format(int prefixRepeat, String format, Object... args) {
		String content = String.format(format, args);
		return String.format("%s%s", StringUtils.repeat('\t', prefixRepeat), content);
	}
	
	private Method setToGet(Method setMethod, Class<?> getClass) {
		String setMethodName = setMethod.getName();
		String getMethodName = "get" + setMethodName.substring(3);
		Method getMethod = ReflectionUtils.findMethod(getClass, getMethodName);
		if (getMethod != null) {
			return getMethod;
		}
		String isMethodName = "is" + setMethodName.substring(3);
		Method isMethod = ReflectionUtils.findMethod(getClass, isMethodName);
		if (isMethod != null) {
			return isMethod;
		}
		return null;
	}

}
