<<<<<<< HEAD
package prop;  

import java.io.IOException;  
import java.io.InputStream;  
import java.util.Properties;  

// 參考 http://www.jz123.cn/text/1824271.html  
public class PropertiesWithStream {  
    public static void main(String[] args) throws IOException {  
        PropertiesWithStream propLoader = new PropertiesWithStream();  
          
        System.out.println("--- Properties from Class.getResourceAsStream ---");  
        // 注意，class里的getResourceAsStream()方法里参数的类路径一定要在前面加上"/"，否则会报错  
        InputStream in = propLoader.getClass().getResourceAsStream(  
                "/config/a.properties");  
        displayAllProperties(getPropertiesFromInputStream(in));  

        System.out.println("--- Properties from ClassLoader.getResourceAsStream ---");  
        // 注意，ClassLoader的getResourceAsStream()方法里参数的类路径一定不要在前面加上"/"，否则会报错，是不是很奇怪。  
        in = propLoader.getClass().getClassLoader().getResourceAsStream(  
                "config/a.properties");  
        displayAllProperties(getPropertiesFromInputStream(in));  
    }  

    private static Properties getPropertiesFromInputStream(InputStream in)  
            throws IOException {  
        Properties prop = new Properties();  
        prop.load(in);  
        return prop;  
    }  

    private static void displayAllProperties(Properties prop) {  
        for (Object key : prop.keySet())  
            System.out.println(key + " : " + prop.getProperty(key.toString()));  
    }  
=======
package prop;  

import java.io.IOException;  
import java.io.InputStream;  
import java.util.Properties;  

// 參考 http://www.jz123.cn/text/1824271.html  
public class PropertiesWithStream {  
    public static void main(String[] args) throws IOException {  
        PropertiesWithStream propLoader = new PropertiesWithStream();  
          
        System.out.println("--- Properties from Class.getResourceAsStream ---");  
        // 注意，class里的getResourceAsStream()方法里参数的类路径一定要在前面加上"/"，否则会报错  
        InputStream in = propLoader.getClass().getResourceAsStream(  
                "/config/a.properties");  
        displayAllProperties(getPropertiesFromInputStream(in));  

        System.out.println("--- Properties from ClassLoader.getResourceAsStream ---");  
        // 注意，ClassLoader的getResourceAsStream()方法里参数的类路径一定不要在前面加上"/"，否则会报错，是不是很奇怪。  
        in = propLoader.getClass().getClassLoader().getResourceAsStream(  
                "config/a.properties");  
        displayAllProperties(getPropertiesFromInputStream(in));  
    }  

    private static Properties getPropertiesFromInputStream(InputStream in)  
            throws IOException {  
        Properties prop = new Properties();  
        prop.load(in);  
        return prop;  
    }  

    private static void displayAllProperties(Properties prop) {  
        for (Object key : prop.keySet())  
            System.out.println(key + " : " + prop.getProperty(key.toString()));  
    }  
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
}  