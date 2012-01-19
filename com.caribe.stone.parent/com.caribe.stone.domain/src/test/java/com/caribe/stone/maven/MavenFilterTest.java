package com.caribe.stone.maven;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import junit.framework.Assert;

import org.junit.Test;

public class MavenFilterTest {
	@Test
	public void testMavenFilter() throws Exception {
		InputStream is = MavenFilterTest.class.getResourceAsStream("/mavenTest/testMavenFilter.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		Assert.assertEquals("com.caribe.stone.jpa2", br.readLine());
	}
	@Test
	public void testMavenFilterSpringConfig() throws Exception {
		InputStream is = MavenFilterTest.class.getResourceAsStream("/JPA-CONFIG/spring/context.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		while(true){
			String s = br.readLine();
			if(s==null){
				break;
			}
			System.out.println(s);
		}
	}
}
