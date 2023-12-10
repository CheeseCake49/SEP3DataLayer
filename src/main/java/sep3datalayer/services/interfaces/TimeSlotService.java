package sep3datalayer.services.interfaces;

import org.springframework.stereotype.Service;
import sep3datalayer.models.TimeSlotEntity;

@Service
public interface TimeSlotService {
    TimeSlotEntity addTimeSlot(int courtId, int year, int month, int day, int hour, int minutes, int duration, boolean isBooked);
    void getById(int id);
}
