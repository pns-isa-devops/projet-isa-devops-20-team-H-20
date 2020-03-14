
package stubs.planning;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for delivery complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
     * Gets the value of the dateTimeToShip property.
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
     * Sets the value of the dateTimeToShip property.
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
     * Gets the value of the distance property.
     * 
     */
    public float getDistance() {
        return distance;
    }

    /**
     * Sets the value of the distance property.
     * 
     */
    public void setDistance(float value) {
        this.distance = value;
    }

    /**
     * Gets the value of the flightTime property.
     * 
     */
    public float getFlightTime() {
        return flightTime;
    }

    /**
     * Sets the value of the flightTime property.
     * 
     */
    public void setFlightTime(float value) {
        this.flightTime = value;
    }

    /**
     * Gets the value of the state property.
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
     * Sets the value of the state property.
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
