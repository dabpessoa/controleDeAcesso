package br.com.digitoglobal.accesscontrol.service;

import br.com.digitoglobal.accesscontrol.dao.ModuleDao;
import br.com.digitoglobal.accesscontrol.model.Module;
import br.com.digitoglobal.accesscontrol.util.SpringContextProviderUtils;
import me.dabpessoa.framework.exceptions.ApplicationRuntimeException;
import me.dabpessoa.framework.service.GenericAbstractService;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;

@Service
public class ModuleService extends GenericAbstractService<Module, Long, ModuleDao, SpringContextProviderUtils> {
	private static final long serialVersionUID = 1L;

	@Override
	public List<Module> find(Module entity) {
		return findByHQLEntityFilter(entity);
	}

	public Module findByLabel(String label) {
		Query q = getRepository().getEntityManager().createQuery(" from Module m where m.label = :label ");
		q.setParameter("label", label);
		List<Module> modules = q.getResultList();
		if (modules == null) return null;
		if (modules.size() > 1) throw new ApplicationRuntimeException("Mais de um módulo encontrado com o label: "+label);
		return modules.get(0);
	}

}
