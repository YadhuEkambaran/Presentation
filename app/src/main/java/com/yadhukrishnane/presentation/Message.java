package com.yadhukrishnane.presentation;

public class Message {

    public static final int LEFT_SIDE = 0;
    public static final int RIGHT_SIDE = 1;

    private String message;
    private long timing = System.currentTimeMillis();
    private int side = RIGHT_SIDE;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTiming() {
        return timing;
    }

    public void setTiming(long timing) {
        this.timing = timing;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }
}
