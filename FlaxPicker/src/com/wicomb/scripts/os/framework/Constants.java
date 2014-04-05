package com.wicomb.scripts.os.framework;

import org.powerbot.script.Tile;



/* @author Chrisazy
 * @Description This is really just meant to be a holder for all of the constants.
 *  Everything in here is static, and if I could make the class static, believe me I would.
 */
public final class Constants {
	/* Item constants */
	public static final int TEA_ITEM = 1979;
	
	/* Object Constants */
	public static final int TEASTALL = 635;
	public static final int TEASTALL_EMPTY = 634;
	
	/* Location Determinator Constants 
	 * Used for things like Bank arrays */
	public static final int VARROCK_EAST = 0;
	public static final int VARROCK_WEST = 1;
	public static final int LUMBRIDGE = 2;
	public static final int SEERS = 3;
	public static final int DRAYNOR = 4;
	public static final int ARDOUGNE_EAST = 5;
	public static final int ARDOUGNE_WEST = 6;
	
	/* Location Tiles Constants */
	public static final Tile FLAX_SPOT = new Tile(2736,3443,0);
	
	
	/* Resource Determinator Constants 
	 */
	public static final int FLAX = 0;
	public static final int BANK_BOOTH = 1;
	
	/* Determinated Constants */
	public static Tile[] banks = new Tile[2048];
	public static int[] objects = new int[2048];
	public static int[] items = new int[2048];
	
	static {
		banks[SEERS] = new Tile(2726,3491,0);
		objects[FLAX] = 9315;
		items[FLAX] = 1779;
		objects[BANK_BOOTH] = 25808;
	}
}
