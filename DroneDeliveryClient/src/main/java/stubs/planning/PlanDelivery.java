
package stubs.planning;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour planDelivery complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="planDelivery"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="drone-id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="tracking-number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="shipping-time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "planDelivery", propOrder = {
    "droneId",
    "trackingNumber",
    "shippingTime"
})
public class PlanDelivery {

    @XmlElement(name = "drone-id")
    protected int droneId;
    @XmlElement(name = "tracking-number")
    protected String trackingNumber;
    @XmlElement(name = "shipping-time")
    protected String shippingTime;

    /**
     * Obtient la valeur de la propriété droneId.
     * 
     */
    public int getDroneId() {
        return droneId;
    }

    /**
     * Définit la valeur de la propriété droneId.
     * 
     */
    public void setDroneId(int value) {
        this.droneId = value;
    }

    /**
     * Obtient la valeur de la propriété trackingNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackingNumber() {
        return trackingNumber;
    }

    /**
     * Définit la valeur de la propriété trackingNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackingNumber(String value) {
        this.trackingNumber = value;
    }

    /**
     * Obtient la valeur de la propriété shippingTime.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShippingTime() {
        return shippingTime;
    }

    /**
     * Définit la valeur de la propriété shippingTime.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShippingTime(String value) {
        this.shippingTime = value;
    }

}
