package sep3datalayer.grpc;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import sep3datalayer.grpc.protobuf.CenterGrpc;
import sep3datalayer.grpc.protobuf.CenterList;
import sep3datalayer.grpc.protobuf.CenterServiceGrpc;
import sep3datalayer.grpc.protobuf.CreatingCenter;
import sep3datalayer.models.CenterEntity;
import sep3datalayer.services.CenterServiceImpl;


@GRpcService public class CenterImpl extends CenterServiceGrpc.CenterServiceImplBase {

    private final CenterServiceImpl centerService;

    public CenterImpl(CenterServiceImpl centerService) {
        this.centerService = centerService;
    }

    @Override
    public void createCenter(CreatingCenter center, StreamObserver<CenterGrpc> responseObserver) {
        try {
            centerService.addCenter(new CenterEntity(center.getName(), center.getZipCode(), center.getCity(), center.getAddress()));

            CenterEntity centerCreated = centerService.getByName(center.getName());

            CenterGrpc center1 = centerCreated.convertToCenterGrpc();

            responseObserver.onNext(center1);
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
    public void getCenters(com.google.protobuf.Empty request, StreamObserver<CenterList> responseObserver) {
        try {
            CenterList.Builder centerList = CenterList.newBuilder();
            for (CenterEntity center : centerService.getAllCenters()) {
                centerList.addCenter(center.convertToCenterGrpc());
            }
            responseObserver.onNext(centerList.build());
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
}
