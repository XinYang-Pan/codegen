package ${packageName};
<#if imports??>

<#list imports as class>
import ${class.name};
</#list>
</#if>

${accessModifier.text} ${type.text} ${name}<#if superClass??> extends ${superClass}</#if><#if interfaces??> implements <#list interfaces as class>${class.typedName}<#sep>, </#list></#if> {

<#list fieldTexts as fieldText>
${fieldText}

</#list>
<#list methodTexts as methodText>
${methodText}

</#list>
}