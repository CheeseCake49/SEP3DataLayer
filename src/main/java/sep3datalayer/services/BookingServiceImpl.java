package sep3datalayer.services;

import org.springframework.stereotype.Service;
import sep3datalayer.models.Booking.BookingEntity;
import sep3datalayer.repos.Booking.BookingRepository;
import sep3datalayer.repos.UserRepository;
import sep3datalayer.services.interfaces.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
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
