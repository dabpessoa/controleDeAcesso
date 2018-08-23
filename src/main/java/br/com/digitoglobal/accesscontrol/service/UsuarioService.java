package br.com.digitoglobal.accesscontrol.service;

import br.com.digitoglobal.accesscontrol.dao.UsuarioDao;
import br.com.digitoglobal.accesscontrol.model.Modulo;
import br.com.digitoglobal.accesscontrol.model.Usuario;
import br.com.digitoglobal.accesscontrol.util.MapFactory;
import br.com.digitoglobal.accesscontrol.util.SecurityUtils;
import me.dabpessoa.framework.exceptions.ApplicationRuntimeException;
import me.dabpessoa.framework.service.GenericAbstractService;
import me.dabpessoa.framework.service.SpringContextProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService extends GenericAbstractService<Usuario, Long, UsuarioDao, SpringContextProvider> {

	@Resource
	private ModuloService moduloService;

	@Override
	public List<Usuario> find(Usuario entity) {
		return findByHQLEntityFilter(entity);
	}

	public Usuario findByLoginAndPasswordAndModule(String login, String password, Modulo module) {
		String encryptedPassword = createEncryptedPassword(password);
		return getRepository().findByLoginAndPassword(login, encryptedPassword, module);
	}

	public Usuario findByLoginAndPassword(String login, String password) {
		String encryptedPassword = createEncryptedPassword(password);
		return getRepository().findByLoginAndPassword(login, encryptedPassword, null);
	}

	public void validateUser(Usuario user) {
		if(user.getLogin() == null){
			throw new RuntimeException("O campo Login é obrigatório.");
		}
		
		if(user.getSenha() == null){
			throw new RuntimeException("O campo Senha é obrigatório.");
		}
	}
	
	public void encryptPassword(Usuario usuario) {
		usuario.setSenha(createEncryptedPassword(usuario.getSenha()));
	}
	
	public String createEncryptedPassword(String password) {
		try {
			return SecurityUtils.hashMD5String(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public Usuario findByLogin(String login) {
		if (login == null) return null;
		List<Usuario> usuarioAntigos = getRepository().getDaoHelper().queryHQLList("select u from Usuario u left join fetch u.pessoa p where u.login = :login", MapFactory.create("login", login));
		if (usuarioAntigos != null && usuarioAntigos.size() > 1)
			throw new ApplicationRuntimeException("Mais de um usuário localizado para o login: " + login);
		if (usuarioAntigos != null && usuarioAntigos.size() == 1) return usuarioAntigos.get(0);
		return null;
	}

	public Usuario findByEmail(String email) {
		if (email == null) return null;
		List<Usuario> usuarioAntigos = getRepository().getDaoHelper().queryHQLList("select u from Usuario u left join fetch u.pessoa p where p.email = :email", MapFactory.create("email", email));
		if (usuarioAntigos == null) return null;
		if (usuarioAntigos.size() == 0) return null;
		if (usuarioAntigos.size() == 1) return usuarioAntigos.get(0);
		throw new ApplicationRuntimeException("Mais de um usuário localizado para o e-mail: " + email);
	}

	public Usuario findByLoginOrEmail(String login, String email) {
		Usuario usuarioAntigo = findByLogin(login);
		if (usuarioAntigo != null) return usuarioAntigo;
		return findByEmail(email);
	}

	public boolean existsByLogin(String login) {
		return getRepository().existsByLogin(login);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public void insert(Usuario usuario) {
		insertOrUpdate(usuario);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public void update(Usuario usuario) {
		insertOrUpdate(usuario);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public void insertOrUpdate(Usuario usuario) {
		validateUsuario(usuario);

		if (usuario != null) {
			if (usuario.getId() == null) {
				encryptPassword(usuario);
				super.insert(usuario);
			} else {
				Usuario usuarioAntigoBanco = findByKey(usuario.getId());
				// Para não encryptar duas vezes
				if (!usuarioAntigoBanco.getSenha().equals(usuario.getSenha())) {
					encryptPassword(usuario);
				}
				super.update(usuario);
			}
		}

	}

	public void validateUsuario(Usuario usuario) {

		if (usuario == null) {
			return;
		}

		if (usuario.getLogin() == null || usuario.getLogin().trim().isEmpty()) {
			throw new ApplicationRuntimeException("O login não pode ser nulo.");
		}

		if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
			throw new ApplicationRuntimeException("A senha não pode ser nula.");
		}

		if (usuario != null) {
			if (getRepository().existsByLogin(usuario)) {
				throw new ApplicationRuntimeException("Login já existente. Tente outro!");
			}

			if (usuario.getLogin() != null && !usuario.getLogin().isEmpty()) {
				if (usuario.getLogin().trim().indexOf(" ") != -1) {
					throw new ApplicationRuntimeException("Não é permitido espaços em branco no login.");
				}
			}
		}

		if (usuario.getSenhaRedigitada() == null || usuario.getSenhaRedigitada().trim().isEmpty()) {
			throw new ApplicationRuntimeException("A confirmação de senha é obrigatória");
		}

		String password = usuario.getSenha();
		String senhaRedigitada = usuario.getSenhaRedigitada();
		if (password != null && !password.trim().isEmpty() && senhaRedigitada != null && !senhaRedigitada.trim().isEmpty()) {
			if (usuario.getId() != null)
				senhaRedigitada = createEncryptedPassword(senhaRedigitada);
			if (!password.equals(senhaRedigitada)) {
				throw new ApplicationRuntimeException("A confirmação de senha não confere.");
			}
		}

		if (usuario.getNovaSenha() != null && !usuario.getNovaSenha().trim().isEmpty()
				&& usuario.getNovaSenhaRedigitada() != null && !usuario.getNovaSenhaRedigitada().trim().isEmpty()) {
			if (!usuario.getNovaSenha().equals(usuario.getNovaSenhaRedigitada())) {
				throw new ApplicationRuntimeException("A confirmação da nova senha não confere.");
			}
		}

		if ((usuario.getNovaSenha() != null && !usuario.getNovaSenha().trim().isEmpty()
				&& (usuario.getNovaSenhaRedigitada() == null || usuario.getNovaSenhaRedigitada().trim().isEmpty()))
				|| usuario.getNovaSenhaRedigitada() != null && !usuario.getNovaSenhaRedigitada().trim().isEmpty()
				&& (usuario.getNovaSenha() == null || usuario.getNovaSenha().trim().isEmpty())) {
			throw new ApplicationRuntimeException("Para inserir uma nova senha é necessário digitar e confirmar essa nova senha.");
		}

		if (usuario.getNovaSenha() != null && !usuario.getNovaSenha().trim().isEmpty()
				&& usuario.getNovaSenhaRedigitada() != null && !usuario.getNovaSenhaRedigitada().trim().isEmpty()) {
			if (usuario.getNovaSenha().equals(usuario.getNovaSenhaRedigitada())) {
				// Atualizando a senha do usuário para a nova senha digitada.
				usuario.setSenha(usuario.getNovaSenha());
			}
		}

		if (usuario != null) {
			// removendo espaços em branco antes e depois da senha.
			usuario.setSenha(usuario.getSenha().trim());
		}

	}

	public Usuario findUsuarioParaRecuperacaoAcesso(String login, String recoverHash) {
		if (login == null || login.isEmpty() || recoverHash == null || recoverHash.isEmpty()) {
			return null;
		}
		return getRepository().getDaoHelper().queryHQLSingleResult("from Usuario u where u.login = :login and u.hashRecuperacaoSenha = :recoverHash",
				MapFactory.create("login", login, "recoverHash", recoverHash));
	}

	public List<Usuario> findUsuarioFetchPessoa(Usuario usuarioAntigo) {
		if (usuarioAntigo == null) return null;

		StringBuilder stringBuilder = new StringBuilder("select new Usuario(u.id, u.login, u.password, u.hashRecuperacaoSenha, p) from Usuario u left join u.pessoa p where 1=1 ");
		Map<String, Object> params = new HashMap<String, Object>();

		if (usuarioAntigo.getId() != null) {
			stringBuilder.append(" and u.id = :id");
			params.put("id", usuarioAntigo.getId());
		} else {
			if (usuarioAntigo.getLogin() != null && !usuarioAntigo.getLogin().isEmpty()) {
				stringBuilder.append(" and lower(u.login) like lower(:login)");
				params.put("login", "%" + usuarioAntigo.getLogin() + "%");
			}

			if (usuarioAntigo.getPessoa() != null) {
				if (usuarioAntigo.getPessoa().getNome() != null && !usuarioAntigo.getPessoa().getNome().isEmpty()) {
					stringBuilder.append(" and lower(p.nome) like lower(:nomePessoa)");
					params.put("nomePessoa", "%" + usuarioAntigo.getPessoa().getNome() + "%");
				}

				if (usuarioAntigo.getPessoa().getEmail() != null && !usuarioAntigo.getPessoa().getEmail().isEmpty()) {
					stringBuilder.append(" and lower(p.email) like lower(:emailPessoa)");
					params.put("emailPessoa", "%" + usuarioAntigo.getPessoa().getEmail() + "%");
				}

			}
		}

		return getRepository().getDaoHelper().queryHQLList(stringBuilder.toString(), params);
	}
	
}