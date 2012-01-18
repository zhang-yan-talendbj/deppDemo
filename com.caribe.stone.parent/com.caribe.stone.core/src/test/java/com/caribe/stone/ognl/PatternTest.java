package com.caribe.stone.ognl;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.junit.Test;

public class PatternTest {

	@Test
	public void testName() throws Exception {
//		Pattern p = Pattern.compile("##*##");
//		Assert.assertFalse(p.matcher("####").matches());
//		Assert.assertTrue(p.matcher("1##a##").matches());
//		Assert.assertTrue(p.matcher("##abcdefg##").matches());
//		Matcher matcher = p.matcher("##_ab_##aaa##?cdefg##");
//		System.out.println(matcher.find());
//		System.out.println(matcher.group());
		Pattern p1 = Pattern.compile("##[^#]+##");
		Matcher m1 = p1.matcher("##aa##aabbccdeadfe##aa_bb##");
		System.out.println(m1.find());
		System.out.println(m1.group());
		System.out.println(m1.find());
		System.out.println(m1.group());
		
//		Matcher m = matcher;
//		StringBuffer sb = new StringBuffer();
//		System.out.println(m.appendTail(sb));
//		System.out.println(sb);
//		System.out.println(m.groupCount());
//		
//		System.out.println(m.find());
//		System.out.println(m.group());
//		System.out.println(m.find());
//		System.out.println(m.group());
	}
}
