package fr.zcraft.moustache.listeners;

import fr.zcraft.moustache.Moustache;
import fr.zcraft.quartzlib.core.QuartzComponent;
import fr.zcraft.quartzlib.tools.PluginLogger;
import fr.zcraft.quartzlib.tools.items.ItemStackBuilder;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import net.skinsrestorer.api.PlayerWrapper;
import net.skinsrestorer.shared.exception.SkinRequestException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public final class PlayersListener extends QuartzComponent implements Listener {

    private boolean isAprilFools() {
        Date now = new Date();
        Calendar instance1 = Calendar.getInstance();
        instance1.set(now.getYear(), 3, 1);
        Date firstApril = instance1.getTime();
        Calendar instance2 = Calendar.getInstance();
        instance2.set(now.getYear(), 3, 4);

        Date fourthApril = instance2.getTime();
        PluginLogger.info("first> " + now.after(firstApril));
        PluginLogger.info("4th< " + now.before(fourthApril));
        return now.after(firstApril) && now.before(fourthApril);


    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        if (isAprilFools()) {
            PluginLogger.info("It's april fools!");
            UUID uuid = event.getPlayer().getUniqueId();


            Player player = event.getPlayer();
            String skin = "easter" + uuid.toString().replace("-", "");


            try {
                // setskin for player skin
                Moustache.getSkinrestorerAPI().setSkin(player.getName(), skin);


            } catch (SkinRequestException e) {
                try {
                    Moustache.getSkinrestorerAPI().setSkin(player.getName(), "default");
                } catch (SkinRequestException ex) {
                    PluginLogger.warning("Can't setskin " + ex);
                }
            }
            Moustache.getSkinrestorerAPI().applySkin(new PlayerWrapper(player));
        }
    }

    @EventHandler
    public void onPlayerInteractAtEntityEvent(PlayerInteractAtEntityEvent event) {

        if (!(event.getRightClicked() instanceof Player)) {
            return;
        }
        if (!(event.getHand().toString().equals("HAND"))) {
            return;
        }


        Player player = event.getPlayer();
        if(!(player.getLocation().getWorld().getName().equals("V5")||player.getLocation().getWorld().getName().equals("V5_nether")||player.getLocation().getWorld().getName().equals("V5_the_end"))){
            return;
        }
        UUID playerClickedOn = event.getRightClicked().getUniqueId();
        ItemStack itemInHand = event.getPlayer().getItemInHand();
        Location loc = player.getLocation();
        switch (playerClickedOn.toString()) {
            case "63a8f94e-9d97-45dd-a253-4e40c8ddd4d2": //Sigismund  charbon + particules de fumee + message chat
                loc.getWorld().dropItem(loc,
                        new ItemStackBuilder(Material.COAL, 1).title(ChatColor.BLACK, "Charbon de Sigicoal")
                                .craftItem());
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1, 1);
                ((Player) event.getRightClicked()).spawnParticle(Particle.SMOKE_LARGE, loc, 4);
                player.sendMessage("Tiens prends ca, cadeau de SigiCoal");
                break;
            case "c1268959-9093-4dfe-aac8-d7fa2cc0b5c5"://Swaps becherelle conditionnel + message chat

                ItemStack writtenBook = new ItemStack(Material.WRITTEN_BOOK);
                BookMeta bookMeta = (BookMeta) writtenBook.getItemMeta();
                bookMeta.setTitle("Bescherelle");
                bookMeta.setAuthor("La langue française");
                bookMeta.setPages("Je vois très souvent l’erreur suivante, dans le chat IG ou sur le fofo :" +
                                "    Je verrais ce que je peux faire.",
                        "La version sans faute :" +
                                "    Je verrai ce que je peux faire. " +
                                "En effet, le futur ne prend pas de “s” à la première personne du singulier : “Demain, je mangerai des pommes.” et non “Demain, je mangerais des pommes.” ",
                        "Quand il y a ce fameux “s”, c’est que c’est le conditionnel. “Demain, je mangerais bien des pommes.” est correct car c’est le conditionnel. Vous voyez la nuance ? Ou encore : “Si tu m’aidais un peu, j’aurais fini cette capitale depuis longtemps.” est correct aussi. Le conditionnel, donc."
                        ,
                        "Un truc pour le savoir facilement : quand vous hésitez, dites votre phrase à voix haute à la première personne du pluriel : diriez-vous" +
                                "    Nous verrions ce que nous pouvons faire." +
                                "ou" +
                                "    Nous verrons ce que nous pouvons faire." +
                                "?",
                        "La bonne phrase est la deuxième, of course. Pas de “i” au pluriel -> pas de “s” au singulier, car c’est le futur." +
                                "En espérant que ça vous a aidé à bannir cette faute de votre catalogue.");
                writtenBook.setItemMeta(bookMeta);
                loc.getWorld().dropItem(loc, writtenBook);
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1, 1);

                player.sendMessage("Je verrais ce que je peux faire.");

                break;
            case "da04cd54-c6c7-4672-97c5-85663f5bccf6":

                if (Math.random() < 0.1) {
                    if (Math.random() > 0.75) {
                        loc.getWorld().dropItem(loc,
                                new ItemStackBuilder(Material.LEATHER_HELMET, 1)
                                        .title(ChatColor.DARK_GRAY, "Casque spatial")
                                        .craftItem());
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1, 1);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 5, 1));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 5, 255));

                        player.playSound(player.getLocation().add(0, 100, 0), Sound.ENTITY_LIGHTNING_BOLT_THUNDER,
                                SoundCategory.PLAYERS, 1, 1);

                        player.teleport(loc.add(0, 100, 0));

                        player.sendMessage("Ce vol vous est proposé par Amaury Space Program");
                    } else {
                        loc.getWorld().dropItem(loc,
                                new ItemStackBuilder(Material.PUMPKIN_PIE, 1)
                                        .title(ChatColor.GOLD, "3,14159265...")
                                        .craftItem());
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1, 1);
                        player.sendMessage("Ce non-vol vous est proposé par Amaury Space Program");
                    }
                }
                break;
            case "f961a66d-8903-481b-8edd-e1bcc0955147"://Labrik0  champignon  + message "Ready for adventure!"
                loc.getWorld().dropItem(loc,
                        new ItemStackBuilder(Material.RED_MUSHROOM, 1).title(ChatColor.RED, "1UP").craftItem());
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1, 1);
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 7 * 20, 1));
                player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EAT, SoundCategory.PLAYERS, 1, 1);
                player.sendMessage("Ready for adventure! J'ai abusé sur les champis je crois...");
                break;
            case "55b89885-673c-4458-a8bd-33ad9c6190b4": //DadaE4  fireball (fumigène) +fumée + message "NINJA!"
                loc.getWorld().dropItem(loc,
                        new ItemStackBuilder(Material.FIRE_CHARGE, 1).title(ChatColor.GOLD, "Lacrymogène ninja")
                                .craftItem());
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1, 1);
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 5 * 20, 1));
                player.sendMessage("Disparition Ninja!");

                break;
            case "0ee069bf-a172-47fa-97b9-4ebdb0d6dfeb"://Zedoune pufferfish openbsd
                loc.getWorld().dropItem(loc,
                        new ItemStackBuilder(Material.PUFFERFISH, 1).title(ChatColor.YELLOW, "OpenBSD").craftItem());
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1, 1);
                player.sendMessage("Le libre c'est bon mangez en!");
                break;
            /*case "de79b165-a66c-4faf-bdcf-c120487efe78": //tyranouille seau de bave


                if (itemInHand.isSimilar(new ItemStack(Material.BUCKET, 1))) {
                    player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);

                    loc.getWorld().dropItem(loc,
                            new ItemStackBuilder(Material.WATER_BUCKET, 1).title(ChatColor.BLUE, "Seau de bave")
                                    .craftItem());
                    player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1, 1);
                    player.sendMessage(
                            "Mi tyrannosaure mi bol de nouille, j'ai été quasi modo et maintenant j'ai faim.");

                    try {
                        Moustache.getSkinrestorerAPI()
                                .setSkin(Bukkit.getPlayer(playerClickedOn).getName(), "tyrapropre");
                    } catch (SkinRequestException e) {
                        PluginLogger.warning("Can't setskin " + e);
                    }
                    Moustache.getSkinrestorerAPI().applySkin(new PlayerWrapper(Bukkit.getPlayer(playerClickedOn)));

                    Bukkit.getScheduler().scheduleSyncDelayedTask(Moustache.getPlugin(), new Runnable() {
                        public void run() {
                            try {
                                Moustache.getSkinrestorerAPI().setSkin(Bukkit.getPlayer(playerClickedOn).getName(),
                                        Bukkit.getPlayer(playerClickedOn).getName());

                                Moustache.getSkinrestorerAPI()
                                        .applySkin(new PlayerWrapper(Bukkit.getPlayer(playerClickedOn)));
                            } catch (SkinRequestException e) {
                                PluginLogger.warning("Can't change back the player skin " + e);
                            }
                        }
                    }, 20L * 60L);
                }
                break;*/

            case "00a1669a-d12c-4c2a-afac-c69374dc2036": //DoubleNom
                if (Math.random() < 0.1) {
                    if (itemInHand.isSimilar(new ItemStack(Material.BUCKET, 1))) {
                        player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);

                        loc.getWorld().dropItem(loc,
                                new ItemStackBuilder(Material.MILK_BUCKET, 1)
                                        .title(ChatColor.LIGHT_PURPLE, "Seau de lait")
                                        .craftItem());
                        player.playSound(player.getLocation(), Sound.ENTITY_COW_MILK, SoundCategory.PLAYERS, 1, 1);
                    }
                }

            case "a9ec7532-3347-4fe7-a711-dd8885f20f64": //Magma
                if (itemInHand.isSimilar(new ItemStack(Material.BUCKET, 1))) {
                    player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);

                    loc.getWorld().dropItem(loc,
                            new ItemStackBuilder(Material.LAVA_BUCKET, 1).title(ChatColor.RED, "Seau de magma")
                                    .craftItem());
                    player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, SoundCategory.PLAYERS, 1, 1);
                    try {
                        Moustache.getSkinrestorerAPI()
                                .setSkin(Bukkit.getPlayer(playerClickedOn).getName(), "magmacold");
                    } catch (SkinRequestException e) {
                        PluginLogger.warning("Can't setskin " + e);
                    }
                    Moustache.getSkinrestorerAPI().applySkin(new PlayerWrapper(Bukkit.getPlayer(playerClickedOn)));

                    Bukkit.getScheduler().scheduleSyncDelayedTask(Moustache.getPlugin(), new Runnable() {
                        public void run() {
                            try {
                                Moustache.getSkinrestorerAPI().setSkin(Bukkit.getPlayer(playerClickedOn).getName(),
                                        Bukkit.getPlayer(playerClickedOn).getName());

                                Moustache.getSkinrestorerAPI()
                                        .applySkin(new PlayerWrapper(Bukkit.getPlayer(playerClickedOn)));
                            } catch (SkinRequestException e) {
                                PluginLogger.warning("Can't change back the player skin " + e);
                            }
                        }
                    }, 20L * 60L);
                }


                break;


            case "5e9298d9-5a53-4744-88f2-70f221ff8e66": //Moderatrice a moustache
                if (itemInHand.isSimilar(new ItemStack(Material.SHEARS, 1))) {


                    loc.getWorld().dropItem(loc,
                            new ItemStackBuilder(Material.STRING, 1).title(ChatColor.BLACK, "Moustache de BlackLizard")
                                    .craftItem());
                    player.playSound(player.getLocation(), Sound.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1, 1);
                    try {
                        Moustache.getSkinrestorerAPI().setSkin(Bukkit.getPlayer(playerClickedOn).getName(),
                                "easter5e9298d95a53474488f270f221ff8e66");
                    } catch (SkinRequestException e) {
                        PluginLogger.warning("Can't set skin " + e);
                    }
                    Moustache.getSkinrestorerAPI().applySkin(new PlayerWrapper(Bukkit.getPlayer(playerClickedOn)));

                    Bukkit.getScheduler().scheduleSyncDelayedTask(Moustache.getPlugin(), new Runnable() {
                        public void run() {
                            try {
                                Moustache.getSkinrestorerAPI().setSkin(Bukkit.getPlayer(playerClickedOn).getName(),
                                        Bukkit.getPlayer(playerClickedOn).getName());

                                Moustache.getSkinrestorerAPI()
                                        .applySkin(new PlayerWrapper(Bukkit.getPlayer(playerClickedOn)));
                            } catch (SkinRequestException e) {
                                PluginLogger.warning("Can't change back the player skin " + e);
                            }
                        }
                    }, 20L * 60L);
                }
                break;

            default:
                return;
        }


    }
}
