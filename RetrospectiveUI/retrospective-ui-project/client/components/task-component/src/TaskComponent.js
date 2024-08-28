/**
 * Component TaskComponent is defined as
 * `<e-task-component>`
 *
 * Imperatively create component
 * @example
 * let component = new TaskComponent();
 *
 * Declaratively create component
 * @example
 * <e-task-component></e-task-component>
 *
 * @extends {LitComponent}
 */
import { definition } from '@eui/component';
import { LitComponent, html } from '@eui/lit-component';
import style from './taskComponent.css';

/**
 * @property {Boolean} propOne - show active/inactive state.
 * @property {String} propTwo - shows the "Hello World" string.
 */
@definition('e-task-component', {
  style,
  home: 'task-component',
  props: {
    description: { attribute: true, type: String, default: '' }
  },
})
export default class TaskComponent extends LitComponent {
  /**
   * Render the <e-task-component> component. This function is called each time a
   * prop changes.
   */
  render() {
    return html`
    <eui-layout-v0-card card-title=${this.description} drag>
    <eui-base-v0-tooltip slot="action" message="Category" position="left">
      <eui-base-v0-pill><eui-v0-icon name="severity-major"></eui-v0-icon></eui-base-v0-pill>
    </eui-base-v0-tooltip>
  </eui-layout-v0-card>`;
  }
}

/**
 * Register the component as e-task-component.
 * Registration can be done at a later time and with a different name
 */
TaskComponent.register();
