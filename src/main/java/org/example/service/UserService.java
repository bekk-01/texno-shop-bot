package org.example.service;

import org.example.exception.DataNotFoundException;
import org.example.model.User;
import org.example.repository.UserRepository;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class UserService extends BaseService<User,UserRepository> {
    private final static UserService userService = new UserService();
    private UserService() {
        super(new UserRepository());
    }
    public static UserService getInstance() {
        return userService;
    }
    public User findByChatId(Long chatId){
        Optional<User> byChatId = repository.findByChatId(chatId);
        return byChatId.orElseThrow(() -> {
            return new DataNotFoundException("user with this chat {} is not found");
        });

    }
    public void update(User update){
        ArrayList<User> all = repository.getAll();
        int i = 0;
        for (User user : all) {
            if(Objects.equals(user.getId(),update.getId())){
                all.set(i,update);
                break;
            }
            i++;
        }
        repository.writeFile(all);
    }
}
