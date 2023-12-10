package sep3datalayer.grpc;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import sep3datalayer.grpc.protobuf.*;
import sep3datalayer.models.CenterEntity;
import sep3datalayer.models.UserEntity;
import sep3datalayer.services.UserServiceImpl;

@GRpcService
public class UserImpl extends UserServiceGrpc.UserServiceImplBase {

    private final UserServiceImpl userService;

    public UserImpl(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public void createUser(CreatingUser user, StreamObserver<UserGrpc> responseObserver) {
        try {
            userService.addUser(new UserEntity(user.getUsername(), user.getPassword(), user.getEmail(), user.getName(), user.getRole()));

            UserEntity userCreated = userService.getByUsername(user.getUsername());

            UserGrpc user1 = userCreated.convertToUserGrpc();

            responseObserver.onNext(user1);
            responseObserver.onCompleted();
        } catch (Exception e) {
            Status status;
            if (e instanceof IllegalArgumentException) {
                status = Status.FAILED_PRECONDITION.withDescription(e.getMessage());
            } else {
                status = Status.INTERNAL.withDescription(e.getMessage());
            }
            responseObserver.onError(status.asRuntimeException());
        }
    }

    @Override
    public void getAllUsers(com.google.protobuf.Empty request, StreamObserver<UserList> responseObserver) {
        try {
            UserList.Builder userList = UserList.newBuilder();
            for (UserEntity user : userService.getAllUsers()) {
                userList.addUser(user.convertToUserGrpc());
            }
            responseObserver.onNext(userList.build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            Status status;
            if (e instanceof IllegalArgumentException) {
                status = Status.FAILED_PRECONDITION.withDescription(e.getMessage());
            } else {
                status = Status.INTERNAL.withDescription(e.getMessage());
            }
            responseObserver.onError(status.asRuntimeException());
        }
    }

    @Override
    public void getCenterAdmins(CenterId request, StreamObserver<UserList> responseObserver) {
        try {
            UserList.Builder userList = UserList.newBuilder();
            for (UserEntity user : userService.getCenterAdmins(request.getId())) {
                userList.addUser(user.convertToUserGrpc());
            }
            responseObserver.onNext(userList.build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            Status status;
            if (e instanceof IllegalArgumentException)
                status = Status.FAILED_PRECONDITION.withDescription(e.getMessage());
            else
                status = Status.INTERNAL.withDescription(e.getMessage());
            responseObserver.onError(status.asRuntimeException());
        }
    }

    @Override
    public void getAdminnedCenters(UserUsername request, StreamObserver<CenterAdminList> responseObserver) {
        try {
            CenterAdminList.Builder centerList = CenterAdminList.newBuilder();
            for (CenterEntity center : userService.getAdminnedCenters(request.getUsername())) {
                centerList.addAdmins(center.convertToCenterAdmin(request.getUsername()));
            }
            responseObserver.onNext(centerList.build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            Status status;
            if (e instanceof IllegalArgumentException)
                status = Status.FAILED_PRECONDITION.withDescription(e.getMessage());
            else
                status = Status.INTERNAL.withDescription(e.getMessage());
            responseObserver.onError(status.asRuntimeException());
        }
    }
}
