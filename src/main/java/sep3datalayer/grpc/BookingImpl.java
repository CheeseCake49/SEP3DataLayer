package sep3datalayer.grpc;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import sep3datalayer.grpc.protobuf.BookingGrpc;
import sep3datalayer.grpc.protobuf.BookingServiceGrpc;
import sep3datalayer.grpc.protobuf.CreatingBooking;
import sep3datalayer.models.Booking.BookingEntity;
import sep3datalayer.services.BookingServiceImpl;
import sep3datalayer.services.UserServiceImpl;

public class BookingImpl extends BookingServiceGrpc.BookingServiceImplBase {

    private final BookingServiceImpl bookingService;
    private final UserServiceImpl userService;

    public BookingImpl(BookingServiceImpl bookingService, UserServiceImpl userService) {
        this.bookingService = bookingService;
        this.userService = userService;
    }

    @Override
    public void createBooking(CreatingBooking booking, StreamObserver<BookingGrpc> responseObserver) {
        try {
            BookingEntity bookingCreated = bookingService.createBooking(new BookingEntity(userService.getByUsername(booking.getUsername()), booking.getTotalPrice()));
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
