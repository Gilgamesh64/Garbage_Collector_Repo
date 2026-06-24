package com.mygdx.map;

import java.util.ArrayList;
import java.util.HashMap;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PointMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.GCStage;
import com.mygdx.entities.map.Building;
import com.mygdx.entities.map.Component;
import com.mygdx.entities.map.InvisibleDoor;
import com.mygdx.entities.map.TextureDoor;
import com.mygdx.resources.RM;
import com.mygdx.resources.enums.AtlasEnum;
import com.mygdx.resources.enums.MapEnum;
import com.mygdx.resources.enums.TextureEnum;

public class TileSetManager implements Telegraph {
    private final TiledMapRenderer tiledMapRenderer;
    private final TiledMap map;

    private final ArrayList<TileReplacementManager> tileReplace = new ArrayList<>();

    public final HashMap<String, Vector2> markersMap = new HashMap<>();

    public TileSetManager(MapEnum e) {
        map = RM.get().getMap(e);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);

        MapLayer doorLayer = map.getLayers().get("doors");
        if (doorLayer == null)
            System.out.println("Are you aware there is no door layer right?");

        else loadDoors(doorLayer);

        MapLayer buildingsLayer = map.getLayers().get("buildings");
        if (buildingsLayer == null)
            System.out.println("Are you aware there is no buildings layer right?");
        
        else loadBuildings(buildingsLayer);

        MapLayer componentsLayer = map.getLayers().get("components");
        if (componentsLayer == null)
            System.out.println("Are you aware there is no components layer right?");
        
        else loadComponents(componentsLayer);

        MapLayer markersLayer = map.getLayers().get("markers");
        if (markersLayer == null)
            System.out.println("Are you aware there is no markers layer right?");
        
        else loadMarkers(markersLayer);

        loadReplacers();
    }

    public void render(OrthographicCamera camera) {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    public TiledMap getMap() {
        return map;
    }

    private void loadBuildings(MapLayer buildingsLayer) {

        for (MapObject obj : buildingsLayer.getObjects()) {
            if (obj instanceof RectangleMapObject rectObj) {
                Rectangle rect = rectObj.getRectangle();

                TextureEnum texture = TextureEnum.valueOf(obj.getName());

                TextureRegion region = RM.get().getAtlas(AtlasEnum.BUILDINGS).findRegion(texture.path);
                if (region == null) {
                    throw new RuntimeException("\nERROR\n\nResourceEnum: " + texture +
                            " not found in region, \nthe .png file should be in the folder: assets/raw/" + AtlasEnum.BUILDINGS
                            + " \ncheck your spelling and pack all the assets");
                }
                GCStage.get().addActor(new Building(rect.getX(), rect.getY(), texture));

            }
        }

    }

    private void loadComponents(MapLayer componentsLayer) {

        for (MapObject obj : componentsLayer.getObjects()) {
            if (obj instanceof RectangleMapObject rectObj) {
                Rectangle rect = rectObj.getRectangle();

                TextureEnum texture = TextureEnum.valueOf(obj.getName());

                TextureRegion region = RM.get().getAtlas(AtlasEnum.COMPONENTS).findRegion(texture.path);
                if (region == null) {
                    throw new RuntimeException("\nERROR\n\nResourceEnum: " + texture +
                            " not found in region, \nthe .png file should be in the folder: assets/raw/" + AtlasEnum.COMPONENTS
                            + " \ncheck your spelling and pack all the assets");
                }
                GCStage.get().addActor(new Component(rect.getX(), rect.getY(), texture));
            }
        }
    }

    private void loadDoors(MapLayer layer) {
        for (MapObject obj : layer.getObjects()) {
            if (obj instanceof RectangleMapObject rectObj) {
                Rectangle rect = rectObj.getRectangle();
                String name = obj.getName();
                String dst = name.split("-")[1] + "-" + name.split("-")[0];
                String dir = obj.getProperties().get("dir").toString();
                float x = rect.getX(), y = rect.getY();
                float width = rect.getWidth(), height = rect.getHeight();
                GCStage.get().addActor(new InvisibleDoor(name, dst, dir, x, y, width, height));

            } else if (obj instanceof PointMapObject pointObj) {
                String name = obj.getName();
                String dst = name.split("-")[1] + "-" + name.split("-")[0];
                String dir = obj.getProperties().get("dir").toString();
                int size = obj.getProperties().get("size") == null ? 1 : ((int) obj.getProperties().get("size"));
                float x = pointObj.getPoint().x - (16 * size), y = pointObj.getPoint().y;
                String texture = obj.getProperties().get("texture").toString();
                GCStage.get().addActor(new TextureDoor(name, dst, dir, x, y, size, texture));
            }
        }
    }

    private void loadReplacers() {

        for (TiledMapTile tile : map.getTileSets().getTileSet(0)) {

            var prop = tile.getProperties();

            if (prop.get("category") == null || prop.get("blocker") == null)
                continue;

            tileReplace.add(new TileReplacementManager(map,
                    TileReplacementEnum.valueOf(prop.get("category").toString()), tile));
        }
    }

    private void loadMarkers(MapLayer markersLayer) {
        for (MapObject marker : markersLayer.getObjects()) {
            if (marker instanceof PointMapObject pointMarker) {
                Vector2 coords = pointMarker.getPoint();
                markersMap.put(marker.getName(), coords);
            }
        }
    }



    @Override
    public boolean handleMessage(Telegram msg) {
        for (TileReplacementManager m : tileReplace) {
            if (msg.message == m.getCategory().getMsg().code) {
                m.handle();
                System.out.println("handled");
            }
        }

        return true;
    }

}
