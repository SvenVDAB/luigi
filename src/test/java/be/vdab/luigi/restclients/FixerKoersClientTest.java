package be.vdab.luigi.restclients;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@PropertySource("application.properties")
@Import(FixerKoersClient.class)
public class FixerKoersClientTest {
    private final KoersClient client;

    public FixerKoersClientTest(KoersClient client) {
        this.client = client;
    }

/*    @BeforeEach
    void beforeEach() {
        client = new FixerKoersClient();
    }*/

    @Test
    void deKoersIsPositief() {
        assertThat(client.getDollarKoers()).isPositive();
    }
}
