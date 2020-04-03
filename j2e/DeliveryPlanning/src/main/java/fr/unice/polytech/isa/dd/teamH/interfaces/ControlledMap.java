package fr.unice.polytech.isa.dd.teamH.interfaces;

import fr.unice.polytech.isa.dd.teamH.utils.MapAPI;

import javax.ejb.Local;

@Local
public interface ControlledMap extends  DeliveryPlanner{
    void useMapReference(MapAPI mapAPI);
}
