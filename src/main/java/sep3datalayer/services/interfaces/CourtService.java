package sep3datalayer.services.interfaces;

import sep3datalayer.grpc.protobuf.CourtGrpc;
import sep3datalayer.grpc.protobuf.CreatingCourt;
import sep3datalayer.grpc.protobuf.UpdatingCourt;
import sep3datalayer.models.CourtEntity;

import java.util.List;

public interface CourtService {

    void addCourt(CourtEntity courtEntityRecord);
    CourtEntity getByCenterIdAndCourtNumber(int centerId, int courtNumber);
    void deleteCourt(int centerId, int courtNumber);
    List<CourtEntity> getByCenterID(int centerID);
    CourtEntity updateCourt(UpdatingCourt court);
    CourtEntity getById(int id);

    CourtEntity getById(int id);

}
