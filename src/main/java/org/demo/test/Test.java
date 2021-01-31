package org.demo.test;

import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;

public class Test {
    public static void main(String[] args) {
        String version = SpringVersion.getVersion();
        String version1 = SpringBootVersion.getVersion();
        System.out.println(version);
        System.out.println(version1);
    }
}
