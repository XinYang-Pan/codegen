package io.github.xinyangpan.codegen.classfile.method;

import java.util.List;
import java.util.Set;

import com.google.common.base.Defaults;
import com.google.common.collect.Lists;

import io.github.xinyangpan.codegen.classfile.AccessModifier;
import io.github.xinyangpan.codegen.classfile.ClassPart;
import io.github.xinyangpan.codegen.pojo.bo.wrapper.clazz.ClassWrapper;
import io.github.xinyangpan.codegen.util.Import;

public class MethodPart extends ClassPart {
//	private List<ClassWrapper> throws;
	private List<ClassWrapper> parameters;
	private String content;

	public MethodPart() {
		this.accessModifier = AccessModifier.PUBLIC;
		this.type = ClassWrapper.of(void.class);
	}

	public MethodPart(String name, ClassWrapper returnType, ClassWrapper... parameters) {
		this();
		this.setName(name);
		this.setType(returnType);
		this.parameters = Lists.newArrayList(parameters);
	}

	@Override
	public void addImportsTo(Set<Class<?>> targetSet) {
		super.addImportsTo(targetSet);
		Import.toAdd(targetSet, parameters);
	}

	public String getContent() {
		if (content == null && (type.getClassIfPossible() != void.class)) {
			return String.format("return %s;", Defaults.defaultValue(type.getClassIfPossible()));
		}
		return content;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MethodPart [parameters=");
		builder.append(parameters);
		builder.append(", content=");
		builder.append(content);
		builder.append(", accessModifier=");
		builder.append(accessModifier);
		builder.append(", name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append(", annotationWrapper=");
		builder.append(annotationWrapper);
		builder.append("]");
		return builder.toString();
	}

	public List<ClassWrapper> getParameters() {
		return parameters;
	}

	public void setParameters(List<ClassWrapper> parameters) {
		this.parameters = parameters;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
