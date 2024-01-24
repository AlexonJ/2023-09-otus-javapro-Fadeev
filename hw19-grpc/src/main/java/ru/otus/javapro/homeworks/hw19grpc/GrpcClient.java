package ru.otus.javapro.homeworks.hw19grpc;

import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.javapro.homeworks.hw19grpc.configuration.AppConfig;
import ru.otus.javapro.homeworks.hw19grpc.generated.LoadBalancerServiceGrpc;
import ru.otus.javapro.homeworks.hw19grpc.generated.Request;

public class GrpcClient {

    private static final Logger log = LoggerFactory.getLogger(LoadBalancerServiceImpl.class);

    private static final AppConfig config = AppConfig.getConfiguration();

    public static void main(String[] arg) {

        var channel = ManagedChannelBuilder.forAddress(config.getHost(), config.getPort())
                .usePlaintext()
                .build();

        var stub = LoadBalancerServiceGrpc.newBlockingStub(channel);

        for (int i = 0; i < 10; i++) {
            var request = Request.newBuilder()
                    .setId(RandomNumberHelper.getRandomId())
                    .setContent("content").build();

            stub.processRequest(request).forEachRemaining(response ->
                    log.info("Get response: %s".formatted(response.getContent())));

        }
        channel.shutdown();
    }
}