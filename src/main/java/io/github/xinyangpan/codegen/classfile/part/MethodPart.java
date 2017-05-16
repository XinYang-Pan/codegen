package io.github.xinyangpan.codegen.classfile.part;

import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.base.Defaults;
import com.google.common.collect.Lists;

import io.github.xinyangpan.codegen.classfile.AccessModifier;
import io.github.xinyangpan.codegen.classfile.wrapper.ClassWrapper;
import io.github.xinyangpan.codegen.core.Import;
import io.github.xinyangpan.codegen.core.template.TemplateType;

public class MethodPart extends ClassDeclarePart {
//	private List<ClassWrapper> throws;
	private List<ParameterPart> parameters;
	private List<String> contents;

	public MethodPart() {
		this.accessModifier = AccessModifier.PUBLIC;
		this.type = ClassWrapper.of(void.class);
	}

	public MethodPart(String name, ClassWrapper returnType, ParameterPart... parameters) {
		this();
		this.setName(name);
		this.setType(returnType);
		this.parameters = Lists.newArrayList(parameters);
	}

	@Override
	public TemplateType templateType() {
		return TemplateType.METHOD;
	}

	@Override
	public void addImportsTo(Set<Class<?>> targetSet) {
		super.addImportsTo(targetSet);
		Import.toAdd(targetSet, parameters);
	}

	public List<String> getContents() {
		if (CollectionUtils.isEmpty(contents) && (type.getClassIfPossible() != void.class)) {
			return Lists.newArrayList(String.format("return %s;", Defaults.defaultValue(type.getClassIfPossible())));
		}
		return contents;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MethodPart [parameters=");
		builder.append(parameters);
		builder.append(", contents=");
		builder.append(contents);
		builder.append(", accessModifier=");
		builder.append(accessModifier);
		builder.append(", isStatic=");
		builder.append(isStatic);
		builder.append(", type=");
		builder.append(type);
		builder.append(", name=");
		builder.append(name);
		builder.append(", annotationWrapper=");
		builder.append(annotationWrapper);
		builder.append("]");
		return builder.toString();
	}

	public List<ParameterPart> getParameters() {
		return parameters;
	}

	public void setParameters(List<ParameterPart> parameters) {
		this.parameters = parameters;
	}

	public void setContents(List<String> contents) {
		this.contents = contents;
	}

}
