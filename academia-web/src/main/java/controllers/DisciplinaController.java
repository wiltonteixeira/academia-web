package controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.examplebr.edu.ifal.academia.academiatiweb.modelo.Disciplina;
import repositories.DisciplinaRepository;

@Controller
@RequestMapping("/disciplina")
public class DisciplinaController {
	@Autowired
	DisciplinaRepository disciplinaRepository;
	
	@RequestMapping(value= "/list", method=RequestMethod.GET)
	public String listAluno(ModelMap model) {
		List<Disciplina> diciplinas = disciplinaRepository.findAll();
		
		model.addAttribute("disciplinasList", diciplinas);
		
		model.addAttribute("massage", "Lista de disciplinas");
		System.out.println("list");
		
		return "disciplina/list";

	}
	@RequestMapping (value= {"/new"}, method = RequestMethod.GET)
	public String newDisciplina(ModelMap model) {
		
		Disciplina disciplina = new Disciplina();
		model.addAttribute("disciplina", disciplina);
		model.addAttribute("edit", false);
		
		return "disciplina/form";
		
	}

	@RequestMapping (value= {"/edit-{id}-disciplina"}, method = RequestMethod.GET)
	public String editDisciplina(@PathVariable("id") Integer id, ModelMap model) {	
		Disciplina disciplina = disciplinaRepository.getOne(id);
		model.addAttribute("disciplina", disciplina);
		model.addAttribute("edit", true);
		return "disciplina/form";

	}
	@RequestMapping (value= {"/edit-{id}-disciplina"}, method = RequestMethod.POST)
	public String updateAluno(@Valid Disciplina disciplina, BindingResult result, ModelMap model) {	
		if(result.hasErrors()) {
			return "disciplina/form";
		}
		
		disciplinaRepository.saveAndFlush(disciplina);
		
		model.addAttribute("mensagem", "Disciplina" + disciplina.getNome() + "atualizado com sucesso");

		return "redirect:/disciplina/list";
	}
	@RequestMapping (value= {"/delete-{id}-disciplina"}, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable Integer id) {	
		disciplinaRepository.deleteById(id);
		return "redirect:/disciplina/list";
	}	

}
