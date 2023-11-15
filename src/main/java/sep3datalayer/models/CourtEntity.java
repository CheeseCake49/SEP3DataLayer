package sep3datalayer.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;
import sep3datalayer.grpc.protobuf.CourtGrpc;

import java.util.Objects;

@Entity(name = "Court")
@Table(name = "court", schema = "sep3herskab")
@NaturalIdCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CourtEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "centerId")
    private int centerId;
    @Column(name = "type")
    private String courtType;
    @Column(name = "number")
    private int courtNumber;
    @Column(name = "sponsor")
    private String courtSponsor;

    public CourtEntity(){

    }

    public CourtEntity(int centerId, String courtType, int courtNumber, String courtSponsor){
        this.centerId = centerId;
        this.courtType = courtType;
        this.courtNumber = courtNumber;
        this.courtSponsor = courtSponsor;
    }

    public int getId() {
        return id;
    }

    public int getCenterId() {
        return centerId;
    }

    public String getCourtType() {
        return courtType;
    }

    public int getCourtNumber() {
        return courtNumber;
    }

    public String getCourtSponsor() {
        return courtSponsor;
    }

    @Override
    public String toString(){
        return "Court []";
    }

    public CourtGrpc convertToCourtGrpc() {
        CourtGrpc.Builder builder = CourtGrpc.newBuilder();
        builder.setId(id);
        builder.setCenterId(centerId);
        builder.setCourtType(courtType);
        builder.setCourtNumber(courtNumber);
        builder.setCourtSponsor(courtSponsor);
        return builder.build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourtEntity that = (CourtEntity) o;
        return id == that.id && centerId == that.centerId && courtType.equals(that.courtType) && courtNumber == that.courtNumber && courtSponsor.equals(that.courtSponsor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, centerId, courtType, courtNumber, courtSponsor);
    }
}
