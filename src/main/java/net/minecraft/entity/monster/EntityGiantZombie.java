package net.minecraft.entity.monster;

import net.canarymod.api.entity.living.monster.CanaryGiantZombie;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityGiantZombie extends EntityMob {

    public EntityGiantZombie(World world) {
        super(world);
        this.M *= 6.0F;
        this.a(this.N * 6.0F, this.O * 6.0F);
        this.entity = new CanaryGiantZombie(this); // CanaryMod: Wrap Entity
    }

    protected void aD() {
        super.aD();
        this.a(SharedMonsterAttributes.a).a(100.0D);
        this.a(SharedMonsterAttributes.d).a(0.5D);
        this.a(SharedMonsterAttributes.e).a(50.0D);
    }

    public float a(int i0, int i1, int i2) {
        return this.p.n(i0, i1, i2) - 0.5F;
    }
}
