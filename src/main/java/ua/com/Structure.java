package ua.com;

import java.util.List;
import java.util.Optional;

public interface Structure {

    Optional findBlockByColor(String color);
    List<Block> findBlocksByMaterial(String material);
    int count();
}
