package home.caio.users.service;

import home.caio.users.entity.User;
import home.caio.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public void salvarObj(User user){
        userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public Optional<User> update(User user, Long id){
        Optional<User> findUser = userRepository.findById(id);
        if(findUser.isPresent()){
            findUser.get().setNome(user.getNome());
            findUser.get().setEmail(user.getEmail());
            userRepository.save(findUser.get());
        }
        return findUser;
    }

}
