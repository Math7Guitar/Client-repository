package io.github.matheusascencio.clientes.rest.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.validation.Valid;
import com.ibm.icu.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import io.github.matheusascencio.clientes.model.entity.Cliente;
import io.github.matheusascencio.clientes.model.entity.Servico;
import io.github.matheusascencio.clientes.rest.dto.ServicoDTO;
import io.github.matheusascencio.clientes.rest.repository.ClienteRepository;
import io.github.matheusascencio.clientes.rest.repository.ServicoRepository;

@Service
public class ServicoService {

    @Autowired
    private ClienteRepository cliRepo;
    
    @Autowired
    private ServicoRepository servRepo;


     //CRUD
     public Servico salvar(@RequestBody @Valid ServicoDTO dto) {
         LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
         Integer id = dto.getIdCli();
 
         Cliente cliente = cliRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente INEXISTENTE!"));
 
         Servico servico = new Servico();
         servico.setDescricao(dto.getDescricao());
         servico.setData(data);
         servico.setCliente(cliente);
         servico.setValor(converter(dto.getPreco()));
         
         return servRepo.save(servico);
     }
 
     public List<Servico> pesquisar(@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
                                    @RequestParam(value = "mes", required = false) Integer mes) {
         return servRepo.findByNomeClienteEMes("%" + nome + "%", mes);
     }
 
 
     //Personalizados
 
     public BigDecimal converter (String v) {
         if(v == null) {
             return null;
         } else {
             return new BigDecimal(v.replace(".", "").replace(",", "."));
         }
     }
}
