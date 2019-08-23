package za.co.absa.page;

import za.co.absa.config.EndPoint;
import za.co.absa.config.ReuseableSpecifications;
import net.serenitybdd.rest.SerenityRest;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DogCEOTests extends ReuseableSpecifications {

    public void extractAllBreeds() {
        SerenityRest.rest().given().
                spec(getGenericRequestSpec()).
                when().
                get(EndPoint.getAllBreedList).
                then().
                contentType(ContentType.JSON).
                log().all();
    }

    public void verifyThatRetrieverIsWithInList(String value) {
        String response = SerenityRest.rest().given().
                spec(getGenericRequestSpec()).
                when().
                get(EndPoint.getAllBreedList).jsonPath().getString(EndPoint.verifyRetrieverWithInList);

        if (response.contains(value)) {
            System.out.println("Verify - retriever is within list");
        } else {
            System.out.println("retriever not within list");
        }
    }

    public void extractSubRetrieverBreed() {
        Response listOfBreed = SerenityRest.rest().given().
                spec(getGenericRequestSpec()).
                when().
                get(EndPoint.getAllBreedList).
                then().
                contentType(ContentType.JSON).
                extract().response();

        List<String> subBreed = listOfBreed.path(EndPoint.getGetRetrieverWithInList);

        for (String breedName : subBreed) {
            System.out.println(breedName);
        }
    }

    public void extractRandomImage() {
        SerenityRest.rest().given().
                spec(getGenericRequestSpec()).
                get(EndPoint.getBreedRandomImage).then().log().all();
    }

}