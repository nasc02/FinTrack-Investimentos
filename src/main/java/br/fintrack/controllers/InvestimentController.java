package br.fintrack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fintrack.models.Investiment;
import br.fintrack.repositories.InvestimentRepository;

@RestController
@RequestMapping("/api/investiments")
public class InvestimentController {

    @Autowired
    private InvestimentRepository investimentRepository;

    @GetMapping
    public List<Investiment> getAllInvestiments() {
        return (List<Investiment>) investimentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Investiment getInvestimentById(@PathVariable Short id) {
        return investimentRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public Investiment createInvestiment(@RequestBody Investiment investiment) {
        return investimentRepository.save(investiment);
    }

    @PutMapping("/{id}")
    public Investiment updateInvestiment(@PathVariable Short id, @RequestBody Investiment investiment) {
        investiment.setId(id);
        return investimentRepository.save(investiment);
    }

    @DeleteMapping("/{id}")
    public void deleteInvestiment(@PathVariable Short id) {
        investimentRepository.deleteById(id);
    }
}
