import entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Island {
    private final int width;
    private final int height;
    private List<Entity>[][] locations;

    @SuppressWarnings("unchecked")
    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.locations = new List[width][height];
        initializeIsland();
    }

    private void initializeIsland() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.locations[i][j] = new ArrayList<>();
            }
        }

        
    }


}
