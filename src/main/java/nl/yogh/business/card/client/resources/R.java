package nl.yogh.business.card.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * Global resource class, access resources via R.css(). or R.images().
 */
public final class R {
  private static final ApplicationResource RESOURCES = GWT.create(ApplicationResource.class);

  /**
   * Application CssResources.
   */
  public interface ApplicationCssResource extends CssResource {
    String flex();

    String alignCenter();

    String justifyCenter();

    String spaceAround();

    String wrap();

    String resize();

    String grow();

    String shrink();

    String noShrink();

    String columns();
  }

  /**
   * Ensures css is injected. Should be called as soon as possible on startup.
   */
  public static void init() {
    RESOURCES.css().ensureInjected();
  }

  public interface ApplicationResource extends ClientBundle {
    @Source("strict.css")
    ApplicationCssResource css();
  }

  /**
   * Access to css resources.
   */
  public static ApplicationCssResource css() {
    return RESOURCES.css();
  }
}