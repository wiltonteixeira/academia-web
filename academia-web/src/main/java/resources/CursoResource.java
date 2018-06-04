package resources;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.examplebr.edu.ifal.academia.academiatiweb.modelo.Curso;
import repositories.CursoRepository;

@RestController
@RequestMapping("/api/curso")
public class CursoResource {
	
	@Autowired
	CursoRepository cursorepository;
	
	@RequestMapping(value= "/salvar", method= RequestMethod.POST)
	 public Curso salvar(@RequestBody Curso curso) { 
		cursorepository.save(curso);
		return curso;
	 }
	
	@RequestMapping(value= "/carregar", method= RequestMethod.GET)
	 public String carregar() {
		 Curso i = new Curso("informatica");	 
		 cursorepository.save(i);
		 return "ok";
		 
	 }
	@RequestMapping(value= "{id}", method=RequestMethod.GET)
	public Curso buscar(@PathVariable("id") Integer id) {
		return cursorepository.getOne(id);
		
	}
	
	@RequestMapping(value= "/listar", method=RequestMethod.GET)
	public List<Curso> listar() {
		return cursorepository.findAll();
	}
	
	@RequestMapping(value= "/pesquisar", method=RequestMethod.GET)
	public List<Curso> pesquisar() {
	return cursorepository.findAll();
	
	}
}