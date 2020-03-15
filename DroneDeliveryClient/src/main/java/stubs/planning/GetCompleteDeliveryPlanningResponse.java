
package stubs.planning;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour getCompleteDeliveryPlanningResponse complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="getCompleteDeliveryPlanningResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="delivery-planning" type="{http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning}planningEntry" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCompleteDeliveryPlanningResponse", propOrder = {
    "deliveryPlanning"
})
public class GetCompleteDeliveryPlanningResponse {

    @XmlElement(name = "delivery-planning")
    protected List<PlanningEntry> deliveryPlanning;

    /**
     * Gets the value of the deliveryPlanning property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deliveryPlanning property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeliveryPlanning().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlanningEntry }
     * 
     * 
     */
    public List<PlanningEntry> getDeliveryPlanning() {
        if (deliveryPlanning == null) {
            deliveryPlanning = new ArrayList<PlanningEntry>();
        }
        return this.deliveryPlanning;
    }

}
