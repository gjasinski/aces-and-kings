package pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards;


public class CardResizer {
    private double currentCardSize = 60;
    private double currentVerticalSpacing = 18;
    private static final double enlargementFactor = 1.1;

    public void updateCardSize(final CardView cardView) {
        cardView.getImageView().setFitHeight(currentCardSize);
        cardView.getImageView().setFitHeight(currentCardSize);
    }

    public void enlarge() {
        currentCardSize *= enlargementFactor;
        currentVerticalSpacing *= enlargementFactor;
    }

    public void shrink() {
        currentCardSize /= enlargementFactor;
        currentVerticalSpacing /= enlargementFactor;
    }

    public double getCurrentVerticalSpacing() {
        return currentVerticalSpacing;
    }
}
