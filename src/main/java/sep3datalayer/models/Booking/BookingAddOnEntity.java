package sep3datalayer.models.Booking;

import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;
import sep3datalayer.grpc.protobuf.BookingAddOnGrpc;

import java.util.Objects;

@Entity(name = "BookingAddOn")
@Table(name = "booking_addon", schema = "sep3herskab")
@NaturalIdCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BookingAddOnEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @ManyToOne (fetch = FetchType.LAZY) @JoinColumn(name = "booking_id", referencedColumnName = "id")
    private BookingEntity booking;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "quantity")
    private int quantity;

    public BookingAddOnEntity() {
    }

    public BookingAddOnEntity(BookingEntity booking, String name, int price, int quantity) {
        this.booking = booking;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public BookingEntity getBooking() {
        return booking;
    }

    public void setBooking(BookingEntity booking) {
        this.booking = booking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "BookingAddOn{" +
                "Id=" + Id +
                ", booking=" + booking +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingAddOnEntity that = (BookingAddOnEntity) o;
        return Id == that.Id && price == that.price && quantity == that.quantity && Objects.equals(booking, that.booking) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, booking, name, price, quantity);
    }

    public BookingAddOnGrpc convertToBookingAddOnGrpc() {
        BookingAddOnGrpc.Builder builder = BookingAddOnGrpc.newBuilder();
        builder.setId(this.Id);
        builder.setBookingId(this.booking.getId());
        builder.setName(this.name);
        builder.setPrice(this.price);
        builder.setQuantity(this.quantity);
        return builder.build();
    }
}
