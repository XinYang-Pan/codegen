package io.github.xinyangpan.codegen.classfile.pojo.bo;

import java.util.Map;

import com.google.common.collect.Maps;

public class ValueMapObject {

	protected final Map<String, String> valueMap = Maps.newHashMap();

	public Map<String, String> getValueMap() {
		return valueMap;
	}

}