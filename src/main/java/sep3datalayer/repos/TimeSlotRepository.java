package sep3datalayer.repos;

import org.springframework.data.repository.CrudRepository;
import sep3datalayer.models.TimeSlotEntity;

import java.time.LocalTime;
import java.time.LocalDate;

public interface TimeSlotRepository extends CrudRepository<TimeSlotEntity, Integer> {
}
