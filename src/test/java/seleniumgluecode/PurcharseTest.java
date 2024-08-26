package seleniumgluecode;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class PurcharseTest  extends BaseTest{

    @And("the user add an {} to the marketCar")
    public void theUserAddAnToTheMarketCar(String itemMarket) throws Throwable {
        String productSelled = inventoryPage.addItemToTheCart(itemMarket);
        if(inventoryPage.isItemAddedTotheCart()){
            cartPage=inventoryPage.goToCheckoutItems();
            scenarioContext.setContext("itemToBuy",productSelled);
        }
    }

    @And("the user continues the purchase")
    public void theUserContinuesThePurcharse() throws Throwable{
        List<Boolean> validation = new ArrayList<>();
       var itemToBuy = scenarioContext.getContext("itemToBuy");
       var listItems= cartPage.checkoutItemsToBuy(itemToBuy.toString());
        for (var item:listItems){
            if(item.equals(itemToBuy)){
                validation.add(true);
            }
        }
        Assert.assertTrue("Not all of the item are present",cartPage.areAllTrue(validation));

    }

    @And("the user provides the personal information purchase")
    public void theUserProvidesThePersonalInformationPurchase(DataTable table) throws Throwable {

        checkoutPageStepOne=cartPage.clickCheckoutbutton();
        Assert.assertEquals("Checkout: Your Information",checkoutPageStepOne.validationCheckoutInformation());
        List<List<String>> rows = table.asLists(String.class);
        List<List<String>> rowsWithoutHeading = rows.subList(1,rows.size());

        for (List<String> row: rowsWithoutHeading){
            checkoutPageStepOne.setPersonalInfo(row.get(0),row.get(1),row.get(2));
        }
         checkoutPageStepOne.areCredentialsCorrect();
    }
}
