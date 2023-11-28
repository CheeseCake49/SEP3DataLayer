package sep3datalayer.services;

import org.springframework.stereotype.Service;
import sep3datalayer.models.CourtEntity;
import sep3datalayer.repos.CourtRepository;
import sep3datalayer.services.interfaces.CourtService;

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
    public void deleteCourt(int centerId, int courtNumber) {
        if (getByCenterIdAndCourtNumber(centerId, courtNumber) == null) {
            throw new IllegalArgumentException("Court not found!");
        }
        courtRepository.deleteCourtEntityByCenterIdAndAndCourtNumber(centerId, courtNumber);
    }
}
