<#list column as x>
  ${x_index + 1}. ${x}<#if x_has_next>,</#if>
</#list> 
${msg}
