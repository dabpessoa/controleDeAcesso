package br.com.digitoglobal.accesscontrol.model;

import me.dabpessoa.framework.dao.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "group", schema = "access_control")
public class Group extends BaseEntity {

	private static final long serialVersionUID = 2943429535014474444L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String description;

	@ManyToOne
	@JoinColumn(name = "cd_module")
	private Module module;
	
	@OneToMany(mappedBy="group")
	private List<GroupPermission> permissions;
	
	@Transient
	private List<GroupPermission> permissionsToExclude;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((module == null) ? 0 : module.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", description=" + description + ", module=" + module + "]";
	}

	public List<GroupPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<GroupPermission> permissions) {
		this.permissions = permissions;
	}

	public List<GroupPermission> getPermissionsToExclude() {
		return permissionsToExclude;
	}

	public void setPermissionsToExclude(List<GroupPermission> permissionsToExclude) {
		this.permissionsToExclude = permissionsToExclude;
	}
	
	public void removePermission(GroupPermission permission){
		if(permission.getId() == null){
			return;
		}
		
		if(this.permissionsToExclude == null){
			this.permissionsToExclude = new ArrayList<GroupPermission>();
		}
		
		this.permissionsToExclude.add(permission);
	}
}