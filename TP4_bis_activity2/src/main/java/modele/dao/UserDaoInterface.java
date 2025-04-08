package modele.dao;

import modele.domaine.User;
import java.util.List;

public interface UserDaoInterface {
    List<User> select(); 
    User selectById(int id);
    void insert(User user); 
    void update(User user); 
    void delete(int id);
}
