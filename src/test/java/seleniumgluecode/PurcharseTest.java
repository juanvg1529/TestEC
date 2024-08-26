package seleniumgluecode;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        checkoutPageStepTwo=checkoutPageStepOne.clickCheckoutPage();
    }

    @And("the user checkout the overview Info of the order")
    public void theUserCheckoutTheOverviewInfoOfTheOrder(DataTable table) throws Throwable {

        List<Map<String,String>> rows = table.asMaps(String.class,String.class);

        Assert.assertEquals("Checkout: Overview",checkoutPageStepTwo.validationCheckoutInformation());
       var listPayement= checkoutPageStepTwo.checkoutPaymentInfo();
        Assert.assertNotNull(listPayement);
        String cardInfo = listPayement.stream().flatMap(List::stream).filter(x->x.contains("Card")).findAny().orElseThrow(() ->new RuntimeException("Not card info found"));
        String deliveryInfo= listPayement.stream().flatMap(List::stream).filter(x->x.contains("Pony")).findAny().orElseThrow(() ->new RuntimeException("Not card info found"));
        rows.forEach(row->{
            String expectInfoCard= row.get("CardInfo");
            String expectDeliveryinfo = row.get("DeliveryInfo");
            Assert.assertTrue(cardInfo.contains(expectInfoCard));
            Assert.assertTrue(deliveryInfo.contains(expectDeliveryinfo));
        });

    }

    @Then("the user completes the order")
    public void theUserCompletesTheOrder() throws Throwable{
        checkoutPageStepTwo.clickFinishButton();
       Assert.assertEquals("Thank you for your order!",checkoutCompletePage.getCompleteOrderText());
        var noElementsAdded =checkoutCompletePage.elementsNotAddedToCart();
        Assert.assertTrue("there is still elements added to the cart",noElementsAdded);
        var loginPage=  checkoutCompletePage.clickBackHomeButton();
        Assert.assertTrue( "Driver not at he Inventory Page",loginPage.inventoryDisplayed());
        loginPage.printConsole("PURCHASE COMPLETED");
    }
}
