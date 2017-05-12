package ${packageName};
<#if imports??>

<#list imports as class>
import ${class.name};
</#list>
</#if>

public class ${name}<#if superClass??> extends ${superClass}</#if><#if interfaces??> implements <#list interfaces as class>${class.typedName}<#sep>, </#list></#if> {

<#if fieldTexts?has_content>
<#list fieldTexts as fieldText>
${fieldText}
</#list>

</#if>
<#if methodTexts?has_content>
<#list methodTexts as methodText>
${methodText}
</#list>

</#if>
}