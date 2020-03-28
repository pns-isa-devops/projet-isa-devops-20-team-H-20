package features;

import fr.unice.polytech.isa.dd.teamH.arquillian.AbstractPackageRegistryTest;
import cucumber.runtime.arquillian.CukeSpace;
import fr.unice.polytech.isa.dd.teamH.entities.Package;
import fr.unice.polytech.isa.dd.teamH.entities.Supplier;
import fr.unice.polytech.isa.dd.teamH.interfaces.PackageFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.PackageRegistration;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierFinder;
import fr.unice.polytech.isa.dd.teamH.interfaces.SupplierRegistration;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import javax.ejb.EJB;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(CukeSpace.class)
@CucumberOptions(features = "src/test/resources/features")
public class PackageRegistryStepdefsTest extends AbstractPackageRegistryTest {
    @EJB private PackageRegistration management;
    @EJB private PackageFinder finder;

    @EJB private SupplierRegistration supplierRegistration;
    @EJB private SupplierFinder supplierFinder;

    private Optional<Package> packageFound;

    @Given("^some packages with trackingIds (.*) (.*) and (.*) as supplier$")
    public void background(String trackingId, String trackingId2, String supplier) throws Exception{
        management.flush();
        supplierRegistration.flush();
        supplierRegistration.register(supplier, "0649712258");
        Optional<Supplier> supplierObject = supplierFinder.findByName(supplier);
        if(!supplierObject.isPresent())
            fail();

        management.register(trackingId, supplierObject.get(), 5.0f, "Nice ville");
        management.register(trackingId2, supplierObject.get(), 8.0f, "Nice ville");
        assertEquals(2, finder.findAllPackages().size());
    }

    @Given("^some suppliers (.*) and (.*) and packages with trackingIds (.*) (.*)$")
    public void background(String supplier, String supplier2, String trackingId, String trackingId2) throws Exception{
        management.flush();
        supplierRegistration.flush();
        supplierRegistration.register(supplier, "0649712258");
        supplierRegistration.register(supplier2, "0649712255");

        Optional<Supplier> supplierObject = supplierFinder.findByName(supplier);
        Optional<Supplier> supplierObject2 = supplierFinder.findByName(supplier);
        if(!supplierObject.isPresent() || !supplierObject2.isPresent())
            fail();

        management.register(trackingId, supplierObject.get(), 5.0f, "Nice ville");
        management.register(trackingId2, supplierObject.get(), 8.0f, "Nice ville");
        assertEquals(2, finder.findAllPackages().size());
    }

    @When("^the manutentionnaire adds the package with trackingId (.*) and (.*) as supplier$")
    public void addPackage(String trackingId, String supplier) throws Exception{
        Optional<Supplier> supplierObject = supplierFinder.findByName(supplier);
        if(!supplierObject.isPresent())
            fail();

        management.register(trackingId, supplierObject.get(), 6.0f, "Biot");
    }

    @When("^the manutentionnaire adds the packages with trackingId (.*) and (.*) and (.*) as supplier$")
    public void addPackage(String trackingId, String trackingId2, String supplier) throws Exception{
        Optional<Supplier> supplierObject = supplierFinder.findByName(supplier);
        if(!supplierObject.isPresent())
            fail();

        management.register(trackingId, supplierObject.get(), 6.0f, "Biot");
        management.register(trackingId2, supplierObject.get(), 7.0f, "Biota");
    }

    @When("^the manutentionnaire deletes the package with trackingId (.*)$")
    public void deletePackage(String trackingId) throws Exception{
        management.delete(trackingId);
    }

    @When("^the manutentionnaire deletes the packages with trackingId (.*) and (.*)$")
    public void deletePackages(String trackingId, String trackingId2) throws Exception{
        management.delete(trackingId);
        management.delete(trackingId2);
    }

    @When("^the manutentionnaire search the package with id (.*)$")
    public void searchPackages(String trackingId){
        packageFound = finder.findPackageByTrackingNumber(trackingId);
    }

    @When("^the manutentionnaire edit the package with trackingId (.*) and set (.*) as supplier$")
    public void editPackage(String trackingId, String supplier) throws Exception{
        Optional<Supplier> supplierObject = supplierFinder.findByName(supplier);
        if(!supplierObject.isPresent())
            fail();

        management.edit(trackingId, supplierObject.get(), 6.0f, "Nice ville");
    }

    @Then("^there is (\\d+) items in the package list and the package with trackingId (.*) is found$")
    public void checkAddPackage(int number,  String trackingId){
        assertEquals(number, finder.findAllPackages().size());
        assertTrue(finder.findPackageByTrackingNumber(trackingId).isPresent());
    }

    @Then("^there is (\\d+) item in the package list and the package with trackingId (.*) does not exist anymore$")
    public void checkDeletePackage(int number,  String trackingId){
        assertEquals(number, finder.findAllPackages().size());
        assertFalse(finder.findPackageByTrackingNumber(trackingId).isPresent());
    }

    @Then("^the package with trackingId (.*) has now (.*) as supplier$")
    public void checkDeletePackage(String trackingId, String supplier){
        Optional<Package> packageOptional = finder.findPackageByTrackingNumber(trackingId);
        if(!packageOptional.isPresent())
            fail();
        assertEquals(supplier, packageOptional.get().getSupplier().getName());
    }

    @Then("^the package is found$")
    public void checkFoundPackage(){
        assertTrue(packageFound.isPresent());
    }

    @Then("^the package is not found$")
    public void checkNotFoundPackage(){
        assertFalse(packageFound.isPresent());
    }
}
