package com.example.calendar_backend.services;

import com.example.calendar_backend.models.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private final EntityManager entityManager;

    public UserService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createUser(User user) {
        entityManager.persist(user);
    }

    public User getUserByUsername(String username) {
        try {
            return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public void updateUser(User user) {
        entityManager.merge(user);
    }

    public void deleteUserByUsername(String username) {
        User user = getUserByUsername(username);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    public boolean isUsernameUnique(String username) {
        Long count = entityManager.createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class)
                .setParameter("username", username)
                .getSingleResult();
        return count == 0;
    }

    public boolean isEmailUnique(String email) {
        Long count = entityManager.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();
        return count == 0;
    }
}