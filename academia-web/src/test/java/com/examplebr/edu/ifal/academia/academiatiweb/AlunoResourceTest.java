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
import com.examplebr.edu.ifal.academia.academiatiweb.modelo.Aluno;
import com.examplebr.edu.ifal.academia.academiatiweb.modelo.Endereco;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import repositories.AlunoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AlunoResourceTest {

	final String BASE_PATH = "http://localhost:8080/api/aluno";

	@Autowired
	private AlunoRepository repositorio;

	private RestTemplate restTemplate;

	private ObjectMapper MAPPER = new ObjectMapper();

	@Before
	public void setUp() {

		repositorio.deleteAll();
		
		Endereco e = new Endereco();
		e.setNome("rua do sol");
		e.setNumero(80);
		e.setBairro("centro");
		e.setCidade("Rio Largo");
		e.setEstado("AL");
		e.setCep("57100-522");

		Aluno a1 = new Aluno();
		a1.setNome("juana");
		a1.setMatricula("45871");
		a1.setEndereco(e);
		a1.getTelefones().add("82988567840");
		a1.getTelefones().add("82994254409");

		Aluno a2 = new Aluno();
		a2.setNome("pedro");
		a2.setEndereco(e);
		a2.getTelefones().add("8291234098");
		a2.getTelefones().add("82457963");
		a2.setMatricula("45872");
		

		Aluno a3 = new Aluno();
		a3.setNome("carla");
		a3.setEndereco(e);
		a3.getTelefones().add("829870241687");
		a3.getTelefones().add("8287087098");
		a3.setMatricula("890642");

		repositorio.save(a1);

		repositorio.save(a2);

		repositorio.save(a3);

		restTemplate = new RestTemplate();

	}

	@Test
	public void testdeveFuncionarAListagemDeTodosOsAlunos()
			throws JsonParseException, JsonMappingException, IOException {

		String resposta = restTemplate.getForObject(BASE_PATH + "/listar", String.class);

		List<Aluno> alunos = MAPPER.readValue(resposta,
				MAPPER.getTypeFactory().constructCollectionLikeType(List.class, Aluno.class));

		int tamanhoDaListaDeAlunosesperado = 3;
		assertEquals(tamanhoDaListaDeAlunosesperado, alunos.size());
	}
	
	@Test
	public void deveFuncionarACriacaoDeUmNovoAluno() throws JsonParseException, JsonMappingException, IOException {

		Endereco e = new Endereco();
		e.setNome("rua do sol");
		e.setNumero(80);
		e.setBairro("centro");
		e.setCidade("Rio Largo");
		e.setEstado("AL");
		e.setCep("57100-522");

		Aluno aluno = new Aluno();
		aluno.setNome("juana");
		aluno.setMatricula("458719");
		aluno.setEndereco(e);
		aluno.getTelefones().add("82988567840");
		aluno.getTelefones().add("82994254409");

		restTemplate.postForObject(BASE_PATH + "/salvar", aluno, Aluno.class);

		String resposta = restTemplate.getForObject(BASE_PATH + "/listar", String.class);

		List<Aluno> alunos = MAPPER.readValue(resposta,
				MAPPER.getTypeFactory().constructCollectionLikeType(List.class, Aluno.class));

		assertEquals("juana", alunos.get(0).getNome());

	}
}
