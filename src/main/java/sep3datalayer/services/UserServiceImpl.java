package sep3datalayer.services;

import org.springframework.stereotype.Service;
import sep3datalayer.models.UserEntity;
import sep3datalayer.repos.CenterRepository;
import sep3datalayer.repos.UserRepository;
import sep3datalayer.services.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CenterRepository centerRepository;

    public UserServiceImpl(UserRepository userRepository, CenterRepository centerRepository) {
        this.userRepository = userRepository;
        this.centerRepository = centerRepository;
    }

    @Override
    public void addUser(UserEntity userEntityRecord) {
        if (userRepository.findByUsername(userEntityRecord.getUsername()) != null) {
            throw new IllegalArgumentException("Username is already in use!");
        }

        userRepository.save(userEntityRecord);
    }

    @Override
    public UserEntity getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override public List<UserEntity> getCenterAdmins(int centerId)
    {
        return userRepository.findAllByCentersContains(centerRepository.findById(centerId).orElseThrow());
    }

    @Override
    public ArrayList<UserEntity> getAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }
}
