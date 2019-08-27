package com.springboot.demo.service;

public class JvmService {

    //jvm类加载
    public void JvmGainian(){
        //1.java平台无关性,详细浏览 pricture jvm。
        //java代码将会被编译(javac)成字节码，存储在.class中，再由不同平台版本的jvm虚拟机(ios/window)去解析，转换成各种平台上的机器指令


        //2.jvm加载类文件
        //加载过程：java源码--->编译生成class文件--->ClassLoader加载(主要)--->Runtime Data Area(jvm内存结构模型)

        //ClassLoader类加载器有什么作用？
        //负责将Class文件里面的二进制数据流装载进系统，然后交给java虚拟机进行连接，初始化等操作。
        //类加载器种类:
        //启动类加载器(Bootstrap ClassLoader)：负责加载 JAVA_HOME\lib 目录中的，或通过-Xbootclasspath参数指定路径中的，且被虚拟机认可（按文件名识别，如rt.jar）的类。(java原生类库)
        //扩展类加载器(Extension ClassLoader)：负责加载 JAVA_HOME\lib\ext 目录中的，或通过java.ext.dirs系统变量指定路径中的类库。(java扩展类库)
        //应用程序类加载器(Application ClassLoader)：负责加载用户路径（classpath）上的类库。(自己写的代码)


        //双亲委派模型
        //类加载过程中,如果类之间存在继承关系,jvm将会先加载父类,最后加载子类,因此,子类中一些静态代码块可能不会执行。
        //避免同一个类被多次加载；
        //每个加载器只能加载自己范围内的类；

        //3.类加载过程(详细) 浏览 pricture jvm
        //(1)加载(将.class文件中二进制字节流,在内存中生成一个代表这个类的java.lang.Class对象)
        //通过一个类的全限定名来获取定义此类的二进制字节流（并没有指明要从一个Class文件中获取，可以从其他渠道，譬如：网络、动态生成、数据库等）；
        //将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构；
        //在内存中生成一个代表这个类的java.lang.Class对象，作为方法区这个类的各种数据的访问入口；

        //(2)验证
        //验证是连接阶段的第一步，这一阶段的目的是为了确保Class文件的字节流中包含的信息符合当前虚拟机的要求，并且不会危害虚拟机自身的安全。

        //(3)准备(只针对静态变量)
        //准备阶段是正式为类变量分配内存并设置类变量初始值的阶段，这些变量所使用的内存都将在方法区中进行分配。
        //这时候进行内存分配的仅包括类变量（被static修饰的变量），而不包括实例变量，实例变量将会在对象实例化时随着对象一起分配在堆中。

        //(4)解析
        //解析阶段是虚拟机将常量池内的符号引用替换为直接引用的过程。
        //解析动作主要针对类或接口、字段、类方法、接口方法、方法类型、方法句柄和调用点限定符7类符号引用进行。

        //(5)初始化(静态变量赋值,并且执行静态代码)
        // 静态代码块都会在编译时被前端编译器放在收集器里头，存放到一个特殊的方法中，这个方法就是<clinit>方法，即类/接口初始化方法，该方法只能在类加载的过程中由JVM调用；
        //编译器收集的顺序是由语句在源文件中出现的顺序所决定的，静态语句块中只能访问到定义在静态语句块之前的变量；
        //如果超类还没有被初始化，那么优先对超类初始化，但在<clinit>方法内部不会显示调用超类的<clinit>方法，由JVM负责保证一个类的<clinit>方法执行之前，它的超类<clinit>方法已经被执行。
        //JVM必须确保一个类在初始化的过程中，如果是多线程需要同时初始化它，仅仅只能允许其中一个线程对其执行初始化操作，其余线程必须等待，只有在活动线程执行完对类的初始化操作之后，才会通知正在等待的其他线程。(所以可以利用静态内部类实现线程安全的单例模式) 如果一个类没有声明任何的类变量，也没有静态代码块，那么可以没有类<clinit>方法；(类加载只能单线程)



        //4.loadClass和forName的区别
        //Class.forName得到的class是已经初始化完成的，变量已经赋值,静态代码已经执行。(forName参数里面表示已经初始化)
        //Classloder.loadClass得到的class是还没有链接(第二步)的。
    }

    //jmm内存模型
    public void JvmJmm(){
       //资料地址 https://www.cnblogs.com/andy-zhou/p/5327288.html
        //https://www.jianshu.com/p/76959115d486
        //jmm请查看 priture 内存模型分布区域

        //1.大概了解
        //是什么
        //栈是运行时的单位，而堆是存储的单位。（栈代表了处理逻辑，而堆代表了数据）
        //栈解决程序的运行问题，即程序如何执行，或者说如何处理数据；堆解决的是数据存储的问题，即数据怎么放、放在哪儿。
        //栈只能向上增长，因此就会限制住栈存储内容的能力。而堆不同，堆中的对象是可以根据需要动态增长的。（相应栈中只需记录堆中的一个地址即可）

        //堆中存什么？栈中存什么？
        //栈因为是运行单位，因此里面存储的信息都是跟当前线程（或程序）相关信息的。包括局部变量、程序运行状态、方法返回地址等等；(相当于一个线程)
        //而堆只负责存储对象信息。（堆是所有线程共享的）
        //堆中存的是对象。栈中存的是基本数据类型和堆中对象的引用。(基本数据类型大小固定)

        //堆和栈中，栈是程序运行最根本的东西。程序运行可以没有堆，但是不能没有栈。而堆是为栈进行数据存储服务，说白了堆就是一块共享的内存。
        //不过，正是因为堆和栈的分离的思想，才使得Java的垃圾回收成为可能。
        //在Java中，Main函数就是栈的起始点，也是程序的起始点。

        //java对象的大小，例子：
        //Class NewObject {
        //    int count;
        //    boolean flag;
        //    Object ob;
        //}
        //其大小为：空对象大小(8byte)+int大小(4byte)+Boolean大小(1byte)+空Object引用的大小(4byte)=17byte。
        //但是因为Java在对对象内存分配时都是以8的整数倍来分，因此大于17byte的最接近8的整数倍的是24( 17 < 8*3,按8的倍数向上运算)，因此此对象的大小为24byte。


        //2.java栈(虚拟机栈)
        //栈描述的是Java方法执行的内存模型。
        //每个方法被执行的时候都会创建一个栈帧用于存储局部变量表，操作栈，动态链接，方法出口等信息。
        //每一个方法被调用的过程就对应一个栈帧在虚拟机栈中从入栈到出栈的过程。
        //注意:【栈先进后出】,就像我们调试程序,大方法包小方法，大方法最后才跳出来。

        //需要注意的是，局部变量表所需要的内存空间在编译期完成分配，当进入一个方法时，
        //这个方法在栈中需要分配多大的局部变量空间是完全确定的，在方法运行期间不会改变局部变量表大小。(因为栈中存的是基本类型变量所以大小已经定好)

        //Java虚拟机栈可能出现两种类型的异常：（两种内存溢出异常[注意内存溢出是error级别的]）
        //线程请求的栈深度大于虚拟机允许的栈深度，将抛出StackOverflowError。
        //虚拟机栈空间可以动态扩展，当动态扩展是无法申请到足够的空间时，抛出OutOfMemory异常。

        //3.本地栈(执行java底层native方法的栈)
        //本地方法栈是与虚拟机栈发挥的作用十分相似,区别是虚拟机栈执行的是Java方法(也就是字节码)服务，
        //而本地方法栈则为虚拟机使用到的native方法服务，可能底层调用的c或者c++,我们打开jdk安装目录可以看到也有很多用c编写的文件，
        //可能就是native方法所调用的c代码。

        //4.堆
        //堆是java虚拟机管理内存最大的一块内存区域，因为堆存放的对象是线程共享的，所以多线程的时候也需要同步机制。
        //它是所有线程共享的，它的目的是存放对象实例。同时它也是GC所管理的主要区域，
        //因此常被称为GC堆，又由于现在收集器常使用分代算法，Java堆中还可以细分为新生代和老年代。
        //当前主流的虚拟机如HotPot都能按扩展实现(通过设置 -Xmx和-Xms)，如果堆中没有内存内存完成实例分配，
        //而且堆无法扩展将报OOM错误(OutOfMemoryError)

        //5.方法区(元空间)
        //方法区同堆一样，是所有线程共享的内存区域，为了区分堆，又被称为非堆。
        //用于存储已被虚拟机加载的类信息、常量、静态变量，如static修饰的变量加载类的时候就被加载到方法区中。


        //6.内存泄漏检查
        //堆栈溢出 异常：java.lang.StackOverflowError
        //说明：一般就是递归没返回，或者循环调用造成，因为jvm中栈都会设置一定深度。

        //内存溢出
        //(1)年老代堆空间被占满 异常： java.lang.OutOfMemoryError: Java heap space
        //说明：所有堆空间都被无法回收的垃圾对象占满，虚拟机无法再在分配新空间。一般是集合对象太多，导致内存不足。

        //(2)持久代被占满 异常：java.lang.OutOfMemoryError: PermGen space
        //说明：重新设置持久代大小 -XX:MaxPermSize=16m。

        //(3)系统内存被占满 异常：java.lang.OutOfMemoryError: unable to create new native thread
        //这个异常是由于操作系统没有足够的资源来产生这个线程造成的。
        //当线程数量大到一定程度以后，操作系统分配不出资源来了，就出现这个异常了。
        //重新设计系统减少线程数量。
        //线程数量不能减少的情况下，通过-Xss减小单个线程大小。以便能生产更多的线程。

        //7.voliate关键字
        //使变量在线程间可见性：
        // 当一个变量被声明为volatile时候，线程写入时候不会把值缓存在寄存器或者或者在其他地方，
        // 当线程读取的时候会从主内存重新获取最新值，而不是使用当前线程的拷贝内存变量值。(voliate关键字的变量直接存储值到主内存，主内存数据是全部线程共享的)
        //volatile的内存语义和synchronized有类似之处，
        //具体说是说当线程写入了volatile变量值就等价于线程退出synchronized同步块（会把写入到本地内存的变量值同步到主内存），
        // 读取volatile变量值就相当于进入同步块（会先清空本地内存变量值，从主内存获取最新值）。

        //voliate实现技术:
        //通过穿越内存屏障(越过线程内存,直接访问主内存) 和 禁止重排序优化(不会按线程里面变量赋值而叠加,只会写入最新的值) 来实现的
        //对voliate进行写操作时 会在写操作后加一条 store 屏障指令,将本地内存中的变量刷新到 主内存
        //对voliate时行读操作时,会在读操作后加一条 load 屏障指令,从主内存中读取共享变量的值

        //为什么要使用voliate 线程不可见的原因
        //线程交叉执行，重排序结合线程交叉执行，共享变量更新后的值没有在工作内存与主存间及时更新

        //其他特点:
        //不是线程安全的,不具有原子性
        //对变量的写操作不依赖于当前值
        //特别适合状态标记量

        //禁止指令重排序解释：
        //例如：
        //new Thread().start(); //线程1
        //volatile int i = 1;  //volatile标识的i会等待线程1执行完成,才会赋值1，然后执行线程2(也可以说是按代码排序执行)。
        //if(i == 1)new Thread().start(); //线程2
    }

    //jvm垃圾回收
    public void JvmGC(){
        //1.什么是垃圾回收
        //即是回收jvm内存模型中，堆的内存。

        //2.基本回收策略
        //引用计数：原理是此对象有一个引用，即增加一个计数，删除一个引用则减少一个计数。垃圾回收时，只用收集计数为0的对象。此算法最致命的是无法处理循环引用的问题。
        //标记-清除：第一阶段从引用根节点开始标记所有被引用的对象，第二阶段遍历整个堆，把未标记的对象清除。此算法需要暂停整个应用，同时，会产生内存碎片。

        //复制：此算法把内存空间划为两个相等的区域，每次只使用其中一个区域。垃圾回收时，遍历当前使用区域，
        // 把正在使用中的对象复制到另外一个区域中。次算法每次只处理正在使用中的对象，因此复制成本比较小，
        // 同时复制过去以后还能进行相应的内存整理，不会出现“碎片”问题。
        // 当然，此算法的缺点也是很明显的，就是需要两倍内存空间。

        //标记-整理(主要回收策略)：第一阶段从根节点开始标记所有被引用对象，第二阶段遍历整个堆，把清除未标记对象并且把存活对象“压缩”到堆的其中一块，按顺序排放。
        // 此算法避免了“标记-清除”的碎片问题，同时也避免了“复制”算法的空间问题。


        //3.按分代回收
        //年轻代:
        //所有新生成的对象首先都是放在年轻代的。年轻代的目标就是尽可能快速的收集掉那些生命周期短的对象。
        // 年轻代分三个区。一个Eden区，两个Survivor区(一般而言)。大部分对象在Eden区中生成。
        // 当Eden区满时，还存活的对象将被复制到Survivor区（两个中的一个），当这个Survivor区满时，此区的存活对象将被复制到另外一个Survivor区，当这个Survivor去也满了的时候，
        // 从第一个Survivor区复制过来的并且此时还存活的对象，将被复制“年老区(Tenured)”。需要注意，Survivor的两个区是对称的，没先后关系，所以同一个区中可能同时存在从Eden复制过来 对象，
        // 和从前一个Survivor复制过来的对象，而复制到年老区的只有从第一个Survivor去过来的对象。而且，Survivor区总有一个是空的。
        // 同时，根据程序需要，Survivor区是可以配置为多个的（多于两个），这样可以增加对象在年轻代中的存在时间，减少被放到年老代的可能。

        //年老代(老年代):
        //在年轻代中经历了N次垃圾回收后仍然存活的对象，就会被放到年老代中。因此，可以认为年老代中存放的都是一些生命周期较长的对象。

        //持久代(永久代):
        //用于存放静态文件，如今Java类、方法等。持久代对垃圾回收没有显著影响，但是有些应用可能动态生成或者调用一些class，
        //例如Hibernate等，在这种时候需要设置一个比较大的持久代空间来存放这些运行过程中新增的类。持久代大小通过-XX:MaxPermSize=进行设置。

        //按年代回收流程步骤：
        //一个对象实例化时 先去看伊甸园有没有足够的空间
        //如果有 不进行垃圾回收 ,对象直接在伊甸园存储.
        //如果伊甸园内存已满,会进行一次minor gc
        //然后再进行判断伊甸园中的内存是否足够
        //如果不足 则去看存活区的内存是否足够.
        //如果内存足够,把伊甸园部分活跃对象保存在存活区,然后把对象保存在伊甸园.
        //如果内存不足,向老年代发送请求,查询老年代的内存是否足够
        //如果老年代内存足够,将部分存活区的活跃对象存入老年代.然后把伊甸园的活跃对象放入存活区,对象依旧保存在伊甸园.
        //如果老年代内存不足,会进行一次full gc,之后老年代会再进行判断 内存是否足够,如果足够 同上.
        //如果不足 会抛出OutOfMemoryError.


        //4.垃圾回收类型
        //Minor GC：当年轻代满时就会触发Minor GC，回收Eden中的内存，使Eden去能尽快空闲出来，这里的年轻代满指的是Eden代满，Survivor满不会引发GC。(因为只遍历Eden一个区，所以效率很快)
        //Full GC 对整个堆进行整理，包括Young、Tenured和Perm。Full GC因为需要对整个对进行回收，所以比Minor GC要慢，因此应该尽可能减少Full GC的次数。在对JVM调优的过程中，很大一部分工作就是对于FullGC的调节。
         //有如下原因可能导致Full GC：
         //年老代（Tenured）被写满
         //持久代（Perm）被写满
         //System.gc()被显示调用
         //上一次GC之后Heap的各域分配策略动态变化

        //5.垃圾回收处理器
        //串行处理器：适用情况：数据量比较小（100M左右）；单处理器下并且对响应时间无要求的应用。缺点：只能用于小型应用。
        //并行处理器：适用情况：“对吞吐量有高要求”，多CPU、对应用响应时间无要求的中、大型应用。举例：后台处理、科学计算。缺点：垃圾收集过程中应用响应时间可能加长。
        //并发处理器：适用情况：“对响应时间有高要求”，多CPU、对应用响应时间有较高要求的中、大型应用。举例：Web服务器/应用服务器、电信交换、集成开发环境。

        //6.jvm调优配置(重点！！！)
        //(1)java -Xmx3550m -Xms3550m -Xmn2g –Xss128k
        //-Xmx3550m：设置JVM最大可用内存为3550M。(注意重点:一般为服务器物理内存的1/4)
        //-Xms3550m：设置JVM促使内存为3550m。此值可以设置与-Xmx相同，以避免每次垃圾回收完成后JVM重新分配内存。

        //-Xmn2g：设置年轻代大小为2G。整个堆大小=年轻代大小 + 年老代大小 + 持久代大小。持久代一般固定大小为64m，
        //所以增大年轻代后，将会减小年老代大小。此值对系统性能影响较大，Sun官方推荐配置为整个堆的3/8。

        //-Xss128k：设置每个线程的堆栈大小。JDK5.0以后每个线程堆栈大小为1M，以前每个线程堆栈大小为256K。
        // 更具应用的线程所需内存大小进行调整。在相同物理内存下，减小这个值能生成更多的线程。
        // 但是操作系统对一个进程内的线程数还是有限制的，不能无限生成，经验值在3000~5000左右。


        //(2)java -Xmx3550m -Xms3550m -Xss128k -XX:NewRatio=4 -XX:SurvivorRatio=4 -XX:MaxPermSize=16m -XX:MaxTenuringThreshold=0
        //-XX:NewRatio=4:设置年轻代（包括Eden和两个Survivor区）与年老代的比值（除去持久代）。设置为4，则年轻代与年老代所占比值为1：4，年轻代占整个堆栈的1/5。
        //-XX:SurvivorRatio=4：设置年轻代中Eden区与Survivor区的大小比值。设置为4，则两个Survivor区与一个Eden区的比值为2:4，一个Survivor区占整个年轻代的1/6。
        //-XX:MaxPermSize=16m:设置持久代大小为16m。
        //-XX:MaxTenuringThreshold=0：设置垃圾最大年龄。如果设置为0的话，则年轻代对象不经过Survivor区，直接进入年老代。对于年老代比较多的应用，可以提高效率。
        // 如果将此值设置为一个较大值，则年轻代对象会在Survivor区进行多次复制，这样可以增加对象再年轻代的存活时间，增加在年轻代即被回收的概论。

        //(3)其他
        //-XX:+UseParallelGC：选择垃圾收集器为并行收集器。此配置仅对年轻代有效。即上述配置下，年轻代使用并发收集，而年老代仍旧使用串行收集。
        //-XX:ParallelGCThreads=20：配置并行收集器的线程数，即：同时多少个线程一起进行垃圾回收。此值最好配置与处理器数目相等。
        //-XX:+UseParallelOldGC：配置年老代垃圾收集方式为并行收集。JDK6.0支持对年老代并行收集。
        //-XX:MaxGCPauseMillis=100:设置每次年轻代垃圾回收的最长时间，如果无法满足此时间，JVM会自动调整年轻代大小，以满足此值。
        //-XX:+UseAdaptiveSizePolicy：设置此选项后，并行收集器会自动选择年轻代区大小和相应的Survivor区比例，以达到目标系统规定的最低相应时间或者收集频率等，此值建议使用并行收集器时，一直打开。
        //-XX:+UseConcMarkSweepGC：设置年老代为并发收集。测试中配置这个以后，-XX:NewRatio=4的配置失效了，原因不明。所以，此时年轻代大小最好用-Xmn设置。
        //-XX:+UseParNewGC: 设置年轻代为并行收集。可与CMS收集同时使用。JDK5.0以上，JVM会根据系统配置自行设置，所以无需再设置此值。
        //-XX:CMSFullGCsBeforeCompaction：由于并发收集器不对内存空间进行压缩、整理，所以运行一段时间以后会产生“碎片”，使得运行效率降低。此值设置运行多少次GC以后对内存空间进行压缩、整理。
        //-XX:+UseCMSCompactAtFullCollection：打开对年老代的压缩。可能会影响性能，但是可以消除碎片

        //7.调优总结

        //(1)年轻代大小选择(让GC尽量在年轻代中执行)
        //响应时间优先的应用：尽可能设大，直到接近系统的最低响应时间限制（根据实际情况选择）。在此种情况下，年轻代收集发生的频率也是最小的。同时，减少到达年老代的对象。
        //吞吐量优先的应用：尽可能的设置大，可能到达Gbit的程度。因为对响应时间没有要求，垃圾收集可以并行进行，一般适合8CPU以上的应用。

        //(2)年老代大小选择
        //如果堆设置小了，可以会造成内存碎片、高回收频率以及应用暂停而使用传统的标记清除方式；如果堆大了，则需要较长的收集时间。最优化的方案，一般需要参考以下数据获得：
            //1.并发垃圾收集信息
            //2.持久代并发收集次数
            //3.传统GC信息
            //4.花在年轻代和年老代回收上的时间比例
            //5.减少年轻代和年老代花费的时间，一般会提高应用的效率

        //(3)吞吐量优先的应用
        //一般吞吐量优先的应用都有一个很大的年轻代和一个较小的年老代。原因是，这样可以尽可能回收掉大部分短期对象，减少中期的对象，而年老代尽存放长期存活对象。

        //自己总结大概流程: jvm选择垃圾回收处理器(并发处理器)-->在堆中寻找要回收的内存(年轻代,老年代)-->使用垃圾回收策略进行回收(标记整理)

//        常见配置汇总
//           堆设置
//                -Xms:初始堆大小
//                -Xmx:最大堆大小
//                -XX:NewSize=n:设置年轻代大小
//                -XX:NewRatio=n:设置年轻代和年老代的比值。如:为3，表示年轻代与年老代比值为1：3，年轻代占整个年轻代年老代和的1/4
//                -XX:SurvivorRatio=n:年轻代中Eden区与两个Survivor区的比值。注意Survivor区有两个。如：3，表示Eden：Survivor=3：2，一个Survivor区占整个年轻代的1/5
//                -XX:MaxPermSize=n:设置持久代大小
//           收集器设置
//                -XX:+UseSerialGC:设置串行收集器
//                -XX:+UseParallelGC:设置并行收集器
//                -XX:+UseParalledlOldGC:设置并行年老代收集器
//                -XX:+UseConcMarkSweepGC:设置并发收集器
//           垃圾回收统计信息
//                -XX:+PrintGC
//                -XX:+PrintGCDetails
//                -XX:+PrintGCTimeStamps
//                -Xloggc:filename
//           并行收集器设置
//                -XX:ParallelGCThreads=n:设置并行收集器收集时使用的CPU数。并行收集线程数。
//                -XX:MaxGCPauseMillis=n:设置并行收集最大暂停时间
//                -XX:GCTimeRatio=n:设置垃圾回收时间占程序运行时间的百分比。公式为1/(1+n)
//           并发收集器设置
//                -XX:+CMSIncrementalMode:设置为增量模式。适用于单CPU情况。
//                -XX:ParallelGCThreads=n:设置并发收集器年轻代收集方式为并行收集时，使用的CPU数。并行收集线程数。
    }

}