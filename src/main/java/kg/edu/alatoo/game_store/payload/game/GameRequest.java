package kg.edu.alatoo.game_store.payload.game;

public record GameRequest(
        String title,
        Double price,
        Integer discount
) {
}
