package sep3datalayer.grpc;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import sep3datalayer.grpc.protobuf.BookingAddOnGrpc;
import sep3datalayer.grpc.protobuf.BookingAddOnServiceGrpc;
import sep3datalayer.grpc.protobuf.CreatingBookingAddOn;
import sep3datalayer.models.Booking.BookingAddOnEntity;
import sep3datalayer.services.BookingAddOnServiceImpl;
import sep3datalayer.services.BookingServiceImpl;

public class BookingAddOnImpl extends BookingAddOnServiceGrpc.BookingAddOnServiceImplBase{

    private final BookingAddOnServiceImpl bookingAddOnService;
    private final BookingServiceImpl bookingService;

    public BookingAddOnImpl(BookingAddOnServiceImpl bookingAddOnService, BookingServiceImpl bookingService) {
        this.bookingAddOnService = bookingAddOnService;
        this.bookingService = bookingService;
    }

    @Override
    public void createBookingAddOn(CreatingBookingAddOn bookingAddOn, StreamObserver<BookingAddOnGrpc> responseObserver) {
        try {
            BookingAddOnEntity bookingAddOnCreated = bookingAddOnService.addBookingAddOn(new BookingAddOnEntity(bookingService.getById(bookingAddOn.getBookingId()),
                    bookingAddOn.getName(), bookingAddOn.getPrice(), bookingAddOn.getQuantity()));
            BookingAddOnGrpc bookingAddOn1 = bookingAddOnCreated.convertToBookingAddOnGrpc();

            responseObserver.onNext(bookingAddOn1);
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