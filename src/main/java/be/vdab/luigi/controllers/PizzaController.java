package be.vdab.luigi.controllers;

import be.vdab.luigi.exceptions.KoersClientException;
import be.vdab.luigi.services.EuroService;
import be.vdab.luigi.services.PizzaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
@RequestMapping("pizzas")
public class PizzaController {
    /*    private final Pizza[] allePizzas = {
                new Pizza(1, "Prosciutto", BigDecimal.valueOf(4), true),
                new Pizza(2, "Margherita", BigDecimal.valueOf(5), false),
                new Pizza(3, "Calzone", BigDecimal.valueOf(4), false)
        };*/
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final EuroService euroService;
    private final PizzaService pizzaService;

    public PizzaController(EuroService euroService, PizzaService pizzaService) {
        this.euroService = euroService;
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ModelAndView findAll() {
        //return new ModelAndView("pizzas", "pizzas", allePizzas);
        return new ModelAndView("pizzas", "allePizzas",
                pizzaService.findAll());
    }

/*    private Optional<Pizza> findByIdHelper(long id) {
        return Arrays.stream(allePizzas).filter(pizza -> pizza.getId() == id).findFirst();
    }*/

    @GetMapping("{id}")
    public ModelAndView findById(@PathVariable long id) {
        var modelAndView = new ModelAndView("pizza");
/*        findByIdHelper(id)
                .ifPresent(gevondenPizza -> {
                    modelAndView.addObject("pizza", gevondenPizza);
                    try {
                        modelAndView.addObject(
                                "inDollar", euroService.naarDollar(gevondenPizza.getPrijs())
                        );
                    } catch (KoersClientException ex) {
                        logger.error("Kan dollar koers niet lezen", ex);
                    }
                });*/
        pizzaService.findById(id).ifPresent(pizza -> { // service oproepen
            modelAndView.addObject("pizza", pizza);
            try {
                modelAndView.addObject("inDollar", euroService.naarDollar(pizza.getPrijs()));
            } catch (KoersClientException ex) {
                logger.error("Kan dollar koers niet lezen", ex);
            }
        });
        return modelAndView;
    }

/*    private Stream<BigDecimal> findPrijzenHelper() {
        return Arrays.stream(allePizzas).map(Pizza::getPrijs).distinct().sorted();
    }*/

    @GetMapping("prijzen")
    public ModelAndView findPrijzen() {
/*        return new ModelAndView("pizzasperprijs",
                "prijzen", findPrijzenHelper().iterator());*/
        return new ModelAndView("pizzasperprijs", "prijzen",
                pizzaService.findUniekePrijzen());
    }

/*    private Stream<Pizza> findByPrijsHelper(BigDecimal prijs) {
        return Arrays.stream(allePizzas)
                .filter(pizza -> pizza.getPrijs().compareTo(prijs) == 0);
    }*/

    @GetMapping("prijzen/{prijs}")
    public ModelAndView findByPrijs(@PathVariable BigDecimal prijs) {
/*        return new ModelAndView("pizzasperprijs", "pizzas", findByPrijsHelper(prijs).iterator())
                .addObject("prijzen", findPrijzenHelper().iterator());*/
        return new ModelAndView("pizzasperprijs", "pizzas",
                pizzaService.findByPrijs(prijs))
                .addObject("prijzen", pizzaService.findUniekePrijzen());
    }
}
