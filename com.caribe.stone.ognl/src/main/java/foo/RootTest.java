package foo;

import junit.framework.TestCase;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.SimpleNode;

public class RootTest extends TestCase {

	private OgnlContext context;

	private static Root ROOT = new Root();

	public void testProperties() throws Exception {
		SimpleNode expression;
		context=new OgnlContext();

		// 直接用Root中map属性的名字来访问
		expression = (SimpleNode) Ognl.parseExpression("map");
		assertTrue(Ognl.getValue(expression, context, ROOT) == ROOT.getMap());
		
		System.out.println("---------------------------------------");
		System.out.println(Ognl.getValue(Ognl.parseExpression("map.size"),ROOT));
		System.out.println("---------------------------------------");
		// 访问Root中map属性的test属性
		expression = (SimpleNode) Ognl.parseExpression("map.test");
		assertTrue(Ognl.getValue(expression, context, ROOT).equals(ROOT));
		// 上面用map.test来访问，现在用下标形式访问
		expression = (SimpleNode) Ognl.parseExpression("map[\"test\"]");
		assertTrue(Ognl.getValue(expression, context, ROOT).equals(ROOT));
		// 跟上面的是一样的，这里演示了下标计算之后，访问到的值
		expression = (SimpleNode) Ognl.parseExpression("map[\"te\" + \"st\"]");
		assertTrue(Ognl.getValue(expression, context, ROOT).equals(ROOT));
		/*
		 * 来看看对size的访问，这里有看头，在初始化的时候是map.put( "size", new Integer(5000) );
		 * 很自然我们会想到用map.size或者map["size"]来访问，显然没有问题这里要演示的是，怎样访问静态变量，在Root中定义了：
		 * public static final String SIZE_STRING = "size";
		 * 我们不可以用map[Root.SIZE_STRING]的形式访问吗？写成下面的形式：expression = (SimpleNode)
		 * Ognl.parseExpression("map[Root.SIZE_STRING]");
		 * OGNL就会认为有Root.SIZE_STRING这样一个对象是map的属性
		 * ，而不是先去解释Root.SIZE_STRING为字符串size的看看下面是怎么办的,@做为静态导航
		 */
//		expression = (SimpleNode) Ognl.parseExpression("map[@net.wide.ognl.bean.Root@SIZE_STRING]");
//		System.out.println(Ognl.getValue(expression, context, ROOT));

		// 下面通过下标访问List或者数组
		expression = (SimpleNode) Ognl.parseExpression("map.array[0]");// map.list[1]
		System.out.println(Ognl.getValue(expression, context, ROOT));

		/*
		 * 对DynamicSubscript的测试 先看看它的代码： switch (flag) { case FIRST: return "^";
		 * case MID: return "|"; case LAST: return "$"; case ALL: return "*";
		 * default: return "?"; // Won't happen } 很清楚了！下面来试试 在Root中有这么一个初始化的地方：
		 * map.put( DynamicSubscript.first, new Integer(99) ); 我们通过OGNL表达式怎么访问呢？
		 * 
		 * 对于一个数组或List应用上面的表达式，则是取出在这个列表中对应位置的元素
		 * 在Map中我们需要显示地使用DynamicSubscript.first等做为key才能取得到值
		 */
		expression = (SimpleNode) Ognl.parseExpression("map[^]");

		System.out.println("first-^:" + Ognl.getValue(expression, context, ROOT));

		expression = (SimpleNode) Ognl.parseExpression("map.array[|]");

		System.out.println("middle-|:" + Ognl.getValue(expression, context, ROOT));

		expression = (SimpleNode) Ognl.parseExpression("map.array[$]");

		System.out.println("last-$:" + Ognl.getValue(expression, context, ROOT));

		expression = (SimpleNode) Ognl.parseExpression("map.array[*]");

		System.out.println("all-*:" + Ognl.getValue(expression, context, ROOT));

		// 测试数组或列表的伪属性
		expression = (SimpleNode) Ognl.parseExpression("map.array.length");

		System.out.println("array length:" + Ognl.getValue(expression, context, ROOT));

		/*
		 * 看看下面有这么一个东东： map.(array[2] + size()).doubleValue()
		 * 在前面的学习中，我们了解了OGNL的导航链，解析链中的属性或方法都是基于当前解释出来的结果的
		 * 因此array[2]就是map.array[2] size()就是map.size() 他们相加转换成Double型。 看看结果是:8.0
		 */
		expression = (SimpleNode) Ognl.parseExpression("map.(array[2] + size()).doubleValue()");

		System.out.println("map.(array[2] + size()).doubleValue():" + Ognl.getValue(expression, context, ROOT));

		// map.(#this),this是对自身的引用，另外注意在变量名前加#符号，这个变量在这个表达式里面是全局的

		expression = (SimpleNode) Ognl.parseExpression("map.(#this)");

		System.out.println("map.(#this):" + Ognl.getValue(expression, context, ROOT));

		// 几个OGNL表达式，下面的意思是，测试map的第一个元素是否为空，如果为空则返回empty否则返回该对象

		// 这个写法我们非常熟悉，无论是java还是c都有这种写法

		expression = (SimpleNode) Ognl.parseExpression("map[^].(#this == null ? 'empty' : #this)");

		System.out.println("map[^].(#this == null ? 'empty' : #this):" + Ognl.getValue(expression, context, ROOT));

	}

}