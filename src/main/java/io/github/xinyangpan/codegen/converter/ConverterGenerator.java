package io.github.xinyangpan.codegen.converter;

import java.beans.PropertyDescriptor;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.google.common.base.Defaults;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import io.github.xinyangpan.codegen.core.ContentGenerator;
import io.github.xinyangpan.commons.PropertyCollectingType;

public class ConverterGenerator implements ContentGenerator {

	private Class<?> target;
	private Class<?> source;
	private String targetParamName;
	private String sourceParamName;
	private PropertyCollectingType propertyCollectingType;

	public ConverterGenerator(Class<?> target, Class<?> source, String sourceParamName, PropertyCollectingType propertyCollectingType) {
		super();
		this.target = target;
		this.source = source;
		this.sourceParamName = sourceParamName;
		this.propertyCollectingType = propertyCollectingType;
	}

	@Override
	public List<String> contents() {
		List<String> contents = Lists.newArrayList();
		sourceParamName = ObjectUtils.firstNonNull(sourceParamName, StringUtils.uncapitalize(source.getSimpleName()));
		targetParamName = ObjectUtils.firstNonNull(targetParamName, StringUtils.uncapitalize(target.getSimpleName()));
		if (Objects.equal(sourceParamName, targetParamName)) {
			targetParamName = targetParamName + "1";
		}
		String setClazzName = target.getSimpleName();
		//
		contents.add(format(0, "if (%s == null) {", sourceParamName));
		contents.add(format(1, "return null;"));
		contents.add(format(0, "}"));
		contents.add(format(0, "%s %s = new %s();", setClazzName, targetParamName, setClazzName));
		//
		List<PropertyDescriptor> propertyDescriptors = propertyCollectingType.getPropertyDescriptors(target);
		for (PropertyDescriptor targetPd : propertyDescriptors) {
			PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source, targetPd.getName());
			Object value;
			if (sourcePd == null) {
				value = Defaults.defaultValue(targetPd.getPropertyType());
			} else {
				value = String.format("%s.%s()", sourceParamName, sourcePd.getReadMethod().getName());
			}
			contents.add(format(0, "%s.%s(%s);", targetParamName, targetPd.getName(), value));
		}
		contents.add(format(0, "return %s;", targetParamName));
		return contents;
	}

	public String format(int prefixRepeat, String format, Object... args) {
		String content = String.format(format, args);
		return String.format("%s%s", StringUtils.repeat('\t', prefixRepeat), content);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConverterGenerator [target=");
		builder.append(target);
		builder.append(", source=");
		builder.append(source);
		builder.append(", targetParamName=");
		builder.append(targetParamName);
		builder.append(", sourceParamName=");
		builder.append(sourceParamName);
		builder.append(", propertyCollectingType=");
		builder.append(propertyCollectingType);
		builder.append("]");
		return builder.toString();
	}

	public Class<?> getTarget() {
		return target;
	}

	public void setTarget(Class<?> target) {
		this.target = target;
	}

	public Class<?> getSource() {
		return source;
	}

	public void setSource(Class<?> source) {
		this.source = source;
	}

	public String getTargetParamName() {
		return targetParamName;
	}

	public void setTargetParamName(String targetParamName) {
		this.targetParamName = targetParamName;
	}

	public String getSourceParamName() {
		return sourceParamName;
	}

	public void setSourceParamName(String sourceParamName) {
		this.sourceParamName = sourceParamName;
	}

	public PropertyCollectingType getPropertyCollectingType() {
		return propertyCollectingType;
	}

	public void setPropertyCollectingType(PropertyCollectingType propertyCollectingType) {
		this.propertyCollectingType = propertyCollectingType;
	}

}
