package com.voja.Zljeb.Storage;
import java.lang.RuntimeException;

public class StorageExceptions extends RuntimeException{
    public StorageExceptions(String message){
        super(message);
    }
    public StorageExceptions(String message, Throwable cause){
        super(message, cause);
    }
}
