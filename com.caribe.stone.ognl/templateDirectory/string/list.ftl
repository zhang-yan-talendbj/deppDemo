<#assign seq = ["winter", "spring", "summer", "autumn"]>
<#list seq as x>
  ${x_index + 1}. ${x}<#if x_has_next>,</#if>
</#list>  
    * item_index: This is a numerical value that contains the index of the current item being stepped over in the loop.
    * item_has_next: Boolean value that tells if the current item the last in the sequence or not.

<#assign x=3>
<#list 1..x as i>
  ${i}
</#list>  

<#list seq as x>
  ${x}
  <#if x = "spring"><#break></#if>
</#list>