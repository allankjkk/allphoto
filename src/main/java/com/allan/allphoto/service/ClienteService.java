package com.allan.allphoto.service;

import com.allan.allphoto.model.Cliente;
import com.allan.allphoto.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java .util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository ClienteRepo;

    public Cliente salvar(Cliente cliente){
        try {
            return ClienteRepo.save(cliente);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar cliente: ");
        }
    }

    public Cliente buscarPorId(Long Id) {
        Optional<Cliente> cli = ClienteRepo.findById(Id);
        if (cli.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado");
        } else {
            return cli.get();
        }
    }

    public List<Cliente> listar(){
        try {
            return ClienteRepo.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar clientes: ");
        }
    }

    public Cliente atualizar(Long id, Cliente cliente){
        Optional<Cliente> cli = ClienteRepo.findById(id);
        if (cli.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado");
        } else {
            return ClienteRepo.save(cliente);
        }
    }

    public void deletar(Long id){
        if(!ClienteRepo.existsById(id)){
            throw new RuntimeException("Cliente não encontrado");
        } else {
            ClienteRepo.deleteById(id);
        }
    }
}
