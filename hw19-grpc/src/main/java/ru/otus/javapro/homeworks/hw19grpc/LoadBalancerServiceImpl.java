package ru.otus.javapro.homeworks.hw19grpc;

import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.javapro.homeworks.hw19grpc.configuration.AppConfig;
import ru.otus.javapro.homeworks.hw19grpc.generated.LoadBalancerServiceGrpc;
import ru.otus.javapro.homeworks.hw19grpc.generated.NodeServiceGrpc;
import ru.otus.javapro.homeworks.hw19grpc.generated.Request;
import ru.otus.javapro.homeworks.hw19grpc.generated.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LoadBalancerServiceImpl extends LoadBalancerServiceGrpc.LoadBalancerServiceImplBase{

    private static final Logger log = LoggerFactory.getLogger(LoadBalancerServiceImpl.class);

    private static final AppConfig config = AppConfig.getConfiguration();

    private int nextPortNumber;

    Map<Integer, Server> nodes = new HashMap<>();

    public LoadBalancerServiceImpl(int numberOfNodes, int portNumber) {
        this.nextPortNumber = portNumber;
        for (int i = 0; i < numberOfNodes; i++) {
            var nodeId = i + 1;
            var server = ServerBuilder.forPort(nextPortNumber++)
                    .addService(new NodeServiceImpl(nodeId)).build();
            try {
                server.start();
                log.info("Node {} server start on port {}", nodeId, server.getPort());
            } catch (IOException e) {
                log.error(e.getMessage());
            }
            nodes.put(server.getPort(), server);
        }
    }

    @Override
    public void processRequest(Request request, StreamObserver<Response> responseObserver){

        while (!nodes.isEmpty()) {

            var node = getNextNode();
            var channel = ManagedChannelBuilder.forAddress(config.getHost(), node.getPort())
                    .usePlaintext()
                    .build();

            var stub = NodeServiceGrpc.newBlockingStub(channel);

            try {
                var result = stub.processRequest(request);
                responseObserver.onNext(result);
                responseObserver.onCompleted();
                break;
            } catch (StatusRuntimeException e) {
                log.error("Node on port {} was fault due to {}. Shutting down node.", node.getPort(), e.getMessage());
                nodes.remove(node.getPort());
            }

            channel.shutdownNow();
            try {
                channel.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Server getNextNode(){
        var keysArray = nodes.keySet().toArray();
        var randomNodePort = keysArray[RandomNumberHelper.getRandomNumber(nodes.size())];
        return nodes.get(randomNodePort);
    }

}
