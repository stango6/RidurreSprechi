package com.generation.wasteless.controller;

import com.generation.wasteless.entities.Prodotto;
import com.generation.wasteless.entities.Utente;
import com.generation.wasteless.service.ServiceProdotto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@Scope("session")
public class ProdottiController {

    @Autowired
    private ServiceProdotto serviceProdotto;

    @GetMapping("/dispensa")
    public String dispensa(Model model, HttpSession session) {

        Utente utente = (Utente) session.getAttribute("utente");
        if (utente == null)
            return "login.html";
        String ruolo = utente.getRuolo();
        model.addAttribute("ruolo", ruolo);
        serviceProdotto.setUtente(utente);
        model.addAttribute("listaProdotti", serviceProdotto.tuttiProdotti());
        model.addAttribute("nSprecati", serviceProdotto.nSprecati());
        model.addAttribute("nEdibili", serviceProdotto.nEdibili());
        return "dispensa.html";
    }

    @GetMapping("/modProdotto")
    public String updateProdotto(@RequestParam Map<String, String> params, HttpSession session) {
        Utente utente = (Utente) session.getAttribute("utente");
        int userId = utente.getId(); // Ottieni l'ID dell'utente
        System.out.println(params);
        serviceProdotto.updateProdotto(params, userId);
        return "redirect:/dispensa";
    }

    @GetMapping("/delProdotto")
    public String deleteProdotto(@RequestParam("id") int id) {
        serviceProdotto.deleteProdotto(id);
        return "redirect:/dispensa";
    }

    @GetMapping(path="/addProdotto")
    public String addProdotto(@RequestParam Map<String, String> params, HttpSession session) {
        Utente utente = (Utente) session.getAttribute("utente");
        int userId = utente.getId(); // Ottieni l'ID dell'utente
        System.out.println(userId);
        System.out.println(params);
        serviceProdotto.addProdotto(params, userId);
        return "redirect:/dispensa";
    }

    @GetMapping(path="/sellProdotto")
    public String aggiornaQuantita(@RequestParam("id") int id,
                                   @RequestParam ("quantita") int q) {
        serviceProdotto.aggiornaQuantita(id,q);
        return  "redirect:/dispensa";
    }


}