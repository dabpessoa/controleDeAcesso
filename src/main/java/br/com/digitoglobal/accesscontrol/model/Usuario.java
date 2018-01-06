package br.com.digitoglobal.accesscontrol.model;

import br.com.digitoglobal.util.bean.model.Pessoa;
import me.dabpessoa.framework.dao.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "usuario", schema = "controle_acesso")
public class Usuario extends BaseEntity {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String login;
	
	@Column
	private String senha;

	@Column(name = "hash_recupera_senha")
	private String hashRecuperacaoSenha;

	@ManyToOne
	@JoinColumn(name = "fk_modulo", referencedColumnName = "id")
	private Modulo modulo;

	@ManyToOne
	@JoinColumn(name = "fk_pessoa", referencedColumnName = "id")
	private Pessoa pessoa;

	@Transient
	private String senhaRedigitada;

	@Transient
	private String novaSenha;

	@Transient
	private String novaSenhaRedigitada;
	
	public Usuario() {
		this(null, null, null);
	}

	public Usuario(Long id) {
		this(id, null, null);
	}

	public Usuario(String login) {
		this(null, login, null);
	}

	public Usuario(String login, String senha) {
		this(null, login, senha);
	}

	public Usuario(Long id, String login, String senha) {
		this.id = id;
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getSenhaRedigitada() {
		return senhaRedigitada;
	}

	public void setSenhaRedigitada(String senhaRedigitada) {
		this.senhaRedigitada = senhaRedigitada;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getNovaSenhaRedigitada() {
		return novaSenhaRedigitada;
	}

	public void setNovaSenhaRedigitada(String novaSenhaRedigitada) {
		this.novaSenhaRedigitada = novaSenhaRedigitada;
	}

	public String getHashRecuperacaoSenha() {
		return hashRecuperacaoSenha;
	}

	public void setHashRecuperacaoSenha(String hashRecuperacaoSenha) {
		this.hashRecuperacaoSenha = hashRecuperacaoSenha;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Usuario usuario = (Usuario) o;

		if (id != null ? !id.equals(usuario.id) : usuario.id != null) return false;
		if (login != null ? !login.equals(usuario.login) : usuario.login != null) return false;
		if (senha != null ? !senha.equals(usuario.senha) : usuario.senha != null) return false;
		if (modulo != null ? !modulo.equals(usuario.modulo) : usuario.modulo != null) return false;
		return pessoa != null ? pessoa.equals(usuario.pessoa) : usuario.pessoa == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (id != null ? id.hashCode() : 0);
		result = 31 * result + (login != null ? login.hashCode() : 0);
		result = 31 * result + (senha != null ? senha.hashCode() : 0);
		result = 31 * result + (modulo != null ? modulo.hashCode() : 0);
		result = 31 * result + (pessoa != null ? pessoa.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Usuario{" +
				"id=" + id +
				", login='" + login + '\'' +
				", senha='" + senha + '\'' +
				", modulo=" + modulo +
				", pessoa=" + pessoa +
				'}';
	}

}