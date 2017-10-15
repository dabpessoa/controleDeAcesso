package br.com.digitoglobal.accesscontrol.service;

import br.com.digitoglobal.accesscontrol.dao.GroupDao;
import br.com.digitoglobal.accesscontrol.model.Group;
import br.com.digitoglobal.accesscontrol.util.SpringContextProviderUtils;
import me.dabpessoa.framework.service.GenericAbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService extends GenericAbstractService<Group, Long, GroupDao, SpringContextProviderUtils> {

	@Override
	public List<Group> find(Group entity) {
		return findByHQLEntityFilter(entity);
	}

}
