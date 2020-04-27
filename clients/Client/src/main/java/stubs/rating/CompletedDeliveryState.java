
package stubs.rating;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour completedDeliveryState complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="completedDeliveryState">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating}deliveryState">
 *       &lt;sequence>
 *         &lt;element name="shippedAt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "completedDeliveryState", propOrder = {
    "shippedAt"
})
public class CompletedDeliveryState
    extends DeliveryState
{

    protected String shippedAt;

    /**
     * Obtient la valeur de la propriété shippedAt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShippedAt() {
        return shippedAt;
    }

    /**
     * Définit la valeur de la propriété shippedAt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShippedAt(String value) {
        this.shippedAt = value;
    }

}
