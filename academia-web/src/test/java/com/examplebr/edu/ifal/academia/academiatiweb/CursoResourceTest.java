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
import com.examplebr.edu.ifal.academia.academiatiweb.modelo.Curso;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import repositories.CursoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CursoResourceTest {

	String BASE_PATH = "http://localhost:8080/api/curso";

	@Autowired
	private CursoRepository repositorio;

	private RestTemplate restTemplate;

	private ObjectMapper MAPPER = new ObjectMapper();

	@Before
	public void setUp() {
		repositorio.deleteAll();

		Curso c1 = new Curso();

		Curso c2 = new Curso();

		Curso c3 = new Curso();

		c1.setNome("Logistica");
		c2.setNome("informatica");
		c3.setNome("emfermagem");

		repositorio.save(c1);

		repositorio.save(c2);

		repositorio.save(c3);
		
		restTemplate = new RestTemplate();

	}

	@Test
	public void deveFuncionarAListagemDeTodosOsCursos() throws JsonParseException, JsonMappingException, IOException {

		String resposta = restTemplate.
				getForObject(BASE_PATH + "/listar", String.class);

		List<Curso> cursos = MAPPER.readValue(resposta,
				MAPPER.getTypeFactory().constructCollectionLikeType(List.class, Curso.class));

		int tamanhoDaListaDeCursosesperado = 3;
		assertEquals(tamanhoDaListaDeCursosesperado, cursos.size());
	}

	@Test
	public void deveFuncionarACriacaoDeUmNovoCurso() throws JsonParseException, JsonMappingException, IOException {

		Curso curso = new Curso("administração");

		restTemplate.postForObject
			(BASE_PATH + "/salvar", curso, Curso.class);

		String resposta = restTemplate.
				getForObject(BASE_PATH + "/listar", String.class);

		List<Curso> cursos = MAPPER.readValue(resposta,
				MAPPER.getTypeFactory().
				constructCollectionLikeType(List.class, Curso.class));

		assertEquals("administração", cursos.get(0).getNome());

	}

}
