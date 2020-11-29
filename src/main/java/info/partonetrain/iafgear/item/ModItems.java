package info.partonetrain.iafgear.item;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {

    public static Item ShinyScaleBlue;
    public static Item ShinyScaleBronze;
    public static Item ShinyScaleDeepBlue;
    public static Item ShinyScaleGreen;
    public static Item ShinyScalePurple;
    public static Item ShinyScaleRed;
    public static Item ShinyScaleTeal;



    public static void register(IForgeRegistry<Item> registry){
        registry.registerAll(
           ShinyScaleBlue = new ShinyScaleItem("blue"),
           ShinyScaleBronze = new ShinyScaleItem("bronze"),
           ShinyScaleDeepBlue = new ShinyScaleItem("deep_blue"),
           ShinyScaleGreen = new ShinyScaleItem("green"),
           ShinyScalePurple = new ShinyScaleItem("purple"),
           ShinyScaleRed = new ShinyScaleItem( "red"),
           ShinyScaleTeal = new ShinyScaleItem("teal")
        );
    }

}
