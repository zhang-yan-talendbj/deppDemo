<<<<<<< HEAD
<#assign x = 'http://localhost'>
${x?url('ISO-8859-1')} 

=======
<#assign x = 'http://localhost'>
${x?url('ISO-8859-1')} 

>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
<a href="foo.cgi?x=${x?url('ISO-8859-1')}&y=${y?url('ISO-8859-1')}">Click here...</a>