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
import com.examplebr.edu.ifal.academia.academiatiweb.modelo.Nota;

import repositories.NotaRepository;

@Controller
@RequestMapping("nota")
public class NotaController {
	
	@Autowired
	NotaRepository notaRepository;
	
	@RequestMapping(value= "/list", method=RequestMethod.GET)
	public String listNota(ModelMap model) {
		List<Nota> notas = notaRepository.findAll();
		
		model.addAttribute("notasList", notas);
		
		model.addAttribute("massage", "Lista de notas");
		System.out.println("list");
		
		return "notaslist";

	}
	@RequestMapping (value= {"new"}, method = RequestMethod.GET)
	public String newNota(ModelMap model) {
		
		Nota nota = new Nota(0);
		model.addAttribute("nota", nota);
		model.addAttribute("edit", false);
		
		return "notaform";
		
	}
	@RequestMapping (value= {"edit-{id}-nota"}, method = RequestMethod.GET)
	public String editNota(@PathVariable("id") Integer id, ModelMap model) {	
		Nota nota = notaRepository.getOne(id);
		model.addAttribute("nota", nota);
		model.addAttribute("edit", true);
		return "notaform";

	}
	@RequestMapping (value= {"/edit-{id}-nota"}, method = RequestMethod.POST)
	public String updateNota(@Valid Nota nota, BindingResult result, ModelMap model) {	
		if(result.hasErrors()) {
			return "notaform";
		}
		notaRepository.saveAndFlush(nota);
		
		
		model.addAttribute("mensagem", "Nota" + nota.getValor() + "atualizado com sucesso");

		return "redirect:/nota/list";
 }
	@RequestMapping (value= {"/delete-{id}-nota"}, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable Integer id) {	
		notaRepository.deleteById(id);
		return "redirect:notalist";
	}	
}

