/**
 * PlanApp is defined as
 * `<e-plan-app>`
 *
 * Imperatively create application
 * @example
 * let app = new PlanApp();
 *
 * Declaratively create application
 * @example
 * <e-plan-app></e-plan-app>
 *
 * @extends {App}
 */
import { definition } from '@eui/component';
import { App, html } from '@eui/app';
import style from './planApp.css';

@definition('e-plan-app', {
  style,
  props: {
    response: { attribute: false },
  },
})
export default class PlanApp extends App {
  // Uncomment this block to add initialization code
  // constructor() {
  //   super();
  //   // initialize
  // }

  /**
  * Render the <e-plan-app> app. This function is called each time a
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
 * Register the component as e-plan-app.
 * Registration can be done at a later time and with a different name
 * Uncomment the below line to register the App if used outside the container
 */
// PlanApp.register();
