package sep3datalayer.grpc;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import sep3datalayer.grpc.protobuf.BookingGrpc;
import sep3datalayer.grpc.protobuf.BookingServiceGrpc;
import sep3datalayer.grpc.protobuf.CreatingBooking;
import sep3datalayer.models.Booking.BookingEntity;
import sep3datalayer.models.TimeSlotEntity;
import sep3datalayer.repos.TimeSlotRepository;
import sep3datalayer.services.interfaces.BookingService;
import sep3datalayer.services.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;

@GRpcService
public class BookingImpl extends BookingServiceGrpc.BookingServiceImplBase {

    private final BookingService bookingService;
    private final UserService userService;
    private final TimeSlotRepository timeSlotRepository;


    public BookingImpl(BookingService bookingService, UserService userService, TimeSlotRepository timeSlotRepository) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.timeSlotRepository = timeSlotRepository;
    }

    @Override
    public void createBooking(CreatingBooking booking, StreamObserver<BookingGrpc> responseObserver) {
        try {
            List<TimeSlotEntity> convertedList = new ArrayList<>();

            for (int i = 0; i < booking.getTimeSlotList().getTimeSlotsCount(); i++) {
                convertedList.add(timeSlotRepository.findById(booking.getTimeSlotList().getTimeSlots(i).getId()).orElseThrow());
            }

            BookingEntity bookingCreated = bookingService.createBooking(new BookingEntity(userService.getByUsername(booking.getUsername()), booking.getTotalPrice(), convertedList));
            for (int i = 0; i < booking.getTimeSlotList().getTimeSlotsCount(); i++) {
                TimeSlotEntity timeSlot = timeSlotRepository.findById(booking.getTimeSlotList().getTimeSlots(i).getId()).orElseThrow();
                timeSlot.setBooked(true);
                timeSlotRepository.save(timeSlot);
            }
            BookingGrpc booking1 = bookingCreated.convertToBookingGrpc();

            responseObserver.onNext(booking1);
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
