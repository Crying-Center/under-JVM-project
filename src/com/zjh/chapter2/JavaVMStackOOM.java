package com.zjh.chapter2;

/**
 * JavaVMStackOOM class
 *
 * @author zhangjiahuang
 * @date 2022/5/18 17:38
 */
public class JavaVMStackOOM {
    private void dontStop() {
        while (true) {
        }
    }
    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }
    // 重点提示一下，如果读者要尝试运行上面这段代码，记得要先保存当前的工作，由于在
    // Windows平台的虚拟机中，Java的线程是映射到操作系统的内核线程上，无限制地创建线程会对操
    // 作系统带来很大压力，上述代码执行时有很高的风险，可能会由于创建线程数量过多而导致操作系统
    // 假死
    public static void main(String[] args) throws Throwable {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}

