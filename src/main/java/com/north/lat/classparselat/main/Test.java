package com.north.lat.classparselat.main;

import com.north.lat.classparselat.JavaClassFile;

import java.io.FileInputStream;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        java.io.File file = new java.io.File("F:\\code\\python\\classparselat\\target\\classes\\com\\north\\lat\\classparselat\\JavaClassFile.class");
        FileInputStream fileInputStream = new FileInputStream(file);
        int size = fileInputStream.available();
        byte [] bytes = new byte[size];
        fileInputStream.read(bytes);
        JavaClassFile javaClassFile = new JavaClassFile(bytes);
        javaClassFile.printClassInfo();
    }
}
