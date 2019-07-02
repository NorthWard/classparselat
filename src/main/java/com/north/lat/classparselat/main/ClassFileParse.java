package com.north.lat.classparselat.main;

import com.north.lat.classparselat.utils.ByteUtils;

import java.io.FileInputStream;
import java.io.IOException;

public class ClassFileParse {
    public static void main(String[] args) throws IOException {
        java.io.File file = new java.io.File("F:\\code\\spilat\\target\\classes\\com\\north\\spilat\\controller\\HelloWorldController.class");
        FileInputStream fileInputStream = new FileInputStream(file);
        int size = fileInputStream.available();
        byte [] bytes = new byte[size];
        fileInputStream.read(bytes);
        int index = 0;
        index = print_magic_number(bytes, index);
        index = print_minor_version_number(bytes, index);
        index = print_major_version_number(bytes, index);
        index = print_constant_pool_count(bytes, index);
        index = print_constant_pool_table(bytes, index);
        byte [] tmp = ByteUtils.getSubByteArr(bytes, index, 2);
        index +=2;
        System.out.println("access flags, a bitmask = [" + ByteUtils.bytesToHexStr(tmp) + "]");
        tmp = ByteUtils.getSubByteArr(bytes, index, 2);
        index +=2;
        System.out.println("identifies this class = [#" + ByteUtils.byteArrToShort(tmp) + "]");

        tmp = ByteUtils.getSubByteArr(bytes, index, 2);
        index +=2;
        System.out.println("identifies super class = [#" + ByteUtils.byteArrToShort(tmp) + "]");

        tmp = ByteUtils.getSubByteArr(bytes, index, 2);
        index +=2;
        System.out.println("interface count = [" + ByteUtils.byteArrToShort(tmp) + "]");

        tmp = ByteUtils.getSubByteArr(bytes, index, 2);
        index +=2;
        System.out.println("field count = [" + ByteUtils.byteArrToShort(tmp) + "]");

        System.out.println("第一个field:");
        tmp = ByteUtils.getSubByteArr(bytes, index, 2);
        index +=2;
        System.out.println("access_flags = [" + ByteUtils.bytesToHexStr(tmp) + "]");
        tmp = ByteUtils.getSubByteArr(bytes, index, 2);
        index +=2;
        System.out.println("name_index = [#" + ByteUtils.byteArrToShort(tmp) + "]");
        tmp = ByteUtils.getSubByteArr(bytes, index, 2);
        index +=2;
        System.out.println("descriptor_index = [#" + ByteUtils.byteArrToShort(tmp) + "]");
        tmp = ByteUtils.getSubByteArr(bytes, index, 2);
        index +=2;
        System.out.println("attributes_count = [" + ByteUtils.byteArrToShort(tmp) + "]");
        System.out.println("第一个field的第一个attributes:");
        tmp = ByteUtils.getSubByteArr(bytes, index, 2);
        index +=2;
        System.out.println("attribute_name_index = [#" + ByteUtils.byteArrToShort(tmp) + "]");
        tmp = ByteUtils.getSubByteArr(bytes, index, 4);
        index +=4;
        int attrLen = ByteUtils.byteArrToInt(tmp);
        System.out.println("attribute_length = [" + attrLen + "]");

        tmp = ByteUtils.getSubByteArr(bytes, index, attrLen);
        index +=attrLen;
        System.out.println("attribute_info  = [" + ByteUtils.bytesToHexStr(tmp) + "]");

        tmp = ByteUtils.getSubByteArr(bytes, index, 2);
        index +=2;
        System.out.println("method count = [" + ByteUtils.byteArrToShort(tmp) + "]");

    }

    public static int print_magic_number(byte [] bytes, int begin){
        int size = 4;
        System.out.print("print_magic_number : ");
        for(int i = begin; i < begin+size; i++){
            System.out.print(byteToHex(bytes[i]));
        }
        System.out.println();
        return begin + size;
    }

    public static int print_minor_version_number(byte [] bytes, int begin){
        int size = 2;
        byte [] tmp = new byte[size];
        int pos = 0;
        System.out.print("print_minor_version_number : ");
        for(int i = begin; i < begin+size; i++){
            tmp[pos++] = bytes[i];
            System.out.print(byteToHex(bytes[i]));
        }
        System.out.print("(" + ByteUtils.byteArrToShort(tmp) + ")");
        System.out.println();
        return begin + size;
    }

    public static int print_major_version_number(byte [] bytes, int begin){
        int size = 2;
        byte [] tmp = new byte[size];
        int pos = 0;
        System.out.print("print_major_version_number : ");
        for(int i = begin; i < begin+size; i++){
            tmp[pos++] = bytes[i];
            System.out.print(byteToHex(bytes[i]));
        }
        System.out.print("(" + ByteUtils.byteArrToShort(tmp) + ")");
        System.out.println();
        return begin + size;
    }

    private static int print_constant_pool_count(byte [] bytes, int begin){
        int size = 2;
        byte [] tmp = new byte[size];
        int pos = 0;
        System.out.print("print_constant_pool_count : ");
        for(int i = begin; i < begin+size; i++){
            tmp[pos++] = bytes[i];
            System.out.print(byteToHex(bytes[i]));
        }
        System.out.print("(" + ByteUtils.byteArrToShort(tmp) + ")");
        System.out.println();
        return begin + size;
    }
    private static int print_constant_pool_table(byte [] bytes, int begin){
        int size = 62;
        byte [] tmp = bytes;
        int pos = 0;
        System.out.print("print_constant_pool_table : ");
        pos = begin;
        System.out.println("第一个常量池 #1");
        System.out.println("tag:" + tmp[pos++]);
        byte [] classIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("classIndex : #" + ByteUtils.byteArrToShort(classIndex));
        byte [] nameAndTypeIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("nameAndTypeIndex : #" + ByteUtils.byteArrToShort(nameAndTypeIndex));

        System.out.println("第2个常量池 #2");
        System.out.println("tag:" + tmp[pos++]);
        byte [] name_index = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("name_index : #" + ByteUtils.byteArrToShort(name_index));

        System.out.println("第3个常量池 #3");
        System.out.println("tag:" + tmp[pos++]);
        byte [] string_index = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos += 2;
        System.out.println("string_index : #" + ByteUtils.byteArrToShort(string_index));

        System.out.println("第4个常量池 #4");
        System.out.println("tag:" + tmp[pos++]);
        classIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("classIndex : #" + ByteUtils.byteArrToShort(classIndex));
        nameAndTypeIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("nameAndTypeIndex : #" + ByteUtils.byteArrToShort(nameAndTypeIndex));

        System.out.println("第5个常量池 #5");
        System.out.println("tag:" + tmp[pos++]);
        classIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("classIndex : #" + ByteUtils.byteArrToShort(classIndex));
        nameAndTypeIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("nameAndTypeIndex : #" + ByteUtils.byteArrToShort(nameAndTypeIndex));

        System.out.println("第6个常量池 #6");
        System.out.println("tag:" + tmp[pos++]);
        classIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("classIndex : #" + ByteUtils.byteArrToShort(classIndex));
        nameAndTypeIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("nameAndTypeIndex : #" + ByteUtils.byteArrToShort(nameAndTypeIndex));

        System.out.println("第7个常量池 #7");
        System.out.println("tag:" + tmp[pos++]);
        classIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("classIndex : #" + ByteUtils.byteArrToShort(classIndex));
        nameAndTypeIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("nameAndTypeIndex : #" + ByteUtils.byteArrToShort(nameAndTypeIndex));


        System.out.println("第8个常量池 #8");
        System.out.println("tag:" + tmp[pos++]);
        classIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("classIndex : #" + ByteUtils.byteArrToShort(classIndex));
        nameAndTypeIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("nameAndTypeIndex : #" + ByteUtils.byteArrToShort(nameAndTypeIndex));


        System.out.println("第9个常量池 #9");
        System.out.println("tag:" + tmp[pos++]);
        classIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("classIndex : #" + ByteUtils.byteArrToShort(classIndex));
        nameAndTypeIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("nameAndTypeIndex : #" + ByteUtils.byteArrToShort(nameAndTypeIndex));


        System.out.println("第10个常量池 #10");
        System.out.println("tag:" + tmp[pos++]);
        name_index = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("name_index : #" + ByteUtils.byteArrToShort(name_index));


        System.out.println("第11个常量池 #11");
        System.out.println("tag:" + tmp[pos++]);
        byte []  lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        Short length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        byte [] contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;

        System.out.println("第12个常量池 #12");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;

        System.out.println("第13个常量池 #13");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;

        System.out.println("第14个常量池 #14");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;

        System.out.println("第15个常量池 #15");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;


        System.out.println("第16个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;

        System.out.println("第17个常量池 #17");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;
        System.out.println("第18个常量池 #18");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;
        System.out.println("第19个常量池 #19");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;
        System.out.println("第20个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;
        System.out.println("第21个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;
        System.out.println("第22个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;
        System.out.println("第23个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;
        System.out.println("第24个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;
        System.out.println("第25个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;
        System.out.println("第26个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;
        System.out.println("第27个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;
        System.out.println("第28个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;
        System.out.println("第19个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;
        System.out.println("第30个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;
        System.out.println("第31个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;
        System.out.println("第32个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;
        System.out.println("第33个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;
        System.out.println("第34个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;


        System.out.println("第35个常量池 #35");
        System.out.println("tag:" + tmp[pos++]);
        name_index = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("name_index : #" + ByteUtils.byteArrToShort(name_index));


        System.out.println("第36个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;
        System.out.println("第37个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;
        System.out.println("第38个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;
        System.out.println("第39个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;
        System.out.println("第40个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;
        System.out.println("第41个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;

        System.out.println("第42个常量池 #9");
        System.out.println("tag:" + tmp[pos++]);
        classIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("classIndex : #" + ByteUtils.byteArrToShort(classIndex));
        nameAndTypeIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("nameAndTypeIndex : #" + ByteUtils.byteArrToShort(nameAndTypeIndex));



        System.out.println("第43个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;
        System.out.println("第44个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;


        System.out.println("第45个常量池 #45");
        System.out.println("tag:" + tmp[pos++]);
        name_index = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("name_index : #" + ByteUtils.byteArrToShort(name_index));


        System.out.println("第46个常量池 #9");
        System.out.println("tag:" + tmp[pos++]);
        classIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("classIndex : #" + ByteUtils.byteArrToShort(classIndex));
        nameAndTypeIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("nameAndTypeIndex : #" + ByteUtils.byteArrToShort(nameAndTypeIndex));

        System.out.println("第47个常量池 #9");
        System.out.println("tag:" + tmp[pos++]);
        classIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("classIndex : #" + ByteUtils.byteArrToShort(classIndex));
        nameAndTypeIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("nameAndTypeIndex : #" + ByteUtils.byteArrToShort(nameAndTypeIndex));

        System.out.println("第48个常量池 #9");
        System.out.println("tag:" + tmp[pos++]);
        classIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("classIndex : #" + ByteUtils.byteArrToShort(classIndex));
        nameAndTypeIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("nameAndTypeIndex : #" + ByteUtils.byteArrToShort(nameAndTypeIndex));

        System.out.println("第49个常量池 #9");
        System.out.println("tag:" + tmp[pos++]);
        classIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("classIndex : #" + ByteUtils.byteArrToShort(classIndex));
        nameAndTypeIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("nameAndTypeIndex : #" + ByteUtils.byteArrToShort(nameAndTypeIndex));


        System.out.println("第50个常量池 #45");
        System.out.println("tag:" + tmp[pos++]);
        name_index = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("name_index : #" + ByteUtils.byteArrToShort(name_index));

        System.out.println("第51个常量池 #9");
        System.out.println("tag:" + tmp[pos++]);
        classIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("classIndex : #" + ByteUtils.byteArrToShort(classIndex));
        nameAndTypeIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("nameAndTypeIndex : #" + ByteUtils.byteArrToShort(nameAndTypeIndex));


        System.out.println("第52个常量池 #45");
        System.out.println("tag:" + tmp[pos++]);
        name_index = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("name_index : #" + ByteUtils.byteArrToShort(name_index));


        System.out.println("第53个常量池 #9");
        System.out.println("tag:" + tmp[pos++]);
        classIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("classIndex : #" + ByteUtils.byteArrToShort(classIndex));
        nameAndTypeIndex = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        System.out.println("nameAndTypeIndex : #" + ByteUtils.byteArrToShort(nameAndTypeIndex));


        System.out.println("第54个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;

        System.out.println("第55个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;

        System.out.println("第56个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;

        System.out.println("第57个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;

        System.out.println("第58个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;

        System.out.println("第59个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;

        System.out.println("第60个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;

        System.out.println("第61个常量池 #16");
        System.out.println("tag:" + tmp[pos++]);
        lengthBytes = ByteUtils.getSubByteArr(tmp, pos, 2);
        pos +=2;
        length = ByteUtils.byteArrToShort(lengthBytes);
        System.out.println("length : " + length);
        contentsByte = ByteUtils.getSubByteArr(tmp, pos, length);
        System.out.println("content : " + new String(contentsByte));
        pos += length;

        System.out.println();
        return pos;
    }
    /**
     * 字节转十六进制
     * @param b 需要进行转换的byte字节
     * @return  转换后的Hex字符串
     */
    public static String byteToHex(byte b){
        String hex = Integer.toHexString(b & 0xFF);
        if(hex.length() < 2){
            hex = "0" + hex;
        }
        return hex;
    }

    /**
     * 把byte转为字符串的bit
     */
    public static String byteToBit(byte b) {
        return ""
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)
                + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);

    }
}
