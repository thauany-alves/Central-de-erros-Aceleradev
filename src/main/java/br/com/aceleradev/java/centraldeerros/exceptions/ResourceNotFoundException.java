package br.com.aceleradev.java.centraldeerros.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName){
        super("Resource: " + resourceName + " not found");
    }
}
