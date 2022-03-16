package ua.com;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {

    private final List<Block> blocks = new LinkedList<>();

    public CompositeBlock createCompositeBlock() {
        CompositeBlock compositeBlock = new CompositeBlock() {
            final List<Block> blockList = new LinkedList<>();

            @Override
            public List<Block> getBlocks() {
                return blockList;
            }

            @Override
            public void createBlock(Block block) {
                blocks.remove(block);
                blockList.add(block);
            }

            @Override
            public void deleteBlock(Block block) {
                blockList.remove(block);
                blocks.add(block);
            }

            @Override
            public Integer checkSize() {
                int size = 1;
                for (Block block : getBlocks()) {
                    if (block instanceof CompositeBlock) {
                        size = size + ((CompositeBlock) block).checkSize();
                    } else {
                        size++;
                    }
                }
                return size;
            }

            @Override
            public Optional findBlockByColor(String color) {
                for (Block block : getBlocks()) {
                    if (block instanceof CompositeBlock) {
                        if (((CompositeBlock) block).findBlockByColor(color).isPresent()) {
                            return ((CompositeBlock) block).findBlockByColor(color);
                        }
                    } else {
                        if (block.getColor().equals(color)) {
                            return Optional.of(block);
                        }
                    }
                }
                return Optional.empty();
            }

            @Override
            public List<Block> findBlocksByMaterial(String material) {
                List<Block> result = new LinkedList<>();
                for (Block block : getBlocks()) {
                    if (block instanceof CompositeBlock) {
                        result.addAll(((CompositeBlock) block).findBlocksByMaterial(material));
                    } else {
                        if (block.getMaterial().equals(material)) {
                            result.add(block);
                        }
                    }
                }
                return result;
            }

            @Override
            public String toString() {
                return "CompositeBlock{" +
                        blockList + '}';
            }

            @Override
            public String getColor() {
                StringBuilder result = new StringBuilder();
                for (Block block : getBlocks()) {
                    if (block instanceof CompositeBlock) {
                        result.append(block.getColor());
                    } else {
                        result.append(block.getColor()).append(" ");
                    }
                }
                return result.toString();
            }

            @Override
            public String getMaterial() {
                StringBuilder result = new StringBuilder();
                for (Block block : getBlocks()) {
                    if (block instanceof CompositeBlock) {
                        result.append(block.getMaterial());
                    } else {
                        result.append(block.getMaterial()).append(" ");
                    }
                }
                return result.toString();
            }
        };
        blocks.add(compositeBlock);
        return compositeBlock;
    }

    @Override
    public Optional findBlockByColor(String color) {
        for (Block block : blocks) {
            if (block instanceof CompositeBlock) {
                if (((CompositeBlock) block).findBlockByColor(color).isPresent()) {
                    return ((CompositeBlock) block).findBlockByColor(color);
                }
            }
            if (block.getColor().equals(color)) {
                return Optional.of(block);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> result = new LinkedList<>();
        for (Block block : blocks) {
            if (block instanceof CompositeBlock) {
                result.addAll(((CompositeBlock) block).findBlocksByMaterial(material));
            }
            if (block.getMaterial().equals(material)) {
                result.add(block);
            }
        }
        return result;
    }

    @Override
    public int count() {
        int size = 0;
        for (Block block : blocks) {
            if (block instanceof CompositeBlock) {
                size = size + ((CompositeBlock) block).checkSize();
            } else {
                size++;
            }
        }
        return size;
    }

    public Block createBlock(String color, String material) {
        Block block = new Block() {

            @Override
            public String getColor() {
                return color;
            }

            @Override
            public String getMaterial() {
                return material;
            }

            @Override
            public String toString() {
                return "Block{" + this.getColor() + ", " + this.getMaterial() + "}";
            }
        };
        blocks.add(block);
        return block;
    }

    public static void main(String[] args) {
        Wall wall = new Wall();

        Block block1 = wall.createBlock("white", "iron");
        Block block2 = wall.createBlock("red", "brick");
        Block block3 = wall.createBlock("black", "rock");
        Block block4 = wall.createBlock("blue", "ice");
        Block block5 = wall.createBlock("green", "brick");

        CompositeBlock compositeBlock1 = wall.createCompositeBlock();
        CompositeBlock compositeBlock2 = wall.createCompositeBlock();
        CompositeBlock compositeBlock3 = wall.createCompositeBlock();
        CompositeBlock compositeBlock4 = wall.createCompositeBlock();
        CompositeBlock compositeBlock5 = wall.createCompositeBlock();

        compositeBlock1.createBlock(block1);
        compositeBlock1.createBlock(block2);
        compositeBlock2.createBlock(block2);
        compositeBlock2.createBlock(block3);
        compositeBlock3.createBlock(block3);
        compositeBlock3.createBlock(block4);
        compositeBlock4.createBlock(block4);
        compositeBlock4.createBlock(block5);
        compositeBlock5.createBlock(block5);
        compositeBlock5.createBlock(block1);

        System.out.println("All Block List");
        System.out.println();
        System.out.println(wall.blocks);
        System.out.println();
        System.out.println("Count = " + wall.count());
        System.out.println();
        System.out.println("Find Block By Color");
        System.out.println();
        System.out.print("white:");
        System.out.println(wall.findBlockByColor("white"));
        System.out.print("red:");
        System.out.println(wall.findBlockByColor("red"));
        System.out.print("black:");
        System.out.println(wall.findBlockByColor("black"));
        System.out.print("blue:");
        System.out.println(wall.findBlockByColor("blue"));
        System.out.print("green:");
        System.out.println(wall.findBlockByColor("green"));
        System.out.println();

        System.out.println("Find Blocks By Material");
        System.out.println();
        System.out.println(wall.findBlocksByMaterial("iron"));
        System.out.println(wall.findBlocksByMaterial("brick"));
        System.out.println(wall.findBlocksByMaterial("rock"));
        System.out.println(wall.findBlocksByMaterial("ice"));
    }
}
