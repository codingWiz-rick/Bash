package com.codingwizrick.bash;

import com.jaredrummler.ktsh.Shell;

import java.util.List;

public class Res {
    private List<String> output;
    private String cmd,elapsedTime,uuid;
    private boolean isSucess;
    private String exitCode,toStringOutput;
    private Thread runBy;

    public List<String> getOutput() {
        return output;
    }

    public String getCmd() {
        return cmd;
    }

    public String getElapsedTime() {
        return Double.valueOf(elapsedTime)/1000+"sec";
    }

    public String getUuid() {
        return uuid;
    }

    public boolean isSucess() {
        return isSucess;
    }

    public String getExitCode() {
        return exitCode;
    }

    public String getToStringOutput() {
        return toStringOutput;
    }


    public Thread getRunBy() {
        return runBy;
    }

    public Res(Shell.Command.Result result) {
        this.output = result.getOutput();
        cmd = result.getDetails().getCommand();
        elapsedTime = String.valueOf(result.getDetails().getElapsed());
        uuid = String.valueOf(result.getDetails().getUuid());
        isSucess = result.isSuccess();
        exitCode = String.valueOf(result.getExitCode());
        toStringOutput = result.output();
        runBy = Thread.currentThread();

        System.out.println();
    }
}
