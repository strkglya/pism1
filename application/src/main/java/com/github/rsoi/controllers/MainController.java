package com.github.rsoi.controllers;
import com.github.rsoi.domain.TelephoneInfo;
import com.github.rsoi.repository.RepositoryFunctional;
import com.github.rsoi.service.TelephoneComparator;
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
    private final TelephoneComparator telephoneComparator;
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
    public String phoneAdd( @RequestParam("newName") String newName,
                            @RequestParam("newAmountOfRAM") int newAmountOfRAM,
                            @RequestParam("newSizeOfScreen") double newSizeOfScreen,
                            @RequestParam("newSdCardAvailability") String newSdCardAvailability,
                            @RequestParam("newPrice") int newPrice
    ) {
        boolean boolSDCard= newSdCardAvailability.equals("Yes");
        repositoryFunctional.savePhone(new TelephoneInfo(newName,newPrice,newSizeOfScreen,newAmountOfRAM,boolSDCard));
        return "redirect:/telephonesPage";
    }

    @GetMapping("/telephonesPage/{id}/edit")
    public String phoneEdit(Model model, @PathVariable(value = "id") long id) {
        if (!repositoryFunctional.existsById(id)) {
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
                               @RequestParam("editedName") String editedName,
                               @RequestParam("editedRAM") int editedRAM,
                               @RequestParam("editedSizeOfScreen") double editedSizeOfScreen,
                               @RequestParam("editedSdCard") String editedSdCard,
                               @RequestParam("editedPrice") int editedPrice
    ) {
        boolean boolSD = editedSdCard.equals("Yes");

        TelephoneInfo phone = repositoryFunctional.findById(id);
        phone.setNameOfTheTelephone(editedName);
        phone.setAmountOfRAM(editedRAM);
        phone.setSizeOfTheScreen(editedSizeOfScreen);
        phone.setSdCardIsAvailable(boolSD);
        phone.setPriceOfTheTelephone(editedPrice);

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

        List<TelephoneInfo> telephones;
        telephones = telephoneComparator.searchForPhone(ram,size,sdCard,minPrice,maxPrice);

        String result;
        if(telephones.isEmpty()){
            result="No results found";
        }
        else {
            result="Take a look at the following options";
        }
        model.addAttribute("result", result);
        model.addAttribute("telephones", telephones);
        return "searchPage";
    }
}