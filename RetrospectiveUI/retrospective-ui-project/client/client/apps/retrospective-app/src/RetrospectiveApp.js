/**
 * RetrospectiveApp is defined as
 * `<e-retrospective-app>`
 *
 * Imperatively create application
 * @example
 * let app = new RetrospectiveApp();
 *
 * Declaratively create application
 * @example
 * <e-retrospective-app></e-retrospective-app>
 *
 * @extends {App}
 */
import { definition } from '@eui/component';
import { App, html } from '@eui/app';
import style from './retrospectiveApp.css';

@definition('e-retrospective-app', {
  style,
  props: {
    response: { attribute: false },
  },
})
export default class RetrospectiveApp extends App {
  // Uncomment this block to add initialization code
  // constructor() {
  //   super();
  //   // initialize
  // }

  /**
  * Render the <e-retrospective-app> app. This function is called each time a
  * prop changes.
  */
  render() {
    const { EUI } = window;
    return html`
    <h1>${(EUI && EUI.Localizer.loc.APP_DEFAULT_TEXT) ||
      'Your app markup goes here'}</h1>`;
  }
}

/**
 * Register the component as e-retrospective-app.
 * Registration can be done at a later time and with a different name
 * Uncomment the below line to register the App if used outside the container
 */
// RetrospectiveApp.register();
