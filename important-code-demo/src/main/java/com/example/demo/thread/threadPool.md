#### 线程池

##### 1:线程池的作用？
    1. 降低资源消耗。减少了创建和销毁线程的次数，每个工作线程都可以被重复利用，可执行多个任务。 
    2. 提高响应速度。当任务到达时，任务可以不需要的等到线程创建就能立即执行。
    3. 提高线程的可管理性。可以根据系统的承受能力，调整线程池中工作线线程的数目，防止因为消耗过多的内存;