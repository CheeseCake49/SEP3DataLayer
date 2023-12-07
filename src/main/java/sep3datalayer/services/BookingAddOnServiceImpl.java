package sep3datalayer.services;

import sep3datalayer.models.Booking.BookingAddOnEntity;
import sep3datalayer.repos.Booking.BookingAddOnRepository;
import sep3datalayer.repos.Booking.BookingRepository;
import sep3datalayer.services.interfaces.BookingAddOnService;

public class BookingAddOnServiceImpl implements BookingAddOnService {

    private final BookingAddOnRepository bookingAddOnRepository;
    private final BookingRepository bookingRepository;

    public BookingAddOnServiceImpl(BookingAddOnRepository bookingAddOnRepository, BookingRepository bookingRepository) {
        this.bookingAddOnRepository = bookingAddOnRepository;
        this.bookingRepository = bookingRepository;
    }


    @Override
    public BookingAddOnEntity addBookingAddOn(BookingAddOnEntity bookingAddOnRecord) {
        return bookingAddOnRepository.save(bookingAddOnRecord);
    }
}
