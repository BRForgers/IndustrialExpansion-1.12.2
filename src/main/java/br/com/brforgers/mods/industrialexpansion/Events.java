package br.com.brforgers.mods.industrialexpansion;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class Events {
    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        if (!(event.getEntityLiving() instanceof EntityPlayer) || event.isCanceled() || event.getAmount() <= 0) {
            return;
        }
        EntityPlayer player = (EntityPlayer) event.getEntityLiving();

        boolean quantum = true;
        for (ItemStack a : player.getArmorInventoryList()) {
            if (a.isEmpty() || !a.getUnlocalizedName().contains("quantum")) {
                quantum = false;
                break;
            } else {
                if(a.getTagCompound().getInteger("Energy") == 0) {
                    quantum = false;
                    break;
                }
            }
        }
        if(quantum){
            if(event.getSource().isFireDamage()){
                event.setCanceled(true);
                event.getEntityLiving().extinguish();
            }

            if(event.getSource().damageType.equals("darkness")){
                event.setCanceled(true);
            }
        }
    }
}
