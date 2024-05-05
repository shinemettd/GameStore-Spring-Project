package kg.edu.alatoo.game_store.payload.user;

public record UserBalanceResponse(
        String id,
        String username,
        Double balance
) {
}
