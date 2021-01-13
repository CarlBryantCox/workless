package com.chw.test.enums;

public enum IpAddr {

    IP_ADDR("https://testjz.jtyedu.com"),
    Password("666666"),

//    IP_ADDR("https://jz.jtyedu.com"),
//    Password("jtyedu123"),

    Nothing("Nothing")
    ;

    private String value;

    IpAddr(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
