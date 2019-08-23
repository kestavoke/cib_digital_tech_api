package za.co.absa.steps;

import cucumber.api.java.en.Given;
import net.thucydides.core.annotations.Steps;
import za.co.absa.page.DogCEOTests;

public class DogCEO_steps {

    @Steps
    DogCEOTests dogsInfo;

    @Given("^User sends a GET request to get list of all dog breeds$")
    public void getListOfAllDogBreeds() {
        dogsInfo.extractAllBreeds();
    }

    @Given("^User sends a GET request to verify retriever is within list (.*)$")
    public void getRetriverFromListOfBreeds(String value) {
        dogsInfo.verifyThatRetrieverIsWithInList(value);
    }

    @Given("^User sends a GET request to get list of sub-breeds for retriever$")
    public void getRetriverSubBreed() {
        dogsInfo.extractSubRetrieverBreed();
    }

    @Given("^User sends a GET request to random image link for the golden sub-breed$")
    public void getRandomImageLinkForGoldenSubBreed() {
        dogsInfo.extractRandomImage();
    }
}