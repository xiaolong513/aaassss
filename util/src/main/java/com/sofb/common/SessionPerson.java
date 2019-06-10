package com.sofb.common;

/**
 * 当前登陆的User，线程绑定
 */
public class SessionPerson {
    private static final ThreadLocal currentPerson = new ThreadLocal();

    public static <T> T get() {
        return (T) currentPerson.get();
    }

    public static <T> void set(T person) {
        currentPerson.set(person);
    }

    public static void clear() {
        currentPerson.remove();
    }
}
