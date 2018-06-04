package resources;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.examplebr.edu.ifal.academia.academiatiweb.modelo.Aluno;
import repositories.AlunoRepository;

@RestController
@RequestMapping("/api/aluno")
public class AlunoResource {

	@Autowired
	AlunoRepository alunoRepository;
	
	@RequestMapping(value= "/salvar", method= RequestMethod.POST)
	 public Aluno salvar(@RequestBody Aluno aluno) { 
		 alunoRepository.save(aluno);
		 return aluno;
	 }
		
	@RequestMapping(value = "/carregar", method = RequestMethod.GET)
	public String carregar() {
		Aluno a = new Aluno();
		a.setNome("Theo");
		alunoRepository.save(a);
		return "ok";
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Aluno buscar(@PathVariable("id") Integer id) {
		return alunoRepository.getOne(id);

	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public List<Aluno> listar() {
		return alunoRepository.findAll();
	}

	@RequestMapping(value = "/pesquisar", method = RequestMethod.GET)
	public List<Aluno> pesquisar() {
		return alunoRepository.findAll();

	}

}
