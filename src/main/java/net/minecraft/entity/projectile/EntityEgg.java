package net.minecraft.entity.projectile;

import net.canarymod.api.entity.throwable.CanaryChickenEgg;
import net.canarymod.hook.entity.ProjectileHitHook;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityEgg extends EntityThrowable {

    public EntityEgg(World world) {
        super(world);
        this.entity = new CanaryChickenEgg(this); // CanaryMod: Wrap Entity
    }

    public EntityEgg(World world, EntityLivingBase entitylivingbase) {
        super(world, entitylivingbase);
        this.entity = new CanaryChickenEgg(this); // CanaryMod: Wrap Entity
    }

    public EntityEgg(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
        this.entity = new CanaryChickenEgg(this); // CanaryMod: Wrap Entity
    }

    protected void a(MovingObjectPosition movingobjectposition) {
        // CanaryMod: ProjectileHit
        ProjectileHitHook hook = (ProjectileHitHook) new ProjectileHitHook(this.getCanaryEntity(), movingobjectposition == null || movingobjectposition.g == null ? null : movingobjectposition.g.getCanaryEntity()).call();
        if (!hook.isCanceled()) { //
            if (movingobjectposition.g != null) {
                movingobjectposition.g.a(DamageSource.a((Entity) this, this.j()), 0.0F);
            }

            if (!this.p.E && this.aa.nextInt(8) == 0) {
                byte b0 = 1;

                if (this.aa.nextInt(32) == 0) {
                    b0 = 4;
                }

                for (int i0 = 0; i0 < b0; ++i0) {
                    EntityChicken entitychicken = new EntityChicken(this.p);

                    entitychicken.c(-24000);
                    entitychicken.b(this.t, this.u, this.v, this.z, 0.0F);
                    this.p.d((Entity) entitychicken);
                }
            }

            for (int i1 = 0; i1 < 8; ++i1) {
                this.p.a("snowballpoof", this.t, this.u, this.v, 0.0D, 0.0D, 0.0D);
            }

            if (!this.p.E) {
                this.B();
            }
        }
    }
}
