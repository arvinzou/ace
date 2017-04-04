package com.huacainfo.ace.common.model;

import java.io.Serializable;

public class Tuple <A,B> implements Serializable{

	public Tuple() {

	}
	
	public Tuple(A a, B b) {
		super();
		this.a = a;
		this.b = b;
	}
	A a;
	B b;
	public A getA() {
		return a;
	}
	public void setA(A a) {
		this.a = a;
	}
	public B getB() {
		return b;
	}
	public void setB(B b) {
		this.b = b;
	}
	
}
