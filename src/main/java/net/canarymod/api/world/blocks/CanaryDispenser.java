package net.canarymod.api.world.blocks;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.inventory.CanaryBlockInventory;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.InventoryType;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.CanaryWorld;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;

import java.util.Arrays;

/**
 * Dispenser wrapper implementation
 *
 * @author Jason (darkdiplomat)
 */
public class CanaryDispenser extends CanaryBlockInventory implements Dispenser {
    // private Random random = new Random();

    /**
     * Constructs a new wrapper for TileEntityDispenser
     *
     * @param tileentity
     *         the TileEntityDispenser to be wrapped
     */
    public CanaryDispenser(TileEntityDispenser tileentity) {
        super(tileentity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InventoryType getInventoryType() {
        return InventoryType.DISPENSER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity activate() {
        return dispense(null, -1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity dispenseFromSlot(int slot) {
        Item stack = getSlot(slot);

        if (stack != null) {
            return dispense(((CanaryItem) stack).getHandle(), slot);
        }
        else {
            ((CanaryWorld) getWorld()).getHandle().c(1001, this.getX(), this.getY(), this.getZ(), 0);
        }
        return null;
    }

    private Entity dispense(ItemStack item, int slot) {
        if (item != null) {
            BlockSourceImpl blocksourceimpl = new BlockSourceImpl(((CanaryWorld) getWorld()).getHandle(), getX(), getY(), getZ());
            IBehaviorDispenseItem ibehaviordispenseitem = (IBehaviorDispenseItem) ((BlockDispenser) net.minecraft.block.Block.e(BlockType.Dispenser.getId())).a(item);

            if (ibehaviordispenseitem != IBehaviorDispenseItem.a) {
                ItemStack itemstack1 = ibehaviordispenseitem.a(blocksourceimpl, item);

                if (slot != -1) {
                    getTileEntity().a(slot, itemstack1.b == 0 ? null : itemstack1);
                }
            }
        }
        return null; // No way to get the entity with the behavior classes as is without major refactoring of native code...
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearContents() {
        Arrays.fill(getTileEntity().i, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] clearInventory() {
        ItemStack[] items = Arrays.copyOf(getTileEntity().i, getSize());

        clearContents();
        return CanaryItem.stackArrayToItemArray(items);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item[] getContents() {
        return CanaryItem.stackArrayToItemArray(getTileEntity().i);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContents(Item[] items) {
        System.arraycopy(CanaryItem.itemArrayToStackArray(items), 0, getTileEntity().i, 0, getSize());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInventoryName(String value) {
        getTileEntity().a(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TileEntityDispenser getTileEntity() {
        return (TileEntityDispenser) tileentity;
    }
}
