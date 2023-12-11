package sep3datalayer.services.interfaces;

import org.springframework.stereotype.Service;
import sep3datalayer.models.TimeSlotEntity;

import java.util.List;

@Service
public interface TimeSlotService {
    TimeSlotEntity addTimeSlot(int courtId, int year, int month, int day, int hour, int minutes, int duration, boolean isBooked);
    List<TimeSlotEntity> getByCourtId(int courtId);
}
