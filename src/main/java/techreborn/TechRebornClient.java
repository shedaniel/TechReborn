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

package techreborn;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import reborncore.client.gui.builder.GuiBase;
import reborncore.client.hud.StackInfoHUD;
import techreborn.client.render.DynamicBucketBakedModel;
import techreborn.client.render.DynamicCellBakedModel;
import techreborn.events.StackToolTipHandler;
import techreborn.init.TRContent;
import techreborn.items.ItemDynamicCell;
import techreborn.items.ItemFrequencyTransmitter;
import techreborn.utils.StackWIPHandler;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

public class TechRebornClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ModelLoadingRegistry.INSTANCE.registerAppender((manager, out) -> {
			out.accept(new ModelIdentifier(new Identifier(TechReborn.MOD_ID, "cell_base"), "inventory"));
			out.accept(new ModelIdentifier(new Identifier(TechReborn.MOD_ID, "cell_fluid"), "inventory"));
			out.accept(new ModelIdentifier(new Identifier(TechReborn.MOD_ID, "cell_background"), "inventory"));
			out.accept(new ModelIdentifier(new Identifier(TechReborn.MOD_ID, "cell_glass"), "inventory"));

			out.accept(new ModelIdentifier(new Identifier(TechReborn.MOD_ID, "bucket_base"), "inventory"));
			out.accept(new ModelIdentifier(new Identifier(TechReborn.MOD_ID, "bucket_fluid"), "inventory"));
			out.accept(new ModelIdentifier(new Identifier(TechReborn.MOD_ID, "bucket_background"), "inventory"));
		});

		ModelLoadingRegistry.INSTANCE.registerVariantProvider(resourceManager -> (modelIdentifier, modelProviderContext) -> {
			if (modelIdentifier.getNamespace().equals(TechReborn.MOD_ID)) {
				if (modelIdentifier.getPath().equals("cell")) {
					return new UnbakedModel() {
						@Override
						public Collection<Identifier> getModelDependencies() {
							return Collections.emptyList();
						}

						@Override
						public Collection<Identifier> getTextureDependencies(Function<Identifier, UnbakedModel> function, Set<String> set) {
							return Collections.emptyList();
						}

						@Override
						public BakedModel bake(ModelLoader modelLoader, Function<Identifier, Sprite> function, ModelBakeSettings modelBakeSettings) {
							return new DynamicCellBakedModel();
						}
					};
				}
				Fluid fluid = Registry.FLUID.get(new Identifier(TechReborn.MOD_ID, modelIdentifier.getPath().split("_bucket")[0]));
				if (modelIdentifier.getPath().endsWith("_bucket") && fluid != Fluids.EMPTY) {
					return new UnbakedModel() {
						@Override
						public Collection<Identifier> getModelDependencies() {
							return Collections.emptyList();
						}

						@Override
						public Collection<Identifier> getTextureDependencies(Function<Identifier, UnbakedModel> function, Set<String> set) {
							return Collections.emptyList();
						}

						@Override
						public BakedModel bake(ModelLoader modelLoader, Function<Identifier, Sprite> function, ModelBakeSettings modelBakeSettings) {
							return new DynamicBucketBakedModel();
						}
					};
				}
			}
			return null;
		});

		StackToolTipHandler.setup();
		StackWIPHandler.setup();

		GuiBase.wrenchStack = new ItemStack(TRContent.WRENCH);
		GuiBase.fluidCellProvider = ItemDynamicCell::getCellWithFluid;

		StackInfoHUD.registerElement(new ItemFrequencyTransmitter.StackInfoFreqTransmitter());
	}


}
