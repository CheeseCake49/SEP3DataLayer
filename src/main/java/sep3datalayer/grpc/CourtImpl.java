package sep3datalayer.grpc;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import sep3datalayer.grpc.protobuf.CourtGrpc;
import sep3datalayer.grpc.protobuf.CourtServiceGrpc;
import sep3datalayer.grpc.protobuf.CreatingCourt;
import sep3datalayer.models.CourtEntity;
import sep3datalayer.services.CourtServiceImpl;

@GRpcService
public class CourtImpl extends CourtServiceGrpc.CourtServiceImplBase {

    private final CourtServiceImpl courtService;


    public CourtImpl(CourtServiceImpl courtService) {
        this.courtService = courtService;
    }

    @Override
    public void createCourt(CreatingCourt court, StreamObserver<CourtGrpc> responseObserver) {
        try {


            courtService.addCourt(new CourtEntity(court.getCenterId(), court.getCourtType(), court.getCourtNumber(), court.getCourtSponsor()));

            CourtEntity courtCreated = courtService.getByCenterIdAndCourtNumber(court.getCenterId(), court.getCourtNumber());

            CourtGrpc court1 = courtCreated.convertToCourtGrpc();

            responseObserver.onNext(court1);
            responseObserver.onCompleted();

        } catch (Exception e) {
            Status status;
            if (e instanceof IllegalArgumentException) {
                status = Status.FAILED_PRECONDITION.withDescription(e.getMessage());
            }
            else {
                status = Status.INTERNAL.withDescription(e.getMessage());
            }
            responseObserver.onError(status.asRuntimeException());
        }
    }

}
