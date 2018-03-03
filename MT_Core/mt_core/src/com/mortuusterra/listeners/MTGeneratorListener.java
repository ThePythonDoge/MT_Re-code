package com.mortuusterra.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.mortuusterra.MortuusTerraMain;
import com.mortuusterra.managers.MTGenerator;

public class MTGeneratorListener implements Listener {

	private MortuusTerraMain main;

	public MTGeneratorListener(MortuusTerraMain m) {
		this.main = m;
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		e.getPlayer().sendMessage("blockplace");
		if (e.getBlock().getType().equals(Material.REDSTONE_LAMP_OFF)) {
			e.getPlayer().sendMessage("lamp");
			Block lamp = e.getBlock();
			if (lamp.getRelative(BlockFace.DOWN).getType().equals(Material.FURNACE)) {
				e.getPlayer().sendMessage("furnace");
				Block funace = lamp.getRelative(BlockFace.DOWN);
				if (funace.getRelative(BlockFace.DOWN).getType().equals(Material.SMOOTH_BRICK)) {
					e.getPlayer().sendMessage("brick");
					Block centerGround = funace.getRelative(BlockFace.DOWN);
					if (lamp.getRelative(BlockFace.UP).getType().equals(Material.STEP)) {
						e.getPlayer().sendMessage("slab");
						Block centerTop = lamp.getRelative(BlockFace.UP);
						BlockFace[] squareFaces = { BlockFace.EAST, BlockFace.WEST, BlockFace.NORTH, BlockFace.SOUTH,
								BlockFace.NORTH_EAST, BlockFace.NORTH_WEST, BlockFace.SOUTH_EAST,
								BlockFace.SOUTH_WEST };
						BlockFace[] ironFenceFaces = { BlockFace.NORTH_EAST, BlockFace.NORTH_WEST, BlockFace.SOUTH_EAST,
								BlockFace.SOUTH_WEST };
						e.getPlayer().sendMessage("first for loop");
						for (BlockFace f : squareFaces) {
							e.getPlayer().sendMessage("for blockFace");
							if (!centerGround.getRelative(f).getType().equals(Material.SMOOTH_BRICK)
									|| !centerTop.getRelative(f).getType().equals(Material.STEP)) {
								e.getPlayer().sendMessage("Generator is not built corectly!");
								return;
							}
							e.getPlayer().sendMessage("adding blocks");
							main.getGenBuild().addBlock(centerGround.getRelative(f));
							main.getGenBuild().addBlock(centerTop.getRelative(f));

						}
						e.getPlayer().sendMessage("second for loop");
						for (BlockFace f : ironFenceFaces) {
							e.getPlayer().sendMessage("blcokfaces");
							if (!funace.getRelative(f).getType().equals(Material.IRON_FENCE)
									|| !lamp.getRelative(f).getType().equals(Material.IRON_FENCE)) {
								e.getPlayer().sendMessage("Generator is not built corectly!");
								return;
							}
							e.getPlayer().sendMessage("adding blocks");
							main.getGenBuild().addBlock(funace.getRelative(f));
							main.getGenBuild().addBlock(lamp.getRelative(f));
						}
						// generator must be built correctly
						main.getGenBuild().addBlock(lamp);
						main.getGenBuild().addBlock(funace);
						e.getPlayer().sendMessage("Generator is built corectly");
						e.getPlayer().sendMessage("Generator is starting ...");
						MTGenerator gen = new MTGenerator(e.getPlayer(), lamp.getLocation(),
								main.getGenBuild().getBlocks());
						main.getRad().addGenerator(gen);
						main.getGenBuild().startGenerator(e.getPlayer(), gen);
					}
				}
			}
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (!e.getClickedBlock().getType().equals(Material.FURNACE)) {
			return;
		}
		/**
		if(main.getRad().getGenerator(e.getClickedBlock().getLocation()).getBlocks().contains(e.getClickedBlock())) {
			e.getPlayer().sendMessage("This is not your generator, you can not interact with it.");
		}
		**/
		if (main.getRad().containsGenerator(e.getClickedBlock().getLocation())) {
			if (!main.getRad().canPlayerInteractGenerator(main.getRad().getGenerator(e.getClickedBlock().getLocation()),
					e.getPlayer())) {
				e.getPlayer().sendMessage("This is not your generator, you can not interact with it.");
			}
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(main.getRad().getGenerator(e.getBlock().getLocation()).getBlocks().contains(e.getBlock())) {
			
		}
	}
}
