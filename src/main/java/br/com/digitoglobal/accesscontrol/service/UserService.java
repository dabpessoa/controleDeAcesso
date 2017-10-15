package br.com.digitoglobal.accesscontrol.service;

import br.com.digitoglobal.accesscontrol.dao.UserDao;
import br.com.digitoglobal.accesscontrol.model.Module;
import br.com.digitoglobal.accesscontrol.model.User;
import br.com.digitoglobal.accesscontrol.util.SecurityUtils;
import br.com.digitoglobal.accesscontrol.util.SpringContextProviderUtils;
import me.dabpessoa.framework.service.GenericAbstractService;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService extends GenericAbstractService<User, Long, UserDao, SpringContextProviderUtils> {

	@Override
	public List<User> find(User entity) {
		return findByHQLEntityFilter(entity);
	}

	@Override
	public void insert(User user) {
		this.insertOrUpdate(user);
	}
	
	@Override
	public void update(User user) {
		this.insertOrUpdate(user);
	}

	public void insertOrUpdate(User user) {
		if (user != null) {
			validateUser(user);
			encryptPassword(user);
			if (user.getId() != null) {
				super.update(user);
			} else {
				super.insert(user);
			}
		}
	}

	public User findByLoginAndPasswordAndModule(String login, String password, Module module) {
		String encryptedPassword = createEncryptedPassword(password);
		return getRepository().findByLoginAndPassword(login, encryptedPassword, module);
	}

	public User findByLoginAndPassword(String login, String password) {
		String encryptedPassword = createEncryptedPassword(password);
		return getRepository().findByLoginAndPassword(login, encryptedPassword, null);
	}

	public void validateUser(User user) {
		if(user.getLogin() == null){
			throw new RuntimeException("O campo Login é obrigatório.");
		}
		
		if(user.getPassword() == null){
			throw new RuntimeException("O campo Senha é obrigatório.");
		}
	}
	
	public void encryptPassword(User user) {
		user.setPassword(createEncryptedPassword(user.getPassword()));
	}
	
	public String createEncryptedPassword(String password) {
		try {
			return SecurityUtils.hashMD5String(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
}