package nevek.grupovendas.gerenciador_vendas.model;

import jakarta.validation.constraints.*;

import java.util.UUID;


public class ClienteDTO {

    private UUID id;

    @NotNull
    @Size(max = 255)
    @NotBlank(message = "O nome não pode ser vazio.")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$",
            message = "O nome deve conter apenas letras e espaços.")
    private String nome;

    @NotBlank(message = "O nome não pode ser vazio.")
    @Size(max = 25)
    @Pattern(regexp = "^[0-9]{11}$", message = "O telefone deve conter exatamente 11 números.")
    private String telefone;

    @Size(max = 255)
    private String email;

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(final String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

}
