package bank.controller;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.Links;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
@RestController
public class HomeController {
    @GetMapping("/")
    public Links home() {
        return Links.of(
                linkTo(methodOn(CustomerController.class).getAll()).withRel("customers"),
                linkTo(methodOn(AccountController.class).getAll()).withRel("accounts"),
                linkTo(methodOn(CardController.class).getAll()).withRel("cards"));
    }
}
