/*
 * This file is part of TechReborn, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2018 TechReborn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package techreborn.events;

import net.minecraft.item.Item;

public class TRTickHandler {

	public Item previouslyWearing;

	//TODO fixable with a newer version of fabric

//
//	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
//	public void onPlayerTick(TickEvent.PlayerTickEvent e) {
//		PlayerEntity player = e.player;
//		Item chestslot = !player.getEquippedStack(EquipmentSlot.CHEST).isEmpty()
//		                 ? player.getEquippedStack(EquipmentSlot.CHEST).getItem() : null;
//
//		if (previouslyWearing != chestslot && previouslyWearing == TRContent.CLOAKING_DEVICE && player.isInvisible()
//			&& !player.hasStatusEffect(StatusEffects.INVISIBILITY)) {
//			player.setInvisible(false);
//		}
//
//		previouslyWearing = chestslot;
//	}

}
