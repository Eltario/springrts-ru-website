package springrtsru.pages

import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label

class AboutPage extends BasePage {
  add(new Label("message", "Hello spring!"))
}
