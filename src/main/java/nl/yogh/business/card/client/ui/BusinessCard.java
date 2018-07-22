package nl.yogh.business.card.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class BusinessCard extends Composite {
  interface BusinessCardUiBinder extends UiBinder<Widget, BusinessCard> {}

  private static final BusinessCardUiBinder UI_BINDER = GWT.create(BusinessCardUiBinder.class);

  public BusinessCard() {
    initWidget(UI_BINDER.createAndBindUi(this));
  }
}
