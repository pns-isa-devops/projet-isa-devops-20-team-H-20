
package stubs.rating;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour delivery complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="delivery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="distance" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="flightTime" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="state" type="{http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating}deliveryState" minOccurs="0"/>
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aPackage" type="{http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating}package" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "delivery", propOrder = {
    "date",
    "distance",
    "flightTime",
    "id",
    "state",
    "time",
    "aPackage"
})
public class Delivery {

    protected String date;
    protected float distance;
    protected float flightTime;
    protected int id;
    protected DeliveryState state;
    protected String time;
    protected Package aPackage;

    /**
     * Obtient la valeur de la propriété date.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Définit la valeur de la propriété date.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
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
     * Obtient la valeur de la propriété id.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     */
    public void setId(int value) {
        this.id = value;
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

    /**
     * Obtient la valeur de la propriété time.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTime() {
        return time;
    }

    /**
     * Définit la valeur de la propriété time.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTime(String value) {
        this.time = value;
    }

    /**
     * Obtient la valeur de la propriété aPackage.
     * 
     * @return
     *     possible object is
     *     {@link Package }
     *     
     */
    public Package getAPackage() {
        return aPackage;
    }

    /**
     * Définit la valeur de la propriété aPackage.
     * 
     * @param value
     *     allowed object is
     *     {@link Package }
     *     
     */
    public void setAPackage(Package value) {
        this.aPackage = value;
    }

}
