package net.william278.huskclaims.command;

import net.william278.huskclaims.user.CommandUser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface TabCompletable {

    @Nullable
    List<String> suggest(@NotNull CommandUser user, @NotNull String[] args);

    @NotNull
    default List<String> getSuggestions(@NotNull CommandUser user, @NotNull String[] args) {
        List<String> suggestions = suggest(user, args);
        if (suggestions == null) {
            suggestions = List.of();
        }
        return filter(suggestions, args);
    }

    @NotNull
    default List<String> filter(@NotNull List<String> suggestions, @NotNull String[] args) {
        return suggestions.stream()
                .filter(suggestion -> args.length == 0 || suggestion.toLowerCase()
                        .startsWith(args[args.length - 1].toLowerCase().trim()))
                .toList();
    }

}
