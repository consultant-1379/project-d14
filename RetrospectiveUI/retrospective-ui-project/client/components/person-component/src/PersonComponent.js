
import { definition } from '@eui/component';
import { LitComponent, html } from '@eui/lit-component';
import style from './personComponent.css';

/**
 * @property {Boolean} propOne - show active/inactive state.
 * @property {String} propTwo - shows the "Hello World" string.
 */
@definition('e-person-component', {
  style,
  home: 'person-component',
  props: {
    name: { attribute: true, type: String, default: 'Person Name' },
    email: { attribute: true, type: String, default: 'forename.surname@ericsson.com' },
    teamID: { attribute: true, type: String, default: 'No Team' }
  },
})
export default class PersonComponent extends LitComponent {
  /**
   * Render the <e-person-component> component. This function is called each time a
   * prop changes.
   */
  render() {
    return html `
    <eui-layout-v0-card slot="example" card-title=${this.name} subtitle=${this.email}>
  <div slot="content">
    ${this.teamID}
  </div>
</eui-layout-v0-card>`;
  }
}
// This is the person component where I can create
/**
 * Register the component as e-person-component.
 * Registration can be done at a later time and with a different name
 */
PersonComponent.register();

