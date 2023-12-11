package sep3datalayer.repos;

import org.springframework.data.repository.CrudRepository;
import sep3datalayer.models.TimeSlotEntity;


import java.util.List;

public interface TimeSlotRepository extends CrudRepository<TimeSlotEntity, Integer> {
    List<TimeSlotEntity> findAllByCourtIdOrderByStartTime(int courtId);
}
