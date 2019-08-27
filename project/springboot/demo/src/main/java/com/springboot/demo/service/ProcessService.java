package com.springboot.demo.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.concurrent.*;

@Service
//多线程服务类
public class ProcessService extends Thread {


    //进程:是并发执行的程序在执行过程中分配和管理资源的基本单位，是一个动态概念，竞争计算机系统资源（内存）的基本单位。
    //线程:是进程的一个执行单元,是进程内科调度实体。比进程更小的独立运行的基本单位。线程也被称为轻量级进程。
    //进程是资源分配的最小单位，线程是CPU调度的最小单位。

    //注意:
    //1.一个程序至少有一个进程，一个进程至少有一个线程。
    //2.地址空间:同一进程的线程共享本进程的地址空间，而进程之间则是独立的地址空间。
    //3.资源拥有:同一个进程内的线程共享本经常的资源(内存，I/O,CUP)等。
    //一个进程崩溃后，在保护模式下不会对其他进程产生影响，但是一个线程崩溃整个进程都死掉。所以多进程要比多线程健壮。

    public void ExcuteProcess(){
        System.out.println("Process：" + Thread.currentThread().getName()); //获取当前线程方法名称(最上层的线程名称)
    }














    //start()和run()的区别
    //1.start()方法会创建一个新的子线程并启动(new + run());
    //2.run()只是启用原来线程的一个方法
    public void StartAndRun(){
        //建议多线程使用以下方法，只需重写run方法就可以
        int i =1;
        Thread test = new Thread(){
            public void run(){
                int j = i;
                ExcuteProcess();
            }
        };
        test.run(); //run，输出http-nio-8081-exec-2，controller调用的线程, 注意:run方法不能接收参数
        test.start(); //start,输出Thread-4,新创建的线程
    }

    //Thread和Runnable
    public void ThreadAndRunabled(){
        //1.使用继承Thread的类直接实现多线程
        MyThread mt1 = new MyThread("thread1");
        MyThread mt2 = new MyThread("thread2");
        MyThread mt3 = new MyThread("thread3");
        mt1.run();//注意：使用run，线程会按顺序执行（非多线程）
        mt2.run();
        mt3.run();

        mt1.start();//注意：使用start，线程不会按顺序执行(乱序)，也就是多线程执行
        mt2.start();
        mt3.start();

        //2.使用实现Runnable的run方法重写
        //将要实现的类加载到Thread中start()
        MyRunnable mr1 =new MyRunnable("Runnable1");
        MyRunnable mr2 =new MyRunnable("Runnable2");
        MyRunnable mr3 =new MyRunnable("Runnable3");
        Thread thread1 = new Thread(mr1);
        Thread thread2 = new Thread(mr2);
        Thread thread3 = new Thread(mr3);
        thread1.start();
        thread2.start();
        thread3.start();
    }

    //使用Thread重写run方法（其实run方法是Thread通过Runnable接口获得的）
    class MyThread extends Thread{
        private String process;
        public MyThread(String process) {
            this.process = process;
        }
        @Override
        public void run(){
            for (int i =0;i<10;i++){
                System.out.println(this.process+ ",i=" + i);//通过构造函数，或类成员变量传参
            }
        }
    }

    //使用Runnable重写run方法
    class MyRunnable implements Runnable{
        private String process;
        public MyRunnable(String process) {
            this.process = process;
        }
        @Override
        public void run(){
            for (int i =0;i<10;i++){
                System.out.println(this.process+ ",i=" + i);
            }
        }
    }












    //线程等待方法类
    class MyThreadWait implements Runnable{
        private String process;
        @Override
        public void run(){
            try {
                Thread.currentThread().sleep(3000); //休眠3秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally{
                this.process = "MyThreadWait";
            }
        }
    }

    //线程呼叫类(注意Callable<>中的类型要跟call返回类型一致)
    class MyCallable implements Callable<String> {
        @Override
        public String call() throws InterruptedException {
        String value = "null";

//        Thread.currentThread().sleep(3000);

            for(int i = 1;i<10000000;i++){
                value = "test1";
            }
        value = "isDone";
        System.out.println(value);
        return value;
    }
}

    //在多线程任务中获取返回值!!!
    public void GetThreadValue() throws ExecutionException, InterruptedException {
        String value = "null";
        //1.主线程等待法(不建议,需要设置线程完成时间,但是什么时候完成是不知道的)
        //MainWait();

        //2.使用join，注意使用无参数join后，当前线程是无法start()的，因为无参数join是使线程进入无限状态(不建议,需要设置线程完成时间,但是什么时候完成是不知道的)
        //JoinWait();

        //3.通过Callable接口实现:FutureTask Or 线程池(建议使用)
        FutureTaskAndPool();
    }

    public void FutureTaskAndPool() throws InterruptedException, ExecutionException {
        //(1)FutureTask
        FutureTask<String> task = new FutureTask<String>(new MyCallable());
        new Thread(task).start(); //运行线程，如果是传入task,那么线程就会run task中MyCallable的call方法，无需再重写一个run方法
        if(task.isDone()){ //当前子线程没有阻塞并且执行完成后进行下一步处理
            System.out.println(task.get());//task.get(),直接返回MyCallable中call()的返回值
        }else{
            System.out.println("线程还未完成！");
        }

        //(2)线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        Future<String> future = pool.submit(new MyCallable());//装载到线程池，直接提交运行
        if(future.isDone()){
            System.out.println(future.get());//task.get(),直接返回MyCallable中call()的返回值
        }else{
            System.out.println("线程还未完成！");
        }
        pool.shutdown(); //用完必须要关闭线程池
    }

    public void JoinWait() {
        String value;
        MyThreadWait myw = new MyThreadWait();
        Thread thread1 = new Thread(myw);
        thread1.start();
        try {
            thread1.join();//可以通过阻塞当前线程，等待当前线程处理完成后,继续执行后面代码
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        value = myw.process;
        System.out.println(value);
    }

    public void MainWait() {
        String value;
        MyThreadWait myw = new MyThreadWait();
        Thread thread = new Thread(myw);
        thread.start();
        value = myw.process; //因为myw.process要等待3秒后才赋值，但是因为是开了线程，所以代码会继续执行，这里的myw.process还是null

        //解决方法,判断是否有值，等待时间只能大于等于休眠时间
        if(myw.process == null){
            try {
                Thread.currentThread().sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        value = myw.process;
        System.out.println(value);
    }












    //sleep和wait方法区别(让自己的线程等待,让出资源的行为)
    //1.Thread.sleep，只会让出CPU，不会导致锁行为改变
    //2.Object.wait不仅仅让出CPU，还会释放占有的同步资源锁
    public void SleepAndWait(){
        final Object lock = new Object();
        SimpleDateFormat ft = new SimpleDateFormat ("hh:mm:ss");

        //线程1
        new Thread(){
          @Override
          public void run(){
              System.out.println("线程1开始运行。");
              synchronized (lock){
                  try {
                      System.out.println("线程1，sleep休眠开始!");
                      Thread.sleep(3000);
                      System.out.println("线程1，sleep休眠结束!");
                      lock.wait(4000); //如果不传参数,则无限等待(如果现在线程1设定5秒后才能解锁，那线程2中的方法要等5秒后才能解锁执行)
                      System.out.println("线程1，lock锁释放结束!");
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
          }
        }.start();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //线程2
        new Thread(){
            @Override
            public void run(){
                System.out.println("线程2开始运行。");
                synchronized (lock){
                        try {
                            System.out.println("线程2，sleep休眠开始!");
                            Thread.sleep(5000);
                            System.out.println("线程2，sleep休眠结束!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
            }
        }.start();
    }

    //结果：(注意:由于synchronized有原子性概念,所以每次都只会执行一个synchronized里面的代码)
//    线程1开始运行。
//    线程1，sleep休眠开始!
//    线程2开始运行。        线程sleep3秒钟,让出cpu资源,因此线程1中断,线程2锁外部代码开始
//    线程1，sleep休眠结束!  因为sleep不会让锁资源,所以线程2代码锁住无法执行,要等待线程1 sleep完之后才会才有资源
//    线程2，sleep休眠开始!  线程1 sleep3秒结束，然后执行wait让锁让cpu 4秒
//    线程2，sleep休眠结束!  线程2获得资源开始sleep 5秒，因为没有让锁 所以 上面wait就算是4秒但是还是要等 线程2 sleep完之后才执行
//    线程1，lock锁释放结束!











    //notify和notifyAll,释放线程等待锁;
    public void NotifyAndNotifyAll(){
        Object lock = new Object();
        new Thread(){
            @Override
            public void run(){
                synchronized (lock){
                    System.out.println("线程1,开始");
                    try {
                        System.out.println("线程1,获取等待锁");
                        lock.wait();
                        System.out.println("线程1,获取解锁资源");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();

        new Thread(){
            @Override
            public void run(){
                System.out.println("线程2,开始");
                synchronized (lock){
                    System.out.println("线程2,为其他线程释放等待锁");
//                        lock.notify(); //释放线程池里面的随机一个线程锁资源
                    lock.notifyAll(); //释放线程池里面的所有线程锁资源
                    System.out.println("线程2,释放线程池锁资源成功");
                }
            }
        }.start();
    }











    //线程中断方法(stop(),interrupt())
    public void ProcessStop() {
        //1.stop, 直接强制停止线程.
//        Thread thread1 = new Thread();
//        thread1.stop();

        //2.interrupt, 适用于某种情况中断线程
        InterruptEntity interrupt = new InterruptEntity();
        Thread thread = new Thread(interrupt);
        thread.start();
        thread.isInterrupted(); //使用interrupt中断线程,一般使用变量条件判断,再判断是否中断线程
        System.out.println("根据线程外部判断i值=" + interrupt.getId() + ",中断线程");
    }

    public class InterruptEntity implements Runnable{
        private int id;
        public int getId() {
            return id;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                this.id = id++;
            }

        }
    }



    //线程状态(pricture中有图可看 )
    //1.New,线程创建后尚未启动的线程状态
    //2.Runnable,包含Running和Ready
    //3.Blocked,阻塞,等待获取排他锁
    //4.Waiting,无限期等待,不会被分配CPU执行时间,需要显示唤醒
    //5.Timed_Waiting,限期等待,在一定时间后,系统自动唤醒
    //6.Terminated,结束,已终止线程的状态,线程已结束运行




    //线程安全问题
    //1.存在共享数据(临界资源)
    //2.存在多条线程共同操作这些共享数据
    //解决方案:同一时间只能有一个线程处理共享数据,其他线程必须等待该线程处理完成后再处理共享数据

    // synchronized,互斥线程锁(同步锁),只能锁对象(注意不是代码段)(重点!!!!!!!!!!)
    //互斥性(原子性):在同一时间只允许一个线程持有某个对象锁,实现多线程的协调机制,
    //这样在同一时间只有一个线程对需要同步的代码块(复合操作)进行访问.
    //


    public void SynchronizedTest(){
        //没加Synchronized锁,打印出来的 Count= 将会是乱序,因为是线程异步执行
        MyNotSynchronized myNotSynchronized = new MyNotSynchronized();
        //Thread thread1 = new Thread(myNotSynchronized, "thread1");
        //Thread thread2 = new Thread(myNotSynchronized, "thread2");
        //thread1.start();
        //thread2.start();

        //1.使用Synchronized修饰一个代码块,
        MySynchronized mySynchronized = new MySynchronized();
        //Thread thread3 = new Thread(mySynchronized, "thread3");
        //Thread thread4 = new Thread(mySynchronized, "thread4");
        //thread3.start();
        //thread4.start(); //同步,结果Count按顺序执行

        //Synchronized一个对象生成一个锁
        //注意:这里new的两个对象,虽然是同一个类,但是是两个对象,所以生成两把锁
        Thread thread5 = new Thread(new MySynchronized(), "thread5"); //这里new新对象,检查Synchronized是否有阻塞
        Thread thread6 = new Thread(new MySynchronized(), "thread6");
        //thread5.start(); //结果是异步,Count乱序
        //thread6.start();


        //2.当一个线程访问对象的一个synchronized(this)同步代码块时，
        //另一个线程仍然可以访问该对象中的非synchronized(this)同步代码块。
        MySynchronized2 mySynchronized2 = new MySynchronized2();
        //Thread thread7 = new Thread(mySynchronized2, "SynchronizedAdd");
        //Thread thread8 = new Thread(mySynchronized2, "Add");
        //thread7.start(); // 结果异步, Count乱序
        //thread8.start();


        //3.对象加锁(对象锁)
        //MySynchronized3 mySynchronized3 = new MySynchronized3(mySynchronized2);
        //Thread thread9 = new Thread(mySynchronized3);
        //Thread thread10 = new Thread(mySynchronized3);
        //thread9.start();
        //thread10.start();  //同步,结果Count按顺序执行

        //4.静态方法加锁(类锁)
        //注意:因为静态方法是属于类的,同一个类只会有一个锁(静态类是不需要new的),被多个线程调用就会阻塞(使用Synchronized(类.class)也一样)
        MySynchronized4 mySynchronized4 = new MySynchronized4();
        Thread thread11 = new Thread(mySynchronized4);
        Thread thread12 = new Thread(mySynchronized4);
        thread11.start();
        thread12.start();  //同步,结果Count按顺序执行
    }

    public class MyNotSynchronized implements Runnable{
        private Integer count = 0;

        public Integer getCount() {
            return count;
        }
        @Override
        public void run() {
            for (Integer i=0;i<3;i++){
                count++;
                System.out.println(Thread.currentThread().getName() + ", Count=" + count);
            }
        }
    }

    public class MySynchronized implements Runnable{
        private Integer count = 0;
        @Override
        public void run() {
            synchronized (this){
                for (Integer i=0;i<3;i++){
                    System.out.println(Thread.currentThread().getName() + ", Count=" + count++);
                }
            }
        }
    }

    public class MySynchronized2 implements Runnable{
        private Integer count = 0;

        public Integer getCount() {
            return count;
        }

        public void SynchronizedAdd(){
            synchronized (this){
                for (Integer i=0;i<3;i++){
                    System.out.println(Thread.currentThread().getName() + ", Count=" + count++);
                }
            }
        }

        public void Add(){
            for (Integer i=0;i<3;i++){
                System.out.println(Thread.currentThread().getName() + ", Count=" + count++);
            }
        }

        @Override
        public void run() {
             if(Thread.currentThread().getName() == "SynchronizedAdd"){
                SynchronizedAdd();
             } else if(Thread.currentThread().getName() == "Add"){
                 Add();
             }
        }
    }

    public class MySynchronized3 implements Runnable{
        private  MySynchronized2 mySynchronized2;
        public MySynchronized3(MySynchronized2 mySynchronized2){
            this.mySynchronized2 = mySynchronized2;
        }
        @Override
        public void run() {
            synchronized (mySynchronized2){
                mySynchronized2.Add();
            }
        }
    }

    public static class MySynchronized4 implements Runnable{
        private static Integer count = 0;

        public static Integer getCount() {
            return count;
        }

        public synchronized static void SynchronizedAdd(){ //静态方法添加锁
                for (Integer i=0;i<3;i++){
                    System.out.println(Thread.currentThread().getName() + ", Count=" + count++);
                }
        }

        public static void SynchronizedDelete(){
            synchronized(MySynchronized4.class){ //类添加锁,跟new对象不一样,这里会将这个类绑定一个锁,所有调用这个方法的线程都会阻塞
                System.out.println(Thread.currentThread().getName() + ", Count=" + count--);
            }
        }

        @Override
        public synchronized void run() { //这里加不加synchronized都一样,因为synchronized已经加了
            if(Thread.currentThread().getName() == "Add"){
                SynchronizedAdd();
            }else if(Thread.currentThread().getName() == "Delete"){
                SynchronizedDelete();
            }
        }
    }

    //synchronized,底层原理
    //锁对象存储在对象头中(Mark Work)包含:锁状态(无锁状态,轻量级锁,重量级锁(synchronized),GC标记,偏向锁)
    //Monitor:每个java对象自身都带的一把锁(各种属性存储,例如锁池线程数多少,当前对象是什么,等待时间等)，可以查看pictrue/Monitor中,锁的竞争，获取和释放
    //运行加锁的代码,jvm会添加两个指令monitorenter和monitorexit 或者acc_synchronized,持有Monitor锁的线程运行,其他线程等待

    //java Native代码中Monitor对象属性
    //Owner：初始时为NULL表示当前没有任何线程拥有该monitor record，当线程成功拥有该锁后保存线程唯一标识，当锁被释放时又设置为NULL；
    //EntryQ:关联一个系统互斥锁（semaphore），阻塞所有试图锁住monitor record失败的线程。
    //RcThis:表示blocked或waiting在该monitor record上的所有线程的个数。
    //Nest:用来实现重入锁的计数。
    //HashCode:保存从对象头拷贝过来的HashCode值（可能还包含GC age）。
    //Candidate:用来避免不必要的阻塞或等待线程唤醒，因为每一次只有一个线程能够成功拥有锁，
    //如果每次前一个释放锁的线程唤醒所有正在阻塞或等待的线程，会引起不必要的上下文切换（从阻塞到就绪然后因为竞争锁失败又被阻塞）从而导致性能严重下降。
    //Candidate只有两种可能的值0表示没有需要唤醒的线程1表示要唤醒一个继任线程来竞争锁。


    //锁重入,在锁中继续添加锁,是可以执行的
//    synchronized (this){
//        System.out.println("A");
//        synchronized (this){
//            System.out.println("B");
//        }
//    }

    //自旋锁(用于其他被占用锁资源的线程)
    //许多情况下,共享数据的锁定状态持续时间较短,切换线程不值得
    //通过让线程执行忙循环等待锁的释放,不让出cpu（缺点,开销大,特别是锁占用时间长的线程）
    //简单说就是，线程加锁后，不停循环地去判断锁资源释放已经释放

    //自适应自旋锁
    //由前一次在同一个锁上的自选时间以及所得拥有者的状态来决定,
    //也就是说jvm通过识别上一个锁的获取容易度来判断另一个线程等待要自旋的次数(循环，并且控制循环次数)


    //锁状态(四种：无锁，偏向锁，轻量级锁，重量级锁)
    //
}
