package br.com.digitoglobal.accesscontrol.service;

import br.com.digitoglobal.accesscontrol.dao.ModuloDao;
import br.com.digitoglobal.accesscontrol.model.Modulo;
import br.com.digitoglobal.accesscontrol.util.SpringContextProviderUtils;
import me.dabpessoa.framework.exceptions.ApplicationRuntimeException;
import me.dabpessoa.framework.service.GenericAbstractService;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;

@Service
public class ModuloService extends GenericAbstractService<Modulo, Long, ModuloDao, SpringContextProviderUtils> {
	private static final long serialVersionUID = 1L;

	@Override
	public List<Modulo> find(Modulo entity) {
		return findByHQLEntityFilter(entity);
	}

	public Modulo findByLabel(String label) {
		Query q = getRepository().getEntityManager().createQuery(" from Module m where m.label = :label ");
		q.setParameter("label", label);
		List<Modulo> modules = q.getResultList();
		if (modules == null) return null;
		if (modules.size() > 1) throw new ApplicationRuntimeException("Mais de um módulo encontrado com o label: "+label);
		return modules.get(0);
	}

}
