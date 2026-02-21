package org.example.Concurrency;


import java.io.*;
import java.util.concurrent.Semaphore;

// print alternative foo and baar 500 times each in separate threads
public class FooBar {

    private Semaphore fooSemaphore = new Semaphore(1);
    private Semaphore barSemaphore = new Semaphore(0);

     public void foo(BufferedWriter writer) throws InterruptedException {
         fooSemaphore.acquire();
             try {
                 writer.write("Foo\n");
             } catch (IOException e) {
             }
             barSemaphore.release();
    }


     public void bar(BufferedWriter writer) throws InterruptedException {
         barSemaphore.acquire();
             try {
                 writer.write("Bar\n");
             } catch (IOException e) {
             }
             fooSemaphore.release();
    }

    static void main(String[] args) throws Exception{
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/jatinmishra/Desktop/practice/low-level-design/AwesomeLLD/src/main/resources/out.txt"))){
            FooBar fooBar = new FooBar();
            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 500; i++) {
                    try {
                        fooBar.foo(writer);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            Thread t2 = new Thread(() -> {
                for (int i = 0; i < 500; i++) {
                    try {
                        fooBar.bar(writer);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            t1.start();
            t2.start();
            t1.join();
            t2.join();
        }catch (Exception e){

        }
    }
}
