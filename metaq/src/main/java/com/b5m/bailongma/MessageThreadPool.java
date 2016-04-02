package com.b5m.bailongma;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public enum MessageThreadPool {
	instance;
	public ThreadPoolExecutor pool = new ThreadPoolExecutor(20, 100, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(500), new ThreadPoolExecutor.CallerRunsPolicy());

}
