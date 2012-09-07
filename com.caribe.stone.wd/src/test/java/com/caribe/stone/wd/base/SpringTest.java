package com.caribe.stone.wd.base;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public abstract class SpringTest extends AbstractJUnit4SpringContextTests {
}
