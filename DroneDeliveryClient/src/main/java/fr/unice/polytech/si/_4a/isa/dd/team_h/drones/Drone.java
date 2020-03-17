
package fr.unice.polytech.si._4a.isa.dd.team_h.drones;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour drone complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="drone"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="battery" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="currentFlightTime" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="state" type="{http://www.polytech.unice.fr/si/4a/isa/dd/team-h/drones}droneState" minOccurs="0"/&gt;
 *         &lt;element name="weightCapacity" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "drone", propOrder = {
    "battery",
    "currentFlightTime",
    "id",
    "state",
    "weightCapacity"
})
public class Drone {

    protected int battery;
    protected float currentFlightTime;
    protected int id;
    protected DroneState state;
    protected float weightCapacity;

    /**
     * Obtient la valeur de la propriété battery.
     * 
     */
    public int getBattery() {
        return battery;
    }

    /**
     * Définit la valeur de la propriété battery.
     * 
     */
    public void setBattery(int value) {
        this.battery = value;
    }

    /**
     * Obtient la valeur de la propriété currentFlightTime.
     * 
     */
    public float getCurrentFlightTime() {
        return currentFlightTime;
    }

    /**
     * Définit la valeur de la propriété currentFlightTime.
     * 
     */
    public void setCurrentFlightTime(float value) {
        this.currentFlightTime = value;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété state.
     * 
     * @return
     *     possible object is
     *     {@link DroneState }
     *     
     */
    public DroneState getState() {
        return state;
    }

    /**
     * Définit la valeur de la propriété state.
     * 
     * @param value
     *     allowed object is
     *     {@link DroneState }
     *     
     */
    public void setState(DroneState value) {
        this.state = value;
    }

    /**
     * Obtient la valeur de la propriété weightCapacity.
     * 
     */
    public float getWeightCapacity() {
        return weightCapacity;
    }

    /**
     * Définit la valeur de la propriété weightCapacity.
     * 
     */
    public void setWeightCapacity(float value) {
        this.weightCapacity = value;
    }

}
