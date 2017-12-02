package br.com.digitoglobal.accesscontrol.model;

import me.dabpessoa.framework.dao.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "grupo_usuario", schema = "controle_acesso")
public class GrupoUsuario extends BaseEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_grupo", referencedColumnName = "id")
    private Grupo grupo;

    @ManyToOne
    @JoinColumn(name = "fk_usuario", referencedColumnName = "id")
    private Usuario usuario;

    public GrupoUsuario() {
        this(null, null, null);
    }

    public GrupoUsuario(Long id) {
        this(id, null, null);
    }

    public GrupoUsuario(Long id, Grupo grupo, Usuario usuario) {
        this.id = id;
        this.grupo = grupo;
        this.usuario = usuario;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
