package com.spring.config;

public class Emp {
	
	int id;
	String name;
	int Age;
	
   public Emp(int i,String n,int a) {
	   this.id=i;
	   this.name=n;
	   this.Age=a;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getAge() {
	return Age;
}

public void setAge(int age) {
	Age = age;
}
   
   

}
