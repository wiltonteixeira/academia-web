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

import com.examplebr.edu.ifal.academia.academiatiweb.modelo.Curso;

import repositories.CursoRepository;

@Controller
@RequestMapping("/curso")
public class CursoController {

	@Autowired
	CursoRepository cursoRepository;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listCurso(ModelMap model) {
		List<Curso> cursos = cursoRepository.findAll();

		model.addAttribute("cursosList", cursos);

		model.addAttribute("message", "Lista de cursos");
		System.out.println("list");

		return "curso/List";

	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newCurso(ModelMap model) {

		Curso curso = new Curso();
		model.addAttribute("curso", curso);
		model.addAttribute("edit", false);

		return "curso/form";

	}

	@RequestMapping(value = { "/edit-{id}-curso" }, method = RequestMethod.GET)
	public String editCurso(@PathVariable("id") Integer id, ModelMap model) {
		Curso curso = cursoRepository.getOne(id);
		model.addAttribute("curso", curso);
		model.addAttribute("edit", true);
		return "curso/form";

	}

	@RequestMapping(value = { "/edit-{id}-curso" }, method = RequestMethod.POST)
	public String updateCurso(@Valid Curso curso, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "curso/form";
		}

		cursoRepository.saveAndFlush(curso);

		model.addAttribute("mensagem", "Curso" + curso.getNome() + "atualizado com sucesso");

		return "redirect:/curso/list";
	}

	@RequestMapping(value = { "/delete-{id}-curso" }, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable Integer id) {
		cursoRepository.deleteById(id);
		return "redirect:/curso/list";
	}

}
