package edu.westga.cs4225.project1.group6.client;

/**
 * This object can run a Runnable on a new thread. Then,
 * when it is finished, it can call a callback runnable to 
 * execute code when it is complete.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public final class SignalThread {
	
	private Runnable onComplete;

	/**
	 * Sets the callback runnable. This is executed when
	 * the Runnable passed to start(Runnable) finishes.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param onComplete the callback runnable.
	 */
	public void setOnComplete(Runnable onComplete) {
		this.onComplete = onComplete;
		
	}
	
	/**
	 * Starts the specified runnable on a new thread. When it
	 * finishes, if a callback has been set, it runs the callback.
	 * 
	 * @precondition action != null
	 * @postcondition none
	 * 
	 * @param action the primary runnable.
	 */
	public void start(Runnable action) {
		if (action == null) {
			throw new IllegalArgumentException("action should not be null.");
		}
		
		Thread actionThread = new Thread(() -> {
			action.run();
			if (this.onComplete != null) {
				this.onComplete.run();
			}
		});
		actionThread.start();
	}
}
