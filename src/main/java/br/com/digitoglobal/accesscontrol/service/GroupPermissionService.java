package br.com.digitoglobal.accesscontrol.service;

import br.com.digitoglobal.accesscontrol.dao.GroupPermissionDao;
import br.com.digitoglobal.accesscontrol.model.GroupPermission;
import br.com.digitoglobal.accesscontrol.util.SpringContextProviderUtils;
import me.dabpessoa.framework.service.GenericAbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupPermissionService extends GenericAbstractService<GroupPermission, Long, GroupPermissionDao, SpringContextProviderUtils> {

	@Override
	public List<GroupPermission> find(GroupPermission entity) {
		return findByHQLEntityFilter(entity);
	}

}
