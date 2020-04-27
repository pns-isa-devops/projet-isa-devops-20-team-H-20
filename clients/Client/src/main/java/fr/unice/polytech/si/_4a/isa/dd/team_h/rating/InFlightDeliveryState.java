
package fr.unice.polytech.si._4a.isa.dd.team_h.rating;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour inFlightDeliveryState complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="inFlightDeliveryState"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating}deliveryState"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="shippedAt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
