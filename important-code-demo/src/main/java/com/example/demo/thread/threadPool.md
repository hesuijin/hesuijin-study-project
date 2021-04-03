#### 线程池

##### 1:线程池的作用？
    1. 降低资源消耗。减少了创建和销毁线程的次数，每个工作线程都可以被重复利用，可执行多个任务。 
    2. 提高响应速度。当任务到达时，任务可以不需要的等到线程创建就能立即执行。
    3. 提高线程的可管理性。可以根据系统的承受能力，调整线程池中工作线线程的数目，防止因为消耗过多的内存;
    
##### 2：线程池设计思路
        1. 准备一个任务容器
        2. 一次性启动多个(2个)消费者线程
        3. 刚开始任务容器是空的，所以线程都在wait
        4. 直到一个外部线程向这个任务容器中扔了一个"任务"，就会有一个消费者线程被唤醒
        5. 这个消费者线程取出"任务"，并且执行这个任务，执行完毕后，继续等待下一次任务的到来


##### 3；默认线程池
        //1,创建一个默认的线程池对象.池子中默认是空的.默认最多可以容纳int类型的最大值.
        ExecutorService executorService = Executors.newCachedThreadPool();
        //2.参数不是初始值而是最大值
        ExecutorService executorService = Executors.newFixedThreadPool(10);

##### 3：自定义线程池的关键参数
        public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler)

    corePoolSize：   核心线程的最大值，不能小于0
    maximumPoolSize：最大线程数，不能小于等于0，maximumPoolSize >= corePoolSize  （maximumPoolSize的数量包含了核心线程数的数量）
    keepAliveTime：  空闲线程最大存活时间,不能小于0
    unit：           时间单位
    workQueue：      任务队列，不能为null
    threadFactory：  创建线程工厂,不能为null
    handler：        任务的拒绝策略,不能为null

##### 4：线程池的4种放弃策略
    ThreadPoolExecutor.AbortPolicy: 		    丢弃任务并抛出RejectedExecutionException异常。是默认的策略。
    ThreadPoolExecutor.DiscardPolicy： 		   丢弃任务，但是不抛出异常 这是不推荐的做法。
    ThreadPoolExecutor.DiscardOldestPolicy：    抛弃队列中等待最久的任务 然后把当前任务加入队列中。
    ThreadPoolExecutor.CallerRunsPolicy:        调用任务的run()方法绕过线程池直接执行。
    
    （1）当前线程数小于核心线程数，当前线程直接运行。
    （2）当前线程数大于核心线程数，当前线程会加入到阻塞队列中，
    （3）此时阻塞队列未满，直接加入，等待机会运行。
     (4) 此时阻塞队列已满，但此时线程数小于最大线程数，则直接创建线程运行。
     (5）此时线程数大于等于最大线程数，则实行线程池自定义的拒绝策略。

##### 5实战分析
```java

public class ThreadPoolExecutorDemo {
    
      /**
        * 抛弃队列中等待最久的任务 然后把当前任务加入队列中
        */
       private static void discardOldestPolicyDemo() {
           ThreadPoolExecutor threadPoolExecutor;
           threadPoolExecutor = new ThreadPoolExecutor(1, 3, 20, TimeUnit.SECONDS,
                   new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());
           // 提交5个任务
   
        //由于任务2在线程池中等待时间最长，因此任务2被丢弃。 1 2 3 4 5
        // 1    小于等于进入核心线程数 直接执行
        // 2    阻塞队列未满  进入阻塞队列
        // 3 4  阻塞队列已满  最大线程数未来满  进入最大线程数  直接创建线程运行
        // 5    此时任务数大于等于  最大线程数+阻塞队列容量 ，则实行线程池自定义的拒绝策略。  把队列中的2挤出来
        // 当线程数  大于核心线程数的线程  超过空闲时间  都没有获取到队列 则关闭这些空闲线程
           
           for (int x = 1; x <= 5; x++) {
               // 定义一个变量，来指定指定当前执行的任务;这个变量需要被final修饰
               final int y = x;
               threadPoolExecutor.submit(() -> {
                   System.out.println(Thread.currentThread().getName() + "---->> 执行了任务" + y);
               });
           }
       }
 }   
```
##### 4：可阅读newThreadPool包下代码  