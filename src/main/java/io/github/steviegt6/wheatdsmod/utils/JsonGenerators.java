package io.github.steviegt6.wheatdsmod.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.steviegt6.wheatdsmod.blocks.MaterialCropBlock;
import io.github.steviegt6.wheatdsmod.items.crops.MaterialCropItem;
import net.minecraft.util.registry.Registry;

public class JsonGenerators {
    public static JsonElement createCropBlockLootJson(MaterialCropBlock block) {
        String cropName = new WheatIdentifier(block.getDroppedItem().getIdentifierName()).toString();
        String cropSeedsName = cropName + "_seeds";
        String cropBlockName = cropName + "_crop";

        JsonObject json = new JsonObject();
        json.addProperty("type", "minecraft:block");

        JsonArray pools = new JsonArray();
        json.add("pools", pools);

        JsonObject primaryPool = new JsonObject();
        pools.add(primaryPool);

        primaryPool.addProperty("rolls", 1.0);

        JsonArray primaryPoolEntries = new JsonArray();
        primaryPool.add("entries", primaryPoolEntries);

        JsonObject primaryPoolEntry = new JsonObject();
        primaryPoolEntries.add(primaryPoolEntry);

        primaryPoolEntry.addProperty("type", "minecraft:alternatives");

        JsonArray primaryPoolEntryChildren = new JsonArray();
        primaryPoolEntry.add("children", primaryPoolEntryChildren);

        JsonObject firstChild = new JsonObject();
        primaryPoolEntryChildren.add(firstChild);

        firstChild.addProperty("type", "minecraft:item");

        JsonArray firstChildConditions = new JsonArray();
        firstChild.add("conditions", firstChildConditions);

        JsonObject firstCondition = new JsonObject();
        firstChildConditions.add(firstCondition);

        firstCondition.addProperty("condition", "minecraft:block_state_property");
        firstCondition.addProperty("block", cropBlockName);

        firstChild.addProperty("name", cropName);

        JsonObject firstConditionProperties = new JsonObject();
        firstCondition.add("properties", firstConditionProperties);

        firstConditionProperties.addProperty("age", "7");

        JsonObject secondChild = new JsonObject();
        primaryPoolEntryChildren.add(secondChild);

        secondChild.addProperty("type", "minecraft:item");
        secondChild.addProperty("name", cropSeedsName);

        JsonObject secondaryPool = new JsonObject();
        pools.add(secondaryPool);

        secondaryPool.addProperty("rolls", 1.0);

        JsonArray secondaryPoolEntries = new JsonArray();
        secondaryPool.add("entries", secondaryPoolEntries);

        JsonObject secondaryPoolEntry = new JsonObject();
        secondaryPoolEntries.add(secondaryPoolEntry);

        secondaryPoolEntry.addProperty("type", "minecraft:item");
        secondaryPoolEntry.addProperty("name", cropSeedsName);

        JsonArray secondaryPoolConditions = new JsonArray();
        secondaryPool.add("conditions", secondaryPoolConditions);

        JsonObject secondaryPoolCondition = new JsonObject();
        secondaryPoolConditions.add(secondaryPoolCondition);

        secondaryPoolCondition.addProperty("condition", "minecraft:block_state_property");
        secondaryPoolCondition.addProperty("block", cropBlockName);

        JsonObject secondaryConditionProperties = new JsonObject();
        secondaryPoolCondition.add("properties", secondaryConditionProperties);

        secondaryConditionProperties.addProperty("age", "7");

        JsonObject thirdPool = new JsonObject();
        pools.add(thirdPool);

        thirdPool.addProperty("rolls", 1.0);

        JsonArray thirdPoolEntries = new JsonArray();
        thirdPool.add("entries", thirdPoolEntries);

        JsonObject thirdPoolEntry = new JsonObject();
        thirdPoolEntries.add(thirdPoolEntry);

        thirdPoolEntry.addProperty("type", "minecraft:item");
        thirdPoolEntry.addProperty("name", cropSeedsName);

        JsonArray thirdPoolConditions = new JsonArray();
        thirdPool.add("conditions", thirdPoolConditions);

        JsonObject thirdPoolPrimaryCondition = new JsonObject();
        thirdPoolConditions.add(thirdPoolPrimaryCondition);

        thirdPoolPrimaryCondition.addProperty("condition", "minecraft:random_chance");
        thirdPoolPrimaryCondition.addProperty("chance", block.getDroppedItem().getCropTier().getSeedDupeChance());

        JsonObject thirdPoolAgeCondition = new JsonObject();
        thirdPoolConditions.add(thirdPoolAgeCondition);

        thirdPoolAgeCondition.addProperty("condition", "minecraft:block_state_property");
        thirdPoolAgeCondition.addProperty("block", cropBlockName);

        JsonObject thirdPoolAgeConditionProperties = new JsonObject();
        thirdPoolAgeCondition.add("properties", thirdPoolAgeConditionProperties);

        thirdPoolAgeConditionProperties.addProperty("age", "7");

        JsonArray functions = new JsonArray();
        json.add("functions", functions);

        JsonObject function = new JsonObject();
        functions.add(function);

        function.addProperty("function", "minecraft:explosion_decay");

        return json;
    }

    public static JsonElement createCropsToFlourRecipe(MaterialCropItem item) {
        String cropName = new WheatIdentifier(item.getIdentifierName()).toString();

        JsonObject recipe = new JsonObject();
        recipe.addProperty("type", "minecraft:crafting_shaped");

        JsonArray pattern = new JsonArray();
        recipe.add("pattern", pattern);

        pattern.add("###");
        pattern.add("###");

        JsonObject key = new JsonObject();
        recipe.add("key", key);

        JsonObject hashTagKey = new JsonObject();
        key.add("#", hashTagKey);

        hashTagKey.addProperty("item", cropName);

        JsonObject result = new JsonObject();
        recipe.add("result", result);

        result.addProperty("item", cropName + "_flour");

        return recipe;
    }

    public static JsonElement createFlourToResourceRecipe(MaterialCropItem item) {
        String flourName = new WheatIdentifier(item.getIdentifierName()).toString() + "_flour";
        String resourceName = Registry.ITEM.getId(item.getCropTier().getMaterial()).toString();

        JsonObject recipe = new JsonObject();
        recipe.addProperty("type", "minecraft:smelting");

        JsonObject ingredient = new JsonObject();
        recipe.add("ingredient", ingredient);

        ingredient.addProperty("item", flourName);

        recipe.addProperty("result", resourceName);
        recipe.addProperty("experience", 0.5f);
        recipe.addProperty("cookingtime", 200);

        return recipe;
    }
}
