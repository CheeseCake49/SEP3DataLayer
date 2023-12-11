package sep3datalayer.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;
import sep3datalayer.grpc.protobuf.CenterAdmin;
import sep3datalayer.grpc.protobuf.CenterGrpc;

import java.util.*;

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
    @Column(name = "zip_Code")
    private int zipCode;
    @Column(name = "city")
    private String city;
    @Column(name = "address")
    private String address;
    @ManyToMany
    @JoinTable(
        schema = "sep3herskab",
        name = "Center_Admins",
        inverseJoinColumns = @JoinColumn(
            name = "admin_username",
            referencedColumnName = "username"
        ),
        joinColumns = @JoinColumn(
            name = "center_id",
            referencedColumnName = "id"
        )
    )
    private List<UserEntity> admins;

    public CenterEntity() {

    }

    public CenterEntity(String name, int zipCode, String city, String address) {
        this.name = name;
        this.zipCode = zipCode;
        this.city = city;
        this.address = address;
        admins = new ArrayList<>();
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

    public void setName(String name) {
        this.name = name;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<UserEntity> getAdmins() {
        return admins;
    }

    public void addCenterAdmin(UserEntity user) {
        admins.add(user);
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

    public CenterAdmin convertToCenterAdmin(String username) {
        CenterAdmin.Builder builder = CenterAdmin.newBuilder();
        builder.setCenterId(id);
        builder.setUsername(username);
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
