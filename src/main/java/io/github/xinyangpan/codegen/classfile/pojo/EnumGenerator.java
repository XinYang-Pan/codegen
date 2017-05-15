//package io.github.xinyangpan.codegen.classfile.pojo;
//
//import java.util.List;
//
//import org.apache.commons.lang3.StringUtils;
//
//import io.github.xinyangpan.codegen.classfile.pojo.bo.PojoClass;
//import io.github.xinyangpan.commons.FormatterWrapper;
//
//public class EnumGenerator extends JavaFileGenerator {
//	private List<String> values;
//	
//	public EnumGenerator(PojoClass pojoClass, FormatterWrapper formatterWrapper, boolean autoClose) {
//		super(pojoClass, formatterWrapper, autoClose);
//	}
//
//	public EnumGenerator(PojoClass pojoClass, FormatterWrapper formatterWrapper) {
//		super(pojoClass, formatterWrapper);
//	}
//
//	public EnumGenerator(PojoClass pojoClass, String baseFolder) {
//		super(pojoClass, baseFolder);
//	}
//
//	public EnumGenerator(PojoClass pojoClass) {
//		super(pojoClass);
//	}
//	
//	@Override
//	public void generateClassDeclarationCode() {
//		formatterWrapper.formatln("public enum %s {", pojoClass.getName());
//		formatterWrapper.formatln(1, "%s;", StringUtils.join(values, ", "));
//	}
//	
//	public List<String> getValues() {
//		return values;
//	}
//
//	public void setValues(List<String> values) {
//		this.values = values;
//	}
//
//}
