package com.arcmind.jsfquickstart.controller;

import java.util.Date;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import com.arcmind.jsfquickstart.model.Calculator;

public class CalculatorController {

	private Calculator calculator;
	private UIInput firstNumberInput;
	private UIInput secondNumberInput;

	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}

	public Calculator getCalculator() {
		return calculator;
	}

	public String add() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		try {
			calculator.add();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Added successfully", null));

		} catch (Exception ex) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
		}
		return "results";
	}

	public String multiply() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		try {
			calculator.multiply();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Multiplied successfully", null));

		} catch (Exception ex) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
		}
		return "results";
	}

	public String divide() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		try {
			calculator.divide();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Divided successfully", null));

		} catch (Exception ex) {
			if (ex instanceof ArithmeticException) {
				secondNumberInput.setValue(Integer.valueOf(1));
			}
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
		}
		return "results";
	}

	public String clear() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		try {
			calculator.clear();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Results cleared", null));

		} catch (Exception ex) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
		}
		return null;
	}

	public String print() {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		String fileName = "simple.xml";
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/x-msdownload;charset=UTF-8");
		FacesContext.getCurrentInstance().responseComplete();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Application application = facesContext.getApplication();
		NavigationHandler navigationHandler = application.getNavigationHandler();
//		System.out.println("out come:"+outcome);
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "sssbb", "sssbbbbb123"));
		navigationHandler.handleNavigation(facesContext, null, null);
		// Render Response if needed
		facesContext.renderResponse();
		System.out.println(999);
		return null;
	}

	public String simpleTest() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Application application = facesContext.getApplication();
		NavigationHandler navigationHandler = application.getNavigationHandler();
		navigationHandler.handleNavigation(facesContext, null, "simpleTest");
//		facesContext.renderResponse();
		System.out.println(new Date());
//		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//		String fileName = "simple.xml";
//		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
//		response.setContentType("application/x-msdownload;charset=UTF-8");
//		FacesContext.getCurrentInstance().responseComplete();
		return null;
	}
	
	public String getFirstNumberStyleClass() {
		if (firstNumberInput.isValid()) {
			return "labelClass";
		} else {
			return "errorClass";
		}
	}

	public String getSecondNumberStyleClass() {
		if (secondNumberInput.isValid()) {
			return "labelClass";
		} else {
			return "errorClass";
		}
	}

	public UIInput getFirstNumberInput() {
		return firstNumberInput;
	}

	public void setFirstNumberInput(UIInput firstNumberInput) {
		this.firstNumberInput = firstNumberInput;
	}

	public UIInput getSecondNumberInput() {
		return secondNumberInput;
	}

	public void setSecondNumberInput(UIInput secondNumberInput) {
		this.secondNumberInput = secondNumberInput;
	}

}
