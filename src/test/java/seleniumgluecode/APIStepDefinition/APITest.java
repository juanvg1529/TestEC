package seleniumgluecode.APIStepDefinition;

import APIresources.APIRepositories;
import APIresources.Models.Category;
import APIresources.Models.Pet;
import APIresources.Models.Tag;
import freemarker.ext.jsp.TaglibFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import seleniumgluecode.Common.BaseTest;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APITest extends BaseTest {


    @Given("the user Get all of the Pets {string}")
    public void theUserGetAllOfThePets(String responseExpected) {

        Response response = given().queryParam("status","available").when().get(apiRepositories.getPetStatus());
        Assert.assertEquals(Integer.parseInt( responseExpected),response.getStatusCode());
        List<Pet> pets = response.jsonPath().getList("", Pet.class);
        Assert.assertEquals(true, !pets.isEmpty());
        printConsole("USER GETS ALL OF THE PETS");


    }

    @When("The user add a new Pet")
    public void theUserAddNewPet(DataTable table) {

        List<Map<String,String>> rows = table.asMaps(String.class,String.class);
        rows.forEach(row->{
            var getID= row.get("ID");
            var getName = row.get("Name");
            var getTag = row.get("Tag");
            Pet newPet = apiRepositories.createNewPet(getID,getName,"InitialDoggo");

            Response response = given().contentType("application/json").body(newPet).when().post();
            Assert.assertEquals(200, response.getStatusCode());

            Pet createdPet = response.as(Pet.class);
            Assert.assertEquals(newPet.getId(), createdPet.getId());
            Assert.assertEquals(newPet.getName(), createdPet.getName());
            Assert.assertEquals(newPet.getStatus(), createdPet.getStatus());
            Assert.assertEquals(newPet.getTags()[0].getName(),createdPet.getTags()[0].getName());
            scenarioContext.setContext("petID",createdPet.getId());
            scenarioContext.setContext("petName",createdPet.getName());
            scenarioContext.setContext("tagPet",createdPet.getTags()[0].getName());
            printConsole("USER CREATES A NEW PET "+"|"+createdPet.getName()+"|"+createdPet.getTags()[0].getName()+"|");
        });


    }

    @When("the user consults the pet Created {string}" )
    public void theUseConsultsThePetCreated(String responseExpected) {

        var petID= scenarioContext.getContext("petID").toString();
        var petName= scenarioContext.getContext("petName");
        var getTag = scenarioContext.getContext("tagPet").toString();

        Response response = given().queryParam("status","available").when().get(petID);
        Assert.assertEquals(Integer.parseInt( responseExpected),response.getStatusCode());
        Pet petObtained = response.jsonPath().getObject("", Pet.class);
        Assert.assertNotNull("Pet is null",petObtained);
        Assert.assertEquals(petName,petObtained.getName());
        Assert.assertEquals(petID,petObtained.getId().toString());
        Assert.assertEquals(getTag,petObtained.getTags()[0].getName());
        printConsole("USER GETS THE NEW PET "+"|"+petObtained.getName()+"|"+petObtained.getTags()[0].getName()+"|");
    }

    @When("the user updates an existing pet")
    public void theUserUpdatesAnExistingPet(DataTable table) {
        List<Map<String,String>> rows = table.asMaps(String.class,String.class);
        var petID= scenarioContext.getContext("petID").toString();
        rows.forEach(row->{
            String getName = row.get("Name");
            var updatedPet= apiRepositories.createNewPet(petID,getName,"DoggoUpdateTag");
            Response response = given()
                    .contentType("application/json")
                    .body(updatedPet)
                    .when()
                    .put();
            // Valida el código de estado
            Assert.assertEquals(200, response.getStatusCode());

            // Valida que el pet se ha actualizado correctamente
            Pet petAfterUpdate = response.as(Pet.class);
            Assert.assertEquals(updatedPet.getId(), petAfterUpdate.getId());
            Assert.assertEquals(updatedPet.getName(), petAfterUpdate.getName());
            Assert.assertEquals(updatedPet.getStatus(), petAfterUpdate.getStatus());
            Assert.assertEquals(updatedPet.getTags()[0].getName(),petAfterUpdate.getTags()[0].getName());
            printConsole("USER UPDATES A NEW PET "+"|"+updatedPet.getName()+"|"+updatedPet.getTags()[0].getName()+"|");
        });

    }

    @When("the deletes consults the pet Created")
    public void theDeletesConsultsThePetCreated() {
        var petID= scenarioContext.getContext("petID").toString();
        Response response = given().header("api_key",apiRepositories.getApiKey()).when().delete(petID);
        Assert.assertEquals(200, response.getStatusCode());
        printConsole("USER DELETES THE PET ");
    }

    @Then("then the user consults the pet Created")
    public void thenTheUserConsultsThePetCreated() {
        var petID= scenarioContext.getContext("petID").toString();
        String valueException = "not exception";
        Response response = given().queryParam("status","available").when().get(apiRepositories.getPetStatus());
        Assert.assertEquals(200,response.getStatusCode());
        List<Pet> pets = response.jsonPath().getList("", Pet.class);
        try {
        var petDeleted=  pets.stream().filter(x->x.getId().toString().contains(petID)).findAny().orElseThrow(()->new RuntimeException("Pet not in the List"));
        }catch (Exception ex){
             valueException = ex.getMessage();
        }

        Assert.assertEquals("Pet not in the List",valueException);

        printConsole("THE PET WITH THE ID =>"+petID+" IS NOT LONGER AT THE PET'S LIST");

    }




}
