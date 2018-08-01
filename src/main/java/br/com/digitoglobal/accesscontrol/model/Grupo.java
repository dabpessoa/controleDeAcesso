package br.com.digitoglobal.accesscontrol.model;

import me.dabpessoa.framework.dao.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grupo", schema = "controle_acesso")
public class Grupo extends BaseEntity {

	private static final long serialVersionUID = 2943429535014474444L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String label;

	@Column
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "fk_modulo")
	private Modulo modulo;
	
	@OneToMany(mappedBy="grupo")
	private List<GrupoPermissao> grupoPermissaoList;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public List<GrupoPermissao> getGrupoPermissaoList() {
		return grupoPermissaoList;
	}

	public void setGrupoPermissaoList(List<GrupoPermissao> grupoPermissaoList) {
		this.grupoPermissaoList = grupoPermissaoList;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Grupo grupo = (Grupo) o;

		if (id != null ? !id.equals(grupo.id) : grupo.id != null) return false;
		if (label != null ? !label.equals(grupo.label) : grupo.label != null) return false;
		if (descricao != null ? !descricao.equals(grupo.descricao) : grupo.descricao != null) return false;
		return modulo != null ? modulo.equals(grupo.modulo) : grupo.modulo == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (id != null ? id.hashCode() : 0);
		result = 31 * result + (label != null ? label.hashCode() : 0);
		result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
		result = 31 * result + (modulo != null ? modulo.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Grupo [id=" + id + ", descricao=" + descricao + ", modulo=" + modulo + "]";
	}

}