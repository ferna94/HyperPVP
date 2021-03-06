/*******************************************************************************
 * Copyright 2014 Alex Miller
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package us.hyperpvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.hyperpvp.HyperPVP;

public class PinCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		try {
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "The server doesn't have a PIN!");
				return true;
			}

			Player player = (Player)sender;

			if (HyperPVP.getStorage().readString("SELECT password FROM users WHERE username = '" + player.getName() + "'").length() != 0) {
				sender.sendMessage(ChatColor.RED + "You have already made your account.");
			}
			
			if (HyperPVP.getStorage().entryExists("SELECT code FROM pincodes WHERE name = '" + player.getName() + "'")) {
				player.sendMessage(ChatColor.GREEN + "Your pin code is " + ChatColor.WHITE + HyperPVP.getStorage().readString("SELECT code FROM pincodes WHERE name = '" + player.getName() + "'"));
			} else {
				sender.sendMessage(ChatColor.RED + "You haven't registered, use the " + ChatColor.WHITE + "/register" + ChatColor.RED + "command!");
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
