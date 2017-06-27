package com.goodfriend.app.event;

/**
 * Created by guoqiang on 2017/6/27.
 */

public class RxEvent {
    private int times;

    private String content;

    public RxEvent() {
    }

    public RxEvent(int times, String content) {
        this.times = times;
        this.content = content;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TestEvent{" +
                "times=" + times +
                ", content='" + content + '\'' +
                '}';
    }
}
