package com.north.lat.classparselat;

import com.north.lat.classparselat.utils.ByteUtils;
import com.north.lat.classparselat.utils.Constants;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author laihaohua
 */
public class JavaClassFile implements Serializable {
    private Integer a = 1;
    private Long  b = 2L;
    private Double c = 3D;
    private Float d = 4F;

    private Queue<Byte> classFileByteList;
    public JavaClassFile(byte [] bytes){
        classFileByteList = new LinkedList<Byte>();
        for(Byte b : bytes){
           classFileByteList.add(b);
        }
    }
    public void printClassInfo(){
        System.out.println("magic: " + getMagic());
        System.out.println("minor version:" + getMinorVersion());
        System.out.println("major version:" + getMajorVersion());
        printConstantPoolInfo();
    }

    private short getShortFromQueue(){
        byte [] bytes = pollBytesFromQueue(2);
        return ByteUtils.byteArrToShort(bytes);
    }
    private byte getByteFromQueue(){
        byte [] bytes = pollBytesFromQueue(1);
        return bytes[0];
    }
    private int getIntFromQueue(){
        byte [] bytes = pollBytesFromQueue(4);
        return ByteUtils.byteArrToInt(bytes);
    }
    private long getLongFromQueue(){
        byte [] bytes = pollBytesFromQueue(8);
        return ByteUtils.byteArrToLong(bytes);
    }
    private double getDoubleFromQueue(){
        byte [] bytes = pollBytesFromQueue(8);
        return ByteUtils.byteArrToDouble(bytes);
    }
    private byte [] pollBytesFromQueue(int n) throws  NullPointerException{
        byte [] bytes = new byte[n];
        for(int i = 0; i < n; i++){
             Byte b = classFileByteList.poll();
             if(b == null){
                 throw  new  NullPointerException();
             }
             bytes[i] = b;

        }
        return bytes;
    }

    private String getMagic(){
        byte[] bytes =  pollBytesFromQueue(Constants.MAGIC_BYTE_COUNT);
        return  ByteUtils.bytesToHexStr(bytes);
    }
    private String getMinorVersion(){
        byte [] b  = pollBytesFromQueue(Constants.MINOR_VERSION_COUNT);
        String hex = ByteUtils.bytesToHexStr(b);
        short s = ByteUtils.byteArrToShort(b);
        return hex + "(" + s + ")";
    }
    private String getMajorVersion(){
        byte [] b  = pollBytesFromQueue(Constants.MAJOR_VERSION_COUNT);
        String hex = ByteUtils.bytesToHexStr(b);
        short s = ByteUtils.byteArrToShort(b);
        return hex + "(" + s + ")";
    }
    private void printConstantPoolInfo(){
         short constantPoolCount = getShortFromQueue();
         System.out.println("该类一个有" + constantPoolCount + "常量池");
         int index = 1;
         while(constantPoolCount > 0 && index < constantPoolCount){
             System.out.println( "常量池:#" + (index++));
             byte tag = getByteFromQueue();
             switch (tag){
                 case (Constants.CONSTANT_METHOD_REF):
                     printMethodRef();
                     break;
                 case (Constants.CONSTANT_CLASS):
                     printClass();
                     break;
                 case (Constants.CONSTANT_STRING):
                     printString();
                     break;
                 case (Constants.CONSTANT_FIELD_REF):
                     printFieldRef();
                     break;
                 case (Constants.CONSTANT_INTERFACE_METHOD_REF):
                     printInterfaceMethodRef();
                     break;
                 case (Constants.CONSTANT_UTF8):
                     printUtf8();
                     break;
                 case (Constants.CONSTANT_NAME_AND_TYPE):
                     printNameAndType();
                     break;
                 case (Constants.CONSTANT_INTEGER):
                     printInteger();
                     break;
                 case (Constants.CONSTANT_FLOAT):
                     printFloat();
                     break;
                 case (Constants.CONSTANT_DOUBLE):
                     printDouble();
                     break;
                 case (Constants.CONSTANT_LONG):
                     printLong();
                     break;
                 case (Constants.CONSTANT_METHOD_TYPE):
                     printMethodType();
                     break;
                 case (Constants.CONSTANT_DYNAMIC):
                     printDynamicInfo();
                     break;
                 case (Constants.CONSTANT_INVOKE_DYNAMIC):
                     printInvokeDynamicInfo();
                     break;
                 case (Constants.CONSTANT_MODULE):
                     printModuleInfo();
                     break;
                 case (Constants.CONSTANT_PACKAGE):
                     printPackageInfo();
                     break;
                 case (Constants.CONSTANT_METHOD_HANDLE):
                     printMethodHandler();
                     break;
                default: throw  new IllegalStateException("tag can not be " + tag);
             }
         }
    }

    private void printMethodHandler(){
        System.out.println("MethodHandle :");
        byte referenceKind = getByteFromQueue();
        short referenceIndex = getShortFromQueue();
        System.out.println("reference_kind = " + referenceKind);
        System.out.println("reference_index = #" + referenceIndex);
    }

    private void printDynamicInfo(){
        System.out.println("DynamicInfo:");
        short bootstrapMethodAttrIndex = getShortFromQueue();
        System.out.println("bootstrap_method_attr_index = #" + bootstrapMethodAttrIndex);
        short name_and_type_index = getShortFromQueue();
        System.out.println("name_and_type_index = #" + name_and_type_index);
    }

    private void printInvokeDynamicInfo(){
        System.out.println("InvokeDynamicInfo:");
        short bootstrapMethodAttrIndex = getShortFromQueue();
        System.out.println("bootstrap_method_attr_index = #" + bootstrapMethodAttrIndex);
        short name_and_type_index = getShortFromQueue();
        System.out.println("name_and_type_index = #" + name_and_type_index);
    }
    private void printMethodRef(){
        System.out.println("MethodRef:");
        short classIndex = getShortFromQueue();
        System.out.println("classIndex : #" + classIndex);
        short nameAndTypeIndex = getShortFromQueue();
        System.out.println("nameAndTypeIndex : #" + nameAndTypeIndex);
    }
    private void printClass(){
        System.out.println("CLASS:");
        short nameIndex = getShortFromQueue();
        System.out.println("name_index : #" + nameIndex);
    }

    private void printString(){
        System.out.println("String:");
        short stringIndex = getShortFromQueue();
        System.out.println("string_index : #" + stringIndex);
    }

    private void printFieldRef(){
        System.out.println("FIELD_REF:");
        short classIndex = getShortFromQueue();
        System.out.println("classIndex : #" + classIndex);
        short nameAndTypeIndex = getShortFromQueue();
        System.out.println("nameAndTypeIndex : #" + nameAndTypeIndex);
    }
    private void printInterfaceMethodRef(){
        System.out.println("INTERFACE_METHOD_REF:");
        short classIndex = getShortFromQueue();
        System.out.println("classIndex : #" + classIndex);
        short nameAndTypeIndex = getShortFromQueue();
        System.out.println("nameAndTypeIndex : #" + nameAndTypeIndex);
    }
    private void printUtf8(){
        System.out.println("UTF8:");
        Short length = getShortFromQueue();
        System.out.println("length : " + length);
        byte [] contentsByte = pollBytesFromQueue(length);
        System.out.println("content : " + new String(contentsByte));
    }

    private void printNameAndType(){
        System.out.println("NameAndType:");
        short classIndex = getShortFromQueue();
        System.out.println("classIndex : #" + classIndex);
        short nameAndTypeIndex = getShortFromQueue();
        System.out.println("nameAndTypeIndex : #" + nameAndTypeIndex);
    }

    private void printInteger(){
        System.out.println("Integer:");
        int bytesContent = getIntFromQueue();
        System.out.println("bytesContent : " + bytesContent);
    }

    private void printFloat(){
        System.out.println("Float:");
        int bytesContent = getIntFromQueue();
        System.out.println("bytesContent : " + bytesContent);
    }


    private void printPackageInfo(){
        System.out.println("PackageInfo:");
        short nameIndex = getShortFromQueue();
        System.out.println("name_index : #" + nameIndex);
    }

    private void printModuleInfo(){
        System.out.println("ModuleInfo:");
        short nameIndex = getShortFromQueue();
        System.out.println("name_index : #" + nameIndex);
    }
    private void printLong(){
        System.out.println("Long:");
        long bytesContent = getLongFromQueue();
        System.out.println("bytesContent : " + bytesContent);
    }

    private void printDouble(){
        System.out.println("Double:");
        double bytesContent = getDoubleFromQueue();
        System.out.println("bytesContent : " + bytesContent);
    }
    private void printMethodType(){
        System.out.println("MethodType:");
        short descriptorIndex = getShortFromQueue();
        System.out.println("descriptor_index : " + descriptorIndex);
    }

}
