package features;

import arquillian.AbstractSupplierRegistryTest;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.arquillian.CukeSpace;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingContactException;
import fr.unice.polytech.isa.dd.teamH.exceptions.AlreadyExistingSupplierException;
import fr.unice.polytech.isa.dd.teamH.exceptions.UnknownSupplierException;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierRegistration;

import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(CukeSpace.class)
@CucumberOptions(features = "src/test/resources/features")
@Transactional(TransactionMode.ROLLBACK)
public class SupplierRegistryStepdefsTest extends AbstractSupplierRegistryTest {
    @EJB private SupplierRegistration management;
    @EJB private SupplierFinder finder;

    private Optional<Supplier> supplierFound;
    private Exception exception = null;

    private List<Supplier> suppliersAdded = new ArrayList<>();

    @Given("^some suppliers with names (.*) contact (.*) and (.*) contact (.*)$")
    public void background(String supplier, String contact, String supplier2, String contact2) throws Exception{
        management.register(supplier, contact);
        management.register(supplier2, contact2);

        suppliersAdded.add(finder.findByName(supplier).get());
        suppliersAdded.add(finder.findByName(supplier2).get());
    }


    @When("^the gestionnaire adds the supplier with name (.*) and contact (.*)$")
    public void addSupplier(String supplier, String contact) throws Exception{
        management.register(supplier, contact);
        suppliersAdded.add(finder.findByName(supplier).get());
    }

    @When("^the gestionnaire adds the suppliers with name (.*) and contact (.*) and name (.*) and contact (.*)$")
    public void addSupplier(String supplier, String contact, String supplier2, String contact2) throws Exception{
        management.register(supplier, contact);
        management.register(supplier2, contact2);
        suppliersAdded.add(finder.findByName(supplier).get());
        suppliersAdded.add(finder.findByName(supplier2).get());
    }
    @When("^the gestionnaire deletes the supplier with name (.*)$")
    public void deleteSupplier(String supplier) throws Exception{
        management.delete(supplier);
        suppliersAdded.removeIf(s -> s.getName().equals(supplier));
    }

    @When("^the gestionnaire deletes the suppliers with names (.*) and (.*)$")
    public void deleteSuppliers(String supplier, String supplier2) throws Exception{
        management.delete(supplier);
        management.delete(supplier2);
        suppliersAdded.removeIf(s -> s.getName().equals(supplier));
        suppliersAdded.removeIf(s -> s.getName().equals(supplier2));
    }

    @When("^the gestionnaire search the supplier with name (.*)$")
    public void searchSupplier(String supplier){
        supplierFound = finder.findByName(supplier);
    }

    @When("^the gestionnaire add contact (.*) to the supplier (.*)$")
    public void addContactSupplier(String contact, String supplier) throws Exception{
        Optional<Supplier> supplierObject = finder.findByName(supplier);
        if(!supplierObject.isPresent())
            fail();

        management.addContact(supplier, contact);
    }

    @When("^the gestionnaire wants to add the supplier with name (.*) and contact (.*) there is an error$")
    public void addSupplierError(String supplier, String contact){
        try {
            management.register(supplier, contact);
        }catch (AlreadyExistingSupplierException e){
            exception = e;
        }
    }

    @When("^the gestionnaire wants to delete the supplier with name (.*) there is an error$")
    public void addSupplierError(String supplier){
        try {
            management.delete(supplier);
        }catch (UnknownSupplierException e){
            exception = e;
        }
    }

    @When("^the gestionnaire wants to add contact (.*) to the supplier (.*)$")
    public void addContactSupplierError(String contact, String supplier){
        Optional<Supplier> supplierObject = finder.findByName(supplier);
        if(!supplierObject.isPresent())
            fail();
        try {
            management.addContact(supplier, contact);
        }catch (AlreadyExistingContactException e){
            exception = e;
        }catch (UnknownSupplierException e){
            fail();
        }
    }

    @Then("^there is an exception$")
    public void checkExceptionAdd(){
        assertNotNull(exception);
    }

    @Then("^the supplier with name (.*) is found$")
    public void checkAddSupplier(String supplier){
        assertTrue(finder.findByName(supplier).isPresent());
    }

    @Then("^the supplier with name (.*) does not exist anymore$")
    public void checkDeleteSupplier(String supplier){
        assertFalse(finder.findByName(supplier).isPresent());
    }

    @Then("^the supplier with name (.*) has now (.*) as contact$")
    public void checkAddContact(String supplier, String contact){
        System.out.println("HERE");
        Optional<Supplier> supplierOptional = finder.findByName(supplier);
        if(!supplierOptional.isPresent())
            fail();
        Supplier s = supplierOptional.get();

        assertNotNull(s.getContacts());
        assertTrue(s.getContacts().contains(contact));
    }

    @Then("^the supplier is found$")
    public void checkFoundSupplier(){
        assertTrue(supplierFound.isPresent());
    }

    @Then("^the supplier is not found$")
    public void checkNotFoundSupplier(){
        assertFalse(supplierFound.isPresent());
    }

    @cucumber.api.java.After
    public void cleaningUp(){
        suppliersAdded.forEach(sup -> {
            try {
                management.delete(sup.getName());
            } catch (UnknownSupplierException e) {
                e.printStackTrace();
            }
        });
        exception = null;
        supplierFound = Optional.empty();
        suppliersAdded = new ArrayList<>();
    }
}
