package schema;
/**
 * 
 * @author web
 * @desc 建造者模式
 * 
 */
public class DemoSchema {

	public static void main(String[] args) {
		
		User user =new User.UserBuilder("web1992", "1992").address("beijing").age(20).build();
		System.out.println(user);
	}

}
