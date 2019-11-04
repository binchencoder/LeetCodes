package com.binchencoder.study.concurrent.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenbin on 2017/6/14.
 */
public class TaskExecutorService extends ThreadPoolExecutor {

	private static final Logger logger = LoggerFactory.getLogger(TaskExecutorService.class);

	public TaskExecutorService(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit
			unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		this.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
	}

	@Override
	protected void afterExecute(Runnable runnable, Throwable throwable) {
		super.afterExecute(runnable, throwable);

		if (null != throwable && throwable instanceof Future<?>) {
			try {
				Future<?> future = (Future<?>) runnable;
				if (future.isDone()) {
					future.get();
				}
			} catch (CancellationException e) {
				throwable = e;
			} catch (ExecutionException e) {
				throwable = e.getCause();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		if (throwable != null) {
			logger.error("Uncaught exception", throwable);
		}
	}
}
