package com.monx.BE_monxi.models;

public class Response<T> {
	public String msg;
	public boolean valid = true;
	public T body;
	public long tsIn = System.nanoTime();
	public long tsOut;
	public long duration;
	
	public Response() {	}
	
	public Response<T> send() {
		tsOut = System.nanoTime();
		duration = tsOut - tsIn;
		return this;
	}
}
