package ${packageName};
<#if imports??>

<#list imports as class>
import ${class.name};
</#list>
</#if>

<#list annotationWrappers as annotationWrapper>
${annotationWrapper.displayString}
</#list>
${accessModifier.text} ${type.text} ${name}<#if superClass??> extends ${superClass.typedName}</#if><#if interfaces?has_content> implements <#list interfaces as class>${class.typedName}<#sep>, </#list></#if> {

<#list fieldTexts as fieldText>
${fieldText}

</#list>
<#list methodTexts as methodText>
${methodText}

</#list>
}