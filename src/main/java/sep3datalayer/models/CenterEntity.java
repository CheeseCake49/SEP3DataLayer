package sep3datalayer.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;
import sep3datalayer.grpc.protobuf.CenterGrpc;

import java.util.Objects;


@Entity(name = "Center")
@Table(name = "center", schema = "sep3herskab")
@NaturalIdCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class CenterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "location")
    private String location;


    public CenterEntity() {

    }

    public CenterEntity(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Center [id=" + id + ", name=" + name + ", location=" + location + "]";
    }

    public CenterGrpc convertToCenterGrpc() {
        CenterGrpc.Builder builder = CenterGrpc.newBuilder();
        builder.setId(this.id);
        builder.setName(this.name);
        builder.setLocation(this.location);
        return builder.build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CenterEntity that = (CenterEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location);
    }
}
