import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class commandsManager extends ListenerAdapter{
    @Override
    public void onGuildReady(GuildReadyEvent event) {
        List<SlashCommandData> commands = new ArrayList<>();

        OptionData barrowsItems = new OptionData(OptionType.STRING, "item", "The barrows item the person received.", true)
                .addChoice("Ahrim's hood", "Ahrim's hood").addChoice("Ahrim's top", "Ahrim's top").addChoice("Ahrim's bottoms", "Ahrim's bottoms"). addChoice("Ahrim's staff", "Ahrim's staff")
                .addChoice("Dharok's helm", "Dharok's helm"). addChoice("Dharok's platebody", "Dharok's platebody").addChoice("Dharok's platelegs", "Dharok's platelegs").addChoice("Dharok's greataxe", "Dharok's greataxe")
                .addChoice("Verac's helm", "Verac's helm").addChoice("Verac's brassard", "Verac's brassard").addChoice("Verac's plateskirt", "Verac's plateskirt").addChoice("Verac's flail", "Verac's flail")
                .addChoice("Torag's helm",  "Torag's helm").addChoice("Torag's platebody", "Torag's platebody").addChoice("Torag's platelegs", "Torag's platelegs").addChoice("Torag's hammers", "Torag's hammers")
                .addChoice("Karil's coif", "Karil's coif").addChoice("Karil's top", "Karil's top").addChoice("Karil's skirt", "Karil's skirt").addChoice("Karil's crossbow", "Karil's crossbow")
                .addChoice("Guthan's helm", "Guthan's helm").addChoice("Guthan's platebody", "Guthan's platebody").addChoice("Guthan's chainskirt", "Guthan's chainskirt").addChoice("Guthan's spear", "Guthan's spear");

        OptionData brothers = new OptionData(OptionType.STRING, "brother", "The Barrows Brother of which the items must be received.", true)
                .addChoice("Ahrim", "Ahrim") . addChoice("Dharok", "Dharok").addChoice("Verac", "Verac")
                .addChoice("Torag", "Torag").addChoice("Karil", "Karil").addChoice("Guthan", "Guthan")
                .addChoice("None", "None");

        commands.add(Commands.slash("add", "Adds a new entry into this Barrows competition.")
            .addOption(OptionType.USER, "name", "The person that got the item. Use the @ discord tag.", true)
            .addOptions(barrowsItems));
        commands.add(Commands.slash("brotheroftheday", "Set the brother of the day. Select \"None\" to clear.")
            .addOptions(brothers));
        commands.add(Commands.slash("leaderboard", "Shows the top 10 of the current running event."));
        commands.add(Commands.slash("summary", "Print the current state of the event without finishing it."));
        commands.add(Commands.slash("remove", "Removes 1 entry from the competition. Only use in case of accidental duplication.")
            .addOption(OptionType.INTEGER, "index", "The number in front of the entry in the summary. Must be a number.", true));
        commands.add(Commands.slash("generate-single", "Creates multple fake entries for the username given")
            .addOption(OptionType.USER, "name", "The name being used", true)
            .addOption(OptionType.INTEGER, "amount", "amount that is being created", true)
            .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)));
        commands.add(Commands.slash("clear", "Clear the current competition data."));
        
        event.getGuild().updateCommands().addCommands(commands).queue();;
    }
}
