package sep3datalayer.services;

import org.springframework.stereotype.Service;
import sep3datalayer.models.UserEntity;
import sep3datalayer.repos.UserRepository;
import sep3datalayer.services.interfaces.UserService;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    @Override
    public ArrayList<UserEntity> getAllUsers() {
        ArrayList<UserEntity> users = new ArrayList<>();
        users.addAll(userRepository.findAll());
        return users;
    }
}
