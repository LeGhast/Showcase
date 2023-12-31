package de.leghast.showcase.instance.settings;

import de.leghast.showcase.util.Util;
import org.bukkit.Axis;

public class PositionSettings {

    private AdjusterSettings parent;

    private double factor = 1;

    private Axis axis = Axis.X;

    public PositionSettings(AdjusterSettings parent){
        this.parent = parent;
    }

    public double getFactor(){
        return factor;
    }

    public void setFactor(double factor){
        this.factor = factor;
    }

    public void setFactor(String factor){
        try{
            this.factor = Double.parseDouble(factor);
            parent.getPlayer().sendMessage(Util.PREFIX + "§aThe factor was set to §e" + factor + " blocks");
        }catch (NumberFormatException e){
            parent.getPlayer().sendMessage(Util.PREFIX + "§cPlease provide a valid factor");
        }
    }

    public Axis getAxis(){
        return axis;
    }

    public void setAxis(Axis axis){
        this.axis = axis;
    }

}
