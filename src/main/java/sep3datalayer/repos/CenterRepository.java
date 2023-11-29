package sep3datalayer.repos;

import org.springframework.data.repository.CrudRepository;
import sep3datalayer.models.CenterEntity;

import java.util.List;

public interface CenterRepository extends CrudRepository<CenterEntity, Integer> {
    CenterEntity findByName(String name);
    List<CenterEntity> findAll();
}
