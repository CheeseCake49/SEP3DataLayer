package sep3datalayer.services;

import org.springframework.stereotype.Service;
import sep3datalayer.models.CenterEntity;
import sep3datalayer.models.UserEntity;
import sep3datalayer.repos.CenterRepository;
import sep3datalayer.repos.UserRepository;
import sep3datalayer.services.interfaces.CenterService;
import java.util.ArrayList;

@Service public class CenterServiceImpl implements CenterService {

    private final CenterRepository centerRepository;
    private final UserRepository userRepository;

    public CenterServiceImpl(CenterRepository centerRepository, UserRepository userRepository) {
        this.centerRepository = centerRepository;
        this.userRepository = userRepository;
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

    @Override
    public void deleteCenter(int id) {
        if (getById(id) == null) {
            throw new IllegalArgumentException("Center not found!");
        }
        centerRepository.deleteById(id);
    }

    @Override
    public String addCenterAdmin(int centerId, String username) {
        CenterEntity center = centerRepository.findById(centerId).orElseThrow();
        UserEntity user = userRepository.findByUsername(username);
        center.addCenterAdmin(user);
        centerRepository.save(center);
        return user.getUsername();
    }
}
