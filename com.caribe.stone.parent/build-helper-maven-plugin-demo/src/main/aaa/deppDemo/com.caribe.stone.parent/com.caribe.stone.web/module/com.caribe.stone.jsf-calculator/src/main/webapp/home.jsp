<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
               "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<title>Calculator Application</title>
 		<link rel="stylesheet" 
 		    type="text/css" href="<%=request.getContextPath()%>/css/main.css" />
</head>
<body>
<f:view>
		<h4>Calculator Home Page</h4>
		<h:messages infoClass="infoClass" errorClass="errorClass" layout="table" globalOnly="true"/>
		<p>This is the calculator application that has support for navigation rules.</p>
		<h:form>
			<h:panelGrid columns="1">
				<h:commandLink action="CALCULATOR" value="Calculator Application"/>
				<h:commandLink action="CALCULATOR_REDIRECT" value="Calculator Application (redirect)"/>
				<h:outputLink value="pages/calculator.jsf">
					<h:outputText value="Calculator Application (outputlink)"/>
				</h:outputLink>
			</h:panelGrid>
		</h:form>
		
		
</f:view>

</body>
</html>