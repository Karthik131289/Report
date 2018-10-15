package com.wegot.venaqua.report.ws.response.tree;

import java.util.ArrayList;
import java.util.List;

public class BlockLevelUsageResponse {
    private String name;
    private List<BlockInfo> blocks = new ArrayList<>();

    public BlockLevelUsageResponse() {
    }

    public BlockLevelUsageResponse(String name) {
        this.name = name;
    }

    public BlockLevelUsageResponse(String name, List<BlockInfo> blocks) {
        this.name = name;
        this.blocks = blocks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BlockInfo> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<BlockInfo> blocks) {
        this.blocks = blocks;
    }

    public void addBlock(BlockInfo block) {
        this.blocks.add(block);
    }

    public void addBlock(List<BlockInfo> blocks) {
        this.blocks.addAll(blocks);
    }

    public BlockInfo getBlock(String name) {
        for (BlockInfo block : blocks) {
            if (block.getName().equals(name))
                return block;
        }
        return null;
    }
}
