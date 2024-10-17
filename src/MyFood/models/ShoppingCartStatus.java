package MyFood.models;

public enum ShoppingCartStatus {
    ABERTO("aberto"),
    PREPARANDO("preparando"),
    FECHADO("fechado"),
    PRONTO("pronto"),
    ENTREGANDO("entregando");

    private final String description;

    ShoppingCartStatus(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
