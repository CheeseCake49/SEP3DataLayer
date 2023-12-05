package sep3datalayer.services.interfaces;

import sep3datalayer.grpc.protobuf.UpdatingCenter;
import sep3datalayer.models.CenterEntity;

import java.util.List;

public interface CenterService {

    void addCenter(CenterEntity centerEntityRecord);
    CenterEntity getByName(String name);
    List<CenterEntity> getAllCenters();
    CenterEntity getById(int id);
    void deleteCenter(int id);
    void updateCenter(UpdatingCenter center);
}
