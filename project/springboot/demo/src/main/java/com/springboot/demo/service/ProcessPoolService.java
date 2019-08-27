package com.springboot.demo.service;


import org.springframework.stereotype.Service;

import java.util.concurrent.*;

//线程池服务类
@Service
public class ProcessPoolService {

    //线程池概念

    //合理利用线程池能够带来三个好处：
    //第一：降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的消耗。
    //第二：提高响应速度。当任务到达时，任务可以不需要等到线程创建就能立即执行。
    //第三：提高线程的可管理性。线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一的分配，调优和监控。

    public void ThreadPoolExecutor(){
        //ThreadPoolExecutor线程池创建类，以下参数
        //int corePoolSize,  核心执行线程数,线程池可同时执行任务数
        //int maximumPoolSize, 线程可最大容纳数
        //long keepAliveTime, 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间(意思是之前创建的线程执行完任务后，等待另外一个任务介入的等待时间，超过时间则销毁线程)
        //TimeUnit unit,  keepAliveTime单位

        //BlockingQueue<Runnable> workQueue, 阻塞队列, 存储要运行的任务(即是大于maximumPoolSize的线程任务),包含以下四种队列类型
            //ArrayBlockingQueue  基于数组的有界阻塞队列， 这是一个典型的“有界缓存区”，固定大小的数组在其中保持生产者插入的元素和使用者提取的元素。一旦创建了这样的缓存区，就不能再增加其容量。
            //LinkedBlockingQueue 基于链表的无界阻塞队列。与ArrayBlockingQueue一样采用FIFO原则对元素进行排序。基于链表的队列吞吐量通常要高于基于数组的队列(允许int最大值)。
            //SynchronousQueue  同步的阻塞队列。其中每个插入操作必须等待另一个线程的对应移除操作，等待过程一直处于阻塞状态，同理，每一个移除操作必须等到另一个线程的对应插入操作。
            //PriorityBlockingQueue 基于优先级的无界阻塞队列。优先级队列的元素按照其自然顺序进行排序，或者根据构造队列时提供的 Comparator 进行排序，具体取决于所使用的构造方法。

        //ThreadFactory threadFactory,  线程工厂，主要用来创建线程
        //RejectedExecutionHandler handler, 表示当拒绝处理任务时的策略


        //ThreadPoolExecutor的corePoolSize和maximumPoolSize关系
        //如果池中的实际线程数小于corePoolSize,无论是否其中有空闲的线程，都会给新的任务产生新的线程
        //如果池中的线程数>corePoolSize and <maximumPoolSize,而又有空闲线程，就给新任务使用空闲线程，如没有空闲线程，则产生新线程
        //如果池中的线程数＝maximumPoolSize，则有空闲线程使用空闲线程，否则新任务放入workQueue。（线程的空闲只有在workQueue中不再有任务时才成立）


        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 5, 200, TimeUnit.MILLISECONDS,
                //new ArrayBlockingQueue<Runnable>(5)
                new LinkedBlockingQueue<>(5));

        //注意:！！！！！！！！！
        // 1.要叠加的线程数不能大于maximumPoolSize + BlockingQueue的数量，否则多出来的线程无法处理,并提示异常RejectedExecutionHandler
        // 2.这里因为使用循环叠加线程,所以一开始建好的线程可能已经运行成功，进入空闲状态，该线程就可以直接提供给其他任务使用,所以循环10次,不一定会生成10个线程。
        // 3.线程池的运行顺序, 核心线程数(2)--->任务阻塞队列(5)--->剩余任务数(10-核心线程数-任务阻塞队列=3),详情看priture，

        //  详述第三点线程池原理: 线程池先会创建核心线程数(corePoolSize)个线程,并且同时运行;
        //  如果 多出来的任务(即是 任务数>corePoolSize),将会加载到缓存阻塞队列中,等待核心线程空闲再运行;
        //  如果 任务数量>核心线程数+阻塞队列 并且 <最大线程数(maximumPoolSize),将会创建新的线程(任务数量-corePoolSize-quene 个);
        //  如果 任务数量>最大线程数+阻塞队列，则运行饱和策略(不接受任务或者抛出异常)
        for (int i=0;i<10;i++){
            ThreadPoolExecutorEntity poolEntity = new ThreadPoolExecutorEntity(i);
            //注意submit和execute都能执行线程池中的线程（包括创建线程,运行线程方法）,区别:submit能实现Callable中的call方法并且有返回值,execute只能执行Runable中的run方法
            //poolExecutor.submit(poolEntity);
            poolExecutor.execute(poolEntity);
            System.out.println("线程池中线程数目："+ poolExecutor.getPoolSize()+"，队列中等待执行的任务数目："+ poolExecutor.getQueue().size()+"，已执行完别的任务数目："+ poolExecutor.getCompletedTaskCount());
        }
        poolExecutor.shutdown(); //关闭线程池,将线程池中的空闲线程回收
    }

    //建议使用以下方法创建线程池
    //实现线程池的其他四种不同的功能(使用Executors静态调用生成方法，其实就是ThreadPoolExecutorEntity里面配好了某种参数)
    public void OtherThreadPoolExecutor(){
        //1.创建固定容量大小的缓冲池（队列使用LinkedBlockingQueue无界队列）
        ExecutorService newFixedThreadPool =  Executors.newFixedThreadPool(2);

        //2.创建只有一个线程的线程池(单线程池，队列使用LinkedBlockingQueue无界队列)
        ExecutorService newSingleThreadExecutor =  Executors.newSingleThreadExecutor();

        //3.创建一个不限线程数上限的线程池，任何提交的任务都将立即执行（队列使用SynchronousQueue同步队列)
        // 试图缓存线程并且重用,无缓存线程时，将会新建哦你工作线程
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

    }

    public class ThreadPoolExecutorEntity implements Runnable{
        private  int threadNum;
        public ThreadPoolExecutorEntity(int threadNum){
            this.threadNum = threadNum;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前任务数为: " + threadNum);
        }
    }
}





