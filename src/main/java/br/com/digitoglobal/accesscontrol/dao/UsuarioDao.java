package br.com.digitoglobal.accesscontrol.dao;

import br.com.digitoglobal.accesscontrol.model.Modulo;
import br.com.digitoglobal.accesscontrol.model.Usuario;
import br.com.digitoglobal.accesscontrol.util.MapFactory;
import me.dabpessoa.framework.dao.GenericAbstractDao;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
public class UsuarioDao extends GenericAbstractDao<Usuario, Long> {

    public Usuario findByLoginAndPassword(String login, String senhaCodificada, Modulo modulo) {
        String hql = "from Usuario u where u.login = :login and u.senha = :senha";
        if (modulo != null && modulo.getId() != null) hql += " and u.modulo = :modulo ";
        Query q = getEntityManager().createQuery(hql);
        q.setParameter("login", login);
        q.setParameter("senha", senhaCodificada);
        if (modulo != null && modulo.getId() != null) q.setParameter("modulo", modulo);
        try {
            return (Usuario) q.getSingleResult();
        } catch (NoResultException e) {
          return null;
        }
    }

    public boolean existsByLogin(String login) {
        if (login == null) return false;
        return getDaoHelper().querySQLSingleResult("select count(*) > 0 from controle_acesso.usuario u where u.login = :login", MapFactory.create("login", login));
    }

    public boolean existsByLogin(Usuario usuario) {
        if (usuario == null) return false;
        return existsByLogin(usuario.getId(), usuario.getLogin());
    }

    public boolean existsByLogin(Long usuarioId, String login) {
        if (usuarioId == null) return existsByLogin(login);
        return getDaoHelper().querySQLSingleResult(
                "select count(*) > 0 from controle_acesso.usuario u where u.id <> :usuarioId and u.login = :login",
                MapFactory.create("usuarioId", usuarioId, "login", login));
    }

}

