@echo off  
set Axis_Lib=e:\bruce\program\axis-1_4\lib  
set Java_Cmd=java -Djava.ext.dirs=%Axis_Lib%  
set Axis_Servlet=http://localhost:8080/test/servlet/AxisServlet
%Java_Cmd% org.apache.axis.client.AdminClient -l%Axis_Servlet% service-config.wsdd  
  
echo over
echo. & pause


