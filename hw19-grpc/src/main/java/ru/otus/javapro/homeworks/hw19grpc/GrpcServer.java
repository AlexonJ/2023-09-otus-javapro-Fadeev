package ru.otus.javapro.homeworks.hw19grpc;


import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.javapro.homeworks.hw19grpc.configuration.AppConfig;

import java.io.IOException;

public class GrpcServer {

    private static final Logger log = LoggerFactory.getLogger(LoadBalancerServiceImpl.class);

    private static final AppConfig config = AppConfig.getConfiguration();

    public static void main(String[] args) throws IOException, InterruptedException {

        LoadBalancerServiceImpl loadBalancer = new LoadBalancerServiceImpl(config.getNumberOfNodes(), config.getNodesPort());

        var server = ServerBuilder
                .forPort(config.getPort())
                .addService(loadBalancer).build();
        server.start();
        log.info("Server waiting for client connections...");
        server.awaitTermination();
    }
}
