package io.github.xinyangpan.codegen.classfile.method;

import java.util.List;

import io.github.xinyangpan.codegen.pojo.bo.wrapper.annotation.AnnotationWrapper;
import io.github.xinyangpan.codegen.pojo.bo.wrapper.clazz.ClassWrapper;

public class MethodWrapper {
	private AccessModifier accessModifier;
	private String name;
	private ClassWrapper returnClass;
	private ClassWrapper throwClass;
	private List<AnnotationWrapper<?>> annotations;
	private List<ClassWrapper> parameters;
	private String content;

	public MethodWrapper() {
	}

	public MethodWrapper(AccessModifier accessModifier, ClassWrapper returnClass, List<ClassWrapper> parameterList, String content) {
		super();
		this.accessModifier = accessModifier;
		this.returnClass = returnClass;
		this.parameters = parameterList;
		this.content = content;
	}

	public MethodWrapper(AccessModifier accessModifier, ClassWrapper returnClass, ClassWrapper throwClass, List<ClassWrapper> parameterList, String content) {
		super();
		this.accessModifier = accessModifier;
		this.returnClass = returnClass;
		this.throwClass = throwClass;
		this.parameters = parameterList;
		this.content = content;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MethodWrapper [accessModifier=");
		builder.append(accessModifier);
		builder.append(", name=");
		builder.append(name);
		builder.append(", returnClass=");
		builder.append(returnClass);
		builder.append(", throwClass=");
		builder.append(throwClass);
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

	public ClassWrapper getThrowClass() {
		return throwClass;
	}

	public void setThrowClass(ClassWrapper throwClass) {
		this.throwClass = throwClass;
	}

	public String getContent() {
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
