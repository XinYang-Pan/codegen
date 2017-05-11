package io.github.xinyangpan.codegen.classfile.method;

import java.util.List;
import java.util.Set;

import com.google.common.base.Defaults;
import com.google.common.collect.Lists;

import io.github.xinyangpan.codegen.classfile.AccessModifier;
import io.github.xinyangpan.codegen.pojo.bo.wrapper.annotation.AnnotationWrapper;
import io.github.xinyangpan.codegen.pojo.bo.wrapper.clazz.ClassWrapper;
import io.github.xinyangpan.codegen.util.Import;

public class MethodPart implements Import {
	private AccessModifier accessModifier = AccessModifier.PUBLIC;
	private String name;
	private ClassWrapper returnClass = ClassWrapper.of(void.class);
//	private List<ClassWrapper> throws;
	private List<AnnotationWrapper<?>> annotations;
	private List<ClassWrapper> parameters;
	private String content;

	public MethodPart() {
	}

	public MethodPart(ClassWrapper returnClass, ClassWrapper ... parameters) {
		super();
		this.returnClass = returnClass;
		this.parameters = Lists.newArrayList(parameters);
	}
	
	@Override
	public void addImportsTo(Set<Class<?>> imports) {
		Import.toAdd(imports, returnClass);
		Import.toAdd(imports, annotations);
		Import.toAdd(imports, parameters);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MethodPart [accessModifier=");
		builder.append(accessModifier);
		builder.append(", name=");
		builder.append(name);
		builder.append(", returnClass=");
		builder.append(returnClass);
		builder.append(", annotations=");
		builder.append(annotations);
		builder.append(", parameterList=");
		builder.append(parameters);
		builder.append(", content=");
		builder.append(content);
		builder.append("]");
		return builder.toString();
	}

	public AccessModifier getAccessModifier() {
		return accessModifier;
	}

	public void setAccessModifier(AccessModifier accessModifier) {
		this.accessModifier = accessModifier;
	}

	public ClassWrapper getReturnClass() {
		return returnClass;
	}

	public String getContent() {
		if (content == null && (returnClass.getClassIfPossible() != void.class)) {
			return String.format("return %s;", Defaults.defaultValue(returnClass.getClassIfPossible()));
		}
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setReturnClass(ClassWrapper returnClass) {
		this.returnClass = returnClass;
	}

	public List<ClassWrapper> getParameters() {
		return parameters;
	}

	public void setParameters(List<ClassWrapper> parameterList) {
		this.parameters = parameterList;
	}

	public List<AnnotationWrapper<?>> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<AnnotationWrapper<?>> annotations) {
		this.annotations = annotations;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
