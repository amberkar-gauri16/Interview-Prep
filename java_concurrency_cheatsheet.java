// 🧠 Java Concurrency + Interview Prep Cheatsheet (with Examples, Pros & Cons)

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;
import java.util.regex.*;

// ✅ THREAD CREATION
Thread t = new Thread(() -> {
    System.out.println("Running in thread: " + Thread.currentThread().getName());
});
t.start();
t.join();
// Pros: Simple, low overhead
// Cons: No thread pooling, manual management

// ✅ EXECUTOR SERVICE
ExecutorService executor = Executors.newFixedThreadPool(4);
executor.submit(() -> System.out.println("Task running in thread pool"));
executor.shutdown();
// Pros: Thread reuse, scalable
// Cons: Manual shutdown needed

// ✅ CALLABLE + FUTURE
Callable<String> task = () -> "result";
Future<String> future = executor.submit(task);
String result = future.get();
// Pros: Return values from async tasks
// Cons: Future.get() blocks

// ✅ COMPLETABLEFUTURE
CompletableFuture.supplyAsync(() -> "data")
        .thenApply(String::toUpperCase)
        .thenAccept(System.out::println);
// Pros: Async chaining, non-blocking
// Cons: Debugging chain can be tricky

// ✅ SYNCHRONIZED METHOD
public synchronized void increment() {
    count++;
}
// Pros: Easy to use
// Cons: Whole method locked

// ✅ SYNCHRONIZED BLOCK
synchronized (this) {
    sharedResource.doSomething();
}
// Pros: Finer control
// Cons: Risk of deadlocks

// ✅ WAIT/NOTIFY
synchronized(lock) {
    while (!condition) lock.wait();
    lock.notifyAll();
}
// Pros: Coordination
// Cons: Risk of missed signals

// ✅ REENTRANT LOCK
Lock lock = new ReentrantLock();
lock.lock();
try {
    // critical section
} finally {
    lock.unlock();
}
// Pros: Fairness, tryLock
// Cons: Must release manually

// ✅ COUNTDOWNLATCH
CountDownLatch latch = new CountDownLatch(3);
Runnable worker = () -> {
    doWork();
    latch.countDown();
};
executor.submit(worker);
latch.await();
// Pros: Wait for group completion
// Cons: One-time use

// ✅ SEMAPHORE
Semaphore semaphore = new Semaphore(3);
semaphore.acquire();
try {
    // access resource
} finally {
    semaphore.release();
}
// Pros: Limit access
// Cons: May block

// ✅ BLOCKING QUEUE
BlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
queue.put("data");
String data = queue.take();
// Pros: Blocking behavior
// Cons: Bounded queues can block

// ✅ ATOMIC VARIABLE
AtomicInteger counter = new AtomicInteger(0);
counter.incrementAndGet();
// Pros: Lock-free
// Cons: Limited to primitives

// ✅ CONCURRENT COLLECTIONS
Map<String, String> cmap = new ConcurrentHashMap<>();
Queue<Integer> cqueue = new ConcurrentLinkedQueue<>();
Deque<String> cdeque = new ConcurrentLinkedDeque<>();
// Pros: Thread-safe
// Cons: May not suit complex sync

// ✅ COLLECTION INTERFACES
List<String> list = new ArrayList<>();
Set<String> set = new HashSet<>();
Map<String, Integer> countMap = new HashMap<>();
Queue<String> lqueue = new LinkedList<>();
Deque<String> adeque = new ArrayDeque<>();
// Pros: Standard data types
// Cons: Not thread-safe by default

// ✅ STREAMS API
List<String> names = Arrays.asList("alice", "bob", "carol");
names.stream().map(String::toUpperCase).forEach(System.out::println);
// Pros: Clean, declarative
// Cons: Harder to debug

// ✅ COLLECTORS
Map<String, Long> counts = names.stream()
    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
// Pros: Complex grouping/aggregation
// Cons: Verbose

// ✅ OPTIONAL
Optional<String> nameOpt = Optional.of("value");
nameOpt.ifPresent(System.out::println);
String defaultName = nameOpt.orElse("default");
// Pros: Null-safe
// Cons: Shouldn't be used for fields

// ✅ REGEX - PATTERN & MATCHER
String logLine = "[2025-06-01] GET /api/user/123 HTTP/1.1 200";
Pattern pattern = Pattern.compile("\\[(.*?)\\] (\\w+) (.+?) HTTP.* (\\d+)");
Matcher matcher = pattern.matcher(logLine);
if (matcher.find()) {
    String method = matcher.group(2);
    String endpoint = matcher.group(3);
    String status = matcher.group(4);
}
// Pros: Flexible parsing
// Cons: Readability

// 💡 Example: Normalize and count events
List<String> logLines = List.of(
    "[2025-06-01] GET /api/user/123 HTTP/1.1 200",
    "[2025-06-01] GET /api/user/456 HTTP/1.1 200",
    "[2025-06-01] POST /api/user HTTP/1.1 201"
);
Map<String, Long> eventCount = logLines.stream()
    .map(line -> {
        Matcher m = pattern.matcher(line);
        if (m.find()) {
            return m.group(2) + " " + m.group(3).replaceAll("/\\d+", "/#") + " " + m.group(4);
        }
        return null;
    })
    .filter(Objects::nonNull)
    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
