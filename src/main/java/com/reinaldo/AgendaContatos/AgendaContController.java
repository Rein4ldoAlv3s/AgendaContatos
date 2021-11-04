package com.reinaldo.AgendaContatos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agendadecontatos")
public class AgendaContController {
	
	@Autowired
	private AgendaContRepository agendaContRepository;
	
	
	@GetMapping
	public List<AgendaContatos> listar() {
	    return agendaContRepository.findAll();
	}
	
	@GetMapping("/{agendaDeContatosId}")
	public ResponseEntity<AgendaContatos> buscar(@PathVariable Long agendaDeContatosId){
		Optional<AgendaContatos> agendaCont = agendaContRepository.findById(agendaDeContatosId);
		if(agendaCont.isPresent()) {
			return ResponseEntity.ok(agendaCont.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AgendaContatos persistir(@RequestBody AgendaContatos agendaCont){
		return agendaContRepository.save(agendaCont);
		
	}
	
	@PutMapping("/{agendaDeContatosId}")
	public ResponseEntity<AgendaContatos> atualizar(@PathVariable Long agendaDeContatosId, 
			@RequestBody AgendaContatos agendaContatos){
		if(agendaContRepository.existsById(agendaDeContatosId)) {
			agendaContatos.setId(agendaDeContatosId);
			return ResponseEntity.ok(agendaContRepository.save(agendaContatos));
		}
		return ResponseEntity.notFound().build();
		
	}
	
	
}
