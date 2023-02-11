package com.codingwizrick.bash;


public enum Commands {
    GREP("grep ")
    ,LS("ls ")
    ,LS_ALL("ls -a ")
    ,NO_OF_FILES("ls file | grep index[0-9]  | wc -l")
    ,SWAP_OFF("swapoff ")
    ,MK_SWAP("mkswap ")
    ,SWAP_ON("swapon ")

    ;

    String cmd;

    Commands(String cmd) {
        this.cmd = cmd;
    }

    public String getCmd() {
        return cmd;
    }

    public enum CAT {
        READ("cat ")
        ,CREATE_FILE(" cat > ")




        ;
        String cmd;

        CAT(String cmd) {
            this.cmd = cmd;
        }

        public String getCmd() {
            return cmd;
        }
    }
    public enum ECHO {
        ECHO("echo ")
        ;
        String cmd;

        ECHO(String cmd) {
            this.cmd = cmd;
        }

        public String getCmd() {
            return cmd;
        }
    }


}