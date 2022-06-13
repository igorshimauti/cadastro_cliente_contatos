package br.com.cadastro.web.service;

import br.com.cadastro.web.model.Cliente;
import br.com.cadastro.web.model.Contato;
import br.com.cadastro.web.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        for (Contato contato : cliente.getContatos()) {
            contato.setCliente(cliente);
        }

        Cliente clienteSalvo = clienteRepository.save(cliente);
        return clienteSalvo;
    }

    @Transactional(readOnly = true)
    public Cliente recuperarPorId(Long id) {
        return clienteRepository.findById(id).get();
    }

    public void excluir(Long id) {
        clienteRepository.deleteById(id);
    }
}