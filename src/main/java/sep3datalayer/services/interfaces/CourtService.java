package sep3datalayer.services.interfaces;

import sep3datalayer.models.CourtEntity;

public interface CourtService {

    void addCourt(CourtEntity courtEntityRecord);
    CourtEntity getByCenterIdAndCourtNumber(int centerId, int courtNumber);

}
