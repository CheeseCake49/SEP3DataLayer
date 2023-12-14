package sep3datalayer.models;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.hibernate.annotations.*;
import sep3datalayer.grpc.protobuf.TimeSlotGrpc;
import sep3datalayer.models.Booking.BookingEntity;

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
    @ManyToOne (fetch = FetchType.LAZY) @JoinColumn(name = "court_id", referencedColumnName = "id") @OnDelete(action = OnDeleteAction.CASCADE)
    private CourtEntity court;
    @Column(name = "start_time", columnDefinition = "TIMESTAMP(0) WITHOUT TIME ZONE")
    private LocalDateTime startTime;
    @Column(name = "duration")
    private int duration;

    @Column(name = "is_booked")
    private boolean isBooked;

    @Column(name = "price")
    private int price;

    @ManyToOne @JoinColumn(name = "booking_id", referencedColumnName = "id", insertable = false, updatable = false)
    private BookingEntity booking;

    public TimeSlotEntity(){

    }

    public TimeSlotEntity(CourtEntity court, LocalDateTime startTime, int duration, boolean isBooked, int price) {
        this.court = court;
        this.startTime = startTime;
        this.duration = duration;
        this.isBooked = false;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
        timeSlot.setPrice(this.price);
        return timeSlot.build();
    }
}
