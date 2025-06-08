package org.cultro.helix.util;

import org.cultro.helix.lang.Validate;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("unused")
public final class ThreadUtils {

    private ThreadUtils() {
    }

    /**
     * Sleeps the current thread for the specified number of milliseconds.
     * Any {@link InterruptedException} thrown is caught and the interrupt
     * status is restored.
     *
     * @param millis the time to sleep in milliseconds
     */
    public static void safeSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Sleeps the current thread for at least the specified time while
     * ignoring interruptions. The interrupt status is restored on exit.
     *
     * @param millis the time to sleep in milliseconds
     */
    public static void sleepUninterruptibly(long millis) {
        long end = System.currentTimeMillis() + millis;
        boolean interrupted = false;
        while (true) {
            long remaining = end - System.currentTimeMillis();
            if (remaining <= 0) {
                break;
            }
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                interrupted = true;
            }
        }
        if (interrupted) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Creates a thread factory that names threads using the supplied prefix.
     *
     * @param prefix the prefix to use when naming threads
     * @param daemon whether the threads should be daemon threads
     * @return a new ThreadFactory instance
     */
    public static ThreadFactory namedThreadFactory(final String prefix, final boolean daemon) {
        return new ThreadFactory() {
            private final AtomicInteger counter = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                if (prefix != null) {
                    thread.setName(prefix + "-" + counter.getAndIncrement());
                }
                thread.setDaemon(daemon);
                return thread;
            }
        };
    }

    /**
     * Creates a fixed size thread pool with threads named using the given prefix.
     *
     * @param threadCount the number of threads in the pool
     * @param prefix      the prefix for naming threads
     * @return a new ExecutorService
     */
    public static ExecutorService newFixedThreadPool(int threadCount, String prefix) {
        if (threadCount <= 0) {
            throw new IllegalArgumentException("Thread count must be positive");
        }
        return Executors.newFixedThreadPool(threadCount, namedThreadFactory(prefix, false));
    }

    /**
     * Creates a cached thread pool with threads named using the given prefix.
     *
     * @param prefix the prefix for naming threads
     * @return a new ExecutorService
     */
    public static ExecutorService newCachedThreadPool(String prefix) {
        return Executors.newCachedThreadPool(namedThreadFactory(prefix, false));
    }

    /**
     * Creates a single thread executor with a custom thread name prefix.
     *
     * @param prefix the prefix for naming the thread
     * @return a new ExecutorService
     */
    public static ExecutorService newSingleThreadExecutor(String prefix) {
        return Executors.newSingleThreadExecutor(namedThreadFactory(prefix, false));
    }

    /**
     * Creates a scheduled thread pool with threads named using the given prefix.
     *
     * @param corePoolSize the number of threads to keep in the pool
     * @param prefix       the prefix for naming threads
     * @return a new ScheduledExecutorService
     */
    public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize, String prefix) {
        if (corePoolSize <= 0) {
            throw new IllegalArgumentException("corePoolSize must be positive");
        }
        return Executors.newScheduledThreadPool(corePoolSize, namedThreadFactory(prefix, false));
    }

    /**
     * Gracefully shuts down the provided executor service, waiting for tasks
     * to complete within the given timeout.
     *
     * @param service the executor service to shut down
     * @param timeout the maximum time to wait
     * @param unit    the time unit of the timeout argument
     */
    public static void shutdownAndAwaitTermination(ExecutorService service, long timeout, TimeUnit unit) {
        Validate.notNull(service, "ExecutorService cannot be null");
        Validate.notNull(unit, "TimeUnit cannot be null");
        service.shutdown();
        try {
            if (!service.awaitTermination(timeout, unit)) {
                service.shutdownNow();
            }
        } catch (InterruptedException e) {
            service.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
