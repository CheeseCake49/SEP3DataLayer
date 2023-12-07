package sep3datalayer.models.Booking;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.*;
import org.hibernate.annotations.NaturalIdCache;
import sep3datalayer.grpc.protobuf.BookingGrpc;
import sep3datalayer.models.UserEntity;

import java.util.Objects;

@Entity(name = "Booking")
@Table(name = "booking", schema = "sep3herskab")
@NaturalIdCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BookingEntity {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne (fetch = FetchType.LAZY) @JoinColumn(name = "username", referencedColumnName = "username") @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity username;

   @Column(name = "total_price")
   private int totalPrice;


   public BookingEntity() {

   }

    public BookingEntity(UserEntity username, int totalPrice) {
         this.username = username;
         this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUsername() {
        return username;
    }

    public void setUsername(UserEntity username) {
        this.username = username;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "BookingEntity{" +
                "id=" + id +
                ", username=" + username +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingEntity that = (BookingEntity) o;
        return id == that.id && totalPrice == that.totalPrice && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, totalPrice);
    }

    public BookingGrpc convertToBookingGrpc() {
       BookingGrpc.Builder builder = BookingGrpc.newBuilder();
         builder.setId(this.id);
            builder.setUsername(this.username.getUsername());
            builder.setTotalPrice(this.totalPrice);
            return builder.build();
    }
}
