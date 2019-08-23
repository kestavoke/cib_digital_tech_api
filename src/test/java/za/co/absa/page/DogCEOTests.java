package za.co.absa.page;

import za.co.absa.config.EndPoint;
import za.co.absa.config.ReuseableSpecifications;
import net.serenitybdd.rest.SerenityRest;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import java.util.List;

public class DogCEOTests extends ReuseableSpecifications {

    public void extractAllBreeds() {
        SerenityRest.rest().given().
                spec(getGenericRequestSpec()).
                when().
                get(EndPoint.getAllBreedList).
                then().log().all();
    }

    public void verifyThatRetrieverIsWithInList() {
        Response response = SerenityRest.rest().given().
                spec(getGenericRequestSpec()).
                when().
                get(EndPoint.getAllBreedList).
                then().
                contentType(ContentType.JSON).
                extract().response();

        // List<String> subBreed = response.path(EndPoint.getGetRetrieverWithInList);

        if (response.path(EndPoint.getGetRetrieverWithInList).equals("message.retriever")) {
            System.out.println("Verify - retriever is within list");
        } else {
            System.out.println("Verified - retriever is NOT  within list");
        }

//        for (String breedType : subBreed){
//            if (breedType.equalsIgnoreCase("message.retriever"))
//            System.out.println("Verify - retriever is within list");
//        }
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

        for (String breedType : subBreed) {
            if (breedType != null) {
                System.out.println(breedType);
            }
        }
    }

    public void extractRandomImage() {
        SerenityRest.rest().given().
                spec(getGenericRequestSpec()).
                get(EndPoint.getBreedRandomImage).then().log().all();
    }

}