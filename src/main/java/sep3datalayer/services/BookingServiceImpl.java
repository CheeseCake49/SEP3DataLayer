package sep3datalayer.services;

import sep3datalayer.models.Booking.BookingEntity;
import sep3datalayer.repos.Booking.BookingRepository;
import sep3datalayer.repos.UserRepository;
import sep3datalayer.services.interfaces.BookingService;

public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BookingEntity createBooking(BookingEntity bookingEntityRecord) {
        return bookingRepository.save(bookingEntityRecord);
    }

    @Override
    public BookingEntity getById(int id) {
        return bookingRepository.findById(id).orElse(null);
    }
}
