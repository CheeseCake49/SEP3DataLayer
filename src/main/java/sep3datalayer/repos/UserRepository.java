package sep3datalayer.repos;

import org.springframework.data.repository.CrudRepository;
import sep3datalayer.models.UserEntity;
import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    UserEntity findByUsername(String username);
    List<UserEntity> findAll();

}
