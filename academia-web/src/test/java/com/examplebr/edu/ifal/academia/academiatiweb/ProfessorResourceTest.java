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
import com.examplebr.edu.ifal.academia.academiatiweb.modelo.Professor;
import com.examplebr.edu.ifal.academia.academiatiweb.modelo.Tipo_professor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import repositories.ProfessorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProfessorResourceTest {
	
	final String BASE_PATH = "http://localhost:8080/api/professor";
	
	@Autowired
	private ProfessorRepository repositorio;
	
	private RestTemplate restTemplate;
	
	private ObjectMapper MAPPER = new ObjectMapper();
	
	@Before
	public void setUp(){
		repositorio.deleteAll();
		
		Professor e1 = new Professor("132","João",Tipo_professor.efetivo);
		
		Professor e2 = new Professor("112","Maria",Tipo_professor.efetivo);
		
		Professor e3 = new Professor("245", "carol", Tipo_professor.efetivo);
		
		repositorio.save(e1);
		
		repositorio.save(e2);
		
		repositorio.save(e3);
		
		restTemplate = new RestTemplate();
	
	}

	@Test
	public void deveFuncionarAListagemDeTodosOsProfessores() throws JsonParseException, JsonMappingException, IOException {
		
		String resposta = restTemplate.
				getForObject(BASE_PATH + "/listar", String.class);
		
		List<Professor> professores = MAPPER.readValue(resposta,
				MAPPER.getTypeFactory().constructCollectionLikeType(List.class, Professor.class));
		
		int tamanhoDaListaDeProfessoresesperado = 3;
		assertEquals(tamanhoDaListaDeProfessoresesperado, professores.size());
	}
	
	@Test
	public void deveFuncionarACriacaoDeUmNovoProfessor() throws JsonParseException, JsonMappingException, IOException {
		
		Professor professor = new Professor("456", 
				"Paulo", Tipo_professor.efetivo);
		
		restTemplate.postForObject(BASE_PATH+"/salvar", 
				professor, Professor.class);
		
		String resposta = restTemplate.
				getForObject(BASE_PATH+ "/listar", String.class);
		
		List<Professor> professores = MAPPER.readValue(resposta,
				MAPPER.getTypeFactory().
				constructCollectionLikeType(List.class, Professor.class));
		
		assertEquals("Paulo", professores.get(0).getNome());
		
}	
 }
