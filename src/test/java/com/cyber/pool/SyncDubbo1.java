package com.cyber.pool;

public class SyncDubbo1 {
     
    public synchronized void method1(SyncDubbo1 sd){  
        if(Thread.currentThread().getName().equals("t1")){  
            Thread t3 = new Thread(new Runnable() {  
                  
                @Override  
                public void run() {  
                    sd.method2();  
                }  
            },"t3");  
            t3.start();  
            System.out.println("线程t1的method1方法执行..");  
            method2();  
            System.out.println("线程t1的method1调用结束");  
        }else if(Thread.currentThread().getName().equals("t2")){  
            System.out.println("线程t2的method1方法执行..");  
            method2();  
            System.out.println("线程t2的method1调用结束");  
        }  
    }  
    public void method2() {  
        if(Thread.currentThread().getName().equals("t1")){  
            System.out.println("线程t1的method2方法执行..");  
            method3();  
            System.out.println("线程t1的method2调用结束");  
        }else if(Thread.currentThread().getName().equals("t2")){  
            System.out.println("线程t2的method2方法执行..");  
            method3();  
            System.out.println("线程t2的method2调用结束");  
        }else if(Thread.currentThread().getName().equals("t3")){  
            System.out.println("线程t3的method2方法执行..");  
            method3();  
            System.out.println("线程t3的method2调用结束");  
        }  
    }  
    public void method3(){  
        if(Thread.currentThread().getName().equals("t1")){  
            System.out.println("线程t1的method3方法执行完毕");  
        }else if(Thread.currentThread().getName().equals("t2")){  
            System.out.println("线程t2的method3方法执行完毕");  
        }else if(Thread.currentThread().getName().equals("t3")){  
            System.out.println("线程t3的method3方法执行完毕");  
        }  
    }  
      
    public static void main(String[] args){  
        final SyncDubbo1 sd = new SyncDubbo1();  
        Thread t1 = new Thread(new Runnable() {  
              
            @Override  
            public void run() {  
                sd.method1(sd);  
            }  
        },"t1");  
        t1.start();  
        Thread t2 = new Thread(new Runnable() {  
              
            @Override  
            public void run() {  
                sd.method1(sd);  
            }  
        },"t2");  
        t2.start();  
    }  
}  
