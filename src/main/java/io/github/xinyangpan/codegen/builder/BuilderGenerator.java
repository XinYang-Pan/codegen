package io.github.xinyangpan.codegen.builder;

import static io.github.xinyangpan.codegen.core.CodeGenUtils.format;

import java.beans.PropertyDescriptor;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Defaults;
import com.google.common.collect.Lists;

import io.github.xinyangpan.codegen.core.ContentGenerator;
import io.github.xinyangpan.commons.PropertyCollectingType;

public class BuilderGenerator implements ContentGenerator {

	private Class<?> target;
	private String targetParamName;
	private PropertyCollectingType propertyCollectingType;

	public BuilderGenerator(Class<?> target, String targetParamName, PropertyCollectingType propertyCollectingType) {
		super();
		this.target = target;
		this.targetParamName = targetParamName;
		this.propertyCollectingType = propertyCollectingType;
	}

	@Override
	public List<String> contents() {
		List<String> contents = Lists.newArrayList();
		targetParamName = ObjectUtils.firstNonNull(targetParamName, StringUtils.uncapitalize(target.getSimpleName()));
		String targetClassName = target.getSimpleName();
		//
		contents.add(format(0, "%s %s = new %s();", targetClassName, targetParamName, targetClassName));
		//
		List<PropertyDescriptor> propertyDescriptors = propertyCollectingType.getPropertyDescriptors(target);
		for (PropertyDescriptor targetPd : propertyDescriptors) {
			Object value = Defaults.defaultValue(targetPd.getPropertyType());
			contents.add(format(0, "%s.%s(%s);", targetParamName, targetPd.getWriteMethod().getName(), value));
		}
		contents.add(format(0, "return %s;", targetParamName));
		return contents;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BuilderGenerator [target=");
		builder.append(target);
		builder.append(", targetParamName=");
		builder.append(targetParamName);
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

	public String getTargetParamName() {
		return targetParamName;
	}

	public void setTargetParamName(String targetParamName) {
		this.targetParamName = targetParamName;
	}

	public PropertyCollectingType getPropertyCollectingType() {
		return propertyCollectingType;
	}

	public void setPropertyCollectingType(PropertyCollectingType propertyCollectingType) {
		this.propertyCollectingType = propertyCollectingType;
	}

}
