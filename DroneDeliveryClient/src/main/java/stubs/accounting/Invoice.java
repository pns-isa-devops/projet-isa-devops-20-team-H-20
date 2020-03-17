
package stubs.accounting;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour invoice complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="invoice">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="creationDate" type="{http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting}localDate" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="paid" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="paymentDate" type="{http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting}localDate" minOccurs="0"/>
 *         &lt;element name="supplier" type="{http://www.polytech.unice.fr/si/4a/isa/dd/team-h/accounting}supplier" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "invoice", propOrder = {
    "amount",
    "creationDate",
    "id",
    "paid",
    "paymentDate",
    "supplier"
})
public class Invoice {

    protected float amount;
    protected LocalDate creationDate;
    protected int id;
    protected boolean paid;
    protected LocalDate paymentDate;
    protected Supplier supplier;

    /**
     * Obtient la valeur de la propri�t� amount.
     * 
     */
    public float getAmount() {
        return amount;
    }

    /**
     * D�finit la valeur de la propri�t� amount.
     * 
     */
    public void setAmount(float value) {
        this.amount = value;
    }

    /**
     * Obtient la valeur de la propri�t� creationDate.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * D�finit la valeur de la propri�t� creationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setCreationDate(LocalDate value) {
        this.creationDate = value;
    }

    /**
     * Obtient la valeur de la propri�t� id.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * D�finit la valeur de la propri�t� id.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propri�t� paid.
     * 
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * D�finit la valeur de la propri�t� paid.
     * 
     */
    public void setPaid(boolean value) {
        this.paid = value;
    }

    /**
     * Obtient la valeur de la propri�t� paymentDate.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    /**
     * D�finit la valeur de la propri�t� paymentDate.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setPaymentDate(LocalDate value) {
        this.paymentDate = value;
    }

    /**
     * Obtient la valeur de la propri�t� supplier.
     * 
     * @return
     *     possible object is
     *     {@link Supplier }
     *     
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * D�finit la valeur de la propri�t� supplier.
     * 
     * @param value
     *     allowed object is
     *     {@link Supplier }
     *     
     */
    public void setSupplier(Supplier value) {
        this.supplier = value;
    }

}
