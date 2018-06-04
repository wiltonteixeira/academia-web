package resources;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.examplebr.edu.ifal.academia.academiatiweb.modelo.Assunto;
import repositories.AssuntoRepository;

@RestController
@RequestMapping("/api/assunto")
public class AssuntoResource {
	
	@Autowired
	 AssuntoRepository assuntoRepository;
	
	@RequestMapping(value= "/salvar", method= RequestMethod.POST)
	 public Assunto salvar(@RequestBody Assunto assunto) { 
		assuntoRepository.save(assunto);
		 return assunto;
	 }
	 
	 @RequestMapping(value= "/carregar", method= RequestMethod.GET)
	 public String carregar() {
		 Assunto e = new Assunto("corpo humano");
		 
		 assuntoRepository.save(e);
		 return "ok";	 
	 }
	 
	@RequestMapping(value= "{id}", method=RequestMethod.GET)
	public Assunto buscar(@PathVariable("id") Integer id) {
		return assuntoRepository.getOne(id);
		
	}

	@RequestMapping(value= "/listar", method=RequestMethod.GET)
	public List<Assunto> listar() {
		return assuntoRepository.findAll();
	}
	
	@RequestMapping(value= "/pesquisar", method=RequestMethod.GET)
	public List<Assunto> pesquisar() {
	return assuntoRepository.findAll();
	
	}
}


