package be.vdab.luigi.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class Pizza {
    private final long id;
    private final String naam;
    private final BigDecimal prijs;
    private final boolean pikant;

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public boolean isPikant() {
        return pikant;
    }

    public Pizza(long id, @NotBlank String naam, @NotNull @PositiveOrZero BigDecimal prijs, boolean pikant) {
        this.id = id;
        this.naam = naam;
        this.prijs = prijs;
        this.pikant = pikant;
    }
}
