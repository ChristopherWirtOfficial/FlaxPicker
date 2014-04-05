package com.wicomb.scripts.os.FlaxPicker;

import java.awt.Graphics;
import java.util.ArrayList;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script.Manifest;

import com.wicomb.scripts.os.FlaxPicker.Tasks.BankTask;
import com.wicomb.scripts.os.framework.Task;

@Manifest(name = "Wicomb's FlaxPicker", description = "X", properties = "topic=90210;client=4;")
public class FlaxPicker extends
		PollingScript<org.powerbot.script.rt4.ClientContext> implements
		PaintListener {

	private ArrayList<Task> tasks;
	private Task busyTask = null; // This is used in cases like walking where I
									// literally don't want anything to be done

	@Override
	public void start() {
		if (!ctx.game.loggedIn()) {
			System.out.println("Please start the script while logged in");
			ctx.controller.stop();
		}
		tasks = new ArrayList<Task>();
		// tasks.add(new BusyTask(ctx));
		//tasks.add(new RandomTask(ctx));
		tasks.add(new BankTask(ctx));
		tasks.add(new PickTask(ctx));
		
		System.out.println("Script started");
	}

	@Override
	public void poll() {
		if (busyTask != null && busyTask.busy == false) {
			busyTask = null;

		} else {

			for (Task t : tasks) {
				if (this.busyTask == null && t.busy)
					this.busyTask = t;

				if (t.activate()
						&& (this.busyTask == null || t.override || this.busyTask == t)) {
					t.execute();
				}
			}
		}
	}

	@Override
	public void repaint(Graphics arg0) {
		// TODO Auto-generated method stub

	}

}
