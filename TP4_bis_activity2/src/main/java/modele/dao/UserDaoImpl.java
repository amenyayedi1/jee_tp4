package modele.dao;

import modele.domaine.User;
import java.util.List;
import java.util.ArrayList;

public class UserDaoImpl implements UserDaoInterface {
    
    private List<User> users = new ArrayList<>();
    
    @Override
    public List<User> select() {
        
        return users;
    }

    @Override
    public User selectById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void insert(User user) {
        users.add(user);
    }

    @Override
    public void update(User user) {
     
    }

    @Override
    public void delete(int id) {
        users.removeIf(user -> user.getId() == id);
    }
}
