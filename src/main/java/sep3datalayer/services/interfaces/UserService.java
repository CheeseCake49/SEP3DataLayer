package sep3datalayer.services.interfaces;

import sep3datalayer.models.UserEntity;

import java.util.List;

public interface UserService {

    void addUser(UserEntity userEntityRecord);
    List<UserEntity> getAllUsers();
    UserEntity getByUsername(String username);
    List<UserEntity> getCenterAdmins(int centerId);
}
