package sep3datalayer.services.interfaces;

import sep3datalayer.models.CenterEntity;

public interface CenterService {

    void addCenter(CenterEntity centerEntityRecord);
    CenterEntity getByName(String name);
}
