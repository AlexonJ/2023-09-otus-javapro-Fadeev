package ru.otus.javapro.homeworks.hw19grpc;

import io.grpc.stub.StreamObserver;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.otus.javapro.homeworks.hw19grpc.exceptions.NodeFaultException;
import ru.otus.javapro.homeworks.hw19grpc.generated.NodeServiceGrpc;
import ru.otus.javapro.homeworks.hw19grpc.generated.Request;
import ru.otus.javapro.homeworks.hw19grpc.generated.Response;

import java.util.Random;

@RequiredArgsConstructor
@Getter
public class NodeServiceImpl extends NodeServiceGrpc.NodeServiceImplBase {

    private final int nodeServiceId;

    @Override
    public void processRequest(Request request, StreamObserver<Response> responseObserver) {

        System.out.printf("Start processing request id = %d by node id = %d%n", request.getId(), nodeServiceId);

        if (RandomNumberHelper.getRandomNumber(100) < 10) {
            responseObserver.onError(new NodeFaultException("Node id = %d fault by unforeseen circumstances"));
        } else {

            var response = Response.newBuilder()
                    .setId(new Random().nextInt())
                    .setContent("Response on request id = %d by node id = %d".formatted(request.getId(), nodeServiceId))
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
