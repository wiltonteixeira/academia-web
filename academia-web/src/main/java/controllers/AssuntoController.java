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
import com.examplebr.edu.ifal.academia.academiatiweb.modelo.Assunto;
import repositories.AssuntoRepository;

@Controller
@RequestMapping("/assunto")
public class AssuntoController {

	@Autowired
	AssuntoRepository assuntoRepository;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listAssunto(ModelMap model) {
		List<Assunto> assuntos = assuntoRepository.findAll();

		model.addAttribute("assuntosList", assuntos);

		model.addAttribute("massage", "Lista de assuntos");
		System.out.println("list");

		return "assunto/list";

	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newAssunto(ModelMap model) {

		Assunto assunto = new Assunto();
		model.addAttribute("assunto", assunto);
		model.addAttribute("edit", false);

		return "assunto/form";

	}

	@RequestMapping(value = { "/edit-{id}-assunto" }, method = RequestMethod.GET)
	public String editAssunto(@PathVariable("id") Integer id, ModelMap model) {
		Assunto assunto = assuntoRepository.getOne(id);
		model.addAttribute("assunto", assunto);
		model.addAttribute("edit", true);
		return "assunto/form";

	}

	@RequestMapping(value = { "/edit-{id}-assunto" }, method = RequestMethod.POST)
	public String updateAssunto(@Valid Assunto assunto, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "assunto/form";
		}

		assuntoRepository.saveAndFlush(assunto);

		model.addAttribute("mensagem", "Assunto" + assunto.getNome() + "atualizado com sucesso");

		return "redirect:/assunto/list";
	}

	@RequestMapping(value = { "/delete-{id}-assunto" }, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable Integer id) {
		assuntoRepository.deleteById(id);
		return "redirect:/assunto/list";
	}

}
