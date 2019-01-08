package com.yuranos.javafiles;

public interface InterfaceWithDefaultMethod {

    void printSomethingOrDefault();
    void printSomethingOrDefault(String str);
    void printStatic();
    void printParam(String param);

    default void myDefaultMethod() {
        System.out.println("I'm a default method");
    }

    static void myStaticMethod() {
        System.out.println("I'm a static method");
    }
}
