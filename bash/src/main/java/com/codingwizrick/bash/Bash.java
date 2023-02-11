package com.codingwizrick.bash;

import android.app.Application;
import android.util.Log;

import com.jaredrummler.ktsh.Shell;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Bash extends Application {
    private static Bash BASH;
    private boolean isRoot;

    Shell bash;
    Res res;
    ExecutorService executorService;
    public Bash() {
        try {
            bash = Shell.Companion.getSU();
            isRoot = true;
            System.out.println(Thread.currentThread().getName());
        } catch (Exception e) {
            bash = Shell.Companion.getSH();
            isRoot = false;
        }
        Log.d("TAG", "Bash() called & Running with root perm "+isRoot);

        executorService = Executors.newFixedThreadPool(25, r -> new Thread(r,"Command Execution Thread no: "+Thread.activeCount()));

    }
    public Bash(int noOfThreads) {
        try {
            bash = Shell.Companion.getSU();
            isRoot = true;
            System.out.println(Thread.currentThread().getName());
        } catch (Exception e) {
            bash = Shell.Companion.getSH();
            isRoot = false;
        }
        Log.d("TAG", "Bash() called & Running with root perm "+isRoot);

        executorService = Executors.newFixedThreadPool(noOfThreads, r -> new Thread(r,"Command Execution Thread no: "+Thread.activeCount()));

    }
    public static Bash getInstance() {
        if (BASH == null) {
            BASH = new Bash();
        }
        return BASH;
    }
    public static Bash getInstance(int noOfThreads) {
        if (BASH == null) {
            BASH = new Bash(noOfThreads);
        }
        return BASH;
    }
    public Res exec(String cmd) {
        try {
            res = executorService.submit(() -> new Res(bash.run(cmd))).get();
            Log.d("TAG", "exec() called with: command = [" + cmd + "]");
            return res;
        } catch (Exception e) {
            return res;
            //  throw new RuntimeException(e);
        }
        //
    }

    public String getShellPath(){
        return bash.getPath();
    }

    public boolean isRoot() {
        return isRoot;
    }
    public boolean isAlive(){
       return bash.isAlive();
    }
    public boolean shutdown(){
        if (bash.isAlive()&&executorService.isShutdown()){
            bash.shutdown();
            executorService.shutdown();
            return true;
        }else {
           return false;
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        getInstance();
    }
}
