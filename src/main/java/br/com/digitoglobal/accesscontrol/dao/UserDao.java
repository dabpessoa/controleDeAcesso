package br.com.digitoglobal.accesscontrol.dao;

import br.com.digitoglobal.accesscontrol.model.Module;
import br.com.digitoglobal.accesscontrol.model.User;
import me.dabpessoa.framework.dao.GenericAbstractDao;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
public class UserDao extends GenericAbstractDao<User, Long> {

    public User findByLoginAndPassword(String login, String encryptedPassword, Module module) {
        String hql = "from User u where u.login = :login and u.password = :password";
        if (module != null && module.getId() != null) hql += " and u.module = :module ";
        Query q = getEntityManager().createQuery(hql);
        q.setParameter("login", login);
        q.setParameter("password", encryptedPassword);
        if (module != null && module.getId() != null) q.setParameter("module", module);
        try {
            return (User) q.getSingleResult();
        } catch (NoResultException e) {
          return null;
        }
    }

}

