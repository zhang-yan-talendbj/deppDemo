package com.caribe.stone.junit;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class SeleniumScreenShotRunner extends BlockJUnit4ClassRunner {
	public SeleniumScreenShotRunner(Class<?> clz) throws InitializationError {
		super(clz);
	}

	@Override
	protected Statement methodInvoker(FrameworkMethod method, Object test) {
		ScreenShotStatement statement = new ScreenShotStatement(super.methodInvoker(method, test), test,method.getName());
		return statement;
	}
}