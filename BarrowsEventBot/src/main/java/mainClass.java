import commands.*;
import data.Competition;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class mainClass extends ListenerAdapter {

    public static void main(String[] args){
        String token = "";

        if (args[0] != null){
            token = args[0];
        }

        JDA jda = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .addEventListeners(new mainClass())
                .setActivity(Activity.listening("/commands"))
                .build();

                jda.addEventListener(new commandsManager());
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
            Command c = null;

            switch (event.getName()){
                case "add" -> c = new AddCommand();
                case "brotheroftheday" -> c = new BrotherOfTheDayCommand();
                case "leaderboard" -> c = new LeaderboardCommand();
                case "summary" -> c = new SummaryCommand();
                case "remove" -> c = new RemoveCommand();
                case "clear" -> c = new ClearCommand();
                case "generate-single" -> c = new GenerateFakeData();
                default -> event.reply("Something went wrong. Please try again.").queue();
            }

            if (c != null)
                c.execute(event);
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        switch (event.getComponentId()) {
            case "clearYes" -> {
                Competition.getInstance().resetCompetition();
                event.reply("Competition has been cleared.").setEphemeral(true).queue();
            }
            case "clearCancel" -> {
                event.reply("Clearing competition cancelled.").setEphemeral(true).queue();
            }
        }
    }
}
