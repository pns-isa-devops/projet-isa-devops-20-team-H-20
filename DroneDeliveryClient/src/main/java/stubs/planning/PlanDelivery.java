
package stubs.planning;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for planDelivery complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
     * Gets the value of the droneId property.
     * 
     */
    public int getDroneId() {
        return droneId;
    }

    /**
     * Sets the value of the droneId property.
     * 
     */
    public void setDroneId(int value) {
        this.droneId = value;
    }

    /**
     * Gets the value of the trackingNumber property.
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
     * Sets the value of the trackingNumber property.
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
     * Gets the value of the shippingTime property.
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
     * Sets the value of the shippingTime property.
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
