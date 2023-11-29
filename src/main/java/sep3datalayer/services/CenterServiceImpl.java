package sep3datalayer.services;

import org.springframework.stereotype.Service;
import sep3datalayer.models.CenterEntity;
import sep3datalayer.repos.CenterRepository;
import sep3datalayer.services.interfaces.CenterService;

import java.util.ArrayList;

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
    public ArrayList<CenterEntity> getAllCenters() {
        ArrayList<CenterEntity> centers = new ArrayList<>();
        centers.addAll(centerRepository.findAll());
        return centers;
    }

    @Override
    public CenterEntity getById(int id) {
        return centerRepository.findById(id).orElseThrow();
    }


}
