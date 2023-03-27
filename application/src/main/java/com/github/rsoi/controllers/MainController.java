package com.github.rsoi.controllers;
import com.github.rsoi.domain.TelephoneInfo;
import com.github.rsoi.repository.RepositoryFunctional;
import com.github.rsoi.repository.TelephoneRepository;
import com.github.rsoi.service.UserPhoneComparison;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final RepositoryFunctional repositoryFunctional;
    private final UserPhoneComparison userPhoneComparison;
    private final TelephoneRepository telephoneRepository;
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("title", "Home page");
        return "homePage";
    }

    @GetMapping("/telephonesPage")
    public String phones(Model model, @RequestParam(name = "method", required = false) String method, @RequestParam(name = "id", required = false) Long id) {

       if (method != null && id != null && method.equals("delete")) {
            repositoryFunctional.deleteTelephone(id);
        }
        model.addAttribute("telephonesPage", repositoryFunctional.showTelephones());
        return "telephonesPage";
    }

    @PostMapping("/telephones/addTelephone")
    public String phoneAdd( @RequestParam("name") String name,
                            @RequestParam("ram") int ram,
                            @RequestParam("size") double size,
                            @RequestParam("sdCard") String sdCard,
                            @RequestParam("price") int price
    ) {
        boolean SDCard=false;
        if (sdCard.equals("да")) {
            SDCard = true;
        }
        repositoryFunctional.savePhone(new TelephoneInfo(name,price,size,ram,SDCard));
        return "redirect:/telephonesPage";
    }

    @GetMapping("/telephonesPage/{id}/edit")
    public String phoneEdit(Model model, @PathVariable(value = "id") long id) {
        if(!repositoryFunctional.existsById(id))
        {
            return "redirect:/telephonesPage";
        }
        Optional<TelephoneInfo> telephones = Optional.ofNullable(repositoryFunctional.findById(id));
        ArrayList<TelephoneInfo> res=new ArrayList<>();
        telephones.ifPresent(res::add);
        model.addAttribute("telephones", res);
        return "telephoneEditor";
    }
    @PostMapping("/telephonesPage/{id}/edit")
    public String phoneUpdate( @PathVariable(value = "id") long id,
                               @RequestParam("name") String name,
                               @RequestParam("ram") int ram,
                               @RequestParam("size") double size,
                               @RequestParam("sdCard") String sdCard,
                               @RequestParam("price") int minPrice
    ) {
        boolean userSD = false;
        if (sdCard.equals("да")) {
            userSD = true;
        }

        TelephoneInfo phone = repositoryFunctional.findById(id);
        phone.setNameOfTheTelephone(name);
        phone.setAmountOfRAM(ram);
        phone.setSizeOfTheScreen(size);
        phone.setSdCardIsAvailable(userSD);
        phone.setPriceOfTheTelephone(minPrice);

        repositoryFunctional.savePhone(phone);
        return "redirect:/telephonesPage";
    }

    @GetMapping("/searchPage")
    public String request() {
        return "searchPage";
    }

    @PostMapping("/searchPage/searchPhone")
    public String phoneSearch(  @RequestParam("ram") int ram,
                                @RequestParam("size") double size,
                                @RequestParam("sdCard") String sdCard,
                                @RequestParam("minPrice") int minPrice,
                                @RequestParam("maxPrice") int maxPrice, Model model) {
        String result;
        ArrayList<TelephoneInfo> telephones = new ArrayList<>();
        for (TelephoneInfo telephoneInfo : telephoneRepository.findAll()) {
            if (telephoneInfo.getPriceOfTheTelephone() > minPrice & telephoneInfo.getPriceOfTheTelephone() < maxPrice) {
                telephoneInfo.setMatchCounter(telephoneInfo.getMatchCounter() + 1);
            }
            if (telephoneInfo.getSizeOfTheScreen().equals(size)) {
                telephoneInfo.setMatchCounter(telephoneInfo.getMatchCounter() + 1);
            }
            if (telephoneInfo.getAmountOfRAM() == ram) {
                telephoneInfo.setMatchCounter(telephoneInfo.getMatchCounter() + 1);
            }
            
            boolean userSD = false;
            if (sdCard.equals("да")) {
                userSD = true;
            }
            if (telephoneInfo.getSdCardIsAvailable() == userSD) {
                telephoneInfo.setMatchCounter(telephoneInfo.getMatchCounter() + 1);
            }
        }
        
        int maximum = telephoneRepository.findAll().get(0).getMatchCounter();

        for (TelephoneInfo telephoneInfo : telephoneRepository.findAll()) {
            if (maximum < telephoneInfo.getMatchCounter())
                maximum = telephoneInfo.getMatchCounter();
        }
        
        for (TelephoneInfo telephoneInfo : telephoneRepository.findAll()) {
            if (telephoneInfo.getMatchCounter() == maximum) {
                telephones.add(telephoneInfo);
            }
        }
        
        if(telephones.isEmpty()){
            result="Ничего не найдено";
        }
        else {
            result="Найдено";
        }
        model.addAttribute("result", result);
        model.addAttribute("telephones", telephones);
        return "searchPage";
    }
}