package br.com.digitoglobal.accesscontrol.service;

import br.com.digitoglobal.accesscontrol.dao.GrupoDao;
import br.com.digitoglobal.accesscontrol.model.Grupo;
import br.com.digitoglobal.accesscontrol.util.SpringContextProviderUtils;
import me.dabpessoa.framework.service.GenericAbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoService extends GenericAbstractService<Grupo, Long, GrupoDao, SpringContextProviderUtils> {

	@Override
	public List<Grupo> find(Grupo entity) {
		return findByHQLEntityFilter(entity);
	}

}
