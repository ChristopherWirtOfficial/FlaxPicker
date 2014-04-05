package com.wicomb.scripts.os.framework;

import static com.wicomb.scripts.os.framework.Constants.FLAX;
import static com.wicomb.scripts.os.framework.Constants.items;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class Helpers {
	public static boolean chance(double c) {
		int upper = (int)(100/c);
		
		return Random.nextInt(1, upper) == 1;
	}
	
	public static boolean isFull(ClientContext ctx) {
		return ctx.inventory.select().count() == 28;
	}

	public static int distance(Tile tile, ClientContext ctx) {
		return (int) ctx.players.local().tile().distanceTo(tile);
	}

	public static void walk(Tile tile, final ClientContext ctx) {
		if(distance(tile,ctx) < 4)
			return;
		ctx.movement.step(ctx.movement.closestOnMap(tile));
		Condition.sleep(Random.getDelay());
		Condition.wait(new Callable<Boolean>() {

			@Override
			public Boolean call() throws Exception {
				return distance(ctx.movement.destination(), ctx) < 5;
			}
		});
	}
}
