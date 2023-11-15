package sep3datalayer.services;

import org.springframework.stereotype.Service;
import sep3datalayer.grpc.protobuf.CenterGrpc;
import sep3datalayer.models.CenterEntity;
import sep3datalayer.repos.CenterRepository;
import sep3datalayer.services.interfaces.CenterService;

import java.util.ArrayList;
import java.util.List;

@Service public class CenterServiceImpl implements CenterService {

    private final CenterRepository centerRepository;

    public CenterServiceImpl(CenterRepository centerRepository) {
        this.centerRepository = centerRepository;
    }


    @Override
    public void addCenter(CenterEntity centerEntityRecord) {
        if (centerRepository.findByName(centerEntityRecord.getName()) != null) {
            throw new IllegalArgumentException("Name is already in use!");
        }

        centerRepository.save(centerEntityRecord);
    }

    @Override
    public CenterEntity getByName(String name) {
        return centerRepository.findByName(name);
    }

    @Override
    public List<CenterGrpc> getAllCenters() {
        List<CenterGrpc> centerGrpcList = new ArrayList<>();
        for (CenterEntity centerEntity : centerRepository.findAll()) {
            centerGrpcList.add(centerEntity.convertToCenterGrpc());
        }
        return centerGrpcList;
    }
}
