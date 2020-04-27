
package stubs.rating;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour failedDeliveryState complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="failedDeliveryState">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating}deliveryState">
 *       &lt;sequence>
 *         &lt;element name="localDateTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "failedDeliveryState", propOrder = {
    "localDateTime"
})
public class FailedDeliveryState
    extends DeliveryState
{

    protected String localDateTime;

    /**
     * Obtient la valeur de la propriété localDateTime.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalDateTime() {
        return localDateTime;
    }

    /**
     * Définit la valeur de la propriété localDateTime.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalDateTime(String value) {
        this.localDateTime = value;
    }

}
