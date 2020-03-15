
package stubs.planning;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour delivery complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="delivery"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dateTimeToShip" type="{http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning}localDateTime" minOccurs="0"/&gt;
 *         &lt;element name="distance" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="flightTime" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="state" type="{http://www.polytech.unice.fr/si/4a/isa/dd/team-h/planning}deliveryState" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "delivery", propOrder = {
    "dateTimeToShip",
    "distance",
    "flightTime",
    "state"
})
public class Delivery {

    protected LocalDateTime dateTimeToShip;
    protected float distance;
    protected float flightTime;
    protected DeliveryState state;

    /**
     * Obtient la valeur de la propriété dateTimeToShip.
     * 
     * @return
     *     possible object is
     *     {@link LocalDateTime }
     *     
     */
    public LocalDateTime getDateTimeToShip() {
        return dateTimeToShip;
    }

    /**
     * Définit la valeur de la propriété dateTimeToShip.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDateTime }
     *     
     */
    public void setDateTimeToShip(LocalDateTime value) {
        this.dateTimeToShip = value;
    }

    /**
     * Obtient la valeur de la propriété distance.
     * 
     */
    public float getDistance() {
        return distance;
    }

    /**
     * Définit la valeur de la propriété distance.
     * 
     */
    public void setDistance(float value) {
        this.distance = value;
    }

    /**
     * Obtient la valeur de la propriété flightTime.
     * 
     */
    public float getFlightTime() {
        return flightTime;
    }

    /**
     * Définit la valeur de la propriété flightTime.
     * 
     */
    public void setFlightTime(float value) {
        this.flightTime = value;
    }

    /**
     * Obtient la valeur de la propriété state.
     * 
     * @return
     *     possible object is
     *     {@link DeliveryState }
     *     
     */
    public DeliveryState getState() {
        return state;
    }

    /**
     * Définit la valeur de la propriété state.
     * 
     * @param value
     *     allowed object is
     *     {@link DeliveryState }
     *     
     */
    public void setState(DeliveryState value) {
        this.state = value;
    }

}
