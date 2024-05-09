package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<User> listUsers() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);

        return query.getResultList();
    }

    public void createUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    public User readUser(int id) {
        return entityManager.find(User.class, id);
    }

    public void updateUser(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    public User deleteUser(int id) throws NullPointerException {
        User user = readUser(id);
        if (null == user) {
            throw new NullPointerException("User doesn't found");
        }
        entityManager.remove(user);
        entityManager.flush();
        return user;
    }
}
