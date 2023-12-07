package sep3datalayer.models;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.hibernate.annotations.*;
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
    @ManyToOne (fetch = FetchType.LAZY) @JoinColumn(name = "center_id", referencedColumnName = "id") @OnDelete(action = OnDeleteAction.CASCADE)
    private CenterEntity center;
    @Column(name = "type")
    private String courtType;
    @Column(name = "number")
    private int courtNumber;
    @Column(name = "sponsor")
    private String courtSponsor;

    public CourtEntity(){

    }

    public CourtEntity(CenterEntity center, String courtType, int courtNumber, String courtSponsor){
        this.center = center;
        this.courtType = courtType;
        this.courtNumber = courtNumber;
        this.courtSponsor = courtSponsor;
    }

    public int getId() {
        return id;
    }

    public CenterEntity getCenter() {
        return center;
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
        builder.setCenterId(center.getId());
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
        return id == that.id && center.equals(that.center) && courtType.equals(that.courtType) && courtNumber == that.courtNumber && courtSponsor.equals(that.courtSponsor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, center, courtType, courtNumber, courtSponsor);
    }
}
