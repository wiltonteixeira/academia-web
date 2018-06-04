package resources;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.examplebr.edu.ifal.academia.academiatiweb.modelo.Escola;
import repositories.EscolaRepository;

@RestController
@RequestMapping("/api/escola")
public class EscolaResource {
	
	@Autowired
	 EscolaRepository escolaRepository;
	
	@RequestMapping(value= "/salvar", method= RequestMethod.POST)
	 public Escola salvar(@RequestBody Escola escola) { 
		 escolaRepository.save(escola);
		 return escola;
	 }
	 
	 @RequestMapping(value= "/carregar", method= RequestMethod.GET)
	 public String carregar() {
		 Escola e = new Escola("judth paiva");
		 escolaRepository.save(e);
		 return "ok";	 
	 }
	 
	@RequestMapping(value= "{id}", method=RequestMethod.GET)
	public Escola buscar(@PathVariable("id") Integer id) {
		return escolaRepository.getOne(id);	
	}
	
	@RequestMapping(value= "/listar", method=RequestMethod.GET)
	public List<Escola> listar() {
		return escolaRepository.findAll();
	}
	
	@RequestMapping(value= "/pesquisar", method=RequestMethod.GET)
	public List<Escola> pesquisar() {
	return escolaRepository.findAll();
	
	}
}
