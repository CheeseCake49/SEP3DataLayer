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
    @Column(name = "zipCode")
    private int zipCode;
    @Column(name = "city")
    private String city;
    @Column (name = "address")
    private String address;

    public CenterEntity() {

    }

    public CenterEntity(String name, int zipCode, String city, String address) {
        this.name = name;
        this.zipCode = zipCode;
        this.city = city;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Center [id = " + id + ", name = " + name + ", zip code = " + zipCode + ", city = " + city + ", address = " + address + "]";
    }

    public CenterGrpc convertToCenterGrpc() {
        CenterGrpc.Builder builder = CenterGrpc.newBuilder();
        builder.setId(this.id);
        builder.setName(this.name);
        builder.setZipCode(this.zipCode);
        builder.setCity(this.city);
        builder.setAddress(this.address);
        return builder.build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CenterEntity that = (CenterEntity) o;
        return id == that.id && Objects.equals(name, that.name) && zipCode == that.zipCode && city.equals(that.city) && address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, zipCode, city, address);
    }
}
