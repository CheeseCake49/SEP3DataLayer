package sep3datalayer.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;
import sep3datalayer.grpc.protobuf.TimeSlotGrpc;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "Timeslot")
@Table(name = "time_slot", schema = "sep3herskab")
@NaturalIdCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TimeSlotEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne (fetch = FetchType.LAZY) @JoinColumn(name = "court_id", referencedColumnName = "id")
    private CourtEntity court;
    @Column(name = "start_time", columnDefinition = "TIMESTAMP(0) WITHOUT TIME ZONE")
    private LocalDateTime startTime;
    @Column(name = "duration")
    private int duration;

    @Column(name = "is_booked")
    private boolean isBooked;

    public TimeSlotEntity(){

    }

    public TimeSlotEntity(CourtEntity court, LocalDateTime startTime, int duration, boolean isBooked) {
        this.court = court;
        this.startTime = startTime;
        this.duration = duration;
        this.isBooked = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public CourtEntity getCourt() {
        return court;
    }

    public void setCourt(CourtEntity court) {
        this.court = court;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    @Override
    public String toString() {
        return "TimeSlotEntity{" +
                "id=" + id +
                ", court=" + court +
                ", startTime=" + startTime +
                ", duration=" + duration +
                ", isBooked=" + isBooked +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSlotEntity that = (TimeSlotEntity) o;
        return id == that.id && duration == that.duration && Objects.equals(court, that.court) && Objects.equals(startTime, that.startTime) && Objects.equals(isBooked, that.isBooked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, court, startTime, duration, isBooked);
    }

    public TimeSlotGrpc convertToTimeSlotGrpc() {
        TimeSlotGrpc.Builder timeSlot = TimeSlotGrpc.newBuilder();
        timeSlot.setId(this.id);
        timeSlot.setCourtId(this.court.getId());
        timeSlot.setYear(this.startTime.getYear());
        timeSlot.setMonth(this.startTime.getMonthValue());
        timeSlot.setDay(this.startTime.getDayOfMonth());
        timeSlot.setStartHour(this.startTime.getHour());
        timeSlot.setStartMinute(this.startTime.getMinute());
        timeSlot.setDuration(this.duration);
        timeSlot.setIsBooked(this.isBooked);
        return timeSlot.build();
    }
}
