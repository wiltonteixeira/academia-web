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
import com.examplebr.edu.ifal.academia.academiatiweb.modelo.Professor;
import repositories.ProfessorRepository;

@Controller
@RequestMapping("professor")
public class ProfessorController {
	
	@Autowired
	ProfessorRepository professorRepository;
	
	@RequestMapping(value= "/list", method=RequestMethod.GET)
	public String listProfessor(ModelMap model) {
		List<Professor> professores = professorRepository.findAll();
		
		model.addAttribute("professoresList", professores);
		
		model.addAttribute("massage", "Lista de professores");
		System.out.println("list");
		
		return "professorlist";

	}
	@RequestMapping (value= {"/new"}, method = RequestMethod.GET)
	public String newProfessor(ModelMap model) {
		
		Professor professor = new Professor();
		model.addAttribute("professor", professor);
		model.addAttribute("edit", false);
		
		return "professor/form";
		
	}
	@RequestMapping (value= {"/edit-{id}-professor"}, method = RequestMethod.GET)
	public String editProfessor(@PathVariable("id") Integer id, ModelMap model) {	
		Professor professor = professorRepository.getOne(id);
		model.addAttribute("professor", professor);
		model.addAttribute("edit", true);
		return "professor/form";

	}
	@RequestMapping (value= {"/edit-{id}-professor"}, method = RequestMethod.POST)
	public String updateProfessor(@Valid Professor professor, BindingResult result, ModelMap model) {	
		if(result.hasErrors()) {
			return "professor/form";
		}
		
		professorRepository.saveAndFlush(professor);
		
		model.addAttribute("mensagem", "professor" + professor.getNome() + "atualizado com sucesso");

		return "redirect:/professor/list";
	}
	
	@RequestMapping (value= {"/delete-{id}-aluno"}, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable Integer id) {	
		professorRepository.deleteById(id);
		return "redirect:/professor/list";
	}	
}
