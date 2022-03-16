package ua.com;

import java.util.List;
import java.util.Optional;

public interface CompositeBlock extends Block {

    List<Block> getBlocks();
    void createBlock(Block block);
    void deleteBlock(Block block);
    Integer checkSize();
    Optional findBlockByColor(String color);
    List<Block> findBlocksByMaterial(String material);
}
