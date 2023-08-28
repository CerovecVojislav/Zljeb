package com.voja.Zljeb.Storage;

public class StorageFileNotFoundException extends StorageExceptions{
    public StorageFileNotFoundException(String message){
        super(message);
    }
    public StorageFileNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
