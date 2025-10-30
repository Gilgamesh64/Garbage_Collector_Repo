package com.mygdx.resources;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.dialogues.GameStory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.EnumMap;
import java.util.Objects;
import java.util.stream.Stream;

public class ResourceManager {

    private final AssetManager manager;
    private final EnumMap<ResourceEnum, GameStory> dialogueMap;
    private final EnumMap<ResourceEnum, TiledMap> maps;

    public ResourceManager() {
        manager = new AssetManager();
        dialogueMap = new EnumMap<>(ResourceEnum.class);
        maps = new EnumMap<>(ResourceEnum.class);
        loadManager();
    }

    private void loadManager() {
        Stream.of(ResourceEnum.values()).forEach(e -> {
            switch (e.type) {
                case TEXTURE -> {
                    manager.load(e.label, Texture.class);
                }
                case AUDIO -> {
                    manager.load(e.label, Music.class);
                }
                case DIALOGUE -> {
                    loadDialogue(e);
                }
                case MAP -> {
                    maps.put(e, new TmxMapLoader().load(e.label));
                }
                case ATLAS -> {
                    manager.load(e.label, TextureAtlas.class);
                }
                case ATLAS_REGION -> {
                }
                default -> {
                }
            }
        });
    }

    public void update() {
        manager.update();
    }

    public void dispose() {
        manager.dispose();
    }

    public Texture getTexture(ResourceEnum e) {
        manager.finishLoadingAsset(e.label);
        return manager.get(e.label);
    }

    public TextureAtlas getAtlas(ResourceEnum e) {
        manager.finishLoadingAsset(e.label);
        return manager.get(e.label);
    }

    public Sprite getFromAtlas(ResourceEnum atlas, ResourceEnum texture){
        return getAtlas(atlas).createSprite(texture.label);
    }

    public TiledMap getMap(ResourceEnum e) {
        return maps.get(e);
    }

    public GameStory getStory(ResourceEnum e) {
        try {
            return dialogueMap.get(e);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }

    public Music getAudio(ResourceEnum e) {
        manager.finishLoadingAsset(e.label);
        return manager.get(e.label);
    }

    public void playAudio(ResourceEnum e) {
        if (!getAudio(e).isPlaying()) {
            getAudio(e).setVolume(0.05f);
            getAudio(e).play();
            getAudio(e).setLooping(true);
        }
    }

    public void stopAudio(ResourceEnum e) {
        if (getAudio(e).isPlaying())
            getAudio(e).stop();
    }

    public void stopAllAudio() {
        Stream.of(ResourceEnum.values())
                .filter(e -> e.type.equals(TypeEnum.AUDIO))
                .map(this::getAudio)
                .filter(Music::isPlaying)
                .forEach(Music::stop);
    }

    // helper for dialogue loading
    public void loadDialogue(ResourceEnum dialogue) {

        InputStream systemResourceAsStream = ClassLoader
                .getSystemResourceAsStream("dialogues/entities/" + Lang.getCurrent() + "/" + dialogue.label);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(systemResourceAsStream), StandardCharsets.UTF_8))) {
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            String json = sb.toString().replace('\uFEFF', ' ');
            dialogueMap.put(dialogue, new GameStory(json));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateLang() {
        dialogueMap.clear();
        Stream.of(ResourceEnum.values())
                .filter(e -> e.type.equals(TypeEnum.DIALOGUE))
                .forEach(this::loadDialogue);
    }
}
