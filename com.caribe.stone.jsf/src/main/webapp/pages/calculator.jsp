<<<<<<< HEAD
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@taglib uri="../WEB-INF/myfaces_core.tld" prefix="f"%>
<%@taglib uri="../WEB-INF/myfaces_html.tld" prefix="h"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Calculator Application</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css" />
</head>
<body>
<f:view>
  <h:outputText>ggggg</h:outputText>
  <h:form id="calcForm">
    <h4>Calculator</h4>
    <h:panelGrid columns="3" rowClasses="oddRow, evenRow" styleClass="formGrid">
      <%-- First Number--%>
      <h:outputLabel value="First Number" for="firstNumber" />
      <h:inputText id="firstNumber" label="First Number" value="#{calculator.firstNumber}"
        required="true" requiredMessage="required" converterMessage="not a valid number"
      />
      <h:message for="firstNumber" />
      <%-- Second Number--%>
      <h:outputLabel value="Second Number" for="secondNumber" />
      <h:inputText id="secondNumber" label="Second Number" value="#{calculator.secondNumber}"
        required="true" requiredMessage="required" converterMessage="not a valid number"
      />
      <h:message for="secondNumber" />
    </h:panelGrid>
    <div><h:commandButton action="#{calculator.add}" value="Add" /> <h:commandButton
      action="#{calculator.multiply}" value="Multiply"
    /> <h:commandButton action="#{calculator.clear}" value="Clear" immediate="true" /></div>
  </h:form>
  <h:panelGroup rendered="#{calculator.result != 0}">
    <h4>Results</h4>
    <h:panelGrid columns="1" rowClasses="oddRow, evenRow" styleClass="resultGrid">
      <h:outputText value="First Number  #{calculator.firstNumber}" />
      <h:outputText value="Second Number #{calculator.secondNumber}" />
      <h:outputText value="Result  #{calculator.result}" />
    </h:panelGrid>
  </h:panelGroup>
  
</f:view>
</body>
=======
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@taglib uri="../WEB-INF/myfaces_core.tld" prefix="f"%>
<%@taglib uri="../WEB-INF/myfaces_html.tld" prefix="h"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Calculator Application</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css" />
</head>
<body>
<f:view>
  <h:outputText>ggggg</h:outputText>
  <h:form id="calcForm">
    <h4>Calculator</h4>
    <h:panelGrid columns="3" rowClasses="oddRow, evenRow" styleClass="formGrid">
      <%-- First Number--%>
      <h:outputLabel value="First Number" for="firstNumber" />
      <h:inputText id="firstNumber" label="First Number" value="#{calculator.firstNumber}"
        required="true" requiredMessage="required" converterMessage="not a valid number"
      />
      <h:message for="firstNumber" />
      <%-- Second Number--%>
      <h:outputLabel value="Second Number" for="secondNumber" />
      <h:inputText id="secondNumber" label="Second Number" value="#{calculator.secondNumber}"
        required="true" requiredMessage="required" converterMessage="not a valid number"
      />
      <h:message for="secondNumber" />
    </h:panelGrid>
    <div><h:commandButton action="#{calculator.add}" value="Add" /> <h:commandButton
      action="#{calculator.multiply}" value="Multiply"
    /> <h:commandButton action="#{calculator.clear}" value="Clear" immediate="true" /></div>
  </h:form>
  <h:panelGroup rendered="#{calculator.result != 0}">
    <h4>Results</h4>
    <h:panelGrid columns="1" rowClasses="oddRow, evenRow" styleClass="resultGrid">
      <h:outputText value="First Number  #{calculator.firstNumber}" />
      <h:outputText value="Second Number #{calculator.secondNumber}" />
      <h:outputText value="Result  #{calculator.result}" />
    </h:panelGrid>
  </h:panelGroup>
  
</f:view>
</body>
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
</html>