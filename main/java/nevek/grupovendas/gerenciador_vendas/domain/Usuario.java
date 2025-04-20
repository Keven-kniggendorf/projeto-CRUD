package nevek.grupovendas.gerenciador_vendas.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;


@Entity
public class Usuario {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "char(36)")
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String usuario;

    @Column(nullable = false)
    private String senha;

    @Column
    private String perfil;

    @Column(nullable = false, columnDefinition = "tinyint", length = 1)
    private Boolean estado;

    @Column(nullable = false)
    private LocalDateTime dataHoraCriacao;

    @Column(nullable = false)
    private LocalDateTime ultimoLogin;

    public Usuario() {
        this.estado = true;
        this.dataHoraCriacao = LocalDateTime.now();
        this.ultimoLogin = LocalDateTime.now();
    }

    public Usuario(final String nome, final String usuario, final String senha, final String perfil) {
        this();
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.perfil = perfil;
        this.estado = true;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(final String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(final String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(final String perfil) {
        this.perfil = perfil;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(final Boolean estado) {
        this.estado = estado;
    }

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(final LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public LocalDateTime getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(final LocalDateTime ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public void reset(){
        this.estado = true;
    }

    public void mudarEstado(){
        this.estado = !this.estado;
    }

}
