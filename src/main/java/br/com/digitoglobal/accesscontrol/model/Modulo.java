package br.com.digitoglobal.accesscontrol.model;

import me.dabpessoa.framework.dao.BaseEntity;

import javax.persistence.*;

@Entity
@Table (name = "modulo" , schema = "controle_acesso")
public class Modulo extends BaseEntity {
	private static final long serialVersionUID = 7834746337421936161L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String description;

	private String label;

	public Modulo() {this(null, null);}

	public Modulo(Long id) {
		this(id, null);
	}

	public Modulo(String label) {
		this(null, label);
	}

	public Modulo(Long id, String label) {
		this.id = id;
		this.label = label;
	}

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
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
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
		Modulo other = (Modulo) obj;
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
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Module [id=" + id + ", description=" + description + ", label=" + label + "]";
	}
}