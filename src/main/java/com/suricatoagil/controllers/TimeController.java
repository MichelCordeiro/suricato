package com.suricatoagil.controllers;

import java.security.Principal;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suricatoagil.daos.TimeDao;
import com.suricatoagil.daos.UsuarioDao;
import com.suricatoagil.models.Time;
import com.suricatoagil.models.Usuario;

@Controller
@RequestMapping("/time")
@Transactional
public class TimeController {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private TimeDao timeDao;
	
	@RequestMapping("/novo")
	public String novo(Model model, Principal principal) {
		model.addAttribute("usuario", usuarioDao.buscaPorNome(principal.getName()));
		return "time/novo";
	}
	
	@RequestMapping("/criar")
	public String cadastrar(@Valid @ModelAttribute("time") Time time, Model model, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("usuario", time.getIntegrantes().iterator().next());
			return "time/novo"; 
		}
		timeDao.save(time);
		return "redirect:/index";
	}

	@RequestMapping("/mostra")
	public String mostra(@ModelAttribute("time") Time time, Model model, Principal principal) {
		model.addAttribute("usuario", usuarioDao.buscaPorNome(principal.getName()));
		model.addAttribute("time", timeDao.load(time.getId()));
		return "time/mostra";
	}

	@RequestMapping("/adicionaUsuario")
	public String adicionaIntegrante(@ModelAttribute("usuario") Usuario usuario) {
		Usuario usuarioLoaded = usuarioDao.buscaPorNome(usuario.getNome());
		Time timeLoaded = timeDao.load(usuario.getTimes().get(0).getId());
		timeDao.adicionaUsuarioNoTime(usuarioLoaded, timeLoaded);
		return "redirect:/time/mostra?id=" + timeLoaded.getId();
	}
	
	
}
