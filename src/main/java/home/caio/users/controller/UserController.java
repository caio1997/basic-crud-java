package home.caio.users.controller;

import home.caio.users.entity.User;
import home.caio.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping
    @RequestMapping("/add")
    public ResponseEntity<String>salvar(@RequestBody User user){

        try {
            userService.salvarObj(user);
            return ResponseEntity.ok().body("OK, salvou!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        try {
            return ResponseEntity.ok().body(userService.findAll());
        } catch (Exception e){
            ArrayList<User> listEmpty = new ArrayList();
            return ResponseEntity.badRequest().body(listEmpty);
        }
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable Long id){
        try {
            return ResponseEntity.ok().body(userService.findById(id));
        } catch (Exception e){
            Optional<User> userEmpty = Optional.of(new User());
            return ResponseEntity.badRequest().body(userEmpty);
        }
    }

    @DeleteMapping
    @RequestMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        try {
            userService.delete(id);
            return ResponseEntity.ok().body("Excluído com sucesso");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Não foi possível excluir o registro");
        }
    }

    @PutMapping
    @RequestMapping("/update/{id}")
    public ResponseEntity<Optional<User>> update(@PathVariable Long id, @RequestBody User user){
        try {
            Optional<User> userSaved = userService.update(user, id);
            return ResponseEntity.ok().body(userSaved);
        } catch (Exception e){
            Optional<User> userEmpty = Optional.of(new User());
            return ResponseEntity.badRequest().body(userEmpty);
        }
    }

}
