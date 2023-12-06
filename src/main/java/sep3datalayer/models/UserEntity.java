package sep3datalayer.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;
import sep3datalayer.grpc.protobuf.UserGrpc;

import java.util.*;

@Entity(name = "User")
@Table(name = "user", schema = "sep3herskab")
@NaturalIdCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserEntity {

    @Id
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "role")
    private String role;
    @ManyToMany(mappedBy = "admins")
    private List<CenterEntity> centers;


    public UserEntity() {

    }

    public UserEntity(String username, String password, String email, String name, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.role = role;
        centers = new ArrayList<>();
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
    public List<CenterEntity> getAdminnedCenters() {
        return centers;
    }

    @Override
    public String toString() {
        return "User [username = " + username + ", password = " + password +
                ", email = " + email + ", name = " + name + ", role = " + role + "]";
    }

    public UserGrpc convertToUserGrpc() {
        UserGrpc.Builder builder = UserGrpc.newBuilder();
        builder.setUsername(this.username);
        builder.setPassword(this.password);
        builder.setEmail(this.email);
        builder.setName(this.name);
        builder.setRole(this.role);
        return builder.build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return username.equals(that.username) && password.equals(that.password) && email.equals(that.email) && name.equals(that.name) && role.equals(that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, name, role);
    }

}
