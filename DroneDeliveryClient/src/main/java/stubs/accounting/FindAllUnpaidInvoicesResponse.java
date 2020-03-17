
package stubs.accounting;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour findAllUnpaidInvoicesResponse complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="findAllUnpaidInvoicesResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="unpaid_list" type="{http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting}invoice" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findAllUnpaidInvoicesResponse", propOrder = {
    "unpaidList"
})
public class FindAllUnpaidInvoicesResponse {

    @XmlElement(name = "unpaid_list")
    protected List<Invoice> unpaidList;

    /**
     * Gets the value of the unpaidList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the unpaidList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUnpaidList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Invoice }
     * 
     * 
     */
    public List<Invoice> getUnpaidList() {
        if (unpaidList == null) {
            unpaidList = new ArrayList<Invoice>();
        }
        return this.unpaidList;
    }

}
