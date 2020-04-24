
package stubs.ratings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour inFlightDeliveryState complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="inFlightDeliveryState">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating}deliveryState">
 *       &lt;sequence>
 *         &lt;element name="shippedAt" type="{http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating}localDateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inFlightDeliveryState", propOrder = {
    "shippedAt"
})
public class InFlightDeliveryState
    extends DeliveryState
{

    protected LocalDateTime shippedAt;

    /**
     * Obtient la valeur de la propriété shippedAt.
     * 
     * @return
     *     possible object is
     *     {@link LocalDateTime }
     *     
     */
    public LocalDateTime getShippedAt() {
        return shippedAt;
    }

    /**
     * Définit la valeur de la propriété shippedAt.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDateTime }
     *     
     */
    public void setShippedAt(LocalDateTime value) {
        this.shippedAt = value;
    }

}
