package io.github.matheusascencio.clientes.rest.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicoDTO {

    @NotEmpty(message = "{ campo.descricao.obrigatorio }")
    private String descricao;
    
    @NotEmpty(message = "{ campo.preco.obrigatorio }")
    private String preco;
    
    @NotEmpty(message = "{ campo.data.obrigatorio }")
    private String data;
    
    @NotNull(message = "{ campo.idCli.obrigatorio }")
    private Integer idCli;

     //Getters
     public Integer getIdCli() { return this.idCli; }
     public String getPreco() { return this.preco; }
     public String getData() { return this.data; }
     public String getDescricao() { return this.descricao; }
 
     //Setters
     public void setIdCli(Integer id) { this.idCli = id; }
     public void setPreco(String p) { this.preco = p; }
     public void setData(String data) { this.data = data; }
     public void setDescricao(String des) { this.descricao = des; }
}
