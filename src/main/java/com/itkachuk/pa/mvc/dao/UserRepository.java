package com.itkachuk.pa.mvc.dao;

import com.itkachuk.pa.mvc.model.UserEntity;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 * User: itkachuk
 * Date: 12/22/2015 12:31 PM
 */
@Repository
public class UserRepository {

    @PersistenceContext(name = "PAPersistenceUnit")
    private EntityManager entityManager;

    public UserEntity getUserByName(String userName) {
        return (UserEntity) entityManager.createQuery("from UserEntity U where U.username = '" + userName + "'").getSingleResult();
    }
}
