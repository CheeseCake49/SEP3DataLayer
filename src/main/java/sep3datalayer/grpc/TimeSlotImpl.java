package sep3datalayer.grpc;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import sep3datalayer.grpc.protobuf.*;
import sep3datalayer.models.TimeSlotEntity;
import sep3datalayer.services.TimeSlotServiceImpl;

@GRpcService
public class TimeSlotImpl extends TimeSlotServiceGrpc.TimeSlotServiceImplBase {

    private final TimeSlotServiceImpl timeSlotService;

    public TimeSlotImpl(TimeSlotServiceImpl timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @Override
    public void createTimeSlot(CreatingTimeSlot timeSlot, StreamObserver<TimeSlotGrpc> responseObserver) {
        try{
        TimeSlotEntity timeSlotCreated = timeSlotService.addTimeSlot(timeSlot.getCourtId(), timeSlot.getYear(),
                timeSlot.getMonth(), timeSlot.getDay(), timeSlot.getStartHour(), timeSlot.getStartMinute(), timeSlot.getDuration(), timeSlot.getIsBooked(), timeSlot.getPrice());
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

    @Override
    public void getTimeSlotsFromCourtId(CourtId courtId, StreamObserver<TimeSlotList> responseObserver) {
        try {
            TimeSlotList.Builder timeSlotList = TimeSlotList.newBuilder();
            for (TimeSlotEntity timeSlot : timeSlotService.getByCourtId(courtId.getId())) {
                timeSlotList.addTimeSlots(timeSlot.convertToTimeSlotGrpc());
            }
            responseObserver.onNext(timeSlotList.build());
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
