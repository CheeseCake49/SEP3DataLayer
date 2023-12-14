package sep3datalayer.grpc;

import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import sep3datalayer.grpc.protobuf.*;
import sep3datalayer.models.CourtEntity;
import sep3datalayer.services.CenterServiceImpl;
import sep3datalayer.services.interfaces.CourtService;

@GRpcService
public class CourtImpl extends CourtServiceGrpc.CourtServiceImplBase {

    private final CourtService courtService;
    private final CenterServiceImpl centerService;

    public CourtImpl(CourtService courtService, CenterServiceImpl centerService) {
        this.courtService = courtService;
        this.centerService = centerService;
    }

    @Override
    public void createCourt(CreatingCourt court, StreamObserver<CourtGrpc> responseObserver) {
        try {

            courtService.addCourt(new CourtEntity(centerService.getById(court.getCenterId()), court.getCourtType(), court.getCourtNumber(), court.getCourtSponsor()));

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

    @Override
    public void deleteCourtFromCenterId(CourtDeletion courtDeletion, StreamObserver<Empty> responseObserver) {
        try {
            courtService.deleteCourt(courtDeletion.getCenterId(), courtDeletion.getCourtNumber());
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

    @Override
    public void updateCourt(UpdatingCourt court, StreamObserver<CourtGrpc> responseObserver) {
        try {
            CourtGrpc court1 = courtService.updateCourt(court).convertToCourtGrpc();

            responseObserver.onNext(court1);
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
    public void getCourtsFromCenterId(CenterId centerID, StreamObserver<CourtList> responseObserver) {
        try {
            CourtList.Builder courtList = CourtList.newBuilder();
            for (CourtEntity court : courtService.getByCenterID(centerID.getId())) {
                courtList.addCourt(court.convertToCourtGrpc());
            }
            responseObserver.onNext(courtList.build());
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

    @Override
    public void getByCenterIdAndCourtNumber(CourtDeletion court, StreamObserver<CourtGrpc> responseObserver) {
        try {
            CourtEntity courtEntity = courtService.getByCenterIdAndCourtNumber(court.getCenterId(), court.getCourtNumber());

            if (courtEntity == null)
                throw new IllegalArgumentException("No court matching params");

            CourtGrpc courtGrpc = CourtGrpc.newBuilder().setId(courtEntity.getId()).setCenterId(courtEntity.getCenter().getId()).setCourtType(courtEntity.getCourtType())
                    .setCourtNumber(courtEntity.getCourtNumber()).setCourtSponsor(courtEntity.getCourtSponsor()).build();

            responseObserver.onNext(courtGrpc);
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

    @Override
    public void getById(CourtId courtId, StreamObserver<CourtGrpc> responseObserver) {
        try {
            CourtGrpc courtGrpc = courtService.getById(courtId.getId()).convertToCourtGrpc();

            responseObserver.onNext(courtGrpc);
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
