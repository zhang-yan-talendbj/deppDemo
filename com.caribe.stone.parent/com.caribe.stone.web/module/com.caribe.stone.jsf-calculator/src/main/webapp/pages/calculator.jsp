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
	<h:form id="calcForm">
		<h4>Calculator 4th Example</h4>
		<h:messages infoClass="infoClass" errorClass="errorClass" layout="table" globalOnly="true"/>
		<h:panelGrid columns="3" rowClasses="oddRow, evenRow" 
					styleClass="formGrid">
				<%-- First Number--%>
				<h:outputLabel value="First Number" for="firstNumber" 
								styleClass="#{calculatorController.firstNumberStyleClass}"/>
				<h:inputText id="firstNumber" label="First Number"
					value="#{calculatorController.calculator.firstNumber}" required="true"
					binding="#{calculatorController.firstNumberInput}" />
				<h:message for="firstNumber" errorClass="errorClass"/>
				
				
				<%-- Second Number--%>
				<h:outputLabel id="snl" value="Second Number" for="secondNumber"
								styleClass="#{calculatorController.secondNumberStyleClass}"/>
				<h:inputText id="secondNumber" label="Second Number"
					value="#{calculatorController.calculator.secondNumber}" required="true"
					binding="#{calculatorController.secondNumberInput}"/>
				<h:message for="secondNumber" errorClass="errorClass"/>
		</h:panelGrid>
		<div>
			<h:commandButton action="#{calculatorController.add}"  value="Add" />
			<h:commandButton action="#{calculatorController.multiply}"  value="Multiply" />
			<h:commandButton action="#{calculatorController.divide}"  value="Divide" />
			<h:commandButton action="#{calculatorController.clear}"  value="Clear" immediate="true"/>
			<h:commandButton action="#{calculatorController.print}"  value="Print" immediate="true"/>
			<h:commandButton action="#{calculatorController.simpleTest}"  value="SimpleTest" immediate="true"/>
			<h:commandButton action="HOME" value="Home" immediate="true"/>
		</div>
	</h:form>
</f:view>

</body>
</html>