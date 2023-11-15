package sep3datalayer.services.interfaces;

import sep3datalayer.grpc.protobuf.CenterGrpc;
import sep3datalayer.models.CenterEntity;

import java.util.List;

public interface CenterService {

    void addCenter(CenterEntity centerEntityRecord);
    CenterEntity getByName(String name);

    List<CenterGrpc> getAllCenters();
}
