package nevek.grupovendas.gerenciador_vendas.model;

import java.util.List;
import java.util.UUID;

public class SalvarProdutosTemporarioDTO {

    private List<UUID> uuids;

    public List<UUID> getUuids() {
        return uuids;
    }

    public void setUuids(List<UUID> uuids) {
        this.uuids = uuids;
    }

}
