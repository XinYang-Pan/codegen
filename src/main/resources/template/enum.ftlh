package ${packageName};
<#if imports?has_content>

<#list imports as class>
import ${class.name};
</#list>
</#if>

${accessModifier.text} ${type.text} ${name}<#if superClass??> extends ${superClass}</#if><#if interfaces?has_content> implements <#list interfaces as class>${class.typedName}<#sep>, </#list></#if> {
	${values?join(", ")};
<#list fieldTexts as fieldText>
${fieldText}

</#list>
<#list methodTexts as methodText>
${methodText}

</#list>
}