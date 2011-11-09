<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Calculator Application</title>
</head>
<body>
<f:view>
	<h:form id="calcForm">
		<h4>Calculator</h4>
		<table>
			<tr>
				<td><h:outputLabel value="First Number" for="firstNumber" /></td>
				<td><h:inputText id="firstNumber"
					value="#{calculator.firstNumber}" required="true" /></td>
				<td><h:message for="firstNumber" /></td>
			</tr>

			<tr>
				<td><h:outputLabel value="Second Number" for="secondNumber" />
				</td>
				<td><h:inputText id="secondNumber"
					value="#{calculator.secondNumber}" required="true" /></td>
				<td><h:message for="secondNumber" /></td>
			</tr>
		</table>
		<div>
		
			<h:commandButton action="#{calculator.add}"  value="Add" />
			<h:commandButton action="#{calculator.multiply}"  value="Multiply" />
			<h:commandButton action="#{calculator.clear}"  value="Clear" immediate="true"/>
		</div>

	</h:form>

	<h:panelGroup rendered="#{calculator.result != 0}">
		<h4>Results</h4>
		<table>
			<tr><td>
				First Number  ${calculator.firstNumber}
			</td></tr>
			<tr><td>
				Second Number  ${calculator.secondNumber}
			</td></tr>
			<tr><td>
				Result  ${calculator.result}
			</td></tr>
		</table>
	</h:panelGroup>
</f:view>

</body>
</html>