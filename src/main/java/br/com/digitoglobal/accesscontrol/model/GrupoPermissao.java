package br.com.digitoglobal.accesscontrol.model;

import me.dabpessoa.framework.dao.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "grupo_permissao", schema="controle_acesso")
public class GrupoPermissao extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="cd_group")
	private Grupo group;
	
	@ManyToOne
	@JoinColumn(name="cd_permission")
	private Permissao permission;
	
	public GrupoPermissao() {}
	
	public GrupoPermissao(Permissao permission) {
		super();
		this.permission = permission;
	}
	
	public GrupoPermissao(Grupo group, Permissao permission) {
		super();
		this.group = group;
		this.permission = permission;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Grupo getGroup() {
		return group;
	}
	public void setGroup(Grupo group) {
		this.group = group;
	}
	public Permissao getPermission() {
		return permission;
	}
	public void setPermission(Permissao permission) {
		this.permission = permission;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((permission == null) ? 0 : permission.hashCode());
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
		GrupoPermissao other = (GrupoPermissao) obj;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (permission == null) {
			if (other.permission != null)
				return false;
		} else if (!permission.equals(other.permission))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "GroupPermission [id=" + id + ", group=" + group + ", permission=" + permission + "]";
	}
}
