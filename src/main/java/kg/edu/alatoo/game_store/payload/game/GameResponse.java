package kg.edu.alatoo.game_store.payload.game;

public record GameResponse(
        Long id,
        String title,
        Double price
) {
}
