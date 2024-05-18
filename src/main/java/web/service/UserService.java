package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import web.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    public List<User> listUsers() {
        return usersRepository.findAll();
    }


    public User readUser(int id) {
        Optional<User> foundUser = usersRepository.findById(id);
        return foundUser.orElse(null);
    }

    @Transactional
    public void createUser(User user) {
        usersRepository.save(user);
    }

    @Transactional
    public void updateUser(User updatedUser) {
        usersRepository.save(updatedUser);
    }

    @Transactional
    public void deleteUser(int id) {
        usersRepository.deleteById(id);
    }

}
