package ${packageName};
<#if imports??>

<#list imports as class>
${class.fullName}
</#list>
</#if>

public class ${name}<#if superClass??> extends ${superClass}</#if><#if interfaces??> implements <#list interfaces as class>${class.typedName}<#sep>, </#list></#if> {
<#if methodTexts??>

<#list methodTexts as methodText>
${methodText}
</#list>
</#if>
}