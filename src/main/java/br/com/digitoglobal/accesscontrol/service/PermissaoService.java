package br.com.digitoglobal.accesscontrol.service;

import br.com.digitoglobal.accesscontrol.dao.PermissaoDao;
import br.com.digitoglobal.accesscontrol.model.Permissao;
import me.dabpessoa.framework.service.GenericAbstractService;
import me.dabpessoa.framework.service.SpringContextProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissaoService extends GenericAbstractService<Permissao, Long, PermissaoDao, SpringContextProvider> {

	@Override
	public List<Permissao> find(Permissao entity) {
		return findByHQLEntityFilter(entity);
	}
}
