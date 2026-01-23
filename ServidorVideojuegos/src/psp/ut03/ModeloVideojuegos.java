package psp.ut03;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ModeloVideojuegos {
    private List<Videojuego> videojuegos;

    public ModeloVideojuegos() {
        Videojuego[] videojuegosArray = {
            new Videojuego(1,"The Witcher 3", "RPG", "CD Projekt Red", 18, "portadas/witcher3.jpeg"),
            new Videojuego(2,"Cyberpunk 2077", "RPG", "CD Projekt Red", 18, "portadas/cyberpunk2077.jpg"),
            new Videojuego(3,"God of War", "Acción", "Santa Monica Studio", 18, "portadas/godofwar.jpg"),
            new Videojuego(4,"The Last of Us Part II", "Aventura", "Naughty Dog", 18, "portadas/tlou2.jpg"),
            new Videojuego(5,"Halo Infinite", "FPS", "343 Industries", 16, "portadas/haloinfinite.jpg"),
            new Videojuego(6,"Minecraft", "Sandbox", "Mojang", 7, "portadas/minecraft.jpg"),
            new Videojuego(7,"Fortnite", "Battle Royale", "Epic Games", 12, "portadas/fortnite.jpg"),
            new Videojuego(8,"Call of Duty: Modern Warfare", "FPS", "Infinity Ward", 18, "portadas/codmw.jpg"),
            new Videojuego(9,"Red Dead Redemption 2", "Aventura", "Rockstar Games", 18, "portadas/rdr2.jpg"),
            new Videojuego(10,"Grand Theft Auto V", "Acción", "Rockstar Games", 18, "portadas/gtav.jpeg"),
            new Videojuego(11,"Super Mario Odyssey", "Plataformas", "Nintendo", 7, "portadas/marioodyssey.jpg"),
            new Videojuego(12,"The Legend of Zelda: Breath of the Wild", "Aventura", "Nintendo", 12, "portadas/zelda_botw.jpg"),
            new Videojuego(13,"Animal Crossing: New Horizons", "Simulación", "Nintendo", 3, "portadas/animalcrossing.jpg"),
            new Videojuego(14,"FIFA 23", "Deportes", "EA Sports", 3, "portadas/fifa23.jpg"),
            new Videojuego(15,"NBA 2K23", "Deportes", "2K Sports", 3, "portadas/nba2k23.jpg"),
            new Videojuego(16,"Overwatch 2", "FPS", "Blizzard Entertainment", 12, "portadas/overwatch2.jpg"),
            new Videojuego(17,"World of Warcraft", "MMORPG", "Blizzard Entertainment", 12, "portadas/wow.jpg"),
            new Videojuego(18,"Dark Souls III", "RPG", "FromSoftware", 16, "portadas/darksouls3.jpeg"),
            new Videojuego(19,"Elden Ring", "RPG", "FromSoftware", 16, "portadas/eldenring.jpg"),
            new Videojuego(20,"Street Fighter V", "Lucha", "Capcom", 12, "portadas/streetfighterv.jpg"),
            new Videojuego(21,"Mortal Kombat 11", "Lucha", "NetherRealm Studios", 18, "portadas/mortalkombat11.jpeg"),
            new Videojuego(22,"Tekken 7", "Lucha", "Bandai Namco", 16, "portadas/tekken7.jpeg"),
            new Videojuego(23,"Assassin's Creed Valhalla", "Acción", "Ubisoft", 18, "portadas/acvalhalla.jpg"),
            new Videojuego(24,"Far Cry 6", "FPS", "Ubisoft", 18, "portadas/farcry6.jpg"),
            new Videojuego(25,"Destiny 2", "FPS", "Bungie", 16, "portadas/destiny2.jpg"),
            new Videojuego(26,"Battlefield 2042", "FPS", "DICE", 18, "portadas/bf2042.jpeg"),
            new Videojuego(27,"Among Us", "Social Deduction", "InnerSloth", 7, "portadas/amongus.jpg"),
            new Videojuego(28,"Hades", "Roguelike", "Supergiant Games", 12, "portadas/hades.jpg"),
            new Videojuego(29,"Celeste", "Plataformas", "Matt Makes Games", 7, "portadas/celeste.jpg"),
            new Videojuego(30,"Hollow Knight", "Metroidvania", "Team Cherry", 7, "portadas/hollowknight.jpg"),
            new Videojuego(31,"Fishing Planet","Simulador", "Fishing Planet LLC",0,"portadas/fishing.jpg"),
            new Videojuego(32,"Hollow Knight:Silksong","Metroidvania","Team Cherry",7,"portadas/silksong.jpeg"),
            new Videojuego(33,"The Binding of Isaac: Rebirth","Roguelike","Nicalis",12,"portadas/isaac.jpg")
        };
                                
        videojuegos = Arrays.asList(videojuegosArray);        
    }

    public List<String> getGeneros(){
        return videojuegos.stream()
                .map(videojuego -> videojuego.getGenero())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
    
    public List<String> getDesarrolladora(){
        return videojuegos.stream()
                .map(videojuego -> videojuego.getDesarrolladora())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
    
    public  Videojuego getById(int id){
        return videojuegos.stream()
                          .filter(videojuego -> videojuego.getId() == id)
                          .findFirst().orElse(null);
    }
    
    public List<Videojuego> filterAll(String titulo, String genero, String desarrolladora){
        return videojuegos.stream()
                .filter(videojuego -> ((genero == null) || videojuego.getGenero().equalsIgnoreCase(genero)) &&
                                      ((desarrolladora == null) || videojuego.getDesarrolladora().equalsIgnoreCase(desarrolladora)) &&
                                      ((titulo == null) || videojuego.getTitulo().startsWith(titulo)))                          
                .sorted((v1, v2) -> v1.getTitulo().compareToIgnoreCase(v2.getTitulo()))
                .collect(Collectors.toList());
    }        
    
}
