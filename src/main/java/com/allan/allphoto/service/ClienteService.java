package com.allan.allphoto.service;

import com.allan.allphoto.DTO.ClienteDTO;
import com.allan.allphoto.mapper.ClienteMapper;
import com.allan.allphoto.model.Cliente;
import com.allan.allphoto.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository ClienteRepo;
    private final ClienteMapper ClienteMap;

    public ClienteDTO salvar(ClienteDTO clienteDTO) {
        Cliente cliente = ClienteMap.toEntity(clienteDTO);
        return ClienteMap.toDTO(ClienteRepo.save(cliente));
    }

    public ClienteDTO buscarPorId(Long id) {
        Optional<Cliente> cli = ClienteRepo.findById(id);
        if (cli.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado");
        } else {
            return ClienteMap.toDTO(cli.get());
        }
    }

    public List<ClienteDTO> listar() {
        return ClienteRepo.findAll()
                .stream()
                .map(ClienteMap::toDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO atualizar(Long id, ClienteDTO clienteDTO) {
        Optional<Cliente> cli = ClienteRepo.findById(id);
        if (cli.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado");
        } else {
            Cliente cliente = ClienteMap.toEntity(clienteDTO);
            cliente.setId(id);
            return ClienteMap.toDTO(ClienteRepo.save(cliente));
        }
    }

    public void deletar(Long id) {
        if (!ClienteRepo.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado");
        } else {
            ClienteRepo.deleteById(id);
        }
    }
}