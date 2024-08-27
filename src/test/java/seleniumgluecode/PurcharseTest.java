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
import java.util.NoSuchElementException;

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
        if(itemToBuy != null) {
            var listItems = cartPage.checkoutItemsToBuy(itemToBuy.toString());
            if(listItems != null && !listItems.isEmpty()) {
                if(itemToBuy instanceof List<?>) {
                    List<?> itemsToBuyList = (List<?>) itemToBuy;
                    for (var itemInCart : listItems) {
                        boolean found = false;
                        for (var itemToCheck : itemsToBuyList) {
                            if(itemInCart.equals(itemToCheck)) {
                                validation.add(true);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            validation.add(false);
                        }
                    }
                } else {
                    for (var itemInCart : listItems) {
                        if(itemInCart.equals(itemToBuy)) {
                            validation.add(true);
                        } else {
                            validation.add(false);
                        }
                    }
                }
            } else {
                inventoryPage.printConsole("La lista de ítems está vacía o es nula.");
            }
        } else {
            inventoryPage.printConsole("El ítem a comprar es nulo.");
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

    @And("the user add Items to the marketCar")
    public void theUserAddItemsToTheMarketCar(DataTable table) throws Throwable{
        List<Map<String,String>> rows = table.asMaps(String.class,String.class);
        List <String> itemsNamesList= new ArrayList<>();
        rows.forEach(row->{
            String getItem= row.get("Item");
            itemsNamesList.add(getItem);
            try {
                inventoryPage.addItemToCart(getItem);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        if(inventoryPage.isItemAddedTotheCart()){
            cartPage=inventoryPage.goToCheckoutItems();
            scenarioContext.setContext("itemToBuy",itemsNamesList);
        }

    }
    @And("the user removes one Item")
    public void theUserRemovesOneItem(DataTable table) throws Throwable {

        var itemToBuy = scenarioContext.getContext("itemToBuy");
        List<Map<String,String>> rows = table.asMaps(String.class,String.class);
        if(itemToBuy instanceof List<?>) {
            List<String> itemList = (List<String>) itemToBuy;
            var selectedItem= itemList.stream().findAny().orElseThrow(() -> new NoSuchElementException("No item found in the list"));
            cartPage.removeItem(selectedItem);
        }
        rows.forEach(row->{
            var valueNumberOfItems= row.get("Number Of Items to Buy");
            try {
                Assert.assertTrue(cartPage.checkNumberOfItemsToBuy(valueNumberOfItems));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    @And("the user filters the WebPage")
    public void theUserFilterTheWebPage() throws Throwable{
        
    }

    @And("the user selects the item after filtering")
    public void theUserSelectsTheItemAfterFiltering() throws Throwable {
    }
}
