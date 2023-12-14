package sep3datalayer.services;

import org.springframework.stereotype.Service;
import sep3datalayer.models.CourtEntity;
import sep3datalayer.models.TimeSlotEntity;
import sep3datalayer.repos.CourtRepository;
import sep3datalayer.repos.TimeSlotRepository;
import sep3datalayer.services.interfaces.TimeSlotService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TimeSlotServiceImpl implements TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;
    private final CourtRepository courtRepository;

    public TimeSlotServiceImpl(TimeSlotRepository timeSlotRepository, CourtRepository courtRepository) {
        this.timeSlotRepository = timeSlotRepository;
        this.courtRepository = courtRepository;
    }

    @Override
    public TimeSlotEntity addTimeSlot(int courtId, int year, int month, int day, int hour, int minutes, int duration, boolean isBooked, int price) {
        CourtEntity court = courtRepository.findById(courtId);
        TimeSlotEntity timeSlot = timeSlotRepository.save(new TimeSlotEntity(court, convertToDate(year, month, day, hour, minutes), duration, isBooked, price));
        court.addTimeSlot(timeSlot);
        courtRepository.save(court);
        return timeSlot;
    }

    @Override
    public List<TimeSlotEntity> getByCourtId(int courtId) {
        return timeSlotRepository.findAllByCourtIdOrderByStartTime(courtId);
    }

    public LocalDateTime convertToDate(int year, int month, int day, int hour, int minutes){
        return LocalDateTime.of(year, month, day, hour, minutes);
    }

    @Override
    public TimeSlotEntity getById(int id) {
        return timeSlotRepository.findById(id).orElseThrow();
    }
}
