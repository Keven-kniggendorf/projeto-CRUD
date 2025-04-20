package nevek.grupovendas.gerenciador_vendas.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/cadastro-cliente")
    public String cadastroCliente() {
        return "cadastro-cliente";
    }

    @GetMapping("/cadastro-usuario")
    public String cadastroUsuario() {
        return "cadastro-usuario";
    }

    @GetMapping("/cadastro-produto")
    public String cadastroProduto() {
        return "cadastro-produto";
    }

    @GetMapping("/consulta-servicos")
    public String consultaProduto() {
        return "consulta-servicos";
    }

    @GetMapping("/agendamento")
    public String agendamento() {
        return "agendamento"; // carrega templates/agendamento.html
    }

}