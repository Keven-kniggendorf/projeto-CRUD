package nevek.grupovendas.gerenciador_vendas.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;


public class UsuarioDTO {

    private UUID id;

    @NotNull
    @Size(max = 255)
    private String nome;

    @NotNull
    @Size(max = 255)
    @UsuarioUsuarioUnique
    private String usuario;

    @NotNull
    @Size(max = 255)
    private String senha;

    @Size(max = 255)
    private String perfil;

    @NotNull
    private Boolean estado;

    @NotNull
    private LocalDateTime dataHoraCriacao;

    @NotNull
    private LocalDateTime ultimoLogin;

    public UsuarioDTO() {
        this.dataHoraCriacao = LocalDateTime.now();
        this.ultimoLogin = LocalDateTime.now();
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

}
