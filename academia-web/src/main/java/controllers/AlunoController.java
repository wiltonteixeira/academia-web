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
import com.examplebr.edu.ifal.academia.academiatiweb.modelo.Aluno;
import repositories.AlunoRepository;

<<<<<<< HEAD
@Controller //Define que minha classe será um controller
@RequestMapping("/aluno")
=======
@Controller
@RequestMapping("aluno")
>>>>>>> 7d089b13ae2ba13756569ebbb8af6672fd2e21d8
public class AlunoController {
	
	@Autowired
	AlunoRepository alunoRepository;
	
	@RequestMapping(value= "list", method=RequestMethod.GET)
	public String listAluno(ModelMap model) {
		List<Aluno> alunos = alunoRepository.findAll();
		
		model.addAttribute("alunosList", alunos);
		
		model.addAttribute("massage", "Lista de alunos");
		System.out.println("list");
		
		return "alunolist";

	}
	@RequestMapping (value= {"/new"}, method = RequestMethod.GET)
	public String newAluno(ModelMap model) {
		
		Aluno aluno = new Aluno();
		model.addAttribute("aluno", aluno);
		model.addAttribute("edit", false);
		
		return "aluno/form";	
	}
	
	@RequestMapping (value= {"/edit-{id}-aluno"}, method = RequestMethod.GET)
	public String editAluno(@PathVariable("id") Integer id, ModelMap model) {	
		Aluno aluno = alunoRepository.getOne(id);
		model.addAttribute("aluno", aluno);
		model.addAttribute("edit", true);
		return "aluno/form";
	}
	
	@RequestMapping (value= {"/edit-{id}-aluno"}, method = RequestMethod.POST)
	public String updateAluno(@Valid Aluno aluno, BindingResult result, ModelMap model) {	
		if(result.hasErrors()) {
			return "aluno/form";
		}
		
		alunoRepository.saveAndFlush(aluno);
		
	
		model.addAttribute("mensagem", "Aluno" + aluno.getNome() + "atualizado com sucesso");

		return "redirect:/aluno/list";
 }
	@RequestMapping (value= {"/delete-{id}-aluno"}, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable Integer id) {	
		alunoRepository.deleteById(id);
		return "redirect:/aluno/list";
	}	
}
