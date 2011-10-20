package foo;

public class HelloServiceImpl implements HelloService {

	
	@Override
	public String say() {
		System.out.println("HelloWorld!");
		return "HelloWorld!";
	}
}
