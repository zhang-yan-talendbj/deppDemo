<#assign x = 'http://localhost'>
${x?url('ISO-8859-1')} 

<a href="foo.cgi?x=${x?url('ISO-8859-1')}&y=${y?url('ISO-8859-1')}">Click here...</a>