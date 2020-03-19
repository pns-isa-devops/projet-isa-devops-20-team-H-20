
package stubs.planning;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour planDeliveryResponse complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="planDeliveryResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return code" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "planDeliveryResponse", propOrder = {
    "return0020Code"
})
public class PlanDeliveryResponse {

    @XmlElement(name = "return code")
    protected boolean return0020Code;

    /**
     * Obtient la valeur de la propriété return0020Code.
     * 
     */
    public boolean isReturn_0020Code() {
        return return0020Code;
    }

    /**
     * Définit la valeur de la propriété return0020Code.
     * 
     */
    public void setReturn_0020Code(boolean value) {
        this.return0020Code = value;
    }

}
