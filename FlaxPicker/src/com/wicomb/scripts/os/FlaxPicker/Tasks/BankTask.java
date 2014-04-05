package com.wicomb.scripts.os.FlaxPicker.Tasks;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import static com.wicomb.scripts.os.framework.Constants.*;
import com.wicomb.scripts.os.framework.Helpers;
import com.wicomb.scripts.os.framework.Task;

public class BankTask extends Task {

	public BankTask(ClientContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "BankTask";
	}

	@Override
	public boolean activate() {
		return Helpers.isFull(ctx);
	}

	@Override
	public void execute() {
		Condition.sleep(500);
		System.out.println("Banking");
		if (Helpers.distance(banks[SEERS], ctx) < 4) {
			System.out.println("In the bank");
			// Then I need to do banking
			GameObject bank = ctx.objects.id(objects[BANK_BOOTH]).nearest().limit(2).shuffle().poll();
			
			if (ctx.bank.opened()) {
				System.out.println("Bank is open");
				if (!ctx.inventory.select().id(items[FLAX]).isEmpty()) {
					System.out.println("Depositing flax");
					ctx.bank.depositInventory();
					Condition.wait(new Callable<Boolean>() {

						@Override
						public Boolean call() throws Exception {
							return !ctx.inventory.select()
									.id(items[FLAX]).isEmpty();
						}
					});
				}
			} else {
				if(bank.inViewport()) {
					if (bank.interact("Bank")) {
						Condition.wait(new Callable<Boolean>() {
		
							@Override
							public Boolean call() throws Exception {
								if (ctx.bank.opened())
									return true;
								return false;
							}
						});
					}
				} else {
					ctx.camera.turnTo(bank);
				}
			}
		} else {
			System.out.println("Walking to bank");
			System.out.println(ctx.movement.step(banks[SEERS]));
		}
	}
}
