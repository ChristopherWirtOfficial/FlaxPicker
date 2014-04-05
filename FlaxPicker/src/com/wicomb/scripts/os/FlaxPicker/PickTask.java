package com.wicomb.scripts.os.FlaxPicker;

import static com.wicomb.scripts.os.framework.Constants.FLAX;
import static com.wicomb.scripts.os.framework.Constants.FLAX_SPOT;
import static com.wicomb.scripts.os.framework.Constants.objects;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Interactive;

import com.wicomb.scripts.os.framework.Helpers;
import com.wicomb.scripts.os.framework.Task;

public class PickTask extends Task {

	public PickTask(ClientContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activate() {
		return !Helpers.isFull(ctx);
	}

	@Override
	public void execute() {
		if(Helpers.distance(FLAX_SPOT,ctx) > 10) {
			System.out.println("Walking");
			Helpers.walk(FLAX_SPOT, ctx);
		} else {
			// This is where the picking happens
			GameObject flax = ctx.objects.select(Interactive.areInViewport()).id(objects[FLAX]).nearest().limit(5).shuffle().poll();
			System.out.println("First click");
			final Tile flaxTile = flax.tile();
			flax.click();
			System.out.println(flax);
			Condition.wait(new Callable<Boolean>() {

				@Override
				public Boolean call() throws Exception {
					return Helpers.distance(flaxTile, ctx) <= 1;
				}
			});
			while(ctx.objects.select().at(flax.tile()).poll().equals(flax) && !Helpers.isFull(ctx)) {
				flax.click();
				System.out.println("Clicking a lot");
			}
		}

	}

}
