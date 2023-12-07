package sep3datalayer.services;

import org.springframework.stereotype.Service;
import sep3datalayer.models.TimeSlotEntity;
import sep3datalayer.repos.CourtRepository;
import sep3datalayer.repos.TimeSlotRepository;
import sep3datalayer.services.interfaces.TimeSlotService;

import java.time.LocalDateTime;

@Service
public class TimeSlotServiceImpl implements TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;
    private final CourtRepository courtRepository;

    public TimeSlotServiceImpl(TimeSlotRepository timeSlotRepository, CourtRepository courtRepository) {
        this.timeSlotRepository = timeSlotRepository;
        this.courtRepository = courtRepository;
    }

    @Override
    public TimeSlotEntity addTimeSlot(int courtId, int year, int month, int day, int hour, int minutes, int duration) {
        return timeSlotRepository.save(new TimeSlotEntity(courtRepository.findById(courtId), convertToDate(year, month, day, hour, minutes), duration));
    }

    @Override
    public void getById(int id) {
        timeSlotRepository.findById(id);
    }

    public LocalDateTime convertToDate(int year, int month, int day, int hour, int minutes){
        return LocalDateTime.of(year, month, day, hour, minutes);
    }
}
