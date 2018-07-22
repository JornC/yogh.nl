package nl.yogh.business.card.client.widget;

import java.util.ArrayList;

import nl.yogh.business.card.client.util.BitcoinBlockchain;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Hex;
import com.googlecode.gwt.crypto.util.Str;

public class BlockChainHexMatrix extends Composite implements ResizeHandler {
  private static final double FLEX_SPACE = 1.15;

  interface BlockChainHexMatrixUiBinder extends UiBinder<Widget, BlockChainHexMatrix> {}

  private static final BlockChainHexMatrixUiBinder UI_BINDER = GWT.create(BlockChainHexMatrixUiBinder.class);

  private static final int PERIOD = 3000;

  /**
   * Hard coded label surface. Hack used to make execution way more efficient.
   */
  private static final double LABEL_SURFACE = 22.25 * 18;

  public static interface CustomStyle extends CssResource {
    String highlight();
  }

  @UiField FlowPanel container;

  @UiField CustomStyle style;

  private final Timer timer;

  private String highlightPhrase;

  private final ArrayList<Label> highlights = new ArrayList<Label>();

  private HandlerRegistration resizeRegistration;

  private final ScheduledCommand layoutCmd = new ScheduledCommand() {
    @Override
    public void execute() {
      layoutScheduled = false;
      forceLayout();
    }
  };

  private boolean layoutScheduled;

  private int counter;

  public BlockChainHexMatrix() {
    initWidget(UI_BINDER.createAndBindUi(this));

    timer = new Timer() {
      @Override
      public void run() {
        doHighlight();
      }
    };

    forceLayout();
  }

  @Override
  public void onResize(final ResizeEvent event) {
    scheduledLayout();
  }

  @Override
  protected void onLoad() {
    resizeRegistration = Window.addResizeHandler(this);
    doHighlight();
    startHighlighter();
  }

  @Override
  protected void onUnload() {
    resizeRegistration.removeHandler();
    stopHighligher();
  }

  private void doHighlight() {
    highlightPhrase = generateRandom(highlightPhrase);

    for (final Widget w : highlights) {
      w.removeStyleName(style.highlight());
    }

    highlights.clear();

    for (final Widget w : container) {
      if (w instanceof Label) {
        final Label label = (Label) w;

        if (highlightPhrase.equals(label.getText())) {
          w.addStyleName(style.highlight());
        }

        highlights.add(label);
      }
    }
  }

  private String generateRandom(final String current) {
    final String random = Str.toString(Hex.encode(new byte[] { (byte) (Math.random() * 255) }));
    return random.equals(current) ? generateRandom(current) : random;
  }

  private void scheduledLayout() {
    if (!layoutScheduled) {
      layoutScheduled = true;
      Scheduler.get().scheduleDeferred(layoutCmd);
    }
  }

  private void forceLayout() {
    final int capacity = BitcoinBlockchain.BLOCKCHAIN_HEX.length() / 2;
    final double windowSurface = Window.getClientHeight() * Window.getClientWidth() * FLEX_SPACE;

    while (LABEL_SURFACE * counter < windowSurface && counter < capacity) {
      container.add(new Label(BitcoinBlockchain.getHexAt(counter)));

      counter++;
    }

    while (LABEL_SURFACE * counter >= windowSurface) {
      container.remove(--counter);
    }
  }

  private void startHighlighter() {
    if (!timer.isRunning()) {
      timer.scheduleRepeating(PERIOD);
    }
  }

  private void stopHighligher() {
    timer.cancel();
  }
}
