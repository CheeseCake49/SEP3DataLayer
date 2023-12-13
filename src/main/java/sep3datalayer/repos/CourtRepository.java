package sep3datalayer.repos;

import org.springframework.data.repository.CrudRepository;
import sep3datalayer.models.CourtEntity;

import java.util.List;

public interface CourtRepository extends CrudRepository<CourtEntity, Integer> {

    CourtEntity findByCenterIdAndCourtNumber(int centerId, int courtNumber);
    List<CourtEntity> findAllByCenterIdOrderByCourtTypeDescCourtNumberAsc(int centerID);
    CourtEntity findById(int id);
}
