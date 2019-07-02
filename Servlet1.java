package com.fang;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServlet;

public class Servlet1 extends HttpServlet{
	
	public static void main(String[] args) {
		AtomicInteger integ = new AtomicInteger(0);
		int incrementAndGet = integ.incrementAndGet();
		System.out.println(incrementAndGet);
	}
}
