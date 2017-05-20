package io.github.xinyangpan.codegen.core;

import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import io.github.xinyangpan.codegen.classfile.part.FieldPart;
import io.github.xinyangpan.commons.PropertyCollectingType;

public abstract class CodeGenUtils {

	public static String format(int prefixRepeat, String format, Object... args) {
		String content = String.format(format, args);
		return String.format("%s%s", StringUtils.repeat('\t', prefixRepeat), content);
	}

	public static List<FieldPart> getFieldParts(Class<?> clazz, PropertyCollectingType propertyCollectingType) {
		List<PropertyDescriptor> propertyDescriptors = propertyCollectingType.getPropertyDescriptors(clazz);
		return propertyDescriptors.stream()
				.map(pd -> new FieldPart(pd.getPropertyType(), pd.getName()))
				.collect(Collectors.toList());
	}

}
