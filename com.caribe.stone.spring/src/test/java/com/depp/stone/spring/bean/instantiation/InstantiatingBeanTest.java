package com.depp.stone.spring.bean.instantiation;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration
public class InstantiatingBeanTest extends AbstractJUnit4SpringContextTests {

=======
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;


@ContextConfiguration
public class InstantiatingBeanTest extends AbstractJUnit4SpringContextTests {

	

>>>>>>> 5233cf07bab044b6b2d6fda7befca71d17204def
	@Autowired
	private ConstructorBean constructorBean3;
	@Autowired
	private String instance;
	@Autowired
	private ConstrtorBean constrtorBean;
	@Autowired
	private ConstrtorBean constrtorBean2;
	@Autowired
	private Bean bean;
	@Autowired
	private CollectionBean collectionBean;
	@Autowired
	private Bean objectNull;
	@Autowired
	private Bean pNamespaceBean;
	@Autowired
	private Bean singletonBean;
	@Autowired
	private Bean prototypeBean;
	@Autowired
	private String instance2;
	@Autowired
	private LookupBean lookupBean;
	@Autowired
	private InitBean initBean;
<<<<<<< HEAD

=======
	
>>>>>>> 5233cf07bab044b6b2d6fda7befca71d17204def
	public InitBean getInitBean() {
		return initBean;
	}

	public void setInitBean(InitBean initBean) {
		this.initBean = initBean;
	}

	public LookupBean getLookupBean() {
		return lookupBean;
	}

	public void setLookupBean(LookupBean lookupBean) {
		this.lookupBean = lookupBean;
	}

	public Bean getSingletonBean() {
		return singletonBean;
	}

	public void setSingletonBean(Bean singletonBean) {
		this.singletonBean = singletonBean;
	}

	public Bean getPrototypeBean() {
		return prototypeBean;
	}

	public void setPrototypeBean(Bean prototypeBean) {
		this.prototypeBean = prototypeBean;
	}

	public Bean getpNamespaceBean() {
		return pNamespaceBean;
	}

	public void setpNamespaceBean(Bean pNameSpaceBean) {
		this.pNamespaceBean = pNameSpaceBean;
	}

	public Bean getObjectNull() {
		return objectNull;
	}

	public void setObjectNull(Bean objectNull) {
		this.objectNull = objectNull;
	}

	public CollectionBean getCollectionBean() {
		return collectionBean;
	}

	public void setCollectionBean(CollectionBean collectionBean) {
		this.collectionBean = collectionBean;
	}

	public Bean getBean() {
		return bean;
	}

	public void setBean(Bean bean) {
		this.bean = bean;
	}

	public ConstrtorBean getConstrtorBean2() {
		return constrtorBean2;
	}

	public void setConstrtorBean2(ConstrtorBean constrtorBean2) {
		this.constrtorBean2 = constrtorBean2;
	}

	public String getInstance2() {
		return instance2;
	}

	public void setInstance2(String instance2) {
		this.instance2 = instance2;
	}

	public ConstructorBean getConstructorBean3() {
		return constructorBean3;
	}

	public String getInstance() {
		return instance;
	}

	public ConstrtorBean getConstrtorBean() {
		return constrtorBean;
	}

	public void setConstructorBean3(ConstructorBean bean) {
		this.constructorBean3 = bean;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	public void setConstrtorBean(ConstrtorBean string) {
		this.constrtorBean = string;
	}

	@Test
	public void testBean() throws Exception {
		assertNotNull(getConstructorBean3());
		assertNotNull(getConstrtorBean());
		assertNotNull(getConstrtorBean2());
		assertEquals("instance", getInstance());
		assertEquals("instance factory method", getInstance2());
		assertEquals("bar", bean.getProperty());
		assertEquals("admin",
				getCollectionBean().getProperties().getProperty("admin"));
		assertEquals("value", getCollectionBean().getMap().get("key"));
		assertEquals(3, getCollectionBean().getSet().size());
		assertEquals(null, getObjectNull().getBar());
		assertEquals("pNamespaceProperty", getpNamespaceBean().getProperty());
		assertNotNull(getpNamespaceBean().getBar());
		assertSame(applicationContext.getBean("singletonBean"),
				applicationContext.getBean("singletonBean"));
		assertNotSame(applicationContext.getBean("prototypeBean"),
				applicationContext.getBean("prototypeBean"));
		assertNotSame(getLookupBean().createCommander(), getLookupBean().createCommander());
		assertEquals("have been initialed", getInitBean().getInitMsg());
		// TODO Collection merging
		// TODO Custom scope
<<<<<<< HEAD

=======
>>>>>>> 5233cf07bab044b6b2d6fda7befca71d17204def
	}

}
