package sep3datalayer.repos;

import org.springframework.data.repository.CrudRepository;
import sep3datalayer.models.CenterEntity;

public interface CenterRepository extends CrudRepository<CenterEntity, Integer> {
    CenterEntity findByName(String name);
}
