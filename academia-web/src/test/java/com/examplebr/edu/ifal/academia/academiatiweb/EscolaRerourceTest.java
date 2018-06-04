package com.examplebr.edu.ifal.academia.academiatiweb;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import com.examplebr.edu.ifal.academia.academiatiweb.modelo.Escola;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import repositories.EscolaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class EscolaRerourceTest {
	
final String BASE_PATH = "http://localhost:8080/api/escola";
	
	@Autowired
	private EscolaRepository repositorio;
	
	private RestTemplate restTemplate;
	
	private ObjectMapper MAPPER = new ObjectMapper();
	
	@Before
	public void setUp(){
		
		repositorio.deleteAll();
				
        Escola e1 = new Escola();
		
		Escola e2 = new Escola();
		
		Escola e3 = new Escola();
		
		e1.setNome("IFAL Campus-RL");
	    e2.setNome("IFAL Campus-Meceió");
	    e3.setNome("pompeu sarmento");
		
		repositorio.save(e1);
		
		repositorio.save(e2);
		
		repositorio.save(e3);
		
		restTemplate = new RestTemplate();
			
	}
	@Test
	public void testdeveFuncionarAListagemDeTodosAsEscolas() throws JsonParseException, JsonMappingException, IOException {
		
		String resposta = restTemplate.
				getForObject(BASE_PATH + "/listar", String.class);
		
		List<Escola> escolas = MAPPER.readValue(resposta,
				MAPPER.getTypeFactory().constructCollectionLikeType(List.class, Escola.class));
		
		int tamanhoDaListaDeEscolasesperado = 3;
		assertEquals(tamanhoDaListaDeEscolasesperado, escolas.size());	
	}
	@Test
	public void deveFuncionarACriacaoDeUmaNovaEscola() throws JsonParseException, JsonMappingException, IOException {
		
		Escola escola = new Escola("Francisco Leão");
		
		restTemplate.postForObject(BASE_PATH+"/salvar", 
				escola, Escola.class);
		
		String resposta = restTemplate.
				getForObject(BASE_PATH+ "/listar", String.class);
		
		List<Escola> escolas = MAPPER.readValue(resposta,
				MAPPER.getTypeFactory().
				constructCollectionLikeType(List.class, Escola.class));
		
		assertEquals("Francisco Leão", escolas.get(0).getNome());
		
}

 }
