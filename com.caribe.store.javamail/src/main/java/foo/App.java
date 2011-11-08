<<<<<<< HEAD
<<<<<<< HEAD
package foo;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
=======
package foo;

import java.util.Timer;

public class App {

	public static void main(String[] args) {
		Timer timer=new Timer();   
		timer.schedule(new AppTask(),1000,1000*60*60);   
	}
}
>>>>>>> 5233cf07bab044b6b2d6fda7befca71d17204def
=======
package foo;

import java.util.Timer;

public class App {

	public static void main(String[] args) {
		Timer timer=new Timer();   
		timer.schedule(new AppTask(),1000,1000*60*60);   
	}
}
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
