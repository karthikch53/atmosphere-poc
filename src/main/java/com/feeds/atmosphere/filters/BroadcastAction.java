package com.feeds.atmosphere.filters;


public class BroadcastAction {

    private final ACTION a;
    private final Object o;
    private Object originalMsg;

    public enum ACTION {
        CONTINUE,
        ABORT,
        SKIP
    }

    public BroadcastAction(ACTION a, Object o) {
        this.a = a;
        this.o = o;
    }

    public BroadcastAction(Object o) {
        this.a = ACTION.CONTINUE;
        this.o = o;
    }

    public Object message() {
        return o;
    }

    public ACTION action() {
        return a;
    }

    public Object originalMessage() {
        return originalMsg;
    }

    void setOriginalMsg(Object originalMsg) {
        this.originalMsg = originalMsg;
    }
}
