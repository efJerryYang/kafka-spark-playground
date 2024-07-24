package com.example.demo.grpc;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.proto.UserServiceGrpc;
import com.example.demo.proto.GetUserRequest;
import com.example.demo.proto.CreateUserRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    private UserService userService;

    @Override
    public void getUser(GetUserRequest request, StreamObserver<com.example.demo.proto.User> responseObserver) {
        String userId = request.getId();
        userService.getUserById(userId).ifPresentOrElse(
                user -> {
                    com.example.demo.proto.User grpcUser = com.example.demo.proto.User.newBuilder()
                            .setId(user.getId())
                            .setName(user.getName())
                            .setEmail(user.getEmail())
                            .build();
                    responseObserver.onNext(grpcUser);
                    responseObserver.onCompleted();
                },
                () -> responseObserver.onError(new RuntimeException("User not found"))
        );
    }

    @Override
    public void createUser(CreateUserRequest request, StreamObserver<com.example.demo.proto.User> responseObserver) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        User createdUser = userService.createUser(user);

        com.example.demo.proto.User grpcUser = com.example.demo.proto.User.newBuilder()
                .setId(createdUser.getId())
                .setName(createdUser.getName())
                .setEmail(createdUser.getEmail())
                .build();

        responseObserver.onNext(grpcUser);
        responseObserver.onCompleted();
    }
}