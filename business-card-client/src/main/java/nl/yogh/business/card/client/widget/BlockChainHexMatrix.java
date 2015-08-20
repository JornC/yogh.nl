package nl.yogh.business.card.client.widget;

import nl.yogh.business.card.client.util.BitcoinBlockchain;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class BlockChainHexMatrix extends Composite {
  interface BlockChainHexMatrixUiBinder extends UiBinder<Widget, BlockChainHexMatrix> {}

  private static final BlockChainHexMatrixUiBinder UI_BINDER = GWT.create(BlockChainHexMatrixUiBinder.class);

  @UiField FlowPanel container;

  public BlockChainHexMatrix() {
    initWidget(UI_BINDER.createAndBindUi(this));

    final Label label = new Label(BitcoinBlockchain.BLOCKCHAIN_HEX);

    label.getElement().getStyle().setProperty("wordBreak", "break-word");
    label.getElement().getStyle().setProperty("textAlign", "center");

    container.add(label);

    // final int capacity = BitcoinBlockchain.BLOCKCHAIN_HEX.length() / 2;
    //
    // int counter = 0;
    // final boolean full = false;
    //
    // while (!full && counter < capacity) {
    // final String hex = BitcoinBlockchain.getHexAt(counter);
    //
    // container.add(new Label(hex));
    //
    // counter++;
    // }
  }
}
