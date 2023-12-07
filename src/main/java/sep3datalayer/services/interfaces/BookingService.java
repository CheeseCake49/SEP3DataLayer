package sep3datalayer.services.interfaces;

import sep3datalayer.models.Booking.BookingEntity;

public interface BookingService {

    BookingEntity createBooking(BookingEntity bookingEntityRecord);
    BookingEntity getById(int id);
}
