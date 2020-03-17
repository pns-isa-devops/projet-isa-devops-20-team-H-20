
package stubs.drone;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour AlreadyExistingDroneException complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AlreadyExistingDroneException"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="conflictingId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AlreadyExistingDroneException", propOrder = {
    "conflictingId",
    "message"
})
public class AlreadyExistingDroneException {

    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer conflictingId;
    protected String message;

    /**
     * Obtient la valeur de la propriété conflictingId.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getConflictingId() {
        return conflictingId;
    }

    /**
     * Définit la valeur de la propriété conflictingId.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setConflictingId(Integer value) {
        this.conflictingId = value;
    }

    /**
     * Obtient la valeur de la propriété message.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Définit la valeur de la propriété message.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

}
