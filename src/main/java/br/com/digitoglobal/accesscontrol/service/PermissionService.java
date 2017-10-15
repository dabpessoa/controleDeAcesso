package br.com.digitoglobal.accesscontrol.service;

import br.com.digitoglobal.accesscontrol.dao.PermissionDao;
import br.com.digitoglobal.accesscontrol.model.Permission;
import br.com.digitoglobal.accesscontrol.util.SpringContextProviderUtils;
import me.dabpessoa.framework.service.GenericAbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService extends GenericAbstractService<Permission, Long, PermissionDao, SpringContextProviderUtils> {

	@Override
	public List<Permission> find(Permission entity) {
		return findByHQLEntityFilter(entity);
	}
}
