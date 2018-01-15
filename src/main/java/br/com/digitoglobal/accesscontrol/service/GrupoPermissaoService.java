package br.com.digitoglobal.accesscontrol.service;

import br.com.digitoglobal.accesscontrol.dao.GrupoPermissaoDao;
import br.com.digitoglobal.accesscontrol.model.GrupoPermissao;
import me.dabpessoa.framework.service.GenericAbstractService;
import me.dabpessoa.framework.service.SpringContextProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoPermissaoService extends GenericAbstractService<GrupoPermissao, Long, GrupoPermissaoDao, SpringContextProvider> {

	@Override
	public List<GrupoPermissao> find(GrupoPermissao entity) {
		return findByHQLEntityFilter(entity);
	}

}
