package fr.zcraft.moustache.listeners;

import fr.zcraft.moustache.Moustache;
import fr.zcraft.quartzlib.core.QuartzComponent;
import fr.zcraft.quartzlib.tools.PluginLogger;
import fr.zcraft.quartzlib.tools.items.ItemStackBuilder;
import java.util.UUID;
import net.skinsrestorer.api.PlayerWrapper;
import net.skinsrestorer.shared.exception.SkinRequestException;
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


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();


        Player player = event.getPlayer();
        String skin = "easter"+uuid.toString().replace("-","");


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

    @EventHandler
    public void onPlayerInteractAtEntityEvent(PlayerInteractAtEntityEvent event) {

        if (!(event.getRightClicked() instanceof Player)) {
            return;
        }
        if (!(event.getHand().toString().equals("HAND"))) {
            return;
        }

        Player player = event.getPlayer();
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
                loc.getWorld().dropItem(loc,
                        new ItemStackBuilder(Material.LEATHER_HELMET, 1).title(ChatColor.DARK_GRAY, "Casque spatial")
                                .craftItem());
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1, 1);
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 5, 1));

                player.playSound(player.getLocation().add(0, 666, 0), Sound.ENTITY_LIGHTNING_BOLT_THUNDER,
                        SoundCategory.PLAYERS, 1, 1);

                player.teleport(loc.add(0, 666, 0));

                player.sendMessage("Ce vol vous est proposé par ASP (Amaury Space Program)");
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
            case "de79b165-a66c-4faf-bdcf-c120487efe78": //tyranouille sceau de bave


                if (itemInHand.isSimilar(new ItemStack(Material.BUCKET, 1))) {
                    player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);

                    loc.getWorld().dropItem(loc,
                            new ItemStackBuilder(Material.WATER_BUCKET, 1).title(ChatColor.BLUE, "Sceau de bave")
                                    .craftItem());
                    player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1, 1);
                    player.sendMessage(
                            "Mi tyrannosaure mi bol de nouille, j'ai été quasi modo et maintenant j'ai faim.");

                    try {
                        Moustache.getSkinrestorerAPI().setSkin(player.getName(), "tyrapropre");
                    } catch (SkinRequestException e) {
                        PluginLogger.warning("Can't setskin " + e);
                    }
                    Moustache.getSkinrestorerAPI().applySkin(new PlayerWrapper(player));
                }
                break;

            case "a9ec7532-3347-4fe7-a711-dd8885f20f64": //Magma
                if (itemInHand.isSimilar(new ItemStack(Material.BUCKET, 1))) {
                    player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);

                    loc.getWorld().dropItem(loc,
                            new ItemStackBuilder(Material.LAVA_BUCKET, 1).title(ChatColor.RED, "Sceau de magma")
                                    .craftItem());
                    player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, SoundCategory.PLAYERS, 1, 1);
                    try {
                        Moustache.getSkinrestorerAPI().setSkin(player.getName(), "magmacold");
                    } catch (SkinRequestException e) {
                        PluginLogger.warning("Can't setskin " + e);
                    }
                    Moustache.getSkinrestorerAPI().applySkin(new PlayerWrapper(player));
                }

                break;
            default:
                return;
        }


    }
}
