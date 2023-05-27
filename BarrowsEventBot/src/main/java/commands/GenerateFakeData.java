package commands;

import java.util.ArrayList;
import java.util.Random;

import data.BarrowsItem;
import data.Competition;
import data.Entry;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class GenerateFakeData extends Command{
    ArrayList<String> itemsList = new ArrayList<>() {{add("Ahrim's hood"); add("Ahrim's top"); add("Ahrim's bottoms"); add("Ahrim's staff"); add("Dharok's helm"); add("Dharok's platebody"); add("Dharok's platelegs"); add("Dharok's greataxe"); add("Verac's helm"); add("Verac's brassard"); add("Verac's plateskirt"); add("Verac's flail"); add("Torag's helm"); add("Torag's platebody"); add("Torag's platelegs"); add("Torag's hammers"); add("Karil's coif"); add("Karil's top"); add("Karil's skirt"); add("Karil's crossbow"); add("Guthan's helm"); add("Guthan's platebody"); add("Guthan's chainskirt"); add("Guthan's spear");}};

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();

        // event.getName() used for name of the command used
        // look into having an option choose between single user and multiple users
        generateSingleSet(event.getOption("name").getAsString(), event.getOption("amount").getAsInt());

        event.getHook().sendMessage("Entries have been created").queue();;
    }

    public void generateSingleSet(String name, int amount){
        for(int i = 0; i < amount;  i++){
            entryCreation(name);
        }
    }
    
    public void entryCreation (String name){
        Random rand = new Random();
        Entry entry = new Entry();

        entry.owner = name;
        entry.item = getItemFromEntry(itemsList.get(rand.nextInt(itemsList.size())));

        Competition.getInstance().addEntry(entry);
    }

    private BarrowsItem getItemFromEntry(String item){
        return switch (item){
            case "Ahrim's hood" -> BarrowsItem.AHRIMS_HOOD;
            case "Ahrim's top" ->  BarrowsItem.AHRIMS_TOP;
            case "Ahrim's bottoms" -> BarrowsItem.AHRIMS_BOTTOMS;
            case "Ahrim's staff" ->  BarrowsItem.AHRIMS_STAFF;
            case "Dharok's helm" ->  BarrowsItem.DHAROKS_HELM;
            case "Dharok's platebody" ->  BarrowsItem.DHAROKS_PLATEBODY;
            case "Dharok's platelegs" ->  BarrowsItem.DHAROKS_PLATELEGS;
            case "Dharok's greataxe" ->  BarrowsItem.DHAROKS_AXE;
            case "Verac's helm" ->  BarrowsItem.VERACS_HELM;
            case "Verac's brassard" ->  BarrowsItem.VERACS_TOP;
            case "Verac's plateskirt" ->  BarrowsItem.VERACS_SKIRT;
            case "Verac's flail" ->  BarrowsItem.VERACS_FLAIL;
            case "Torag's helm" ->  BarrowsItem.TORAGS_HELM;
            case "Torag's platebody" ->  BarrowsItem.TORAGS_PLATEBODY;
            case "Torag's platelegs" ->  BarrowsItem.TORAGS_PLATELEGS;
            case "Torag's hammers" ->  BarrowsItem.TORAGS_HAMMERS;
            case "Karil's coif" ->  BarrowsItem.KARILS_COIF;
            case "Karil's top" ->  BarrowsItem.KARILS_TOP;
            case "Karil's skirt" ->  BarrowsItem.KARILS_SKIRT;
            case "Karil's crossbow" ->  BarrowsItem.KARILS_CROSSBOW;
            case "Guthan's helm" ->  BarrowsItem.GUTHANS_HELM;
            case "Guthan's platebody" ->  BarrowsItem.GUTHANS_PLATEBODY;
            case "Guthan's chainskirt" ->  BarrowsItem.GUTHANS_SKIRT;
            case "Guthan's spear" ->  BarrowsItem.GUTHANS_SPEAR;
            default ->  null;
        };
    }
}
