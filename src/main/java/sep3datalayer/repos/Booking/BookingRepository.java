package sep3datalayer.repos.Booking;

import org.springframework.data.repository.CrudRepository;
import sep3datalayer.models.Booking.BookingEntity;

public interface BookingRepository extends CrudRepository<BookingEntity, Integer> {

}
