package com.generation.wasteless.controller;

import com.generation.wasteless.database.DaoUtente;
import com.generation.wasteless.entities.Utente;
import com.generation.wasteless.service.ServiceUtente;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private DaoUtente du;

    @Autowired
    ApplicationContext context;

    @Autowired
    private ServiceUtente serviceUtente;

    @GetMapping("/formregistrazione")
    public String formRegistrazione(Model model) {
        Utente utente = context.getBean("utenteVuoto", Utente.class);
        model.addAttribute("utente", utente);
        return "registrazione.html";
    }

    @GetMapping("/notizie")
    public String notizie(Model model) {
        Utente utente = context.getBean("utenteVuoto", Utente.class);
        model.addAttribute("utente", utente);
        return "notizie.html";
    }


    /*Nel metodo registerUser, il parametro @ModelAttribute("registrazioneUtente")
     svolge la funzione di legare i dati inviati dal form di registrazione all'oggetto utente */
    @PostMapping("/register")
    public String registerUser(@ModelAttribute Utente utente,
                               @RequestParam Map<String, String> params,
                               HttpSession session, Model model) {
        Utente u = context.getBean("utenteVuoto", Utente.class);
        u.fromMap(params);
        if (serviceUtente.registrazione(u)) {
            session.setAttribute("loggato","true");
            session.setAttribute("utente", serviceUtente.utente);

            String ris = "redirect:formlogin";
            return ris;
        }
        else {
            model.addAttribute("error", "Email giÃ  in uso");
            return "registrazione.html";
        }
    }






}