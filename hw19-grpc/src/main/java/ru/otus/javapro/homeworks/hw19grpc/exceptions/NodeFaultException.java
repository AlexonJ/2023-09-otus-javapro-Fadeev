package ru.otus.javapro.homeworks.hw19grpc.exceptions;

public class NodeFaultException extends RuntimeException {
    public NodeFaultException(String message) {
        super(message);
    }
}
