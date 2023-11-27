package sep3datalayer.services;

import org.springframework.stereotype.Service;
import sep3datalayer.models.CourtEntity;
import sep3datalayer.repos.CourtRepository;
import sep3datalayer.services.interfaces.CourtService;

import java.util.List;

@Service
public class CourtServiceImpl implements CourtService {

    private final CourtRepository courtRepository;

    public CourtServiceImpl(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    @Override
    public void addCourt(CourtEntity courtEntityRecord) {
        if (courtRepository.findByCenterIdAndCourtNumber(courtEntityRecord.getCenterId(), courtEntityRecord.getCourtNumber()) != null) {
            throw new IllegalArgumentException("Court with that number already exists!");
        }
        courtRepository.save(courtEntityRecord);
    }

    @Override
    public CourtEntity getByCenterIdAndCourtNumber(int centerId, int courtNumber) {
        return courtRepository.findByCenterIdAndCourtNumber(centerId, courtNumber);
    }

    @Override
    public List<CourtEntity> getByCenterID(int centerID) {
        return courtRepository.findAllByCenterId(centerID);
    }
}
