package com.yuranos.javafiles;

import clojure.java.api.Clojure;

public abstract class ShittyClass {

    private final String classMission;

//    Must be public. Otherwise:
// No matching ctor found for class com.yuranos.general.datatypes.proxy$com.yuranos.javafiles.ShittyClass$ISeq$ShittyInterface$9bd1e475
    public ShittyClass(String classMission) {
        this.classMission = classMission;
    }

//    Must be public for Clojure interop to work. Otherwise:
// IllegalArgumentException No matching field found: abstractMethod for class com.yuranos.general.datatypes.proxy$com.yuranos.javafiles.ShittyClass$ISeq$ShittyInterface$9bd1e475  clojure.lang.Reflector.getInstanceField (Reflector.java:271)
    public abstract void abstractMethod();

    public void realMethod() {
        abstractMethod();
    }

    public String getClassMission() {
        return classMission;
    }
}
