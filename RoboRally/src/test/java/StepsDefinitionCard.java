import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import dtu.roboRally.Card;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
	
public class StepsDefinitionCard {
	Card card;
	@Given("a random card")
	public void a_random_card() {
	   card =card.random();
	}
	@When("when a priority is checked")
	public void when_a_priority_is_checked() {
	   card.getPriority();
	}
	@Then("the card has a priority")
	public void the_card_has_a_priority() {
	    assertNotEquals(null, card.getPriority());
	}
}
