package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadManager {
    private static int nThreads = 4;
    private static final ExecutorService requestThreadPool = Executors.newFixedThreadPool(nThreads);

    public static ExecutorService getRequestThreadPool() {
        return requestThreadPool;
    }
}
