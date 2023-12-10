package sep3datalayer.grpc;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import sep3datalayer.grpc.protobuf.CreatingTimeSlot;
import sep3datalayer.grpc.protobuf.TimeSlotGrpc;
import sep3datalayer.grpc.protobuf.TimeSlotServiceGrpc;
import sep3datalayer.models.TimeSlotEntity;
import sep3datalayer.services.CourtServiceImpl;
import sep3datalayer.services.TimeSlotServiceImpl;

public class TimeSlotImpl extends TimeSlotServiceGrpc.TimeSlotServiceImplBase {

    private final TimeSlotServiceImpl timeSlotService;
    private final CourtServiceImpl courtService;

    public TimeSlotImpl(TimeSlotServiceImpl timeSlotService, CourtServiceImpl courtService) {
        this.timeSlotService = timeSlotService;
        this.courtService = courtService;
    }

    @Override
    public void createTimeSlot(CreatingTimeSlot timeSlot, StreamObserver<TimeSlotGrpc> responseObserver) {
        try{
        TimeSlotEntity timeSlotCreated = timeSlotService.addTimeSlot(timeSlot.getCourtId(), timeSlot.getYear(),
                timeSlot.getMonth(), timeSlot.getDay(), timeSlot.getStartHour(), timeSlot.getStartMinute(), timeSlot.getDuration(), timeSlot.getIsBooked());
        TimeSlotGrpc timeSlot1 = timeSlotCreated.convertToTimeSlotGrpc();


        responseObserver.onNext(timeSlot1);
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
