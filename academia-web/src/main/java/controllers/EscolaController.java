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
import com.examplebr.edu.ifal.academia.academiatiweb.modelo.Escola;

import repositories.EscolaRepository;

@Controller
@RequestMapping("/escola")
public class EscolaController {
	
	@Autowired
	EscolaRepository escolaRepository;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listEscola(ModelMap model) {
		List<Escola> escolas = escolaRepository.findAll();

		model.addAttribute("escolasList", escolas);

		model.addAttribute("message", "Lista de Escolas");
		System.out.println("list");

		return "escola/list";

	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newEscola(ModelMap model) {

		Escola escola = new Escola();
		model.addAttribute("escola", escola);
		model.addAttribute("edit", false);

		return "escola/form";

	}

	@RequestMapping(value = { "/edit-{id}-escola" }, method = RequestMethod.GET)
	public String editEscola(@PathVariable("id") Integer id, ModelMap model) {
		Escola escola = escolaRepository.getOne(id);
		model.addAttribute("escola", escola);
		model.addAttribute("edit", true);
		return "escola/form";

	}

	@RequestMapping(value = { "/edit-{id}-escola" }, method = RequestMethod.POST)
	public String updateAluno(@Valid Escola escola, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "escola/form";
		}

		escolaRepository.saveAndFlush(escola);

		model.addAttribute("mensagem", "Escola" + escola.getNome() + "atualizado com sucesso");

		return "redirect:/escola/list";
	}

	@RequestMapping(value = { "/delete-{id}-escola" }, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable Integer id) {
		escolaRepository.deleteById(id);
		return "redirect:/escola/list";
	}
}
