package org.geogebra.common.gui.toolcategorization.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.geogebra.common.gui.toolcategorization.ToolCategory;
import org.geogebra.common.gui.toolcategorization.ToolCollection;
import org.geogebra.common.gui.toolcategorization.ToolCollectionFilter;
import org.geogebra.common.gui.toolcategorization.ToolsetLevel;

/**
 * Generic implementation of a ToolCollection.
 */
class ToolCollectionImpl implements ToolCollection {

	private int level = -1;
	private List<ToolsCollection> collections = new ArrayList<>();
	private List<String> levels = new ArrayList<>();

    private static class ToolsCollection {
        private List<ToolCategory> categories = new ArrayList<>();
        private List<List<Integer>> tools = new ArrayList<>();
    }

    void addLevel(ToolsetLevel toolsetLevel) {
        levels.add(toolsetLevel.getLevel());
        collections.add(new ToolsCollection());
        level += 1;
    }

    void addCategory(ToolCategory category, List<Integer> tools) {
        ToolsCollection collection = collections.get(level);
        collection.categories.add(category);
        collection.tools.add(tools);
    }

    void addCategory(ToolCategory category, Integer... tools) {
        addCategory(category, Arrays.asList(tools));
    }

    void extendCategory(ToolCategory category, List<Integer> tools) {
        if (level == 0) {
            addCategory(category, tools);
        } else {
            int previousLevel = level - 1;
            int categoryIndex = collections.get(previousLevel)
                    .categories.indexOf(category);
            if (categoryIndex < 0) {
                addCategory(category, tools);
            } else {
                List<Integer> previousTools =
                        collections.get(previousLevel).tools.get(categoryIndex);
                List<Integer> newTools = new ArrayList<>(previousTools);
                newTools.addAll(tools);
                addCategory(category, newTools);
            }
        }
    }

    void extendCategory(ToolCategory category, Integer... tools) {
        extendCategory(category, Arrays.asList(tools));
    }

    @Override
    public List<ToolCategory> getCategories() {
        return collections.get(level).categories;
    }

    @Override
    public List<Integer> getTools(int category) {
        return collections.get(level).tools.get(category);
    }

    @Override
    public List<String> getLevels() {
        return levels;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        if (level < 0 || level >= levels.size()) {
            throw new UnsupportedOperationException("Level size not in range");
        }
        this.level = level;
    }

    @Override
    public void filter(ToolCollectionFilter filter) {
        for (ToolsCollection collection: collections) {
            for (int i = collection.tools.size() - 1; i >= 0; i--) {
                List<Integer> tools = collection.tools.get(i);
                List<Integer> filteredTools = new ArrayList<>();
                for (Integer tool : tools) {
                    if (filter.filter(tool)) {
                        filteredTools.add(tool);
                    }
                }
                if (filteredTools.size() == 0) {
                    collection.tools.remove(i);
                    collection.categories.remove(i);
                } else {
                    collection.tools.set(i, filteredTools);
                }
            }
        }
    }
}
