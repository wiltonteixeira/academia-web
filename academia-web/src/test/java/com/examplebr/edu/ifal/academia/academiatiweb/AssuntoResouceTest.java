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
import com.examplebr.edu.ifal.academia.academiatiweb.modelo.Assunto;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import repositories.AssuntoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AssuntoResouceTest {

	final String BASE_PATH = "http://localhost:8080/api/assunto";

	@Autowired
	private AssuntoRepository repositorio;

	private RestTemplate restTemplate;

	private ObjectMapper MAPPER = new ObjectMapper();

	@Before
	public void setUp() {
		repositorio.deleteAll();

		Assunto a1 = new Assunto("corpo humano");

		Assunto a2 = new Assunto("historia do brasil");

		Assunto a3 = new Assunto("potencia");

		repositorio.save(a1);

		repositorio.save(a2);

		repositorio.save(a3);

		restTemplate = new RestTemplate();

	}

	@Test
	public void deveFuncionarAListagemDeTodosOsAssuntos() throws JsonParseException, JsonMappingException, IOException {

		String resposta = restTemplate.
				getForObject(BASE_PATH + "/listar", String.class);

		List<Assunto> assuntos = MAPPER.readValue(resposta,
				MAPPER.getTypeFactory().constructCollectionLikeType(List.class, Assunto.class));

		int tamanhoDaListaDeAssuntosesperado = 3;
		assertEquals(tamanhoDaListaDeAssuntosesperado, assuntos.size());
	}

	@Test
	public void deveFuncionarACriacaoDeUmNovoAssunto() throws JsonParseException, JsonMappingException, IOException {

		Assunto assunto = new Assunto("raiz quadrada");

		restTemplate.postForObject
			(BASE_PATH + "/salvar", assunto, Assunto.class);

		String resposta = restTemplate.
				getForObject(BASE_PATH + "/listar", String.class);

		List<Assunto> assuntos = MAPPER.readValue(resposta,
				MAPPER.getTypeFactory().
				constructCollectionLikeType(List.class, Assunto.class));

		assertEquals("raiz quadrada", assuntos.get(0).getNome());

	}
}
