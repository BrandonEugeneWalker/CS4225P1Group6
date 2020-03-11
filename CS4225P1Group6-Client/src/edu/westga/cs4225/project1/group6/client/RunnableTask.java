package edu.westga.cs4225.project1.group6.client;

import java.util.function.Consumer;

public class RunnableTask implements Runnable {

	private Runnable action;
	private Consumer<RunnableTask> callback;
	
	public RunnableTask(Runnable action, Consumer<RunnableTask> callback) {
		this.action = action;
		this.callback = callback;
	}
	
	@Override
	public void run() {
		this.action.run();
		this.callback.accept(this);
	}

}
