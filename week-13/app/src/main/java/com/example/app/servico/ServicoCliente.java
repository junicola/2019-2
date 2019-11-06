package com.example.app.servico;

import com.example.app.negocio.dominio.ClienteDTO;
import com.example.app.negocio.dominio.PaisDTO;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class ServicoCliente {
    
    private List<ClienteDTO> clientes;
    private PaisDTO pais;
    
    public ServicoCliente() {
                
        clientes = Stream.of(
            ClienteDTO.builder().id(1).nome("Julia").idade(22).telefone("55555").limiteCredito(1000).pais(pais).build(),
            ClienteDTO.builder().id(2).nome("Luis").idade(23).telefone("666666").limiteCredito(2000).pais(pais).build()
        ).collect(Collectors.toList());
    }
    
    @GetMapping("servico/cliente")
    public ResponseEntity<List<ClienteDTO>> listar() {
        return ResponseEntity.ok(clientes);
    }
    
    @PostMapping ("/servico/pais")
    public ResponseEntity<ClienteDTO> incluir(@RequestBody ClienteDTO cliente) {

        cliente.setId(clientes.size() + 1);
        clientes.add(cliente);

        return ResponseEntity.status(201).body(cliente);
    }
}
