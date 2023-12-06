package sep3datalayer.services.interfaces;

import sep3datalayer.models.CenterEntity;

import java.util.List;

public interface CenterService {

    void addCenter(CenterEntity centerEntityRecord);
    CenterEntity getByName(String name);
    List<CenterEntity> getAllCenters();
    CenterEntity getById(int id);
    void deleteCenter(int id);
    String addCenterAdmin(int centerId, String username);
}
