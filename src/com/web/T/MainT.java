package com.web.T;

import java.util.List;

public class MainT {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
// java  中类泛型的使用
class Person<T extends List<T>>{
		
	private T t;

	public Person() {
		super();
	}

	public Person(T t) {
		super();
		this.t = t;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
	
}
//java  中接口泛型的使用
interface PayUrl<T extends List ,U extends List>{

	public void pay(T t,U u);
	
}

class PayUrlImpl implements PayUrl<List<Person>,List<Person>>{

	@Override
	public void pay(List<Person> t, List<Person> u) {
		System.out.println("pay ...");
	}

	
	public static void main(String[] args) {
		PayUrlImpl payUrlImpl=new PayUrlImpl();
		payUrlImpl.pay(null, null);
		
	}
	
	
}




