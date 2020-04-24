
package fr.unice.polytech.si._4a.isa.dd.team_h.rating;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour failedDeliveryState complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="failedDeliveryState"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating}deliveryState"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="localDateTime" type="{http://www.polytech.unice.fr/si/4a/isa/dd/team-h/rating}localDateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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

    protected LocalDateTime localDateTime;

    /**
     * Obtient la valeur de la propriété localDateTime.
     * 
     * @return
     *     possible object is
     *     {@link LocalDateTime }
     *     
     */
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    /**
     * Définit la valeur de la propriété localDateTime.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDateTime }
     *     
     */
    public void setLocalDateTime(LocalDateTime value) {
        this.localDateTime = value;
    }

}
