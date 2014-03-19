import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;


/**
 * CardListener.java
 *
 * @author  Jake
 * @version Mar 18, 2014
 */
public class CardListener extends MouseInputAdapter {
  
  private GamePanel panel;
  
  private boolean cardPressed;
  private Deck deck;
  
  private Pile[] mainPiles, suitPiles;
  
  private int lastX, lastY;
  private int origX, origY;
  
  /**
   * Constructor for a Card Listener
   * @param panel the game panel in which to manipulate when the user clicks/drags/drops cards
   */
  public CardListener(GamePanel panel) {
    this.panel   = panel;
    cardPressed  = false;
    deck = panel.getDeck();
    mainPiles = panel.getMainPiles();
    suitPiles = panel.getSuitPiles();
    lastX = 0;
    lastY = 0;
    origX = 0;
    origY = 0;
  }
  
  @Override
  public void mouseClicked(MouseEvent e) {
    
  }
  
  @Override
  /**
   * Selects a card when it is clicked
   */
  public void mousePressed(MouseEvent e) {
    panel.selectedPile = getPileClicked(e);
    if (panel.selectedPile != null) {
      cardPressed = true;
      lastX = e.getX();
      lastY = e.getY();
      origX = panel.selectedPile.getX();
      origY = panel.selectedPile.getY();
    } else {
      return;
    }
    panel.repaint();
  }
  
  @Override
  /**
   * Drops a card on a pile only if it has the right face and color
   */
  public void mouseReleased(MouseEvent e) {
    
  }
  
  @Override
  public void mouseMoved(MouseEvent e) {
    
  }
  
  @Override
  /**
   * Moves the card as it is dragged by the mouse
   */
  public void mouseDragged(MouseEvent e) {
    if (cardPressed && panel.selectedPile != null) {
      int newX = panel.selectedPile.getX() + (e.getX() - lastX);
      int newY = panel.selectedPile.getY() + (e.getY() - lastY);
      panel.selectedPile.setLocation(newX, newY);
      lastX = e.getX();
      lastY = e.getY();
    }
    panel.repaint();
  }
  
  /**
   * Returns the card that was clicked or null if no card was clicked
   * @param e the mouse event to check
   * @return the card that was clicked or null if no card was clicked
   */
  private Pile getPileClicked(MouseEvent e) {
    Pile clicked = new Pile();
    // check the main piles and then the suit piles
    // TODO: move this to it's own method that gets called if this one returns a pile of size 0
    //clicked = deck.cardHasBeenClicked(e);
    for (int i = 0; i < mainPiles.length; i++) {
      if ((clicked = mainPiles[i].pileHasBeenClicked(e)).size() > 0)
        return clicked;
    }
    
    for (int i = 0; i < suitPiles.length; i++) {
      if ((clicked = suitPiles[i].pileHasBeenClicked(e)).size() > 0)
        break;
    }
    
    return clicked;
  }
  
}