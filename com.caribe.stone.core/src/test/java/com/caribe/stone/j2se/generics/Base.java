package com.caribe.stone.j2se.generics;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * -----------------------------------------
 * 
 * @描述 泛型的实际类型参数
 * @作者 fancy
 * @邮箱 fancydeepin@yeah.net
 * @日期 2012-8-25
 *     <p>
 *     -----------------------------------------
 */
public class Base<T> {

	private Class<T> entityClass;

	//代码块,也可将其放置到构造子中
    {
        //entityClass =(Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        try {
            Class<?> clazz = getClass(); //获取实际运行的类的 Class
            Type type = clazz.getGenericSuperclass(); //获取实际运行的类的直接超类的泛型类型
            if(type instanceof ParameterizedType){ //如果该泛型类型是参数化类型
                Type[] parameterizedType = ((ParameterizedType)type).getActualTypeArguments();//获取泛型类型的实际类型参数集
                entityClass = (Class<T>) parameterizedType[0]; //取出第一个(下标为0)参数的值
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            
    }

	// 泛型的实际类型参数的类全名
	public String getEntityName() {

		return entityClass.getName();
	}

	// 泛型的实际类型参数的类名
	public String getEntitySimpleName() {

		return entityClass.getSimpleName();
	}

	// 泛型的实际类型参数的Class
	public Class<T> getEntityClass() {
		return entityClass;
	}

}